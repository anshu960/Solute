package com.solute.ui.business.receipt

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.solute.R
import com.utilitykit.Constants.Key.Companion.product
import com.utilitykit.feature.cart.handler.CartHandler
import com.utilitykit.feature.cart.model.Sale

class ReceiptAdapter(val context: Context, val sales: List<Sale>) :
    RecyclerView.Adapter<ReceiptViewHolder>() {

    override fun getItemCount(): Int {
        return sales.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReceiptViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ReceiptViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: ReceiptViewHolder, position: Int) {
        val item = sales[position]
        holder.bind(item)
    }
}

class ReceiptViewHolder(inflater: LayoutInflater, parent: ViewGroup) : RecyclerView.ViewHolder(
    inflater.inflate(
        R.layout.recycler_item_receipt, parent, false
    )
) {
    private var productName: TextView? = null
    private var productPrice: TextView? = null
    private var productDiscount: TextView? = null
    private var productQuantity: TextView? = null
    private var productFinalPrice: TextView? = null

    init {
        productName = itemView.findViewById(R.id.recycler_item_receipt_name_txt)
        productPrice = itemView.findViewById(R.id.recycler_item_receipt_price_txt)
        productDiscount = itemView.findViewById(R.id.recycler_item_receipt_discount_txt)
        productQuantity = itemView.findViewById(R.id.recycler_item_receipt_quantity_txt)
        productFinalPrice = itemView.findViewById(R.id.recycler_item_receipt_total_price_txt)
    }

    fun bind(sale: Sale) {
        productName?.text = sale.ProductName
        productPrice?.text = "₹ " +  sale.Price
        productDiscount?.text = "₹ " + sale.Discount
        productQuantity?.text =  sale.Quantity.toString()
        productFinalPrice?.text = "₹ " +  sale.FinalPrice
    }

}