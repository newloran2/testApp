package br.com.testeuol.feature.home.viewmodel

import br.com.testeuol.data.model.Customers

sealed class CustomerViewState {
    object Loading : CustomerViewState()
    object Error : CustomerViewState()
    class DataCustomer(val response : Customers) : CustomerViewState()
}