package com.benhurqs.customerlist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.benhurqs.network.model.CustomerResponse
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.customer_item_list.view.*

class CustomerAdapter(private val context: Context,
                      private val list: List<CustomerResponse?>) : BaseAdapter() {

    override fun getCount() = list.size
    override fun getItem(position: Int) = list[position]
    override fun getItemId(position: Int) = position.toLong()
    override fun getView(position: Int, view: View?, parent: ViewGroup?): View {
        val itemView: View = view ?: LayoutInflater.from(context).inflate(R.layout.customer_item_list, parent, false)
        initItem(itemView, getItem(position))
        return itemView
    }

    private fun initItem(itemView: View, data: CustomerResponse?){
        data?.let {
            itemView.customer_item_name.text = it.name
//            it.isEnabled()

            Glide.with(context)
                .load(it.profileImage)
                .circleCrop()
                .placeholder(com.benhurqs.commons.R.drawable.placeholder_banana)
                .into(itemView.customer_item_image);
        }
    }
}