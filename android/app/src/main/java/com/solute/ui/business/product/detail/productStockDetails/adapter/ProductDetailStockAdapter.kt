package com.solute.ui.business.product.detail.productStockDetails.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.utilitykit.feature.product.model.ProductStock

class ProductDetailStockAdapter(val allStock: ArrayList<ProductStock>) :
    RecyclerView.Adapter<ProductDetailStockViewHolder>() {

    override fun getItemCount(): Int {
        return allStock.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductDetailStockViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ProductDetailStockViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: ProductDetailStockViewHolder, position: Int) {
        val item = allStock[position]
        holder.itemView.setOnClickListener {

        }
        holder.bind(item)
    }
}