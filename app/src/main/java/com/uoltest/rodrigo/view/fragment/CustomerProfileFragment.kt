package com.uoltest.rodrigo.view.fragment

import android.net.http.SslError
import android.os.Bundle
import android.view.View
import android.webkit.*
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.uoltest.rodrigo.R
import com.uoltest.rodrigo.databinding.FragmentCustomerProfileBinding
import com.uoltest.rodrigo.model.data.Customer
import com.uoltest.rodrigo.view.CustomerActivity
import com.uoltest.rodrigo.viewmodel.CustomerViewModel


class CustomerProfileFragment : Fragment(R.layout.fragment_customer_profile) {

    lateinit var viewModel: CustomerViewModel
    private val args: CustomerProfileFragmentArgs by navArgs()
    private lateinit var binding: FragmentCustomerProfileBinding
    private var error = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCustomerProfileBinding.bind(view)
        viewModel = (activity as CustomerActivity).viewModel
        val customer = args.customer
        webViewSettings(binding.webView, customer)
        initOnClickListeners()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.webView.destroy()
    }

    private fun webViewSettings(webView: WebView, customer: Customer) {
        webView.loadUrl(customer.profileLink)
        webView.webViewClient = object : WebViewClient() {
            override fun onReceivedError(
                view: WebView,
                errorCode: Int,
                description: String,
                failingUrl: String
            ) {
                this@CustomerProfileFragment.error = true
                binding.error.message.text = handlerErrorMessage(description)
            }

            override fun onReceivedSslError(
                view: WebView?,
                handler: SslErrorHandler?,
                error: SslError?
            ) {
                binding.error.message.text = handlerErrorMessage(error.toString())
                responseStatusToChangeVisibility(false)
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                responseStatusToChangeVisibility(!error)
            }
        }
    }

    private fun handlerErrorMessage(error: String = String()): String{
        val result = with(error) {
            when {
                contains("ERR_NAME_NOT_RESOLVED") -> getString(R.string.err_name_not_resolved)
                contains("ERR_CLEARTEXT_NOT_PERMITTED") -> getString(R.string.err_cleartext_not_permitted)
                contains("ERR_CONNECTION_TIMED_OUT") -> getString(R.string.err_connection_timed_out)
                contains("certificate") -> getString(R.string.ssl_error)
                else -> getString(R.string.unkonw_error)
            }
        }
        return result
    }

    private fun initOnClickListeners() {
        binding.error.reloadBtn.setOnClickListener {
            showProgressBar()
            binding.webView.reload()
        }
    }

    private fun responseStatusToChangeVisibility(status: Boolean) {
        binding.webView.isVisible = status
        binding.error.errorLayout.isVisible = !status
        binding.loading.progressLayout.isVisible = false
    }

    private fun showProgressBar() {
        binding.loading.progressLayout.isVisible = true
        binding.webView.isVisible = false
        binding.error.errorLayout.isVisible = false
        error = false
    }

}