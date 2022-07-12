package com.example.customerstest.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.customerstest.models.CustomerList
import com.example.customerstest.network.BaseInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    var recyclerListLiveData : MutableLiveData<CustomerList>

    init {
        recyclerListLiveData = MutableLiveData()
    }

    fun getRecyclerListObserver(): MutableLiveData<CustomerList>{
        return recyclerListLiveData
    }

    fun makeApiCall(){
        viewModelScope.launch( Dispatchers.IO ){
        val baseInstance = BaseInstance.getInstance()
        val response = baseInstance.getCustomers().body()
        recyclerListLiveData.postValue(response)
        }
    }

}