package com.example.customerstest

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.customerstest.adapter.RecyclerAdapter
import com.example.customerstest.models.Customer
import com.example.customerstest.models.CustomerList
import com.example.customerstest.viewmodel.MainViewModel


class RecyclerListFragment : Fragment(), RecyclerListClickListener {

    private lateinit var recyclerAdapter: RecyclerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_recycler_list, container, false)

        initViewModel(view)
        initViewModel()
        return view
    }

    private fun initViewModel(view: View){
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        val decoration = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(decoration)

        recyclerAdapter = RecyclerAdapter()
        recyclerView.adapter = recyclerAdapter

    }

    private fun initViewModel(){
        val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.getRecyclerListObserver().observe(viewLifecycleOwner, Observer <CustomerList>{
            if(it != null){
                it?.customers?.let { it1 -> recyclerAdapter.setUpdateData(it1, this) }
            } else{
                Toast.makeText(activity, "Error in getting data", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.makeApiCall()
    }

    override fun onRecyclerListItemClick(view: View, customer: Customer) {
        when(view.id){
            R.id.ivThumb ->{
                Toast.makeText(view.context, "Click em ivThumb", Toast.LENGTH_SHORT).show()
                openWebView(customer.profileImage)
            }

            R.id.tvProfileLink ->{
                Toast.makeText(view.context, "Click em link externo", Toast.LENGTH_SHORT).show()
                openWebView(customer.profileLink)
            }
        }
    }

    private fun openWebView(urlProfile: String) {
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(urlProfile)
        view?.context?.startActivity(i)
    }


    companion object {
        @JvmStatic
        fun newInstance() =
            RecyclerListFragment()
    }
}