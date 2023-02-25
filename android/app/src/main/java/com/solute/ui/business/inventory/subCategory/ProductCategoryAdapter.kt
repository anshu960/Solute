package com.solute.ui.business.inventory.category

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.friendly.framework.feature.productSubCategory.handler.ProductSubCategoryHandler
import com.friendly.framework.feature.productSubCategory.model.ProductSubCategory
import com.solute.ui.business.BusinessActivity
import com.solute.ui.business.inventory.subCategory.SubCategoryDetailsActivity


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
            }else if(context is BusinessActivity){
                ProductSubCategoryHandler.shared().repository.selectedSubCategoryLiveData.postValue(item)
                val intent = Intent(context, SubCategoryDetailsActivity::class.java)
                context.startActivity(intent)
            }
        }
        holder.bind(fragment,item)
    }
}