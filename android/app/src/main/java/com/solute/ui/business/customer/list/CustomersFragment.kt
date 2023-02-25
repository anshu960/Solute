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
import com.solute.R
import com.solute.ui.business.customer.adapter.CustomerAdapter


class CustomersFragment : Fragment() {

    var adapter : CustomerAdapter? = null
    var viewModal : CustomerViewModel? = null

    var allCustomer : ArrayList<Customer> = arrayListOf()

    var recycler : RecyclerView? = null
    var searchText : EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModal = ViewModelProvider(
            this,
            CustomerViewModalFactory(CustomerHandler.shared().repository)
        ).get(
            CustomerViewModel::class.java
        )
        viewModal?.allCustomer?.observe(this) {
            if (it.isNotEmpty()) {
                allCustomer = it
                this.reload()
            }
        }
        CustomerHandler.shared().setup(viewModal!!)
        viewModal?.loadCustomer()
        viewModal?.fetchAllCustomer()
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
        return view
    }

    fun reload(){
        this.recycler!!.layoutManager = LinearLayoutManager(this.context)
        adapter = this.context?.let { CustomerAdapter(it, allCustomer,null) }
        this.recycler!!.adapter = this.adapter
    }
    fun searchCustomer(query:String){
        if(query.isNotEmpty()){
            if(viewModal?.allCustomer?.value?.isEmpty() == false){
                this.allCustomer = arrayListOf()
                viewModal?.allCustomer?.value?.forEach {
                    if(it.MobileNumber?.contains(query) == true){
                       allCustomer.add(it)
                    }
                }
            }
        }else{
            if(viewModal?.allCustomer?.value?.isEmpty() == false){
                this.allCustomer = viewModal?.allCustomer?.value!!
            }
        }
        reload()
    }
}