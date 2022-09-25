package com.solute.ui.business.inventory.category

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.solute.R
import com.utilitykit.feature.productCategory.model.ProductCategory

class ProductCategoryViewHolder(inflater: LayoutInflater, parent: ViewGroup) : RecyclerView.ViewHolder(
    inflater.inflate(
        R.layout.recycler_item_product_category, parent, false
    )
) {
    private var categoryName: TextView? = null

    init {
        categoryName = itemView.findViewById(R.id.recycler_product_category_name)
    }

    fun bind(fragment: Fragment, product: ProductCategory) {
        categoryName?.text = product.Name
    }
}