package prank.broken.screen.realistic.crack.cracking.sounds.wallpaper.realistic.cracked.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import prank.broken.screen.realistic.crack.cracking.sounds.wallpaper.realistic.cracked.App
import prank.broken.screen.realistic.crack.cracking.sounds.wallpaper.realistic.cracked.R

class HairClipperFragment : Fragment(R.layout.fragment_hair_clipper) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().findViewById<ImageButton>(R.id.ibStartHair).setOnClickListener {
            App.showInterstitialAd(requireActivity())

            findNavController().navigate(R.id.action_hairClipperFragment_to_hairClipperMachineFragment)
        }

        requireActivity().findViewById<AdView>(R.id.adViewClipper)
            .loadAd(AdRequest.Builder().build())
    }
}