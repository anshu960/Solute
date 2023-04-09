package com.solute.ui.business.inventory.category

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.friendly.framework.feature.productCategory.handler.ProductCategoryHandler
import com.friendly.framework.feature.productCategory.model.ProductCategory
import com.solute.R
import com.solute.app.App

class ProductCategoryViewHolder(inflater: LayoutInflater, parent: ViewGroup) : RecyclerView.ViewHolder(
    inflater.inflate(
        R.layout.recycler_item_product_category, parent, false
    )
) {
    private var categoryName: TextView? = null
    var selectImg : ImageView? = null
    var disclosureImg : ImageView? = null

    init {
        categoryName = itemView.findViewById(R.id.recycler_product_category_name)
        selectImg = itemView.findViewById(R.id.recycler_product_category_select_img)
        disclosureImg = itemView.findViewById(R.id.recycler_product_category_arrow_image)
    }

    fun bind(fragment: Fragment, category: ProductCategory, onSelect:((category: ProductCategory) -> Unit)? = null) {
        categoryName?.text = category.Name
        if(onSelect != null){
            selectImg?.visibility = View.VISIBLE
            disclosureImg?.visibility = View.GONE
            validateSelection(category)
        }else{
            selectImg?.visibility = View.GONE
            disclosureImg?.visibility = View.VISIBLE
        }
    }
    fun validateSelection(category : ProductCategory){
        val selectedCategory = ProductCategoryHandler.shared().repository.selectedCategory.value?.Id
        if (App.shared().mainActivity != null) {
            ProductCategoryHandler.shared().repository.selectedCategory.observe(App.shared().mainActivity!!){
                if(selectedCategory == category.Id){
                    selectImg?.setImageResource(R.drawable.ic_selected)
                }else{
                    selectImg?.setImageResource(R.drawable.ic_un_selected)
                }
            }
        }
        if(selectedCategory == category.Id){
            selectImg?.setImageResource(R.drawable.ic_selected)
        }else{
            selectImg?.setImageResource(R.drawable.ic_un_selected)
        }
    }
}