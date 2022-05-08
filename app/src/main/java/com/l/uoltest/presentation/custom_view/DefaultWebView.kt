package com.l.uoltest.presentation.custom_view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.net.http.SslError
import android.net.http.SslError.SSL_IDMISMATCH
import android.net.http.SslError.SSL_INVALID
import android.util.AttributeSet
import android.view.LayoutInflater
import android.webkit.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.l.uoltest.R
import com.l.uoltest.databinding.DefaultWebViewBinding
import com.l.uoltest.presentation.util.animate
import com.l.uoltest.presentation.util.getErrorMessage
import com.l.uoltest.presentation.util.hideAnimation

class DefaultWebView : ConstraintLayout {
    constructor(context: Context) : super(context) { init(context, null) }
    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet) { init(context, attributeSet) }
    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) : super (context, attributeSet, defStyleAttr) { init(context, attributeSet) }

    private val binding: DefaultWebViewBinding =
        DefaultWebViewBinding.inflate(LayoutInflater.from(context), this, true)

    var errorText: String? = null
        get() = binding.tvError.text?.toString()
        set(value) {
            field = value
            binding.tvError.text = field
        }

    var status: Status = Status.LOADING
        set(value) {
            field = value

            binding.tvError.isVisible = value == Status.ERROR
            binding.btnRetry.isVisible = value == Status.ERROR
            binding.webView.isVisible = value != Status.ERROR

            when (value) {
                Status.LOADING -> {
                    binding.animStatus.animate(R.raw.anim_loading)
                }

                Status.SUCCESS -> {
                    binding.animStatus.hideAnimation()
                }

                Status.ERROR -> {
                    binding.animStatus.animate(R.raw.anim_error)
                }
            }
        }

    val webView: WebView
        get() = binding.webView

    private var wasErrorOccurred: Boolean = false

    private fun init(context: Context, attributeSet: AttributeSet?) {
        attributeSet?.let { attrSet ->
            val styledAttributes = context.obtainStyledAttributes(attrSet, R.styleable.DefaultWebView)

            errorText = styledAttributes.getString(R.styleable.DefaultWebView_errorText)
            status = Status[styledAttributes.getInt(R.styleable.DefaultWebView_status, Status.LOADING.ordinal)]

            styledAttributes.recycle()
        }

        setWebView()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setWebView() {
        webView.run {
            settings.javaScriptEnabled = true
            webViewClient = buildWebViewClient()
        }

        binding.btnRetry.setOnClickListener {
            webView.reload()
        }
    }

    private fun buildWebViewClient(): WebViewClient = object : WebViewClient() {

        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
            wasErrorOccurred = false
            status = Status.LOADING

            println("_WEB_ onPageStarted")
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            if (!wasErrorOccurred) {
                status = Status.SUCCESS
            }

            println("_WEB_ onPageFinished")
        }

        override fun onReceivedError(
            view: WebView?,
            request: WebResourceRequest?,
            error: WebResourceError?
        ) {
            super.onReceivedError(view, request, error)
            onError(error?.getErrorMessage(context))

            println("_WEB_ onReceivedError ${error?.errorCode}: ${error?.description}")
        }

        override fun onReceivedHttpError(
            view: WebView?,
            request: WebResourceRequest?,
            errorResponse: WebResourceResponse?
        ) {
            super.onReceivedHttpError(view, request, errorResponse)
            onError(context.getString(R.string.error_site_connect))

            println("_WEB_ onReceivedHttpError: ${errorResponse?.reasonPhrase}")
        }

        override fun onReceivedSslError(
            view: WebView?,
            handler: SslErrorHandler?,
            error: SslError?
        ) {
            super.onReceivedSslError(view, handler, error)

            when (error?.primaryError) {
                SSL_IDMISMATCH, SSL_INVALID -> {
                    onError(error.getErrorMessage(context))
                }
            }

            println("_WEB_ onReceivedSslError: $error")
        }
    }

    private fun onError(errorMessage: String?) {
        wasErrorOccurred = true
        status = Status.ERROR
        errorText = errorMessage
    }

    fun loadUrl(url: String?) {
        url ?: return onError(context.getString(R.string.error_site_not_found))
        webView.loadUrl(url)
    }

    enum class Status {
        LOADING,
        SUCCESS,
        ERROR;

        companion object {
            operator fun get(index: Int): Status {
                return values().getOrNull(index) ?: LOADING
            }
        }
    }
}