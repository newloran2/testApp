package br.com.testeuol.data.network

import android.app.Application
import br.com.testeuol.utils.Constants.BASE_URL
import br.com.testeuol.utils.Network
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitServiceProvider(private val application: Application) {

    private lateinit var okHttpClient: OkHttpClient

    fun <T> create(restApiClass: Class<T>) : T {

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(createOkHttp())
            .build()
            .create(restApiClass)

    }

    private fun createOkHttp() : OkHttpClient {

        val cacheSize = (5 * 1024 * 1024).toLong()
        val cacheRequest = Cache(application.cacheDir, cacheSize)

        val okHttpBuilder = OkHttpClient.Builder()
            .cache(cacheRequest)
            .addInterceptor { chain ->
                var request = chain.request()
                request = if (Network.isNetworkAvailable(application.applicationContext))
                    request.newBuilder().header("Cache-Control", "public, max-age=" + 5).build()
                else
                    request.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7).build()
                chain.proceed(request)
            }
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(
                BasicInterceptor()
            )


        okHttpClient = okHttpBuilder.build()

        return okHttpClient

    }
}