package com.solute.ui.business.product.detail.productStockDetails.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.friendly.framework.feature.product.model.ProductStock
import com.solute.R


class ProductDetailStockViewHolder(inflater: LayoutInflater, parent: ViewGroup) : RecyclerView.ViewHolder(
    inflater.inflate(
        R.layout.recycler_item_product_stock, parent, false
    )
) {
    private var productName: TextView? = null
    private var productStock: TextView? = null
    init {
        productName = itemView.findViewById(R.id.recycler_item_product_stock_name)
        productStock = itemView.findViewById(R.id.recycler_item_product_stock_quantity)
    }

    fun bind(stock: ProductStock) {
        productName?.text = stock.Comment
        if(stock.TotalQuantity != null){
            productStock?.text = stock!!.TotalQuantity.toString()
        }else{
            productStock?.text = " "
        }
    }
}