package com.benhurqs.commons.view

import android.graphics.Bitmap
import android.net.http.SslError
import android.os.Bundle
import android.view.View
import android.webkit.*
import androidx.appcompat.app.AppCompatActivity
import com.benhurqs.actions.SharedDefs
import com.benhurqs.commons.R
import com.benhurqs.network.ErrorType
import kotlinx.android.synthetic.main.activity_webview.*

class WebviewActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)
        configureWebview()
        loadUrl()
    }

    private fun configureWebview(){
        customer_webview.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                customer_webview.visibility = View.INVISIBLE
                loading.playAnimation()
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                loading.pauseAnimation()
                customer_webview.visibility = View.VISIBLE
                loading.visibility = View.GONE
            }

            override fun onReceivedError(
                view: WebView?,
                request: WebResourceRequest?,
                error: WebResourceError?
            ) {
                super.onReceivedError(view, request, error)
                loading.visibility = View.GONE
                customer_webview.visibility = View.INVISIBLE
                error_view.setup(ErrorType.WEBVIEW){
                    loadUrl()
                }
            }

            override fun onReceivedSslError(
                view: WebView?,
                handler: SslErrorHandler?,
                error: SslError?
            ) {
                super.onReceivedSslError(view, handler, error)
                handler?.proceed()
            }
        }
    }

    private fun loadUrl(){
        if(intent.hasExtra(SharedDefs.PROFILE_LINK_COD)){
            intent.getStringExtra(SharedDefs.PROFILE_LINK_COD)?.let {
                customer_webview.loadUrl(it)
            }
        }
    }

}