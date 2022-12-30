package com.solute.ui.business.product.detail.productDetailBarcode.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.solute.R
import com.utilitykit.feature.product.model.ProductBarCode

class ProductBarCodeViewHolder(inflater: LayoutInflater, parent: ViewGroup) : RecyclerView.ViewHolder(
    inflater.inflate(
        R.layout.recycler_item_product_bar_code, parent, false
    )
) {
    private var barCode: TextView? = null
    var barCodeImage : ImageView? = null

    init {
        barCode = itemView.findViewById(R.id.recycler_item_product_bar_code)
        barCodeImage = itemView.findViewById(R.id.recycler_item_product_bar_code_img)
    }

    fun bind(productBarCode: ProductBarCode, onSelect:((barCode: ProductBarCode) -> Unit)? = null) {
        barCode?.text = productBarCode.BarCode.toString()
    }

}