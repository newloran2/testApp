package com.benhurqs.network.repository

import com.benhurqs.network.ErrorType
import com.benhurqs.network.model.CustomerResponse

interface CustomerProtocol {
    fun loadList(list: List<CustomerResponse?>)
    fun showError(errorType: ErrorType)
    fun showLoading()
}