package br.com.testeuol.feature.home.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.testeuol.data.model.Customers
import br.com.testeuol.data.network.SafeResponse
import br.com.testeuol.data.network.safeRequest
import br.com.testeuol.feature.home.usecase.CustomerUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class HomeViewModel(private val customerUseCase: CustomerUseCase,
                    private val dispatcher : CoroutineContext = Dispatchers.Main + SupervisorJob()
                    ): ViewModel() {

    private var coroutineScope = CoroutineScope(dispatcher)

    private val viewState = MutableLiveData<CustomerViewState>()
    fun viewStateLiveData(): LiveData<CustomerViewState> = viewState

    private val _list = MutableLiveData<Customers>()

    fun getList(): MutableLiveData<Customers> = _list


    fun getCustomers() {

        viewState.postValue(CustomerViewState.Loading)
        coroutineScope.launch(dispatcher) {
            when (val response = safeRequest { customerUseCase.invoke() }) {

                is SafeResponse.Success -> {
                    viewState.postValue(CustomerViewState.DataCustomer(response.value))
                }

                is SafeResponse.GenericError -> {

                    response.error?.errorBody()?.string().orEmpty()
                    Log.i("Teste" ,  response.error?.errorBody()?.string().orEmpty())

                    viewState.postValue(CustomerViewState.Error)
                }
                else -> {

                    viewState.postValue(CustomerViewState.Error)
                }
            }
        }


    }
}