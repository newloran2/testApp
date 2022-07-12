package br.com.testeuol.feature.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.testeuol.R
import br.com.testeuol.data.model.Customers
import br.com.testeuol.databinding.FragmentListCustomerBinding
import br.com.testeuol.extension.showDialog
import br.com.testeuol.feature.home.view.adapter.CustomerAdapter
import br.com.testeuol.feature.home.viewmodel.CustomerViewState
import br.com.testeuol.feature.home.viewmodel.HomeViewModel
import org.koin.android.ext.android.inject

class ListCustomerFragment : Fragment() {

    private lateinit var binding: FragmentListCustomerBinding
    private val viewModel by inject<HomeViewModel>()
    private lateinit var adapter : CustomerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListCustomerBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObserver()
        iniUI()
    }

    private fun iniUI() {

        viewModel.getCustomers()
    }

    private fun initObserver() {

        viewModel.viewStateLiveData().observe(viewLifecycleOwner) { viewState->
            when(viewState) {
                is CustomerViewState.Loading -> {
                    binding.progressBarList.visibility = View.VISIBLE
                }

                is CustomerViewState.Error -> {
                    binding.progressBarList.visibility = View.GONE
                    requireContext().showDialog(
                        getString(R.string.text_title_alert),
                        getString(R.string.text_mensagem_dialog),
                        listenerDialog = {viewModel.getCustomers()}
                    )
                }

                is CustomerViewState.DataCustomer -> {
                    openListCustomers(viewState.response)
                }
            }
        }
    }

    private fun openListCustomers(response: Customers) {

        with(binding) {
            rcCustomer.layoutManager = LinearLayoutManager(requireActivity(), RecyclerView.VERTICAL, false)
            adapter = CustomerAdapter(response.customers, object : CustomerAdapter.OnCustomerLinkClicked{
                override fun onLinkClicked(url: String) {

                    val direction = ListCustomerFragmentDirections.actionListCustomerFragmentToGalleryLinkWebViewFragment().setUrlProfile(url)
                    findNavController().navigate(direction)
                }


                override fun onPhotoUrlClicked(url: String) {
                    val direction = ListCustomerFragmentDirections.actionListCustomerFragmentToGalleryPhotoProfileFragment().setUrlImage(url)
                    findNavController().navigate(direction)
                }

            })
            rcCustomer.adapter = adapter
            progressBarList.visibility = View.GONE
        }
    }
}