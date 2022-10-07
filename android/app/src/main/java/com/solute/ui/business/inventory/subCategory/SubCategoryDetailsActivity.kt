package com.solute.ui.business.inventory.subCategory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.solute.R
import com.solute.ui.business.inventory.product.ProductAdapter
import com.solute.ui.business.product.BusinessProductAdapter
import com.utilitykit.feature.product.handler.ProductHandler
import com.utilitykit.feature.product.model.Product
import com.utilitykit.feature.productSubCategory.handler.ProductSubCategoryHandler
import com.utilitykit.feature.productSubCategory.model.ProductSubCategory

class SubCategoryDetailsActivity : AppCompatActivity() {
    var recycler : RecyclerView? = null
    var selectedSubCategory : ProductSubCategory? = null
    var allProduct: ArrayList<Product> = ArrayList()
    var productAdapter : BusinessProductAdapter? = null
    var title : TextView? = null
    var countTxt : TextView? = null
    var backButton : ImageButton? = null
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
        this.title = findViewById(R.id.sub_category_details_title)
        this.title?.text = selectedSubCategory?.Name
        this.countTxt = findViewById(R.id.sub_category_details_count)
        this.countTxt?.text = "Total ${allProduct.count()} Product"
        this.productAdapter =   BusinessProductAdapter(this,null,allProduct)
        this.recycler?.layoutManager = GridLayoutManager(this,2)
        recycler?.adapter = this.productAdapter
        this.backButton = findViewById(R.id.sub_category_details_header_back)
        backButton?.setOnClickListener { onBackPressed() }
    }
}