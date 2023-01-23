package prank.broken.screen.realistic.crack.cracking.sounds.wallpaper.realistic.cracked.ui

import android.R
import androidx.navigation.navOptions

fun createNavOptions() = navOptions {
    anim {
        enter = R.anim.slide_in_left
        exit = R.anim.slide_out_right
    }
}