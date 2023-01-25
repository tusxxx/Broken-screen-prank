package prank.broken.screen.realistic.crack.cracking.sounds.wallpaper.realistic.cracked.ui

import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import prank.broken.screen.realistic.crack.cracking.sounds.wallpaper.realistic.cracked.App
import prank.broken.screen.realistic.crack.cracking.sounds.wallpaper.realistic.cracked.R

class HairClipperMachineFragment : Fragment(R.layout.fragment_hair_clipper_machine) {
    var isWorking = false
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mp = MediaPlayer.create(requireContext(), R.raw.clipper)
        mp.isLooping = true
        requireActivity().findViewById<Button>(R.id.btToggle).setOnClickListener {
            if (isWorking) {
                mp.pause()
                requireActivity().findViewById<ImageView>(R.id.ivClipper)
                    .setImageResource(R.drawable.clipper_3)
            } else {
                requireActivity().findViewById<ImageView>(R.id.ivClipper)
                    .setImageResource(R.drawable.clipper_4)
                mp.start()
            }
            isWorking = !isWorking
        }

        requireActivity().findViewById<AdView>(R.id.adViewClipperMachine).loadAd(AdRequest.Builder().build())

        requireActivity().findViewById<ImageButton>(R.id.btBackfromClipperMachine).setOnClickListener {
            findNavController().popBackStack()
            App.showInterstitialAd(requireActivity())
        }
    }
}