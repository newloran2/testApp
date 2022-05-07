package com.l.uoltest.presentation.util

import android.content.Context
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