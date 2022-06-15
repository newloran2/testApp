package com.benhurqs.uoltest

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.benhurqs.network.CustomerAPI
import com.benhurqs.network.model.CustomerResponse
import com.benhurqs.network.repository.CustomerProtocol
import com.benhurqs.network.repository.CustomerRepository

class MainActivity : AppCompatActivity(), CustomerProtocol {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CustomerRepository.getCustomerList(this)
    }

    override fun loadList(list: List<CustomerResponse?>) {

    }

    override fun emptyState() {
    }

    override fun showError() {
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }
}