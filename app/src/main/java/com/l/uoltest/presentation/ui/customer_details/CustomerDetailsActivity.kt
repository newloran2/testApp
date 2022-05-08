package com.l.uoltest.presentation.ui.customer_details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.l.uoltest.data.model.Customer
import com.l.uoltest.databinding.ActivityCustomerDetailsBinding
import com.l.uoltest.presentation.util.loadImage
import com.l.uoltest.presentation.util.showImage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CustomerDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCustomerDetailsBinding

    private val customer: Customer by lazy {
        intent.getParcelableExtra(CUSTOMER)!!
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomerDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setWebView()
        setCustomerInfo()
    }

    private fun setWebView() {
        binding.defaultWebView.loadUrl(customer.profileLink)
    }

    private fun setCustomerInfo() {
        binding.run {
            tvName.text = customer.name
            tvEmail.text = customer.email
            tvPhone.text = customer.phone

            tvName.isVisible = !customer.name.isNullOrBlank()
            tvEmail.isVisible = !customer.email.isNullOrBlank()
            tvPhone.isVisible = !customer.phone.isNullOrBlank()

            Glide.with(this@CustomerDetailsActivity)
                .loadImage(
                    imageUrl = customer.profileImage,
                    imageView = imgProfile,
                    transformation = CircleCrop()
                )

            imgProfile.setOnClickListener {
                customer.profileImage?.let {
                    imgProfile.showImage(this@CustomerDetailsActivity, it)
                }
            }
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && binding.defaultWebView.webView.canGoBack()) {
            binding.defaultWebView.webView.goBack()
            return true
        }

        return super.onKeyDown(keyCode, event)
    }

    companion object {
        const val CUSTOMER = "customer"

        fun startIntent(
            context: Context,
            customer: Customer
        ): Intent = Intent(context, CustomerDetailsActivity::class.java).apply {
            putExtra(CUSTOMER, customer)
        }
    }
}