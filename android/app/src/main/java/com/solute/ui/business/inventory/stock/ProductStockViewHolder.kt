package com.solute.ui.business.inventory.stock

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.solute.R
import com.utilitykit.database.Database
import com.utilitykit.feature.product.model.Product

class ProductStockViewHolder(inflater: LayoutInflater, parent: ViewGroup) : RecyclerView.ViewHolder(
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

    fun bind(fragment: Fragment?, product: Product) {
        productName?.text = product.Name
        val stock = Database.shared().getLatestStockForProduct(product)
        if(stock != null){
            productStock?.text = stock!!.TotalQuantity.toString()
        }else{
            productStock?.text = " "
        }
    }
}