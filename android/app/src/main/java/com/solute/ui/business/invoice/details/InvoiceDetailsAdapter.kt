package com.solute.ui.business.invoice.details

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.friendly.framework.feature.cart.model.Sale
import com.solute.R

class InvoiceDetailsAdapter(val context: Context, val sales: List<Sale>) :
    RecyclerView.Adapter<InvoiceHistoryViewHolder>() {

    override fun getItemCount(): Int {
        return sales.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InvoiceHistoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return InvoiceHistoryViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: InvoiceHistoryViewHolder, position: Int) {
        val item = sales[position]
        holder.bind(item)
    }
}

class InvoiceHistoryViewHolder(inflater: LayoutInflater, parent: ViewGroup) : RecyclerView.ViewHolder(
    inflater.inflate(
        R.layout.recycler_item_invoice_details, parent, false
    )
) {
    private var itemNameTv: TextView? = null
    private var taxTv: TextView? = null
    private var quantityTv: TextView? = null
    private var priceTv: TextView? = null
    private var finalPriceTv: TextView? = null

    init {
        itemNameTv = itemView.findViewById(R.id.item_name_tv)
        taxTv = itemView.findViewById(R.id.item_tax_tv)
        quantityTv = itemView.findViewById(R.id.qty_tv)
        priceTv = itemView.findViewById(R.id.item_price_tv)
        finalPriceTv = itemView.findViewById(R.id.item_final_price_tv)
    }

    fun bind(sales: Sale) {
        itemNameTv?.text = "₹ " +  sales.ProductName
        taxTv?.text = "₹ " + sales.Tax
        quantityTv?.text =  sales.Quantity.toString()
        priceTv?.text = "₹ " +  sales.Price
        finalPriceTv?.text = "₹ " +  sales.FinalPrice
    }

}