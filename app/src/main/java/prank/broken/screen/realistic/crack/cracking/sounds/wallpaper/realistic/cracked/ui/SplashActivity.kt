package prank.broken.screen.realistic.crack.cracking.sounds.wallpaper.realistic.cracked.ui

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import prank.broken.screen.realistic.crack.cracking.sounds.wallpaper.realistic.cracked.App
import prank.broken.screen.realistic.crack.cracking.sounds.wallpaper.realistic.cracked.R

private const val COUNTER_TIME = 5L

private const val LOG_TAG = "SplashActivity"

/** Splash Activity that inflates splash activity xml. */
class SplashActivity : AppCompatActivity() {

    private var secondsRemaining: Long = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Create a timer so the SplashActivity will be displayed for a fixed amount of time.
        createTimer(COUNTER_TIME)
        (application as App).showAdIfAvailable(this, object : App.OnShowAdCompleteListener {
            override fun onShowAdComplete() {}
        })
    }

    private fun createTimer(seconds: Long) {
        val counterTextView: TextView = findViewById(R.id.timer)
        val countDownTimer: CountDownTimer = object : CountDownTimer(seconds * 1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                secondsRemaining = millisUntilFinished / 1000 + 1
                counterTextView.text = "App is done loading in: $secondsRemaining"
            }

            override fun onFinish() {
                secondsRemaining = 0
                counterTextView.text = "Done."

                val application = application as? App

                // If the application is not an instance of MyApplication, log an error message and
                // start the MainActivity without showing the app open ad.
                if (application == null) {
                    Log.e(LOG_TAG, "Failed to cast application to MyApplication.")
                    startMainActivity()
                    return
                }

                // Show the app open ad.
                application.showAdIfAvailable(
                    this@SplashActivity,
                    object : App.OnShowAdCompleteListener {
                        override fun onShowAdComplete() {
                            startMainActivity()
                        }
                    })
            }
        }
        countDownTimer.start()
    }

    /** Start the MainActivity. */
    fun startMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}