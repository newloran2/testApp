package com.l.uoltest.presentation.util

import android.content.Context
import android.net.http.SslError.*
import android.webkit.WebViewClient.*
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import com.l.uoltest.R
import com.l.uoltest.data.model.ErrorEntity

import org.junit.Before
import org.junit.Test

class DisplayErrorUtilsKtTest {

    private lateinit var context: Context

    @Before
    fun setUp() {
        context = ApplicationProvider.getApplicationContext()
    }

    @Test
    fun onErrorEntity_getCorrectMessage() {
        assertThat(getErrorMessageFromError(context, ErrorEntity.Network))
            .isEqualTo(context.getString(R.string.error_network))

        assertThat(getErrorMessageFromError(context, ErrorEntity.ServiceUnavailable))
            .isEqualTo(context.getString(R.string.error_service_unavailable))

        assertThat(getErrorMessageFromError(context, ErrorEntity.AccessDenied))
            .isEqualTo(context.getString(R.string.error_denied))

        assertThat(getErrorMessageFromError(context, ErrorEntity.Unknown))
            .isEqualTo(context.getString(R.string.error_unknown))
    }

    @Test
    fun onWebResourceError_getCorrectMessage() {
        assertThat(getWebResourceErrorMessage(context, ERROR_UNKNOWN))
            .isEqualTo(context.getString(R.string.error_unknown))

        assertThat(getWebResourceErrorMessage(context, ERROR_CONNECT))
            .isEqualTo(context.getString(R.string.error_site_connect))

        assertThat(getWebResourceErrorMessage(context, ERROR_TIMEOUT))
            .isEqualTo(context.getString(R.string.error_site_timeout))

        assertThat(getWebResourceErrorMessage(context, ERROR_BAD_URL))
            .isEqualTo(context.getString(R.string.error_site_not_found))

        assertThat(getWebResourceErrorMessage(context, ERROR_UNSAFE_RESOURCE))
            .isEqualTo(context.getString(R.string.error_site_untrusted))

        listOf(
            ERROR_HOST_LOOKUP,
            ERROR_UNSUPPORTED_AUTH_SCHEME,
            ERROR_AUTHENTICATION,
            ERROR_PROXY_AUTHENTICATION,
            ERROR_IO,
            ERROR_REDIRECT_LOOP,
            ERROR_UNSUPPORTED_SCHEME,
            ERROR_FAILED_SSL_HANDSHAKE,
            ERROR_FILE,
            ERROR_FILE_NOT_FOUND,
            ERROR_TOO_MANY_REQUESTS,
        )
            .forEach {
                assertThat(getWebResourceErrorMessage(context, it))
                    .isEqualTo(context.getString(R.string.error_site_connect))
            }
    }

    @Test
    fun onSslError_getCorrectMessage() {
        assertThat(getSslErrorMessage(context, SSL_IDMISMATCH))
            .isEqualTo(context.getString(R.string.error_site_not_found))

        assertThat(getSslErrorMessage(context, SSL_INVALID))
            .isEqualTo(context.getString(R.string.error_site_not_found))

        listOf(
            SSL_EXPIRED,
            SSL_DATE_INVALID,
            SSL_NOTYETVALID,
            SSL_UNTRUSTED
        )
            .forEach {
                assertThat(getSslErrorMessage(context, it))
                    .isEqualTo(context.getString(R.string.error_site_connect))
            }
    }


}