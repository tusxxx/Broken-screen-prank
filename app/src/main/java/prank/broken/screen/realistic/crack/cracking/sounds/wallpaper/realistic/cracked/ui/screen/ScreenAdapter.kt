package prank.broken.screen.realistic.crack.cracking.sounds.wallpaper.realistic.cracked.ui.screen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import prank.broken.screen.realistic.crack.cracking.sounds.wallpaper.realistic.cracked.R

class ScreenAdapter(private val onScreenClick: (Int) -> Unit) :
    ListAdapter<Screen, ScreenViewHolder>(ScreenDiffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScreenViewHolder {
        return ScreenViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.screen_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ScreenViewHolder, position: Int) {
        holder.bind(getItem(position), onScreenClick)
    }

}

class ScreenViewHolder(private val itemView: View) : ViewHolder(itemView) {
    fun bind(item: Screen, onScreenClick: (Int) -> Unit) {
        Glide
            .with(itemView)
            .load(item.drawableRes)
            .into(itemView.findViewById(R.id.ivRootView))

        itemView.setOnClickListener {
            onScreenClick(item.drawableRes)
        }
    }
}

object ScreenDiffUtil : DiffUtil.ItemCallback<Screen>() {
    override fun areItemsTheSame(oldItem: Screen, newItem: Screen): Boolean = newItem == oldItem

    override fun areContentsTheSame(oldItem: Screen, newItem: Screen): Boolean = newItem == oldItem
}