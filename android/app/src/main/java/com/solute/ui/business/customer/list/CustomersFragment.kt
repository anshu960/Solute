package com.solute.ui.business.customer.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.SearchView
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.friendly.framework.feature.customer.handler.CustomerHandler
import com.friendly.framework.feature.customer.model.Customer
import com.friendly.framework.feature.customer.viewModel.CustomerViewModalFactory
import com.friendly.framework.feature.customer.viewModel.CustomerViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.solute.R
import com.solute.app.App
import com.solute.ui.business.customer.adapter.CustomerAdapter


class CustomersFragment : Fragment() {

    var adapter : CustomerAdapter? = null

    var allCustomer : ArrayList<Customer> = arrayListOf()

    var recycler : RecyclerView? = null
    var searchText : EditText? = null
    var fabButton : FloatingActionButton? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        CustomerHandler.shared().viewModel?.allCustomer?.observe(this) {
            if (it.isNotEmpty()) {
                allCustomer = it
                this.reload()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_customers, container, false)
        recycler = view.findViewById(R.id.customer_fragment_recycler)
        searchText = view.findViewById(R.id.customer_fragment_search_text)
        searchText?.doOnTextChanged { text, start, before, count ->
            searchCustomer(text.toString())
        }
        fabButton = view.findViewById(R.id.customer_fragment_fab_btn)
        fabButton?.setOnClickListener { App.shared().mainActivity?.navController?.navigate(R.id.business_customer_create) }
        CustomerHandler.shared().viewModel?.loadCustomer()
        CustomerHandler.shared().viewModel?.fetchAllCustomer()
        return view
    }

    fun reload(){
        this.recycler!!.layoutManager = LinearLayoutManager(this.context)
        adapter = this.context?.let { CustomerAdapter(it, allCustomer,null) }
        this.recycler!!.adapter = this.adapter
    }
    fun searchCustomer(query:String){
        if(query.isNotEmpty()){
            if(CustomerHandler.shared().viewModel?.allCustomer?.value?.isEmpty() == false){
                this.allCustomer = arrayListOf()
                CustomerHandler.shared().viewModel?.allCustomer?.value?.forEach {
                    if(it.MobileNumber?.contains(query) == true){
                       allCustomer.add(it)
                    }
                }
            }
        }else{
            if(CustomerHandler.shared().viewModel?.allCustomer?.value?.isEmpty() == false){
                this.allCustomer = CustomerHandler.shared().viewModel?.allCustomer?.value!!
            }
        }
        reload()
    }
}