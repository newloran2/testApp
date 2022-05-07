package com.l.uoltest.data.repository

import com.l.uoltest.data.model.Customer
import com.l.uoltest.data.remote.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class CustomerRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : CustomerRepository {

    override fun getAllCustomers(): Flow<List<Customer>> = flow {
        emit(
            apiService.getAllCustomers().customers
        )
    }
}