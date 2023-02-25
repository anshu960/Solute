package com.solute.ui.business.inventory.stock

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.friendly.framework.feature.product.model.Product
import com.solute.MainActivity
import com.solute.ui.business.BusinessActivity

class ProductStockAdapter(val context: Context, val fragment: Fragment?, val allProduct: ArrayList<Product>) :
    RecyclerView.Adapter<ProductStockViewHolder>() {

    override fun getItemCount(): Int {
        return allProduct.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductStockViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ProductStockViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: ProductStockViewHolder, position: Int) {
        val item = allProduct[position]
        holder.itemView.setOnClickListener {
            if(context is MainActivity){
                val mainActivty = context as MainActivity
                val intent = Intent(mainActivty, BusinessActivity::class.java)
                mainActivty.startActivity(intent)
            }
        }
        holder.bind(fragment,item)
    }
}

