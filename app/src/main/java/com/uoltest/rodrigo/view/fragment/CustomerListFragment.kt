package com.uoltest.rodrigo.view.fragment

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.uoltest.rodrigo.R
import com.uoltest.rodrigo.databinding.FragmentCustomerListBinding
import com.uoltest.rodrigo.util.Constants.Companion.CUSTOMER
import com.uoltest.rodrigo.util.Resource
import com.uoltest.rodrigo.view.CustomerActivity
import com.uoltest.rodrigo.view.adapter.CustomerListAdapter
import com.uoltest.rodrigo.viewmodel.CustomerViewModel

class CustomerListFragment : Fragment(R.layout.fragment_customer_list) {

    lateinit var viewModel: CustomerViewModel
    private lateinit var customerListAdapter: CustomerListAdapter
    private lateinit var binding: FragmentCustomerListBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCustomerListBinding.bind(view)
        viewModel = (activity as CustomerActivity).viewModel
        setupRecyclerView()

        customerListAdapter.setOnItemClickListener { customer ->
            val bundle = bundleOf(CUSTOMER to customer)
            findNavController().navigate(
                R.id.action_customerList_to_customerProfile,
                bundle
            )
        }

        viewModel.customers.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    responseStatusToChangeVisibility(true)
                    response.data?.let { customerListResponse ->
                        customerListAdapter.differ.submitList(customerListResponse)
                        if (customerListResponse.isEmpty()) {
                            responseStatusToChangeVisibility(status = false, isEmpty = true)
                        }
                    }
                }
                is Resource.Error -> {
                    responseStatusToChangeVisibility(false)
                    response.message?.let { message ->
                        binding.error.message.text = message
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }

        })

        initListeners()
    }

    private fun setupRecyclerView() {
        customerListAdapter = CustomerListAdapter()
        binding.recyclerview.apply {
            adapter = customerListAdapter
        }
    }

    private fun initListeners() {
        binding.error.reloadBtn.setOnClickListener {
            viewModel.getCustomers()
        }

        binding.swipeRefresh.setOnRefreshListener {
            viewModel.getCustomers()
        }

        binding.empty.reloadBtn.setOnClickListener {
            viewModel.getCustomers()
        }
    }

    private fun responseStatusToChangeVisibility(status: Boolean, isEmpty: Boolean = false) {
        binding.recyclerview.isVisible = status
        binding.error.errorLayout.isVisible = !status && !isEmpty
        binding.empty.emptyCustomerList.isVisible = !status  && isEmpty
        binding.loading.progressLayout.isVisible = false
        binding.swipeRefresh.isRefreshing = false
    }

    private fun showProgressBar() {
        binding.loading.progressLayout.isVisible = true
        binding.recyclerview.isVisible = false
        binding.error.errorLayout.isVisible = false
    }

}