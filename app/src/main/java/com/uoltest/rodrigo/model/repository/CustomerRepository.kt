package com.uoltest.rodrigo.model.repository

import com.uoltest.rodrigo.api.RetrofitService

class CustomerRepository {
    suspend fun getCustomers() = RetrofitService.api.getCustomers()
}