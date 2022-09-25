package com.solute.ui.business.inventory.category

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.solute.MainActivity
import com.solute.ui.business.BusinessMainActivity
import com.solute.ui.business.inventory.product.ProductViewHolder
import com.solute.ui.business.inventory.subCategory.SubCategoryDetailsActivity
import com.utilitykit.feature.product.model.Product
import com.utilitykit.feature.productCategory.handler.ProductCategoryHandler
import com.utilitykit.feature.productCategory.model.ProductCategory
import com.utilitykit.feature.productSubCategory.handler.ProductSubCategoryHandler
import com.utilitykit.feature.productSubCategory.model.ProductSubCategory

class ProductSubCategoryAdapter(val context: Context, val fragment: Fragment, val allSubCategory: List<ProductSubCategory>) :
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
            if(context is BusinessMainActivity){
                ProductSubCategoryHandler.shared().repository.selectedSubCategoryLiveData.postValue(item)
                val intent = Intent(context, SubCategoryDetailsActivity::class.java)
                context.startActivity(intent)
            }
        }
        holder.bind(fragment,item)
    }
}