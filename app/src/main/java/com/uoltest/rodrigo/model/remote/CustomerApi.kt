package com.uoltest.rodrigo.model.remote

import com.uoltest.rodrigo.model.data.CustomerList
import retrofit2.Response
import retrofit2.http.GET

interface CustomerApi {

    @GET("service.json")
    suspend fun getCustomers(): Response<CustomerList>

}