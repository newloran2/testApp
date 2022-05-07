package com.l.uoltest.data.repository

import com.l.uoltest.data.model.Customer
import kotlinx.coroutines.flow.Flow

interface CustomerRepository {
    fun getAllCustomers(): Flow<List<Customer>>
}