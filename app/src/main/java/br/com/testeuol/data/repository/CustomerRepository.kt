package br.com.testeuol.data.repository

import br.com.testeuol.data.api.CustomerApi

class CustomerRepository(private val customerApi: CustomerApi) {

    suspend fun getCustomers() = customerApi.getCustomers()
}