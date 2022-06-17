package com.benhurqs.network.repository

import com.benhurqs.network.CustomerAPI
import com.benhurqs.network.ErrorType
import com.benhurqs.network.model.CustomerListResponse

object CustomerRepository{

    private var protocol: CustomerProtocol? = null

    fun getCustomerList(customerAPI: CustomerAPI = CustomerAPI(),
                        protocol: CustomerProtocol?){

        this.protocol = protocol
        customerAPI.getCustomer({ onSuccess(it) }, { onError(it) }, { onLoading() })
    }

    fun onLoading(){
        this.protocol?.showLoading()
    }

    fun onSuccess(list: CustomerListResponse?){
        if(list == null || list.customers.isNullOrEmpty()){
            this.protocol?.showError(ErrorType.EMPTY)
        }else{
            this.protocol?.loadList(list.customers!!)
        }
    }

    fun onError(type: ErrorType){
        this.protocol?.showError(type)
    }
}