package br.com.testeuol.feature.home.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.testeuol.data.model.Customer
import br.com.testeuol.databinding.ItemCustomerBinding

class CustomerAdapter(private val items: MutableList<Customer>?, private val listener : OnCustomerLinkClicked) :
    RecyclerView.Adapter<ItemCustomerViewHolder>() {

    interface OnCustomerLinkClicked {
        fun onLinkClicked(url : String)
        fun onPhotoUrlClicked(url : String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemCustomerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCustomerBinding.inflate(inflater, parent, false)
        return ItemCustomerViewHolder(binding , listener)
    }

    override fun onBindViewHolder(holder: ItemCustomerViewHolder, position: Int) {
        val customer = items?.get(position)
        if (customer != null) {
            holder.bindItem(customer)
        }
    }

    override fun getItemCount(): Int = items?.count() ?: 0
}