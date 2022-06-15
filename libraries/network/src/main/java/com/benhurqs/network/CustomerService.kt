package com.benhurqs.network

import com.benhurqs.network.model.CustomerListResponse
import com.benhurqs.network.model.CustomerResponse
import retrofit2.Call
import retrofit2.http.GET

interface CustomerService {
    @GET("main/service.json")
    fun getCustomer() : Call<CustomerListResponse?>?
}