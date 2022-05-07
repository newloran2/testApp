package com.l.uoltest.presentation.ui.customer_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.l.uoltest.data.model.Customer
import com.l.uoltest.data.model.Result
import com.l.uoltest.data.repository.CustomerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class CustomerListViewModel @Inject constructor(
    repository: CustomerRepository
) : ViewModel() {

    private val _customers = MutableStateFlow<Result<List<Customer>>>(Result.Loading)
    val customer: StateFlow<Result<List<Customer>>> = _customers.asStateFlow()

    init {
        repository.getAllCustomers()
            .flowOn(Dispatchers.IO)
            .onStart {
                println("_RESULT_ onStart")

                _customers.update {
                    Result.Loading
                }
            }
            .onEach { result ->
                println("_RESULT_ onEach")
                _customers.update {
                    Result.Success(result)
                }
            }
            .catch {
                println("_RESULT_ catch $it")

                _customers.update { _ ->
                    Result.Error(it)
                }
            }
            .launchIn(viewModelScope)
    }

}