package com.example.customerstest.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.customerstest.R
import com.example.customerstest.RecyclerListClickListener
import com.example.customerstest.models.Customer
import com.squareup.picasso.Picasso

class RecyclerAdapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    var items = ArrayList<Customer>()
    lateinit var listener: RecyclerListClickListener

    fun setUpdateData(items: ArrayList<Customer>,
                      listener: RecyclerListClickListener
    ){
        this.items = items
        this.listener = listener
        notifyDataSetChanged()
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val ivThumb = view.findViewById<ImageView>(R.id.ivThumb)
        val tvName = view.findViewById<TextView>(R.id.tvName)
        val tvStatus = view.findViewById<TextView>(R.id.tvStatus)
        val tvId = view.findViewById<TextView>(R.id.tvId)
        val tvPhone = view.findViewById<TextView>(R.id.tvPhone)
        val tvLink = view.findViewById<TextView>(R.id.tvProfileLink)

        fun bind(customer: Customer, listener: RecyclerListClickListener){
            tvName.text = customer.name
            tvStatus.text = customer.status
            tvId.text = customer.id
            tvPhone.text = customer.phone
            tvLink.text = customer.profileLink
            val urlImg = customer.profileImage
            if(urlImg != null){
                Picasso.get()
                    .load(urlImg)
                    .error(R.drawable.uol_avatar_default)
                    .into(ivThumb)
            } else {
                Picasso.get()
                    .load(R.drawable.uol_avatar_default)
                    .into(ivThumb)
            }

            ivThumb.setOnClickListener(){
                listener.onRecyclerListItemClick(ivThumb, customer)
            }
            tvLink.setOnClickListener(){
                listener.onRecyclerListItemClick(tvLink, customer)
            }


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items.get(position), listener)
    }

    override fun getItemCount(): Int {
        return items.size
    }
}