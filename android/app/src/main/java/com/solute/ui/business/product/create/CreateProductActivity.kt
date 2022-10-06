package com.solute.ui.business.product.create

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.shuhart.stepview.StepView
import com.solute.R
import com.squareup.picasso.Picasso
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
    private var stepsPosition = 0
    var allCategoory: ArrayList<ProductCategory> = ArrayList()
    var allSubCategoory: ArrayList<ProductSubCategory> = ArrayList()
    var selectedCategoryIndex = 0
    var selectedCategoryName = ""
    var selectedSubCategoryIndex = 0
    var selectedSubCategoryName = ""
    var selectedCategory : ProductCategory? = null
    var selectedSubCategory : ProductSubCategory? = null

    var productStepView : StepView? = null
    var productInfoCard : CardView? = null
    var productPriceCard : CardView? = null
    var productTaxCard : CardView? = null

    var imageView : ImageView? = null

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

    var productDiscountLayout : TextInputLayout? = null
    var productDiscountEditText : TextInputEditText? = null

    var productFinalPriceLayout : TextInputLayout? = null
    var productFinalPriceEditText : TextInputEditText? = null

    var productIGSTLayout : TextInputLayout? = null
    var productIGSTEditText : TextInputEditText? = null

    var productCGSTLayout : TextInputLayout? = null
    var productCGSTEditText : TextInputEditText? = null

    var productSGSTLayout : TextInputLayout? = null
    var productSGSTEditText : TextInputEditText? = null

    var productVATLayout : TextInputLayout? = null
    var productVATEditText : TextInputEditText? = null

    var productCESSLayout : TextInputLayout? = null
    var productCESSEditText : TextInputEditText? = null

    var productTotalTaxLayout : TextInputLayout? = null
    var productTotalTaxEditText : TextInputEditText? = null

    var productTaxFinalPriceLayout : TextInputLayout? = null
    var productTaxFinalPriceEditText : TextInputEditText? = null

    var saveButton : Button? = null
    var backButton : ImageButton? = null
    private lateinit var productViewModel: ProductViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_product)
        imageView = findViewById(R.id.create_product_image)
        imageView?.setOnClickListener { onClickAddImage() }
        productStepView = findViewById(R.id.create_product_step_view) as StepView
        productInfoCard = findViewById(R.id.create_product_info_card)
        productPriceCard = findViewById(R.id.create_product_price_card)
        productTaxCard = findViewById(R.id.create_product_tax_card)
        saveButton = findViewById(R.id.create_product_save_btn)
        saveButton?.setOnClickListener { onClickSave() }
        productInfoCard?.visibility = View.VISIBLE
        productPriceCard?.visibility = View.GONE
        productTaxCard?.visibility = View.GONE
        saveButton?.text = "Next"
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

        productDiscountLayout = findViewById(R.id.create_product_discount_tel)
        productDiscountEditText = findViewById(R.id.create_product_discount_tiet)

        productFinalPriceLayout = findViewById(R.id.create_product_final_price_til)
        productFinalPriceEditText = findViewById(R.id.create_product_final_price_tiet)

        productIGSTLayout = findViewById(R.id.create_product_igst_til)
        productIGSTEditText = findViewById(R.id.create_product_igst_tiet)

        productCGSTLayout = findViewById(R.id.create_product_cgst_til)
        productCGSTEditText = findViewById(R.id.create_product_cgst_tiet)

        productSGSTLayout = findViewById(R.id.create_product_sgst_til)
        productSGSTEditText = findViewById(R.id.create_product_sgst_tiet)

        productVATLayout = findViewById(R.id.create_product_vat_til)
        productVATEditText = findViewById(R.id.create_product_vat_tiet)

        productCESSLayout = findViewById(R.id.create_product_cess_til)
        productCESSEditText = findViewById(R.id.create_product_cess_tiet)

        productTotalTaxLayout = findViewById(R.id.create_product_total_tax_til)
        productTotalTaxEditText = findViewById(R.id.create_product_total_tax_tiet)

        productTaxFinalPriceLayout = findViewById(R.id.create_product_tax_finial_price_til)
        productTaxFinalPriceEditText = findViewById(R.id.create_product_tax_finial_price_tiet)

        loadProductPreFilledData()
        backButton = findViewById(R.id.create_product_category_header_back)
        backButton?.setOnClickListener { onBackPressed() }

    }

    fun onClickAddImage(){
        getImageUrl {
            val picasso = Picasso.get()
            picasso.load(it).into(this.imageView)
        }
    }

    fun onClickSave(){
        when (stepsPosition) {
            0 -> {
                productInfoCard?.visibility = View.GONE
                productPriceCard?.visibility = View.VISIBLE
                productTaxCard?.visibility = View.GONE
                stepsPosition = 1
                productStepView?.done(false)
                productStepView?.go(stepsPosition, true)
                saveButton?.text = "Next"
            }
            1 -> {
                productInfoCard?.visibility = View.GONE
                productPriceCard?.visibility = View.GONE
                productTaxCard?.visibility = View.VISIBLE
                stepsPosition = 2
                productStepView?.done(false)
                productStepView?.go(stepsPosition, true)
                saveButton?.text = "Finish"
            }
            2 -> {
               saveProductInSever()
            }
        }
    }

    override fun onBackPressed() {
        when (stepsPosition) {
            0 -> {
                super.onBackPressed()
            }
            1 -> {
                productInfoCard?.visibility = View.VISIBLE
                productPriceCard?.visibility = View.GONE
                productTaxCard?.visibility = View.GONE
                stepsPosition = 0
                productStepView?.done(false)
                productStepView?.go(stepsPosition, true)
                saveButton?.text = "Next"
            }
            2 -> {
                productInfoCard?.visibility = View.GONE
                productPriceCard?.visibility = View.VISIBLE
                productTaxCard?.visibility = View.GONE
                stepsPosition = 1
                productStepView?.done(false)
                productStepView?.go(stepsPosition, true)
                saveButton?.text = "Next"
            }
            3 -> {
                productInfoCard?.visibility = View.GONE
                productPriceCard?.visibility = View.GONE
                productTaxCard?.visibility = View.VISIBLE
                stepsPosition = 2
                productStepView?.done(false)
                productStepView?.go(stepsPosition, true)
                saveButton?.text = "Next"
            }
            else -> {

            }
        }
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

    fun saveProductInSever(){
        val prdName = productNameEditText?.text.toString()
        val prdDescription = productDescriptionEditText?.text.toString()
        val prdMMRP = productMRPEditText?.text.toString()
        val prdCostPrice = productCostPriceEditText?.text.toString()
        val prdSalePrice = productPriceEditText?.text.toString()
        var mrp = 0F
        var costPrice = 0F
        var salePrice = 0F

        if(prdMMRP.isNotEmpty()){
            try {
                mrp = prdMMRP.toFloat()
            }catch (error:Error){
                toast("Please enter valid MRP")
            }
        }
        if(prdCostPrice.isNotEmpty()){
            try {
                costPrice = prdCostPrice.toFloat()
            }catch (error:Error){
                toast("Please enter valid Cost Price")
            }
        }
        if(prdSalePrice.isNotEmpty()){
            try {
                salePrice = prdSalePrice.toFloat()
            }catch (error:Error){
                toast("Please enter valid Sale Price")
            }
        }
        productViewModel.createNewProduct(prdName,prdDescription,selectedCategory!!.Id!!,selectedSubCategory!!.Id!!,mrp,costPrice,salePrice)
    }
}