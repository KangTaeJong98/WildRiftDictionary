package com.taetae98.wildriftdictionary.fragment

import android.annotation.SuppressLint
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import androidx.navigation.fragment.navArgs
import com.taetae98.wildriftdictionary.R
import com.taetae98.wildriftdictionary.base.BaseFragment
import com.taetae98.wildriftdictionary.databinding.FragmentWebViewBinding

class WebViewFragment : BaseFragment<FragmentWebViewBinding>(R.layout.fragment_web_view) {
    private val args by navArgs<WebViewFragmentArgs>()

    override fun init() {
        super.init()
        initSupportActionBar()
        initWebView()
    }

    private fun initSupportActionBar() {
        binding.title = args.title
        setSupportActionBar(binding.toolbar)
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebView() {
        with(binding.webView) {
            settings.javaScriptEnabled = true
            webChromeClient = WebChromeClient()
            webViewClient = WebViewClient()
            loadUrl(args.url)
        }
    }
}