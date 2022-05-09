package com.l.uoltest.data.remote

import com.l.uoltest.data.model.Customer
import com.l.uoltest.data.model.CustomersApiCall
import retrofit2.http.GET

interface ApiService {
    @GET("service.json")
    suspend fun getAllCustomers(): CustomersApiCall
}