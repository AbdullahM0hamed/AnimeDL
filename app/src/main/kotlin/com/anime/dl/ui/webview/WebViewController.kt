package com.anime.dl.ui.webview

import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.CookieManager
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import com.anime.dl.R
import com.anime.dl.databinding.WebviewControllerBinding
import com.anime.dl.ui.base.controller.BaseController

class WebViewController : BaseController<WebviewControllerBinding> {

    constructor(animeTitle: String?, link: String) : this(
        Bundle().apply {
            putString("link", link)
        }
    ) {
        this.animeTitle = animeTitle
        this.link = link
    }

    @Suppress("unused")
    constructor(bundle: Bundle) : super(bundle)

    override val hasBottomNav = false

    private var animeTitle: String? = null
        private set

    private var link: String? = null
        private set

    override fun inflateView(inflater: LayoutInflater, container: ViewGroup): View {
        try {
            binding = WebviewControllerBinding.inflate(inflater)
            return binding.root
        } catch (e: Throwable) {
            requireWebView()
        }

        return android.widget.LinearLayout(activity!! as Context)
    }

    override fun onViewCreated(view: View) {
        super.onViewCreated(view)

        if (!supportsWebView()) {
            requireWebView()
            return
        }

        with(binding.webview.settings) {
            javaScriptEnabled = true
            domStorageEnabled = true
            databaseEnabled = true
            setAppCacheEnabled(true)
            useWideViewPort = true
            loadWithOverviewMode = true
            cacheMode = WebSettings.LOAD_DEFAULT
        }

        binding.webview.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                binding.progressBar.isVisible = true
                binding.progressBar.progress = newProgress
                if (newProgress == 100) {
                    binding.progressBar.isInvisible = true
                }
                super.onProgressChanged(view, newProgress)
            }
        }
        binding.webview.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(link)
                return true
            }
        }

        binding.webview.loadUrl(link)
    }

    override fun getTitle() = animeTitle

    fun supportsWebView(): Boolean {
        try {
            CookieManager.getInstance()
        } catch (e: Throwable) {
            return false
        }

        return activity!!.packageManager.hasSystemFeature(PackageManager.FEATURE_WEBVIEW)
    }

    fun requireWebView() {
        Toast.makeText(activity as Context, resources!!.getString(R.string.information_webview_required), Toast.LENGTH_LONG).show()
        router.handleBack()
    }
}
