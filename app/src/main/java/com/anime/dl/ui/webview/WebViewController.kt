package com.anime.dl.ui.webview

import android.content.Context
import android.content.pm.PackageManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.CookieManager
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import com.anime.dl.R
import com.anime.dl.databinding.WebviewControllerBinding
import com.anime.dl.ui.base.controller.BaseController

class WebViewController(
    val animeTitle: String?,
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

        return android.widget.LinearLayout(activity!! as Context)
    }

    override fun onViewCreated(view: View) {
        super.onViewCreated(view)

        if (!supportsWebView()) {
            requireWebView()
            return
        }

        binding.webview.settings.setJavaScriptEnabled(true)
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
