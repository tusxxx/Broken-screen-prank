package prank.broken.screen.realistic.crack.cracking.sounds.wallpaper.realistic.cracked.ui

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import prank.broken.screen.realistic.crack.cracking.sounds.wallpaper.realistic.cracked.R

class MenuFragment : Fragment(R.layout.fragment_menu) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().findViewById<Button>(R.id.btBegin).setOnClickListener {
            findNavController().navigate(
                R.id.action_menuFragment_to_gameSelectorFragment
            )
        }
    }
}