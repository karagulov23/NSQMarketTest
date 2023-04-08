package krg.sltn.nsqmarkettest.screens

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import krg.sltn.nsqmarkettest.adaters.WallpaperViewAdapter
import krg.sltn.nsqmarkettest.databinding.ActivityWallpaperBinding
import krg.sltn.nsqmarkettest.models.Wallpaper

class WallpaperActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWallpaperBinding
    private var wallpapers = ArrayList<Wallpaper>()
    private val adapter = WallpaperViewAdapter(wallpapers)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWallpaperBinding.inflate(layoutInflater)
        setContentView(binding.root)

        wallpapers.add(
            Wallpaper(
                "Mountain",
                "https://images.pexels.com/photos/1366919/pexels-photo-1366919.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260"
            )
        )
        wallpapers.add(
            Wallpaper(
                "Ocean",
                "https://images.pexels.com/photos/3894157/pexels-photo-3894157.jpeg"
            )
        )
        wallpapers.add(
            Wallpaper(
                "Flowers",
                "https://images.pexels.com/photos/8328556/pexels-photo-8328556.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
            )
        )
        wallpapers.add(
            Wallpaper(
                "Clouds",
                "https://images.pexels.com/photos/10821345/pexels-photo-10821345.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
            )
        )

        binding.rvWallpaper.layoutManager = LinearLayoutManager(this)
        binding.rvWallpaper.adapter = adapter

    }




}