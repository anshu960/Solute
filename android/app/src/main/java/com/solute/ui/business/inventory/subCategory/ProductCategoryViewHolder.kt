package com.solute.ui.business.inventory.category

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.solute.R
import com.utilitykit.Constants.Key.Companion.product
import com.utilitykit.feature.productCategory.model.ProductCategory
import com.utilitykit.feature.productSubCategory.model.ProductSubCategory

class ProductSubCategoryViewHolder(inflater: LayoutInflater, parent: ViewGroup) : RecyclerView.ViewHolder(
    inflater.inflate(
        R.layout.recycler_item_product_sub_category, parent, false
    )
) {
    private var subCategoryName: TextView? = null

    init {
        subCategoryName = itemView.findViewById(R.id.recycler_product_sub_category_name)
    }

    fun bind(fragment: Fragment, subCatetgory: ProductSubCategory,onSelect:((subCategory: ProductSubCategory) -> Unit)? = null) {
        subCategoryName?.text = subCatetgory.Name
    }
}