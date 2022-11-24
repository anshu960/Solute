package com.solute.ui.business.invoice
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.solute.R
import com.solute.ui.business.BusinessActivity
import com.solute.ui.business.receipt.ReceiptDetailsActivity
import com.utilitykit.feature.invoice.handler.InvoiceHandler
import com.utilitykit.feature.invoice.model.CustomerInvoice

class InvoiceHistoryAdapter(val context: Context, val invoices: List<CustomerInvoice>) :
    RecyclerView.Adapter<InvoiceHistoryViewHolder>() {

    override fun getItemCount(): Int {
        return invoices.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InvoiceHistoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return InvoiceHistoryViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: InvoiceHistoryViewHolder, position: Int) {
        val item = invoices[position]
        holder.bind(item)
        holder.itemView.setOnClickListener {
            if(context is BusinessActivity){
                val mainActivty = context
                val intent = Intent(mainActivty, ReceiptDetailsActivity::class.java)
                InvoiceHandler.shared().repository.customerInvoiceLiveData.postValue(item)
                mainActivty.startActivity(intent)
            }
        }
    }
}

class InvoiceHistoryViewHolder(inflater: LayoutInflater, parent: ViewGroup) : RecyclerView.ViewHolder(
    inflater.inflate(
        R.layout.recycler_item_invoice, parent, false
    )
) {
    private var invoicenumber: TextView? = null
    private var invoicePrice: TextView? = null
    private var invoiceDiscount: TextView? = null
    private var invoiceQuantity: TextView? = null
    private var invoiceFinalPrice: TextView? = null

    init {
        invoicenumber = itemView.findViewById(R.id.recycler_item_invoice_number_txt)
        invoicePrice = itemView.findViewById(R.id.recycler_item_invoice_price_txt)
        invoiceDiscount = itemView.findViewById(R.id.recycler_item_invoice_discount_txt)
        invoiceQuantity = itemView.findViewById(R.id.recycler_item_invoice_quantity_txt)
        invoiceFinalPrice = itemView.findViewById(R.id.recycler_item_invoice_total_price_txt)
    }

    fun bind(invoice: CustomerInvoice) {
        invoicenumber?.text = invoice.invoiceNumber.toString()
        invoicePrice?.text = "₹ " +  invoice.totalPrice
        invoiceDiscount?.text = "₹ " + invoice.instantDiscount
        invoiceQuantity?.text =  invoice.salesID?.count().toString()
        invoiceFinalPrice?.text = "₹ " +  invoice.finalPrice
    }

}