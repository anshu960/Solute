package com.solute.ui.business.customer.select

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.friendly.framework.constants.KeyConstant
import com.friendly.framework.feature.business.handler.BusinessHandler
import com.friendly.framework.feature.cart.handler.CartHandler
import com.friendly.framework.feature.cart.viewModel.CartViewModel
import com.friendly.framework.feature.customer.handler.CustomerHandler
import com.friendly.framework.feature.customer.model.Customer
import com.friendly.framework.feature.customer.viewModel.CustomerViewModalFactory
import com.friendly.framework.feature.customer.viewModel.CustomerViewModel
import com.friendly.framework.feature.invoice.handler.InvoiceHandler
import com.google.android.material.textfield.TextInputEditText
import com.solute.R
import com.solute.ui.business.BusinessActivity
import com.solute.ui.business.customer.adapter.CustomerAdapter
import com.solute.ui.business.receipt.ReceiptDetailsActivity
import org.json.JSONObject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SelectCustomerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SelectCustomerFragment : Fragment() {
    var adapter: CustomerAdapter? = null
    var viewModal: CustomerViewModel? = null
    private var cartViewModel: CartViewModel? = null
    var allCustomer: ArrayList<Customer> = arrayListOf()
    var selectedCustomer: Customer? = null

    var recycler: RecyclerView? = null
    var searchView: SearchView? = null
    var customerName: TextInputEditText? = null
    var customerMobile: TextInputEditText? = null
    var customerEmail: TextInputEditText? = null
    var finalAmount: TextView? = null
    var addCustomerBtn: TextView? = null
    var saleBtn: Button? = null
    var skipBtn: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cartViewModel = CartHandler.shared().viewModel
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_select_customer, container, false)
        searchView = view.findViewById(R.id.select_customer_fragment_search)
        searchView?.isIconified = false
        recycler = view.findViewById(R.id.select_customer_fragment_recycler)
        customerName = view.findViewById(R.id.select_customer_fragment_name_tiet)
        customerMobile = view.findViewById(R.id.select_customer_fragment_mobile_tiet)
        customerEmail = view.findViewById(R.id.select_customer_fragment_email_tiet)
        finalAmount = view.findViewById(R.id.select_customer_fragment_sale_amount)
        addCustomerBtn = view.findViewById(R.id.select_customer_fragment_add_customer_btn)
        saleBtn = view.findViewById(R.id.select_customer_fragment_sale_btn)
        skipBtn = view.findViewById(R.id.select_customer_fragment_skip_btn)
        addCustomerBtn?.setOnClickListener { }
        saleBtn?.setOnClickListener { onClickSale() }
        skipBtn?.setOnClickListener { onClickSkip() }
        addCustomerBtn?.setOnClickListener { onClickAddCustomer() }
        finalAmount?.text = "Rs ${cartViewModel?.totalAmount?.value}"
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                searchCustomer(newText)
                return false
            }
        })
        saleBtn?.visibility = View.GONE
        skipBtn?.visibility = View.VISIBLE
        addCustomerBtn?.visibility = View.GONE
        customerName?.doOnTextChanged { text, start, before, count ->
            if (text?.count() != null && text!!.count() >= 4 && customerMobile!!.text != null && customerMobile!!.text!!.count() >= 9) {
                addCustomerBtn?.visibility = View.VISIBLE
                skipBtn?.visibility = View.GONE
            } else {
                addCustomerBtn?.visibility = View.GONE
                skipBtn?.visibility = View.VISIBLE
            }
        }
        customerMobile?.doOnTextChanged { text, start, before, count ->
            if (text?.count() != null && text!!.count() >= 9 && customerName!!.text != null && customerName!!.text!!.count() >= 4) {
                addCustomerBtn?.visibility = View.VISIBLE
                skipBtn?.visibility = View.GONE
            } else {
                addCustomerBtn?.visibility = View.GONE
                skipBtn?.visibility = View.VISIBLE
            }
        }
        viewModal?.loadCustomer()
        InvoiceHandler.shared().onCreateNewCustomerInvoiceResponse={
            onCreateInvoiceResponse(it)
        }
        return view
    }

    fun onCreateInvoiceResponse(data:JSONObject){
        val intent = Intent(this.context,ReceiptDetailsActivity::class.java)
        intent.putExtra(KeyConstant.invoice,data.toString())
        val activity = BusinessHandler.shared().activity as? BusinessActivity
        activity?.onBackPressed()
        activity?.onBackPressed()
        activity?.startActivity(intent)
        cartViewModel?.resetCart()
    }

    fun reload() {
        this.recycler!!.layoutManager = LinearLayoutManager(this.context)
        adapter = this.context?.let {
            CustomerAdapter(it, allCustomer) { cust ->
                CustomerHandler.shared().repository.customerLiveData.postValue(cust)
                this.selectedCustomer = cust
                saleBtn?.visibility = View.VISIBLE
                skipBtn?.visibility = View.GONE
                addCustomerBtn?.visibility = View.GONE
            }
        }
        this.recycler!!.adapter = this.adapter
    }

    fun searchCustomer(query: String) {
        if (query.isNotEmpty()) {
            if (viewModal?.allCustomer?.value?.isEmpty() == false) {
                this.allCustomer = arrayListOf()
                viewModal?.allCustomer?.value?.forEach {
                    if (it.MobileNumber?.contains(query) == true) {
                        allCustomer.add(it)
                    }
                }
            }
        } else {
            if (viewModal?.allCustomer?.value?.isEmpty() == false) {
                this.allCustomer = viewModal?.allCustomer?.value!!
            }
        }
        reload()
    }

    fun onClickAddCustomer() {
        if (customerName?.text?.isEmpty() == true && customerMobile?.text?.isEmpty() == true) {
            val activity = BusinessHandler.shared().activity as? BusinessActivity
            activity?.toastLong("Please add name and mobile number")
        }else{
            CustomerHandler.shared().onCreateNewCustomer = {
                this.selectedCustomer = it
                onClickSale()
            }
            viewModal?.createNewCustomer(customerName!!.text!!.toString(),customerMobile!!.text!!.toString(),customerEmail!!.text!!.toString(),customerMobile!!.text!!.toString())
        }
    }

    fun onClickSale() {
        cartViewModel?.createSaleAndGenerateReceipt(selectedCustomer)
    }

    fun onClickSkip() {
        cartViewModel?.createSaleAndGenerateReceipt(selectedCustomer)
    }
}