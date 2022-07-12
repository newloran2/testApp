package br.com.testeuol.feature.home.view.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import br.com.testeuol.data.model.Customer
import br.com.testeuol.databinding.ItemCustomerBinding

class ItemCustomerViewHolder(private val binding: ItemCustomerBinding, private val listener: CustomerAdapter.OnCustomerLinkClicked) : RecyclerView.ViewHolder(binding.root) {

    fun bindItem(customer: Customer) = with(binding) {
        textNameValue.text = customer.name
        textEmailValue.text = customer.email
        textStatusValue.text = customer.status
        txtIdCode.text = customer.id
        textLinkValue.text = customer.profileLink.toString()

        if(!customer.phone.isNullOrBlank()) {
            textPhone.visibility = View.VISIBLE
            textPhoneValue.visibility = View.VISIBLE
            textPhoneValue.text = customer.phone
        } else {
            textPhone.visibility = View.GONE
            textPhoneValue.visibility = View.GONE
        }


        if(!customer.profileLink.isNullOrBlank()) {
            textLinkValue.visibility = View.VISIBLE
            textLink.visibility = View.VISIBLE
            textLinkValue.setOnClickListener {
                listener.onLinkClicked(customer.profileLink.toString())
            }
        } else {
            textLinkValue.visibility = View.GONE
            textLink.visibility = View.GONE
        }

        if(!customer.profileImage.isNullOrBlank()) {
            btProfile.visibility = View.VISIBLE

            btProfile.setOnClickListener {
                listener.onPhotoUrlClicked(customer.profileImage.toString())
            }
        } else {
            btProfile.visibility = View.GONE
        }
    }
}