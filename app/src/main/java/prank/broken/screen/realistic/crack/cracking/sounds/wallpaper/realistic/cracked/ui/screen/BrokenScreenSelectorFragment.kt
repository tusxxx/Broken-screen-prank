package prank.broken.screen.realistic.crack.cracking.sounds.wallpaper.realistic.cracked.ui.screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import prank.broken.screen.realistic.crack.cracking.sounds.wallpaper.realistic.cracked.R

class BrokenScreenSelectorFragment : Fragment(R.layout.fragment_broken_screen_selector) {
    private val screenAdapter: ScreenAdapter = ScreenAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().findViewById<RecyclerView>(R.id.rvScreens).apply {
            adapter = screenAdapter
            layoutManager =
                GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
        }
        screenAdapter.submitList(
            listOf(
                Screen(R.drawable.ic_launcher_background),
                Screen(R.drawable.ic_launcher_background),
                Screen(R.drawable.ic_launcher_background),
                Screen(R.drawable.ic_launcher_foreground),
                Screen(R.drawable.ic_launcher_foreground),
                Screen(R.drawable.ic_launcher_foreground),
                Screen(R.drawable.ic_launcher_background),
                Screen(R.drawable.ic_launcher_background),
                Screen(R.drawable.ic_launcher_foreground),
            )
        )
    }
}