package com.solute.ui.business.product.detail.productDetailBarcode.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.utilitykit.feature.product.model.ProductBarCode

class ProductBarCodeAdapter(val context: Context, val allCategory: List<ProductBarCode>, var onSelect:((barcode: ProductBarCode) -> Unit)? = null) :
    RecyclerView.Adapter<ProductBarCodeViewHolder>() {

    override fun getItemCount(): Int {
        return allCategory.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductBarCodeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ProductBarCodeViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: ProductBarCodeViewHolder, position: Int) {
        val item = allCategory[position]
        holder.itemView.setOnClickListener {

        }
        holder.bind(item,onSelect)
    }
}