package br.com.testeuol.data.network

import br.com.testeuol.utils.Constants.HEADER_API_ACCEPT
import br.com.testeuol.utils.Constants.HEADER_NAME
import okhttp3.Interceptor
import okhttp3.Response

import java.io.IOException

class BasicInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request()
        val headerRequest = request.newBuilder()
            .header(HEADER_NAME, HEADER_API_ACCEPT)
            .build()
        return chain.proceed(headerRequest)
    }
}