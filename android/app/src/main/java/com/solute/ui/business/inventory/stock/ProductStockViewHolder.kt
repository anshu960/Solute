package com.solute.ui.business.inventory.stock

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.friendly.framework.database.DatabaseHandler
import com.friendly.framework.feature.product.model.Product
import com.solute.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ProductStockViewHolder(inflater: LayoutInflater, parent: ViewGroup) : RecyclerView.ViewHolder(
    inflater.inflate(
        R.layout.recycler_item_product_stock, parent, false
    )
) {
    private var productName: TextView? = null
    private var productStock: TextView? = null
    init {
        productName = itemView.findViewById(R.id.recycler_item_product_stock_name)
        productStock = itemView.findViewById(R.id.recycler_item_product_stock_quantity)
    }

    fun bind(fragment: Fragment?, product: Product) {
        productName?.text = product.Name
        productStock?.text = "  "
        val stockDao = DatabaseHandler.shared().database.productStockDao()
        CoroutineScope(Job() + Dispatchers.IO).launch {
            val recentProdFromDb = stockDao.getRecentForProduct(product.Id)
            if(recentProdFromDb != null){
                productStock?.text = recentProdFromDb.TotalQuantity.toString()
            }
        }
    }
}