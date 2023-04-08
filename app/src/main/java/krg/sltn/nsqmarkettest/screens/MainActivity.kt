package krg.sltn.nsqmarkettest.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import krg.sltn.nsqmarkettest.databinding.ActivityMainBinding

import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import krg.sltn.nsqmarkettest.MainViewModel
import krg.sltn.nsqmarkettest.MainViewModelFactory
import krg.sltn.nsqmarkettest.Status

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels { MainViewModelFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.statusLiveData.observe(this) { status ->
            when(status){
                Status.Loading -> {
                    binding.pbLoad.visibility = View.VISIBLE
                    binding.tvLoading.visibility = View.VISIBLE
                }

                Status.One -> {
                        binding.pbLoad.visibility = View.GONE
                        binding.tvLoading.visibility = View.GONE
                        Toast.makeText(this, "Сервер ответил: $status", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, WallpaperActivity::class.java)
                        startActivity(intent)
                }
                Status.Two -> {
                        binding.pbLoad.visibility = View.GONE
                        binding.tvLoading.visibility = View.GONE
                        Toast.makeText(this, "Сервер ответил: $status", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, WebViewActivity::class.java)
                        startActivity(intent)
                }
                Status.Unknown -> {
                        binding.pbLoad.visibility = View.GONE
                        binding.tvLoading.visibility = View.GONE
                        Toast.makeText(this, "Unknown $status", Toast.LENGTH_SHORT).show()
                }
            }
        }


    }
}