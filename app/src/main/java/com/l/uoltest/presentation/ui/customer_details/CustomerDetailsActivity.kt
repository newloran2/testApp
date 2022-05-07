package com.l.uoltest.presentation.ui.customer_details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.l.uoltest.data.model.Customer
import com.l.uoltest.databinding.ActivityCustomerDetailsBinding
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
    }

    private fun setWebView() {
        customer.profileLink ?: return onProfileLinkMissing()

        binding.defaultWebView.loadUrl(customer.profileLink!!)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && binding.defaultWebView.webView.canGoBack()) {
            binding.defaultWebView.webView.goBack()
            return true
        }

        return super.onKeyDown(keyCode, event)
    }

    private fun onProfileLinkMissing() {
        Toast.makeText(this, "Url missing", Toast.LENGTH_SHORT).show()
        finish()
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