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
import com.utilitykit.feature.product.model.Product
import com.utilitykit.feature.productCategory.model.ProductCategory

class ProductCategoryAdapter(val context: Context, val fragment: Fragment, val allCategory: List<ProductCategory>) :
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
            if(context is MainActivity){
//                val mainActivty = context as MainActivity
//                val intent = Intent(mainActivty, BusinessMainActivity::class.java)
//                mainActivty.startActivity(intent)
            }
        }
        holder.bind(fragment,item)
    }
}