package com.solute.ui.business.product.create

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.solute.R
import com.utilitykit.Constants.Key.Companion.category
import com.utilitykit.Constants.Key.Companion.name
import com.utilitykit.Constants.Key.Companion.price
import com.utilitykit.UtilityActivity
import com.utilitykit.feature.product.handler.ProductHandler
import com.utilitykit.feature.product.viewModel.ProductViewModalFactory
import com.utilitykit.feature.product.viewModel.ProductViewModel
import com.utilitykit.feature.productCategory.handler.ProductCategoryHandler
import com.utilitykit.feature.productCategory.model.ProductCategory
import com.utilitykit.feature.productSubCategory.handler.ProductSubCategoryHandler
import com.utilitykit.feature.productSubCategory.model.ProductSubCategory
import com.utilitykit.feature.productSubCategory.viewModel.ProductSubCategoryViewModalFactory
import com.utilitykit.feature.productSubCategory.viewModel.ProductSubCategoryViewModel
import java.lang.Error

class CreateProductActivity : UtilityActivity() {

    var allCategoory: ArrayList<ProductCategory> = ArrayList()
    var allSubCategoory: ArrayList<ProductSubCategory> = ArrayList()
    var selectedCategoryIndex = 0
    var selectedCategoryName = ""
    var selectedSubCategoryIndex = 0
    var selectedSubCategoryName = ""
    var selectedCategory : ProductCategory? = null
    var selectedSubCategory : ProductSubCategory? = null

    var categoryLayout : TextInputLayout? = null
    var categoryEditText : TextInputEditText? = null

    var subCategoryLayout : TextInputLayout? = null
    var subCategoryEditText : TextInputEditText? = null

    var productNameLayout : TextInputLayout? = null
    var productNameEditText : TextInputEditText? = null

    var productDescriptionLayout : TextInputLayout? = null
    var productDescriptionEditText : TextInputEditText? = null

    var productMRPLayout : TextInputLayout? = null
    var productMRPEditText : TextInputEditText? = null

    var productCostPriceLayout : TextInputLayout? = null
    var productCostPriceEditText : TextInputEditText? = null

    var productPriceLayout : TextInputLayout? = null
    var productPriceEditText : TextInputEditText? = null

