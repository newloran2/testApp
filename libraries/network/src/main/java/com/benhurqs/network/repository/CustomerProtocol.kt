package com.benhurqs.network.repository

import com.benhurqs.network.model.CustomerResponse

interface CustomerProtocol {
    fun loadList(list: List<CustomerResponse?>)
    fun emptyState()
    fun showError()
    fun showLoading()
    fun hideLoading()
}