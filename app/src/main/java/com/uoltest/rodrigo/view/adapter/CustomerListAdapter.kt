package com.uoltest.rodrigo.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.uoltest.rodrigo.R
import com.uoltest.rodrigo.model.data.Customer
import com.uoltest.rodrigo.databinding.FragmentCustomerListAdapterBinding
import com.uoltest.rodrigo.util.Constants.Companion.ACTIVE
import com.stfalcon.imageviewer.StfalconImageViewer

class CustomerListAdapter : RecyclerView.Adapter<CustomerListAdapter.CustomerListViewHolder>() {

    private var onItemClickListener: ((Customer) -> Unit)? = null

    private val differCallback = object : DiffUtil.ItemCallback<Customer>() {
        override fun areItemsTheSame(oldItem: Customer, newItem: Customer): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Customer, newItem: Customer): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = FragmentCustomerListAdapterBinding.inflate(inflater, parent, false)
        return CustomerListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CustomerListViewHolder, position: Int) {
        val currentCustomer = differ.currentList[position]
        holder.bind(currentCustomer, onItemClickListener)
    }

    fun setOnItemClickListener(listener: (Customer) -> Unit) {
        onItemClickListener = listener
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    class CustomerListViewHolder(private val binding: FragmentCustomerListAdapterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(customer: Customer, onItemClickListener: ((Customer) -> Unit)? = null) {
            setTextField(binding.name, customer.name)
            setTextField(binding.email, customer.email)
            setTextField(binding.phone, customer.phone)
            setTextField(binding.id, customer.id)
            setStatusColor(customer.status)
            openProfileIconVisibility(customer)
            setIconVisibility(binding.emailIcon, customer.email)
            setIconVisibility(binding.phoneIcon, customer.phone)
            setImage(customer)
            initClickListeners(customer, onItemClickListener)
        }

        private fun setImage(customer: Customer) {
            if (customer.profileImage.isNotEmpty()) {
                Glide.with(binding.thumbnail.context)
                    .load(customer.profileImage)
                    .placeholder(R.drawable.ic_baseline_person_48)
                    .error(R.drawable.ic_baseline_broken_image_48)
                    .into(binding.thumbnail)
            } else {
                Glide.with(binding.thumbnail.context)
                    .load(R.drawable.ic_baseline_person_48)
                    .into(binding.thumbnail)
            }
        }

        private fun initClickListeners(
            customer: Customer,
            onItemClickListener: ((Customer) -> Unit)? = null
        ) {
            if (customer.profileImage.isNotEmpty()) {
                binding.thumbnail.setOnClickListener {
                    StfalconImageViewer.Builder(
                        it.context,
                        arrayListOf(
                            customer.profileImage
                        )
                    ) { view, image ->
                        Glide.with(it.context)
                            .load(image)
                            .error(R.drawable.ic_baseline_broken_image_48)
                            .into(view);
                    }.withHiddenStatusBar(true).show()
                }
            }

            binding.openInNew.setOnClickListener {
                onItemClickListener?.invoke(customer)
            }
        }

        private fun setTextField(field: TextView, data: String) {
            field.text = data
            field.isVisible = data.isNotEmpty()
        }

        private fun setIconVisibility(field: ImageView, data: String) {
            field.isVisible = data.isNotEmpty()
        }

        private fun setStatusColor(status: String) {
            if (status.contains(ACTIVE)) {
                binding.status.setBackgroundColor(
                    ContextCompat.getColor(
                        binding.status.context,
                        android.R.color.holo_green_light
                    )
                )
            } else {
                binding.status.setBackgroundColor(
                    ContextCompat.getColor(
                        binding.status.context,
                        android.R.color.holo_red_light
                    )
                )
            }
        }

        private fun openProfileIconVisibility(customer: Customer) {
            binding.openInNew.isVisible = customer.profileLink.isNotEmpty()
        }

    }
}