    var saveButton : Button? = null
    var backButton : ImageButton? = null
    private lateinit var productViewModel: ProductViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_product)

        categoryLayout = findViewById(R.id.create_product_category_til)
        categoryEditText = findViewById(R.id.create_product_category_tiet)
        categoryEditText?.setOnClickListener { showCategorySellection() }
        subCategoryLayout = findViewById(R.id.create_product_sub_category_til)
        subCategoryEditText = findViewById(R.id.create_product_sub_category_tiet)
        subCategoryEditText?.setOnClickListener { showSubCategorySellection() }
        productNameLayout = findViewById(R.id.create_product_name_til)
        productNameEditText = findViewById(R.id.create_product_name_tiet)

        productDescriptionLayout = findViewById(R.id.create_product_description_til)
        productDescriptionEditText = findViewById(R.id.create_product_description_tiet)

        productMRPLayout = findViewById(R.id.create_product_mrp_til)
        productMRPEditText = findViewById(R.id.create_product_mrp_tiet)

        productCostPriceLayout = findViewById(R.id.create_product_cost_price_til)
        productCostPriceEditText = findViewById(R.id.create_product_cost_price_tiet)

        productPriceLayout = findViewById(R.id.create_product_price_til)
        productPriceEditText = findViewById(R.id.create_product_price_tiet)

        saveButton = findViewById(R.id.create_product_save_btn)
        saveButton?.setOnClickListener { onClickSave() }

        loadProductPreFilledData()
        backButton = findViewById(R.id.create_product_category_header_back)
        backButton?.setOnClickListener { onBackPressed() }

    }

    fun loadProductPreFilledData(){
        //Product Model
        productViewModel = ViewModelProvider(
            this,
            ProductViewModalFactory(ProductHandler.shared().repository)
        ).get(
            ProductViewModel::class.java
        )
        ProductHandler.shared().activity = this
        //Category
        ProductCategoryHandler.shared().repository.categoryLiveData.observe(this){
            allCategoory = it as ArrayList<ProductCategory>
        }
        if(ProductCategoryHandler.shared().repository.allCategory.value != null && ProductCategoryHandler.shared().repository.allCategory.value!!.isNotEmpty()){
            allCategoory = ProductCategoryHandler.shared().repository.allCategory.value as ArrayList<ProductCategory>
        }else{
            ProductCategoryHandler.shared().fetchAllProductCategory()
        }
        //sub category
        ProductSubCategoryHandler.shared().repository.subCategoryLiveData.observe(this){
            allSubCategoory = it as ArrayList<ProductSubCategory>
        }
        if(ProductSubCategoryHandler.shared().repository.allSubCategory.value != null && ProductSubCategoryHandler.shared().repository.allSubCategory.value!!.isNotEmpty()){
            allSubCategoory = ProductSubCategoryHandler.shared().repository.allSubCategory.value as ArrayList<ProductSubCategory>
        }else{
            ProductSubCategoryHandler.shared().fetchAllProductSubCategory()
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
                }
            }
            index+=1
        }
        index = 0
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Select Category")
        builder.setSingleChoiceItems(allCategoryNames, selectedCategoryIndex) { dialog, which ->
            categoryEditText?.setText(allCategoryNames[which])
            allCategoory.forEach {
                it.Name?.let { it1 ->
                    if(which >= 0 && allCategoryNames[which] == it1){
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

    fun showSubCategorySellection(){
        var filteredSubCategory : ArrayList<String> = arrayListOf()
        var index = 0
        allSubCategoory.forEach {
            it.Name?.let { it1 ->
                if(selectedCategory != null && selectedCategory!!.Id == it.CategoryID){
                    filteredSubCategory.add(it1)
                }
                if(it1 == selectedSubCategoryName){
                    selectedSubCategoryIndex = index
                }
            }
            index+=1
        }
        index = 0
        var allSubCategoryNames : Array<String> = Array(filteredSubCategory.count()){""}
        filteredSubCategory.forEach {
            allSubCategoryNames.set(index,it)
            index+=1
        }
        index = 0
        if(allSubCategoryNames.isEmpty()){
            toast("Please select category first")
            return
        }
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Please selecte Sub Category")
        builder.setSingleChoiceItems(allSubCategoryNames, selectedCategoryIndex) { dialog, which ->
            subCategoryEditText?.setText(allSubCategoryNames[which])
            selectedSubCategoryName = allSubCategoryNames[which]
            allSubCategoory.forEach {
                it.Name?.let { it1 ->
                    if(which >= 0 && allSubCategoryNames[which] == it1){
                        selectedSubCategoryIndex = index
                        selectedSubCategory = it
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
        val prdName = productNameEditText?.text.toString()
        val prdDescription = productDescriptionEditText?.text.toString()
        val prdMMRP = productMRPEditText?.text.toString()
        val prdCostPrice = productCostPriceEditText?.text.toString()
        val prdSalePrice = productPriceEditText?.text.toString()
        var mrp = 0F
        var costPrice = 0F
        var salePrice = 0F
        try {
            mrp = prdMMRP.toFloat()
        }catch (error:Error){
            toast("Please enter valid MRP")
        }
        try {
            costPrice = prdCostPrice.toFloat()
        }catch (error:Error){
            toast("Please enter valid Cost Price")
        }
        try {
            salePrice = prdSalePrice.toFloat()
        }catch (error:Error){
            toast("Please enter valid Sale Price")
        }
        productViewModel.createNewProduct(prdName,prdDescription,selectedCategory!!.Id!!,selectedSubCategory!!.Id!!,mrp,costPrice,salePrice)
    }
}