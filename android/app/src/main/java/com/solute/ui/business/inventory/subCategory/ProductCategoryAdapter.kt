package com.solute.ui.business.inventory.category

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.friendly.framework.feature.productSubCategory.handler.ProductSubCategoryHandler
import com.friendly.framework.feature.productSubCategory.model.ProductSubCategory
import com.solute.navigation.AppNavigator


class ProductSubCategoryAdapter(val context: Context, val fragment: Fragment, val allSubCategory: ArrayList<ProductSubCategory>, var onSelect:((subCategory: ProductSubCategory) -> Unit)? = null) :
    RecyclerView.Adapter<ProductSubCategoryViewHolder>() {

    override fun getItemCount(): Int {
        return allSubCategory.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductSubCategoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ProductSubCategoryViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: ProductSubCategoryViewHolder, position: Int) {
        val item = allSubCategory[position]
        holder.itemView.setOnClickListener {
            if(onSelect != null){
                onSelect?.let { it1 -> it1(item) }
            }else {
                ProductSubCategoryHandler.shared().repository.selectedSubCategoryLiveData.postValue(item)
               AppNavigator.shared().goToCreateProductSubCategoryDetails()
            }
        }
        holder.bind(fragment,item)
    }
}