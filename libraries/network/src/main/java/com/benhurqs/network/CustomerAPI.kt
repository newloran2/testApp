package com.benhurqs.network

import com.benhurqs.network.model.CustomerListResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit


class CustomerAPI {

    private val okHttpClient = OkHttpClient.Builder()
        .readTimeout(20, TimeUnit.SECONDS)
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .connectTimeout(20, TimeUnit.SECONDS)
        .build()

    private fun retrofit() : Retrofit = Retrofit.Builder()
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BuildConfig.BASE_API_URL)
        .client(okHttpClient)
        .build()

    private val service = retrofit().create(CustomerService::class.java)

    fun getCustomer(onSuccess: ((CustomerListResponse?) -> Unit)?, onError: ((ErrorType) -> Unit)?, onLoading: (() -> Unit)?) {
        service.getCustomer()?.let {
            onLoading?.invoke()
            it.enqueue(object : Callback<CustomerListResponse?> {
                override fun onResponse(
                    call: Call<CustomerListResponse?>,
                    response: Response<CustomerListResponse?>
                ) {
                    onSuccess?.invoke(response.body())
                }

                override fun onFailure(call: Call<CustomerListResponse?>, t: Throwable) {
                    onError?.invoke(ErrorType.SERVER)
                }
            })
            return
        }

        onError?.invoke(ErrorType.DEFAULT)
    }
}