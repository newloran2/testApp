package com.benhurqs.commons.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.benhurqs.actions.SharedDefs
import com.benhurqs.commons.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_image.*

class FullImageActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)
        loadData()
    }

    private fun loadData(){
        if(intent.hasExtra(SharedDefs.PROFILE_IMG_COD)){
            Glide.with(this)
                .load(intent.getStringExtra(SharedDefs.PROFILE_IMG_COD))
                .placeholder(com.benhurqs.commons.R.drawable.placeholder_banana)
                .into(customer_full_image)
        }
    }
}