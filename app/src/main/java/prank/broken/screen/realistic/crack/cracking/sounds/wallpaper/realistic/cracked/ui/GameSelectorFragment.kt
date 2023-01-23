package prank.broken.screen.realistic.crack.cracking.sounds.wallpaper.realistic.cracked.ui

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import prank.broken.screen.realistic.crack.cracking.sounds.wallpaper.realistic.cracked.R

class GameSelectorFragment : Fragment(R.layout.fragment_game_selector) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(requireActivity()) {
            findViewById<Button>(R.id.btBreakedScreen).setOnClickListener {
                findNavController().navigate(
                    R.id.action_gameSelectorFragment_to_brokenScreenSelectorFragment
                )
            }
            findViewById<Button>(R.id.btSnake).setOnClickListener {
                findNavController().navigate(
                    R.id.action_gameSelectorFragment_to_snakeFragment
                )
            }
            findViewById<Button>(R.id.btHairClipper).setOnClickListener {
                findNavController().navigate(
                    R.id.action_gameSelectorFragment_to_hairClipperFragment
                )
            }
            findViewById<ImageButton>(R.id.btBack).setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }
}