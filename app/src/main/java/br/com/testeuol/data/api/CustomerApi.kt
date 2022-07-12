package br.com.testeuol.data.api

import br.com.testeuol.data.model.Customers
import retrofit2.http.GET

interface CustomerApi {
    @GET("service.json")
    suspend fun getCustomers() : Customers
}