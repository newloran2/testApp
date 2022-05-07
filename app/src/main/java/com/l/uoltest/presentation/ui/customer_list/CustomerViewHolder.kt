package com.l.uoltest.presentation.ui.customer_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.text.PrecomputedTextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.l.uoltest.data.model.Customer
import com.l.uoltest.databinding.AdapterCustomerBinding

class CustomerViewHolder(
    parent: ViewGroup,
    private val requestManager: RequestManager,
    private val onCustomerClick: (customer: Customer) -> Unit,
    binding: AdapterCustomerBinding =
        AdapterCustomerBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
) : RecyclerView.ViewHolder(binding.root) {

    private val name = binding.tvName
    private val email = binding.tvEmail
    private val status = binding.tvStatus
    private val id = binding.tvId
    private val root = binding.root

    fun bindView(customer: Customer) {
        name.isVisible = !customer.name.isNullOrBlank()
        email.isVisible = !customer.email.isNullOrBlank()

        customer.name?.let {
            name.setTextFuture(
                PrecomputedTextCompat.getTextFuture(
                    customer.name,
                    name.textMetricsParamsCompat,
                    null
                )
            )
        }

        customer.email?.let {
            email.setTextFuture(
                PrecomputedTextCompat.getTextFuture(
                    customer.email,
                    email.textMetricsParamsCompat,
                    null
                )
            )
        }

        status.setTextFuture(
            PrecomputedTextCompat.getTextFuture(
                customer.status,
                status.textMetricsParamsCompat,
                null
            )
        )

        id.setTextFuture(
            PrecomputedTextCompat.getTextFuture(
                customer.id,
                id.textMetricsParamsCompat,
                null
            )
        )

        root.setOnClickListener {
            onCustomerClick(customer)
        }
    }
}