package com.benhurqs.customerlist

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import android.widget.GridView
import com.benhurqs.network.model.CustomerResponse

class CustomerListView(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs) {

    private val grid = GridView(context, attrs)

    init {
        grid.numColumns = 2
        grid.horizontalSpacing = 2
        this.addView(grid)
    }

    fun initialize(list: List<CustomerResponse?>){
        val adapter = CustomerAdapter(context, list)
        grid.adapter = adapter
    }
}