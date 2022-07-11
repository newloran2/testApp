package com.uoltest.rodrigo.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.uoltest.rodrigo.model.repository.CustomerRepository

class CustomerViewModelFactory (
    private val app: Application,
    private val repository: CustomerRepository): ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(CustomerViewModel::class.java)) {
            CustomerViewModel(app,repository) as T
        }else{
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }

}