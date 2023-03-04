package com.solute.ui.business.invoice
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.friendly.framework.feature.invoice.handler.InvoiceHandler
import com.friendly.framework.feature.invoice.model.CustomerInvoice
import com.solute.R
import com.solute.app.App


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
                InvoiceHandler.shared().repository.customerInvoiceLiveData.postValue(item)
            App.shared().mainActivity?.goToReceiptDetails()

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
        invoiceQuantity?.text =  invoice.sales?.count().toString()
        invoiceFinalPrice?.text = "₹ " +  invoice.finalPrice
    }

}