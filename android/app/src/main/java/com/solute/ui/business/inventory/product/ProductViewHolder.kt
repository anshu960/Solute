package com.solute.ui.business.inventory.product

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.solute.R
import com.utilitykit.feature.product.model.Product

class ProductViewHolder(inflater: LayoutInflater, parent: ViewGroup) : RecyclerView.ViewHolder(
    inflater.inflate(
        R.layout.rrecycler_item_product, parent, false
    )
) {
    private var productName: TextView? = null
    private var productDescription: TextView? = null
    private var productPrice: TextView? = null
    init {
        productName = itemView.findViewById(R.id.product_item_name)
        productDescription = itemView.findViewById(R.id.product_item_description)
        productPrice = itemView.findViewById(R.id.product_item_mrp)
    }

    fun bind(fragment: Fragment, product: Product) {
        productName?.text = product.Name
        productDescription?.text = product.Description
        productPrice?.text = "₹ ${product.MRP}"
    }
}