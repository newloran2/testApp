package com.l.uoltest.presentation.ui.customer_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.l.uoltest.data.model.Customer
import com.l.uoltest.data.model.Result
import com.l.uoltest.data.repository.CustomerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class CustomerListViewModel @Inject constructor(
    repository: CustomerRepository,
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _customers = MutableStateFlow<Result<List<Customer>>>(Result.Loading)
    val customer: StateFlow<Result<List<Customer>>> = _customers.asStateFlow()

    init {
        repository.getAllCustomers()
            .flowOn(ioDispatcher)
            .onStart {
                _customers.update {
                    Result.Loading
                }
            }
            .onEach { result ->
                _customers.update {
                    Result.Success(result)
                }
            }
            .catch {
                _customers.update { _ ->
                    Result.Error(it)
                }
            }
            .launchIn(viewModelScope)
    }

}