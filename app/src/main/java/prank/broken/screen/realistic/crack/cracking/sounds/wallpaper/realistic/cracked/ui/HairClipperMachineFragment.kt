package prank.broken.screen.realistic.crack.cracking.sounds.wallpaper.realistic.cracked.ui

import android.content.Context.VIBRATOR_SERVICE
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import prank.broken.screen.realistic.crack.cracking.sounds.wallpaper.realistic.cracked.R

class HairClipperMachineFragment : Fragment(R.layout.fragment_hair_clipper_machine) {
    private val VIBRATION_PATTERN = longArrayOf(0, 1, 2)
    var isWorking = false
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mp = MediaPlayer.create(requireContext(), R.raw.clipper)
        mp.isLooping = true
        requireActivity().findViewById<Button>(R.id.btToggle).setOnClickListener {
            if (isWorking) {
                mp.pause()
                cancelVibration()
                requireActivity().findViewById<ImageView>(R.id.ivClipper)
                    .setImageResource(R.drawable.clipper_3)
            } else {
                requireActivity().findViewById<ImageView>(R.id.ivClipper)
                    .setImageResource(R.drawable.clipper_4)
                mp.start()
                vibratePhone()
            }
            isWorking = !isWorking
        }

        requireActivity().findViewById<AdView>(R.id.adViewClipperMachine)
            .loadAd(AdRequest.Builder().build())
    }

    private fun vibratePhone() {
        val vibrator = context?.getSystemService(VIBRATOR_SERVICE) as Vibrator
        if (Build.VERSION.SDK_INT >= 26) {
            vibrator.vibrate(VibrationEffect.createWaveform(VIBRATION_PATTERN, /* repeat = */ 0))
        } else {
            vibrator.vibrate(VIBRATION_PATTERN, /* repeat = */ 0)
        }
    }

    private fun cancelVibration() {
        val vibrator = context?.getSystemService(VIBRATOR_SERVICE) as Vibrator
        vibrator.cancel()
    }
}