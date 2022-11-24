package com.solute.ui.business.inventory.category

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.utilitykit.UtilityActivity
import com.utilitykit.feature.productCategory.handler.ProductCategoryHandler
import com.utilitykit.feature.productCategory.model.ProductCategory

class ProductCategoryAdapter(val context: Context, val fragment: Fragment, val allCategory: List<ProductCategory>,var onSelect:((category: ProductCategory) -> Unit)? = null) :
    RecyclerView.Adapter<ProductCategoryViewHolder>() {

    override fun getItemCount(): Int {
        return allCategory.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductCategoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ProductCategoryViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: ProductCategoryViewHolder, position: Int) {
        val item = allCategory[position]
        holder.itemView.setOnClickListener {
            if(onSelect != null){
                onSelect!!(item)
            }else if(context is UtilityActivity){
                ProductCategoryHandler.shared().repository.selectedCategoryLiveData.postValue(item)
                val intent = Intent(context, CategoryDetailsActivity::class.java)
                context.startActivity(intent)
            }
        }
        holder.bind(fragment,item,onSelect)
    }
}