package br.com.testeuol.feature.home.viewmodel

import android.webkit.WebView
import androidx.lifecycle.ViewModel

class GalleryLinkWebViewModel : ViewModel() {

    fun showUrlWebView(webView: WebView , url : String) {
        webView.loadUrl(url)
    }
}