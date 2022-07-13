package com.example.customerstest

import android.view.View
import com.example.customerstest.models.Customer

interface RecyclerListClickListener {
    fun onRecyclerListItemClick(view: View, customer: Customer)
}