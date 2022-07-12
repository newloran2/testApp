package com.example.customerstest.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.customerstest.R
import com.example.customerstest.models.Customer
import com.squareup.picasso.Picasso

class RecyclerAdapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    var items = ArrayList<Customer>()

    fun setUpdateData(items: ArrayList<Customer>){
        this.items = items
        notifyDataSetChanged()
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val imageThumb = view.findViewById<ImageView>(R.id.imageThumb)
        val tvTitle = view.findViewById<TextView>(R.id.tvTitle)
        val tvDesc = view.findViewById<TextView>(R.id.tvDesc)

        fun bind(data: Customer){
            tvTitle.text = data.name
            tvDesc.text = data.profileLink
            val urlImg = data.profileImage
            Picasso.get()
                .load(urlImg)
                .into(imageThumb)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items.get(position))
    }

    override fun getItemCount(): Int {
        return items.size
    }
}