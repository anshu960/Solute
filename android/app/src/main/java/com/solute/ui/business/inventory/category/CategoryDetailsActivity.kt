package com.solute.ui.business.inventory.category

import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.friendly.framework.feature.product.handler.ProductHandler
import com.friendly.framework.feature.product.model.Product
import com.friendly.framework.feature.productCategory.handler.ProductCategoryHandler
import com.friendly.framework.feature.productCategory.model.ProductCategory
import com.solute.R
import com.solute.ui.business.product.BusinessProductAdapter


class CategoryDetailsActivity : AppCompatActivity() {

    var recycler : RecyclerView? = null
    var selectedCategory : ProductCategory? = null
    var allProduct: ArrayList<Product> = ArrayList()
    var productAdapter : BusinessProductAdapter? = null
    var title : TextView? = null
    var countTxt : TextView? = null
    var backButton : ImageButton? = null

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
        this.title = findViewById(R.id.category_details_title)
        this.title?.text = selectedCategory?.Name
        this.countTxt = findViewById(R.id.category_details_count)
        this.countTxt?.text = "Total ${allProduct.count()} Product"
        this.productAdapter =   BusinessProductAdapter(this,null,allProduct)
        this.recycler?.layoutManager = GridLayoutManager(this,2)
        recycler?.adapter = this.productAdapter
        this.backButton = findViewById(R.id.category_details_header_back)
        backButton?.setOnClickListener { onBackPressed() }
    }



}