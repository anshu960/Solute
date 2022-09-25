package com.solute.ui.business.inventory.subCategory

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
import com.utilitykit.feature.productCategory.viewModel.ProductCategoryViewModalFactory
import com.utilitykit.feature.productCategory.viewModel.ProductCategoryViewModel
import com.utilitykit.feature.productSubCategory.handler.ProductSubCategoryHandler
import com.utilitykit.feature.productSubCategory.viewModel.ProductSubCategoryViewModalFactory
import com.utilitykit.feature.productSubCategory.viewModel.ProductSubCategoryViewModel

class CreateProductSubCategoryActivity : UtilityActivity() {

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
        setContentView(R.layout.activity_create_product_sub_category)
        productCategoryText = findViewById(R.id.create_sub_category_category_txt)
        productCategoryText?.setOnClickListener { showCategorySellection() }
        productSubCategoryText = findViewById(R.id.create_sb_category_name_tiet)
        saveBtn = findViewById(R.id.create_sb_category_save_btn)
        saveBtn?.setOnClickListener { onClickSave() }
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
            allCategoory.forEach {
                it.Name?.let { it1 ->
                    if(allCategoryNames[which] == it1){
                        selectedCategoryIndex = index
                        selectedCategory = it
                    }
                }
                index+=1
            }
            index = 0
        }
        builder.setPositiveButton("OK") { dialog, which ->

        }
        builder.setNegativeButton("Cancel", null)
        val dialog = builder.create()
        dialog.show()
    }

    fun onClickSave(){
        val subCategory = productSubCategoryText?.text.toString()
        if(selectedCategory != null && !subCategory.isEmpty()){
            productSubCategoryViewModal?.createNewSubCategory(subCategory!!,selectedCategory!!)
        }
    }

}