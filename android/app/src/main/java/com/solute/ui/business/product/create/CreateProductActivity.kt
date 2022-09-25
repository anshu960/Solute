package com.solute.ui.business.product.create

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText
import com.solute.R
import com.utilitykit.UtilityActivity
import com.utilitykit.feature.productCategory.handler.ProductCategoryHandler
import com.utilitykit.feature.productCategory.model.ProductCategory
import com.utilitykit.feature.productSubCategory.handler.ProductSubCategoryHandler
import com.utilitykit.feature.productSubCategory.viewModel.ProductSubCategoryViewModalFactory
import com.utilitykit.feature.productSubCategory.viewModel.ProductSubCategoryViewModel

class CreateProductActivity : UtilityActivity() {

    var productSubCategoryViewModal : ProductSubCategoryViewModel? = null
    var productCategoryText : TextView? = null
    var productSubCategoryText : TextInputEditText? = null
    var saveBtn : Button? = null

    var allCategoory: ArrayList<ProductCategory> = ArrayList()
    var selectedCategoryIndex = 0
    var selectedCategoryName = ""
    var selectedCategory : ProductCategory? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_product)
        loadProductCategory()
    }

    fun loadProductCategory(){
        productSubCategoryViewModal = ViewModelProvider(
            this,
            ProductSubCategoryViewModalFactory(ProductSubCategoryHandler.shared().repository)
        ).get(
            ProductSubCategoryViewModel::class.java
        )
        ProductSubCategoryHandler.shared().setup(productSubCategoryViewModal!!)
        ProductSubCategoryHandler.shared().activity = this

        if(ProductCategoryHandler.shared().repository.allCategory.value != null && ProductCategoryHandler.shared().repository.allCategory.value!!.isNotEmpty()){
            allCategoory = ProductCategoryHandler.shared().repository.allCategory.value as ArrayList<ProductCategory>
        }else{
            ProductCategoryHandler.shared().fetchAllProductCategory()
        }
    }
    fun showCategorySellection(){
        var allCategoryNames : Array<String> = Array(allCategoory.count()){""}
        var index = 0
        allCategoory.forEach {
            it.Name?.let { it1 ->
                allCategoryNames.set(index,it1)
                if(it1 == selectedCategoryName){
                    selectedCategoryIndex = index
                    selectedCategory = it
                }
            }
            index+=1
        }
        index = 0
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Choose an animal")
        builder.setSingleChoiceItems(allCategoryNames, selectedCategoryIndex) { dialog, which ->
            productCategoryText?.setText(allCategoryNames[which])
        }
        builder.setPositiveButton("OK") { dialog, which ->
            allCategoory.forEach {
                it.Name?.let { it1 ->
                    allCategoryNames.set(index,it1)
                    selectedCategoryIndex = index
                    selectedCategory = it
                }
                index+=1
            }
            index = 0
        }
        builder.setNegativeButton("Cancel", null)
        val dialog = builder.create()
        dialog.show()
    }
}