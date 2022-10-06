package com.solute.ui.business.product.detail.productStockDetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.solute.R
import com.solute.ui.business.product.detail.productStockDetails.adapter.ProductDetailStockAdapter
import com.utilitykit.database.Database
import com.utilitykit.feature.product.handler.ProductHandler
import com.utilitykit.feature.product.model.ProductStock

class ProductStockDetailsFragment : Fragment() {

    var recycler : RecyclerView? = null
    var allStock : ArrayList<ProductStock> = arrayListOf()
    var adapter : ProductDetailStockAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_product_stock_details, container, false)
        recycler = view.findViewById(R.id.fragment_product_details_recycler)
        loadData()
        return  view
    }

    fun loadData(){
        if(ProductHandler.shared().repository.selectedProduct != null && ProductHandler.shared().repository.selectedProduct.value != null) {
            allStock = Database.shared().getAllStockForProduct(ProductHandler.shared().repository.selectedProduct.value!!)
        }
        adapter = ProductDetailStockAdapter(allStock)
        recycler?.layoutManager = LinearLayoutManager(this.context)
        recycler?.adapter = adapter
    }
}