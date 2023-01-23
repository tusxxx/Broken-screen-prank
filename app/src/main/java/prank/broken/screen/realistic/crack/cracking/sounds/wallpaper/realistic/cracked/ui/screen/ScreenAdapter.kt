package prank.broken.screen.realistic.crack.cracking.sounds.wallpaper.realistic.cracked.ui.screen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import prank.broken.screen.realistic.crack.cracking.sounds.wallpaper.realistic.cracked.R

class ScreenAdapter : ListAdapter<Screen, ScreenViewHolder>(ScreenDiffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScreenViewHolder {
        return ScreenViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.screen_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ScreenViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}

class ScreenViewHolder(val itemView: View) : ViewHolder(itemView) {
    fun bind(item: Screen) {
        Glide
            .with(itemView)
            .load(item.drawableRes)
            .into(itemView.findViewById(R.id.ivRootView))
    }
}

object ScreenDiffUtil : DiffUtil.ItemCallback<Screen>() {
    override fun areItemsTheSame(oldItem: Screen, newItem: Screen): Boolean = newItem == oldItem

    override fun areContentsTheSame(oldItem: Screen, newItem: Screen): Boolean = newItem == oldItem
}