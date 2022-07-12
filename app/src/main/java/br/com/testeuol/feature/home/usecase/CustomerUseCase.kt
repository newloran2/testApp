package br.com.testeuol.feature.home.usecase

import br.com.testeuol.data.model.Customers
import br.com.testeuol.data.repository.CustomerRepository

class CustomerUseCase(private val customerRepository: CustomerRepository) {

    suspend fun invoke() : Customers {
        return customerRepository.getCustomers()
    }
}