package com.anime.dl.ui.base.controller

import android.content.Context
import android.content.pm.PackageManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.CookieManager
import android.widget.Toast
import com.anime.dl.R
import com.anime.dl.databinding.WebviewControllerBinding

class WebViewController(
    val title: String?,
    val link: String
) : BaseController<WebviewControllerBinding>() {

    override val hasBottomNav = false

    override fun inflateView(inflater: LayoutInflater, container: ViewGroup): View {
        try {
            binding = WebviewControllerBinding.inflate(inflater)
            return binding.root
        } catch (e: Throwable) {
            requireWebView()
        }

        return super.inflateView(inflater, container)
    }

    override fun onViewCreated(view: View) {
        super.onViewCreated(view)

        if (!supportsWebView) {
            requireWebView()
            return
        }

        binding.webView.settings.setJavaScriptEnabled(true)
        binding.webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url)
                return true
            }
        }

        binding.webview.loadUrl(url)
    }

    override fun getTitle() = title

    fun supportsWebView() {
        try {
            CookieManager.getInstance()
        } catch (e: Throwable) {
            return false
        }

        return activity.packageManager.hasSystemFeature(PackageManager.FEATURE_WEBVIEW)
    }

    fun requireWebView() {
        Toast.makeText(activity as Context, resources!!.getString(R.string.information_webview_required), Toast.LENGTH_LONG).show()
        finish()
    }
}
