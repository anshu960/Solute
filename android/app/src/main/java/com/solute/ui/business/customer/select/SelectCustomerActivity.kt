package com.solute.ui.business.customer.select

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.solute.R
import com.solute.ui.business.customer.adapter.CustomerAdapter
import com.solute.ui.businessList.BusinessListAdapter
import com.utilitykit.feature.business.handler.BusinessHandler
import com.utilitykit.feature.business.viewModel.BusinessViewModalFactory
import com.utilitykit.feature.business.viewModel.BusinessViewModel
import com.utilitykit.feature.customer.handler.CustomerHandler
import com.utilitykit.feature.customer.model.Customer
import com.utilitykit.feature.customer.viewModel.CustomerViewModalFactory
import com.utilitykit.feature.customer.viewModel.CustomerViewModel

class SelectCustomerActivity : AppCompatActivity() {
    var recycler : RecyclerView? = null
    var searchBar : SearchView? = null
    var fabButton : FloatingActionButton? = null
    var allCustomer : ArrayList<Customer> = arrayListOf()
    var adapter : CustomerAdapter? = null
    var viewModal : CustomerViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_customer)
        recycler = findViewById(R.id.selct_customer_recycler)
        searchBar = findViewById(R.id.selct_customer_search)
        fabButton = findViewById(R.id.selct_customer_fab)
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
        viewModal?.fetchAllCustomer()
    }

    fun reload(){
        this.recycler!!.layoutManager = LinearLayoutManager(this)
        adapter =  CustomerAdapter(this, allCustomer)
        this.recycler!!.adapter = this.adapter
    }

}