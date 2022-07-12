package com.example.customerstest.network

import com.example.customerstest.models.CustomerList
import retrofit2.Response
import retrofit2.http.GET

interface Service {

    @GET("service.json")
    suspend fun getCustomers(): Response<CustomerList>

}