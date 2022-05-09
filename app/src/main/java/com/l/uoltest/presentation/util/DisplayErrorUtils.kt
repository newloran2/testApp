package com.l.uoltest.presentation.util

import android.content.Context
import android.net.http.SslError.SSL_IDMISMATCH
import android.net.http.SslError.SSL_INVALID
import android.webkit.WebViewClient.*
import android.widget.Toast
import com.l.uoltest.R
import com.l.uoltest.data.model.ErrorEntity

fun Context.showErrorToast(error: ErrorEntity, duration: Int = Toast.LENGTH_LONG) {
    val errorMessage = getErrorMessageFromError(this, error)
    Toast.makeText(this, errorMessage, duration).show()
}

fun getErrorMessageFromError(context: Context, error: ErrorEntity): String {
    return when (error) {
        is ErrorEntity.Network -> context.getString(R.string.error_network)
        is ErrorEntity.ServiceUnavailable -> context.getString(R.string.error_service_unavailable)
        is ErrorEntity.AccessDenied -> context.getString(R.string.error_denied)
        else -> context.getString(R.string.error_unknown)
    }
}

fun getWebResourceErrorMessage(context: Context, errorCode: Int?): String {
    return when (errorCode ?: ERROR_UNKNOWN) {
        ERROR_UNKNOWN -> context.getString(R.string.error_unknown)
        ERROR_CONNECT -> context.getString(R.string.error_site_connect)
        ERROR_TIMEOUT -> context.getString(R.string.error_site_timeout)
        ERROR_BAD_URL -> context.getString(R.string.error_site_not_found)
        ERROR_UNSAFE_RESOURCE -> context.getString(R.string.error_site_untrusted)
        else -> context.getString(R.string.error_site_connect)
    }
}

fun getSslErrorMessage(context: Context, primaryError: Int?): String {
    return when (primaryError) {
        SSL_IDMISMATCH, SSL_INVALID -> context.getString(R.string.error_site_not_found)
        else -> context.getString(R.string.error_site_connect)
    }
}