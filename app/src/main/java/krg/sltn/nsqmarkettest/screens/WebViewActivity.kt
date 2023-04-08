package krg.sltn.nsqmarkettest.screens

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import krg.sltn.nsqmarkettest.databinding.ActivityWebBinding


class WebViewActivity: AppCompatActivity() {
    private lateinit var binding: ActivityWebBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityWebBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val webView = WebView(this)
        webView.settings.javaScriptEnabled = true
        webView.webViewClient = WebViewClient()
        binding.webviewC.addView(webView)
        webView.loadUrl("https://html5test.com/")

    }
}