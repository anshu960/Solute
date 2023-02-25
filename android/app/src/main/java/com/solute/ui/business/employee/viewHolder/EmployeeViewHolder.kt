package com.solute.ui.business.employee.viewHolder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.friendly.framework.feature.customer.handler.CustomerHandler
import com.friendly.framework.feature.employee.model.Employee
import com.solute.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class EmployeeViewHolder (inflater: LayoutInflater, parent: ViewGroup) : RecyclerView.ViewHolder(
    inflater.inflate(
        R.layout.recycler_item_customer, parent, false
    )
) {
    private var name: TextView? = null
    private var mobile: TextView? = null
    var invoiceCount : TextView? = null
    var totalInvoiceValue : TextView? = null
    var barcodeImg : ImageView? = null
    var barcodeTxt : TextView? = null
    var selectionImg : ImageView? = null

    init {
        name = itemView.findViewById(R.id.recycler_item_customer_name_txt)
        mobile = itemView.findViewById(R.id.recycler_item_customer_mobile_txt)
        invoiceCount = itemView.findViewById(R.id.recycler_item_customer_receipt_txt)
        totalInvoiceValue = itemView.findViewById(R.id.recycler_item_customer_wallet_txt)
        barcodeImg = itemView.findViewById(R.id.recycler_item_customer_barcode_img)
        barcodeTxt = itemView.findViewById(R.id.recycler_item_customer_barcode_txt)
        selectionImg = itemView.findViewById(R.id.recycler_item_customer_selection_img)
    }

    fun bind(employee: Employee, onSelect: ((employee: Employee) -> Unit)? = null) {
        name?.text = employee.Name
        mobile?.text = employee.MobileNumber
        if(employee.Barcode?.isNotEmpty() == true){
//            generateBarcode(customer.Barcode!!)
//            barcodeTxt?.text = customer.Barcode
//            barcodeImg?.visibility = View.VISIBLE
//            barcodeTxt?.visibility = View.VISIBLE
        }else{
            barcodeImg?.visibility = View.GONE
            barcodeTxt?.visibility = View.GONE
        }
        CustomerHandler.shared().viewModel?.getInvoiceCount(employee.EmployeeUserID!!){
            CoroutineScope(Job() + Dispatchers.Main).launch {
                invoiceCount?.text = "$it Invoices"
            }
        }
        CustomerHandler.shared().viewModel?.getTotalInvoiceValue(employee.EmployeeUserID!!){
            CoroutineScope(Job() + Dispatchers.Main).launch {
                totalInvoiceValue?.text = "$it Invoice Amount"
            }
        }
        if(onSelect != null){
            selectionImg?.visibility = View.VISIBLE
//            validateSelection(customer)
//            CustomerHandler.shared().repository.customer.observe(BusinessHandler.shared().activity){
//                validateSelection(customer)
//            }
        }else{
            selectionImg?.visibility = View.GONE
        }
    }
}