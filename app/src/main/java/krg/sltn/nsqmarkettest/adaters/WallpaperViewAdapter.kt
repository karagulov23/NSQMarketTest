package krg.sltn.nsqmarkettest.adaters

import android.app.WallpaperManager
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import krg.sltn.nsqmarkettest.databinding.ItemWallpaperBinding
import krg.sltn.nsqmarkettest.models.Wallpaper
import java.io.IOException

class WallpaperViewAdapter(private val list: ArrayList<Wallpaper>) :
    RecyclerView.Adapter<WallpaperViewAdapter.ViewHolder>() {
    inner class ViewHolder(private val binding: ItemWallpaperBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(wallpaper: Wallpaper) {
            binding.tvTitle.text = wallpaper.title
            Glide.with(itemView).load(wallpaper.imageUrl).into(binding.imageWallpaper)

            itemView.setOnClickListener {
                Glide.with(itemView).asBitmap().load(wallpaper.imageUrl)
                    .into(object : SimpleTarget<Bitmap?>() {
                        override fun onResourceReady(
                            resource: Bitmap,
                            transition: Transition<in Bitmap?>?
                        ) {
                            try {
                                WallpaperManager.getInstance(itemView.context).setBitmap(resource)
                                Toast.makeText(it.context, "Обои установлены!", Toast.LENGTH_SHORT).show()
                            } catch (e: IOException) {
                                e.printStackTrace()
                            }
                        }
                    })
            }


        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = ItemWallpaperBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }


}