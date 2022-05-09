package com.l.uoltest.repository

import com.l.uoltest.data.model.Customer
import com.l.uoltest.data.repository.CustomerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeCustomerRepository() : CustomerRepository {

    private var shouldReturnError = false

    fun setReturnError(value: Boolean) {
        shouldReturnError = value
    }

    override fun getAllCustomers(): Flow<List<Customer>> = flow {
        if (shouldReturnError) {
            throw Exception("Test exception")
        }
        emit(
            listOf(
                Customer(id = "1"),
                Customer(id = "2"),
                Customer(id = "3"),
                Customer(id = "4"),
            )
        )
    }

}