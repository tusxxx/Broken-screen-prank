package prank.broken.screen.realistic.crack.cracking.sounds.wallpaper.realistic.cracked.ui

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import prank.broken.screen.realistic.crack.cracking.sounds.wallpaper.realistic.cracked.App
import prank.broken.screen.realistic.crack.cracking.sounds.wallpaper.realistic.cracked.R

class MenuFragment : Fragment(R.layout.fragment_menu) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (!Settings.canDrawOverlays(requireContext())) {
            val dialog = AlertDialogFragment()
            dialog.show(parentFragmentManager, null)
        }

        requireActivity().findViewById<ImageButton>(R.id.btBegin).setOnClickListener {
            val intAd = AdRequest.Builder().build()
            App.showInterstitialAd(requireActivity())
            findNavController().navigate(
                R.id.action_menuFragment_to_gameSelectorFragment
            )
        }

        requireActivity().findViewById<AdView>(R.id.adViewMenu1)
            .loadAd(AdRequest.Builder().build())
    }
}