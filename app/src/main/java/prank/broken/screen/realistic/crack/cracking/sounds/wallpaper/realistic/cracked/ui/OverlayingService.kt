package prank.broken.screen.realistic.crack.cracking.sounds.wallpaper.realistic.cracked.ui

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.graphics.PixelFormat
import android.os.Build
import android.os.IBinder
import android.view.Gravity
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.WindowManager
import android.widget.ImageView
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.bumptech.glide.Glide
import prank.broken.screen.realistic.crack.cracking.sounds.wallpaper.realistic.cracked.R


class OverlayingService : Service() {
    private lateinit var windowManager: WindowManager
    private lateinit var floatingFaceBubble: ImageView
    private lateinit var notificationManagerCompat: NotificationManager

    private val broadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            windowManager.removeView(floatingFaceBubble)
            notificationManagerCompat.cancel(1)
            unregisterReceiver(this)
            stopSelf()
        }
    }

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val drawableRes = intent?.extras?.get("screenResId") as Int

        floatingFaceBubble = ImageView(this)
        Glide
            .with(this)
            .load(AppCompatResources.getDrawable(this, drawableRes))
            .into(floatingFaceBubble)
        floatingFaceBubble.scaleType = ImageView.ScaleType.FIT_XY
        windowManager = getSystemService(WINDOW_SERVICE) as WindowManager
        val myParams = WindowManager.LayoutParams(
            MATCH_PARENT,
            MATCH_PARENT,
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE or
                    WindowManager.LayoutParams.FLAG_FULLSCREEN or
                    WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN or
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS or
                    WindowManager.LayoutParams.FLAG_LAYOUT_INSET_DECOR,
            PixelFormat.TRANSLUCENT
        )

        myParams.gravity = Gravity.TOP or Gravity.START
        windowManager.addView(floatingFaceBubble, myParams)

        createNotification()
        hideApp()

        return START_STICKY
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
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
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