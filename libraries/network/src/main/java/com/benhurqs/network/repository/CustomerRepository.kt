package com.benhurqs.network.repository

import android.util.Log
import com.benhurqs.network.CustomerAPI
import com.benhurqs.network.ErrorType
import com.benhurqs.network.model.CustomerListResponse

object CustomerRepository {

    private var protocol: CustomerProtocol? = null

    fun getCustomerList(protocol: CustomerProtocol?){
        this.protocol = protocol
        CustomerAPI().getCustomer(onSuccess, onError, onLoading)
    }

    private val onSuccess : ((CustomerListResponse?) -> Unit) = { list ->
        if(list == null || list.customers.isNullOrEmpty()){
            this.protocol?.showError(ErrorType.EMPTY)
        }else{
            this.protocol?.loadList(list.customers)
        }
    }

    private val onError: ((errorType: ErrorType) -> Unit) = {
        this.protocol?.showError(it)
    }

    private val onLoading: () -> Unit = {
       this.protocol?.showLoading()
    }
}