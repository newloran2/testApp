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
            this.protocol?.emptyState()
            this.protocol?.hideLoading()
        }else{
            this.protocol?.loadList(list.customers)
            this.protocol?.hideLoading()
        }
    }

    private val onError: ((errorType: ErrorType) -> Unit) = {
        this.protocol?.hideLoading()
        this.protocol?.showError()
    }

    private val onLoading: () -> Unit = {
       this.protocol?.showLoading()
    }
}