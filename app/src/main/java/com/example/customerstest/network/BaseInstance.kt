package com.example.customerstest.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BaseInstance {

    companion object{
        val BASE_URL = "https://raw.githubusercontent.com/newloran2/testApp/main/"

        var retrofitService: Service? = null
        fun getInstance(): Service{
            if(retrofitService == null){
                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(Service::class.java)

            }

            return retrofitService!!
        }

    }

}