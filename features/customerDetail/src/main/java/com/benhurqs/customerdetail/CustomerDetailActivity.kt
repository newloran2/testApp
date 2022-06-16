package com.benhurqs.customerdetail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.benhurqs.actions.Actions
import com.benhurqs.actions.SharedDefs
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_customer_detail.*

class CustomerDetailActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_detail)
        loadData()
    }

    private fun loadData(){
        if(intent.hasExtra(SharedDefs.ID_COD)){
            customer_id.text = intent.getStringExtra(SharedDefs.ID_COD)
        }

        if(intent.hasExtra(SharedDefs.NAME_COD)){
            customer_name.text = intent.getStringExtra(SharedDefs.NAME_COD)
        }

        if(intent.hasExtra(SharedDefs.EMAIL_COD)){
            customer_email.text = intent.getStringExtra(SharedDefs.EMAIL_COD)
        }

        if(intent.hasExtra(SharedDefs.PHONE_COD)){
            customer_phone.text = intent.getStringExtra(SharedDefs.PHONE_COD)
        }

        if(intent.hasExtra(SharedDefs.STATUS_COD)){
            customer_status.text = intent.getStringExtra(SharedDefs.STATUS_COD)
        }

        if(intent.hasExtra(SharedDefs.PROFILE_IMG_COD)){
            Glide.with(this)
                .load(intent.getStringExtra(SharedDefs.PROFILE_IMG_COD))
                .circleCrop()
                .placeholder(com.benhurqs.commons.R.drawable.placeholder_banana)
                .into(customer_image)

            customer_image.setOnClickListener {
                Actions.openFullImage(this, intent.getStringExtra(SharedDefs.PROFILE_IMG_COD))
            }
        }

        if(intent.hasExtra(SharedDefs.PROFILE_LINK_COD) && !intent.getStringExtra(SharedDefs.PROFILE_LINK_COD).isNullOrEmpty()){
            customer_see_profile.setOnClickListener {
                Actions.openWebView(this, intent.getStringExtra(SharedDefs.PROFILE_LINK_COD))
            }
        }else{
            customer_see_profile.visibility = View.GONE
        }

    }


}