package com.solute.ui.business.inventory.category

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.solute.R
import com.solute.ui.business.inventory.product.ProductAdapter
import com.utilitykit.feature.product.handler.ProductHandler
import com.utilitykit.feature.product.model.Product
import com.utilitykit.feature.productCategory.handler.ProductCategoryHandler
import com.utilitykit.feature.productCategory.model.ProductCategory

class CategoryDetailsActivity : AppCompatActivity() {

    var recycler : RecyclerView? = null
    var selectedCategory : ProductCategory? = null
    var allProduct: ArrayList<Product> = ArrayList()
    var productAdapter : ProductAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_details)
        recycler = findViewById(R.id.category_details_recycler)
        selectedCategory = ProductCategoryHandler.shared().repository.selectedCategory.value
        val unFilteredProducts = ProductHandler.shared().repository.allProduct.value
        unFilteredProducts?.forEach {
            if(selectedCategory != null){
                if(it.CategoryID == selectedCategory!!.Id){
                    allProduct.add(it)
                }
            }
        }
        this.productAdapter =   ProductAdapter(this,null,allProduct)
        this.recycler?.layoutManager = LinearLayoutManager(this)
        recycler?.adapter = this.productAdapter
    }



}