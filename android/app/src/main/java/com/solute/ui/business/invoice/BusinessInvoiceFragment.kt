package com.solute.ui.business.invoice

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.friendly.framework.feature.business.handler.BusinessHandler
import com.friendly.framework.feature.invoice.handler.InvoiceHandler
import com.friendly.framework.feature.invoice.model.CustomerInvoice
import com.friendly.framework.feature.invoice.viewModel.InvoiceViewModalFactory
import com.friendly.framework.feature.invoice.viewModel.InvoiceViewModel
import com.google.android.material.textfield.TextInputEditText
import com.solute.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BusinessInvoiceFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BusinessInvoiceFragment : Fragment() {
    val business = BusinessHandler.shared().repository.business
    var invoices : ArrayList<CustomerInvoice> = arrayListOf()
    var adapter : InvoiceHistoryAdapter? = null
    var recycler : RecyclerView? = null
    var searchInput : TextInputEditText? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        InvoiceHandler.shared().viewModel?.filteredCustomerInvoice?.observe(this) {
            if(it != null){
                this.invoices = it as ArrayList<CustomerInvoice>
            }
            reloadData()
        }
        InvoiceHandler.shared().viewModel?.loadInvoice()
        InvoiceHandler.shared().viewModel?.fetchAllInvoice()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_business_invoice, container, false)
        recycler = view.findViewById(R.id.business_invoice_fragment_recycler)
        searchInput = view.findViewById(R.id.business_invoice_fragment_top_search_text_input)
        searchInput?.doOnTextChanged { text, start, before, count ->
            if(text?.isEmpty() == false){
                val invoiceNumber = text.toString()!!.toLong()
                InvoiceHandler.shared().viewModel?.filter(invoiceNumber)
            }else{
                InvoiceHandler.shared().viewModel?.clearAllFilters()
            }
        }
        InvoiceHandler.shared().viewModel?.loadInvoice()
        reloadData()
        return view
    }

    fun reloadData(){
        this.recycler!!.layoutManager = LinearLayoutManager(this.context)
        adapter = InvoiceHistoryAdapter(this.requireContext() ,invoices)
        this.recycler!!.adapter = this.adapter
    }
}