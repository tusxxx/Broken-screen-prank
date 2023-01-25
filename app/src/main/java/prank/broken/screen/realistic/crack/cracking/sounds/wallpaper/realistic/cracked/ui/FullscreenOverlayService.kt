package prank.broken.screen.realistic.crack.cracking.sounds.wallpaper.realistic.cracked.ui

import android.Manifest
import android.annotation.TargetApi
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PixelFormat
import android.graphics.Point
import android.os.Build
import android.os.IBinder
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.bumptech.glide.Glide
import prank.broken.screen.realistic.crack.cracking.sounds.wallpaper.realistic.cracked.R

class FullscreenOverlayService : Service() {
    private lateinit var floatingFaceBubble: ImageView

    private val overlay by lazy {
        View.inflate(applicationContext, R.layout.overlay_layout, null)
    }

    private val windowManager by lazy {
        applicationContext.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    }
    private lateinit var notificationManagerCompat: NotificationManager

    private val broadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            windowManager.removeView(floatingFaceBubble)
            notificationManagerCompat.cancel(1)
            unregisterReceiver(this)
            stopSelf()
        }
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        val drawableRes = intent?.extras?.get("screenResId") as Int

        val layoutParams = WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE or
                        WindowManager.LayoutParams.FLAG_FULLSCREEN or
                        WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN or
                        WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS or
                        WindowManager.LayoutParams.FLAG_LAYOUT_INSET_DECOR or
                        WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
                PixelFormat.TRANSLUCENT
        ).also {
            it.gravity = Gravity.START or Gravity.TOP
        }

        floatingFaceBubble = ImageView(applicationContext)
        Glide
            .with(this)
            .load(AppCompatResources.getDrawable(this, drawableRes))
            .into(overlay.findViewById(R.id.ivForeground))
        floatingFaceBubble.scaleType = ImageView.ScaleType.FIT_XY

        windowManager.addView(overlay, layoutParams)
        createNotification()
        hideApp()

        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        windowManager.removeView(overlay)
    }

    private fun createNotification() {
        createNotificationChannel()

        registerReceiver(broadcastReceiver, IntentFilter("bruh"))

        val pIntent = PendingIntent.getBroadcast(
            this,
            0,
            Intent("bruh"),
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val builder = NotificationCompat.Builder(this, "1")
            .setSmallIcon(R.mipmap.ic_app)
            .setContentTitle("Screen Prank")
            .setContentText("Push! (if you want fix screen)")
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText("Push! (if you want fix screen)")
            )
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pIntent)

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        NotificationManagerCompat.from(this).notify(1, builder.build())
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "someName"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel("1", name, importance)
            // Register the channel with the system
            notificationManagerCompat =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManagerCompat.createNotificationChannel(channel)
        }
    }

    private fun hideApp() {
        val startMain = Intent(Intent.ACTION_MAIN)
        startMain.addCategory(Intent.CATEGORY_HOME)
        startMain.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(startMain)
    }
}

class FullscreenOverlayLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val screenSize = Point().also { windowManager.defaultDisplay.getRealSize(it) }

        super.onMeasure(
                MeasureSpec.makeMeasureSpec(screenSize.x, MeasureSpec.getMode(widthMeasureSpec)),
                MeasureSpec.makeMeasureSpec(screenSize.y, MeasureSpec.getMode(heightMeasureSpec))
        )
    }
}

class OverlayDrawingView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val paint by lazy {
        Paint().apply {
            color = Color.LTGRAY
            style = Paint.Style.STROKE
            strokeWidth = 8f
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        if (canvas != null && canvas.width > 0) {
            val W = canvas.width
            val H = canvas.height
            val interval = W / STEP

            // 斜めパターンの描画
            generateSequence(0f) { it + interval }
                    .take(STEP + H / interval + 1)
                    .forEach { canvas.drawLine(it, 0f, 0f, it, paint) }

            canvas.drawRect(0f, 0f, W.toFloat(), H.toFloat(), paint)
        }
    }

    companion object {
        private const val STEP = 9
    }
}