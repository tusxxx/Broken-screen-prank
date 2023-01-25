package prank.broken.screen.realistic.crack.cracking.sounds.wallpaper.realistic.cracked.ui.screen

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import prank.broken.screen.realistic.crack.cracking.sounds.wallpaper.realistic.cracked.R
import prank.broken.screen.realistic.crack.cracking.sounds.wallpaper.realistic.cracked.ui.OverlayingService

class BrokenScreenSelectorFragment : Fragment(R.layout.fragment_broken_screen_selector) {
    private val screenAdapter: ScreenAdapter = ScreenAdapter {
        requireActivity().startService(Intent(context, OverlayingService::class.java).apply {
            putExtra("screenResId", it)
            val mp = MediaPlayer.create(requireContext(), R.raw.glass_breaking)
            mp.start()
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().findViewById<RecyclerView>(R.id.rvScreens).apply {
            adapter = screenAdapter
            layoutManager =
                GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
        }
        screenAdapter.submitList(
            listOf(
                Screen(R.drawable.transparent1),
                Screen(R.drawable.transparent2),
                Screen(R.drawable.transparent3),
                Screen(R.drawable.transparent4),
                Screen(R.drawable.transparent5),
                Screen(R.drawable.transparent6),
                Screen(R.drawable.transparent7),
                Screen(R.drawable.transparent8),
                Screen(R.drawable.transparent9),
                Screen(R.drawable.transparent10),
                Screen(R.drawable.br1),
                Screen(R.drawable.br2),
                Screen(R.drawable.br3),
                Screen(R.drawable.br4),
                Screen(R.drawable.br5),
                Screen(R.drawable.br6),
                Screen(R.drawable.br7),
                Screen(R.drawable.br8),
                Screen(R.drawable.br9),
                Screen(R.drawable.br10),
                Screen(R.drawable.br11),
                Screen(R.drawable.br12),
                Screen(R.drawable.br13),
                Screen(R.drawable.br14),
                Screen(R.drawable.br15),
                Screen(R.drawable.br16),
                Screen(R.drawable.br17),
                Screen(R.drawable.br18),
                Screen(R.drawable.br19),
                Screen(R.drawable.br20),
                Screen(R.drawable.br21),
                Screen(R.drawable.br22),
                Screen(R.drawable.br23),
            )
        )
        requireActivity().findViewById<ImageButton>(R.id.btBackToGameSelector).setOnClickListener {
            findNavController().popBackStack()
        }
    }
}