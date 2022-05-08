package com.l.uoltest.presentation.ui.customer_list

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.l.uoltest.data.model.Customer
import com.l.uoltest.data.model.Result
import com.l.uoltest.databinding.ActivityCustomerListBinding
import com.l.uoltest.presentation.ui.customer_details.CustomerDetailsActivity
import com.l.uoltest.presentation.util.showErrorToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CustomerListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCustomerListBinding

    private val viewModel: CustomerListViewModel by viewModels()

    private val adapterCustomer: CustomersAdapter by lazy {
        CustomersAdapter(
            requestManager = Glide.with(this@CustomerListActivity),
            onCustomerClick = ::onCustomerClick
        )
    }

    private var observeCustomersJob: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomerListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setRecycler()
        observeCustomers()
        setSwipeRefresh()
    }

    private fun setSwipeRefresh() {
        binding.refreshLayout.setOnRefreshListener {
            observeCustomers()
        }
    }

    private fun setRecycler() {
        binding.recyclerCustomers.run {
            layoutManager = LinearLayoutManager(
                this@CustomerListActivity,
                LinearLayoutManager.VERTICAL,
                false
            )

            adapter = adapterCustomer
        }
    }

    private fun observeCustomers() {
        observeCustomersJob?.cancel()

        observeCustomersJob = lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.customer.collect { result ->
                    when (result) {
                        is Result.Loading -> {
                            binding.refreshLayout.isRefreshing = true
                        }
                        is Result.Success -> {
                            binding.refreshLayout.isRefreshing = false
                            adapterCustomer.submitList(result.data)
                        }
                        is Result.Error -> {
                            binding.refreshLayout.isRefreshing = false
                            this@CustomerListActivity.showErrorToast(result.getError())
                        }
                    }
                }
            }
        }
    }

    private fun onCustomerClick(customer: Customer) {
        startActivity(CustomerDetailsActivity.startIntent(this, customer))
    }
}