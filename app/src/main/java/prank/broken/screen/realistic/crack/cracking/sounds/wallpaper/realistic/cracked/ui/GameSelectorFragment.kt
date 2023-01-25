package prank.broken.screen.realistic.crack.cracking.sounds.wallpaper.realistic.cracked.ui

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import prank.broken.screen.realistic.crack.cracking.sounds.wallpaper.realistic.cracked.App
import prank.broken.screen.realistic.crack.cracking.sounds.wallpaper.realistic.cracked.R

class GameSelectorFragment : Fragment(R.layout.fragment_game_selector) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(requireActivity()) {
            findViewById<ImageButton>(R.id.btBreakedScreen).setOnClickListener {
                App.showInterstitialAd(requireActivity())
                findNavController().navigate(
                    R.id.action_gameSelectorFragment_to_brokenScreenSelectorFragment
                )
            }
            findViewById<ImageButton>(R.id.btSnake).setOnClickListener {
                App.showInterstitialAd(requireActivity())

                requireActivity().startService(
                    Intent(
                        context,
                        OverlayingService::class.java
                    ).apply {
                        putExtra("screenResId", R.drawable.giphy)
                    })
            }
            findViewById<ImageButton>(R.id.btHairClipper).setOnClickListener {
                App.showInterstitialAd(requireActivity())

                findNavController().navigate(
                    R.id.action_gameSelectorFragment_to_hairClipperFragment
                )
            }
            findViewById<ImageButton>(R.id.btBack).setOnClickListener {
                App.showInterstitialAd(requireActivity())

                findNavController().popBackStack()
            }
            findViewById<AdView>(R.id.adViewSelector)
                .loadAd(AdRequest.Builder().build())
        }
    }
}