package com.uoltest.rodrigo.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.uoltest.rodrigo.R
import com.uoltest.rodrigo.model.data.Customer
import com.uoltest.rodrigo.util.ApplicationClass
import com.uoltest.rodrigo.model.data.CustomerList
import com.uoltest.rodrigo.model.repository.CustomerRepository
import com.uoltest.rodrigo.util.Resource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

class CustomerViewModel(
    app: Application,
    private val repository: CustomerRepository
) : AndroidViewModel(app) {

    val customers: MutableLiveData<Resource<List<Customer>>> = MutableLiveData()
    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    init {
        viewModelScope.launch {
            getCustomers()
            delay(2000)
            _isLoading.value = false
        }
    }

    fun getCustomers() = viewModelScope.launch {
        safeGetCustomers()
    }

    private fun handleCustomerListResponse(response: Response<CustomerList>): Resource<List<Customer>> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse.customers.filter { it.requiredProperties() })
            }
        }
        return Resource.Error(response.message())
    }

    private suspend fun safeGetCustomers(){
        customers.postValue(Resource.Loading())
        try {
            if (hasInternetConnection()) {
                val response = repository.getCustomers()
                customers.postValue(handleCustomerListResponse(response))
            } else {
                customers.postValue(Resource.Error(getApplication<ApplicationClass>().getString(R.string.no_internet_connection)))
            }
        } catch (t: Throwable) {
            when (t) {
                is IOException -> customers.postValue(Resource.Error(getApplication<ApplicationClass>().getString(R.string.network_failure)))
                else -> customers.postValue(Resource.Error(getApplication<ApplicationClass>().getString(R.string.unkonw_error)))
            }
        }
    }

    private fun hasInternetConnection(): Boolean {
        val connectivityManager = getApplication<ApplicationClass>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val capabilities =
            connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
        return when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }
}