package com.solute.ui.business.inventory.subCategory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.solute.R
import com.solute.ui.business.inventory.product.ProductAdapter
import com.utilitykit.feature.product.handler.ProductHandler
import com.utilitykit.feature.product.model.Product
import com.utilitykit.feature.productSubCategory.handler.ProductSubCategoryHandler
import com.utilitykit.feature.productSubCategory.model.ProductSubCategory

class SubCategoryDetailsActivity : AppCompatActivity() {
    var recycler : RecyclerView? = null
    var selectedSubCategory : ProductSubCategory? = null
    var allProduct: ArrayList<Product> = ArrayList()
    var productAdapter : ProductAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub_category_details)
        recycler = findViewById(R.id.sub_category_details_recycler)
        selectedSubCategory = ProductSubCategoryHandler.shared().repository.selectedSubCategory.value
        val unFilteredProducts = ProductHandler.shared().repository.allProduct.value
        unFilteredProducts?.forEach {
            if(selectedSubCategory != null){
                if(it.SubCategoryID == selectedSubCategory!!.Id){
                    allProduct.add(it)
                }
            }
        }
        this.productAdapter =   ProductAdapter(this,null,allProduct)
        this.recycler?.layoutManager = LinearLayoutManager(this)
        recycler?.adapter = this.productAdapter
    }
}