package com.l.uoltest.presentation.ui.customer_list

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.RequestManager
import com.l.uoltest.data.model.Customer
import com.l.uoltest.presentation.util.SharedViews

class CustomersAdapter(
    private val requestManager: RequestManager,
    private val onCustomerClick: (customer: Customer, sharedViews: SharedViews) -> Unit
) : ListAdapter<Customer, CustomerViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerViewHolder {
        return CustomerViewHolder(
            parent = parent,
            requestManager = requestManager,
            onCustomerClick = onCustomerClick
        )
    }

    override fun onBindViewHolder(holder: CustomerViewHolder, position: Int) {
        holder.bindView(currentList[position])
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Customer>() {
            override fun areItemsTheSame(oldItem: Customer, newItem: Customer): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Customer, newItem: Customer): Boolean {
                return oldItem == newItem
            }

        }
    }
}