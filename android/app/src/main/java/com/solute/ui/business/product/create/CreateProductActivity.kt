package com.solute.ui.business.product.create

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.shuhart.stepview.StepView
import com.solute.App
import com.solute.R
import com.squareup.picasso.Picasso
import com.utilitykit.Constants.Key
import com.utilitykit.UtilityActivity
import com.utilitykit.dataclass.User
import com.utilitykit.feature.business.handler.BusinessHandler
import com.utilitykit.feature.product.handler.ProductHandler
import com.utilitykit.feature.product.model.Product
import com.utilitykit.feature.product.viewModel.ProductViewModalFactory
import com.utilitykit.feature.product.viewModel.ProductViewModel
import com.utilitykit.feature.productCategory.handler.ProductCategoryHandler
import com.utilitykit.feature.productCategory.model.ProductCategory
import com.utilitykit.feature.productSubCategory.handler.ProductSubCategoryHandler
import com.utilitykit.feature.productSubCategory.model.ProductSubCategory
import org.json.JSONObject
import java.lang.Error

class CreateProductActivity : UtilityActivity() {
    val picasso = Picasso.get()
    var prdName = ""
    var prdDescription = ""
    var mrp = 0F
    var costPrice = 0F
    var price = 0F
    var discount = 0F
    var igst = 0F
    var cgst = 0F
    var sgst = 0F
    var vat = 0F
    var cess = 0F
    var totalTax = 0F
    var isTaxIncluded = true
    var finalPrice = 0F
    var fileUri : Uri? = null
    var product:Product? = null

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
    var productFinishCard : CardView? = null

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

    var taxIncludedCheckBox : CheckBox? = null

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
        productFinishCard = findViewById(R.id.create_product_finish_card)
        saveButton = findViewById(R.id.create_product_save_btn)
        saveButton?.setOnClickListener { onClickSave() }
        productInfoCard?.visibility = View.VISIBLE
        productPriceCard?.visibility = View.GONE
        productTaxCard?.visibility = View.GONE
        productFinishCard?.visibility = View.GONE
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
        productMRPEditText?.addTextChangedListener { this.calculateTaxAndPrice() }

        productCostPriceLayout = findViewById(R.id.create_product_cost_price_til)
        productCostPriceEditText = findViewById(R.id.create_product_cost_price_tiet)
        productCostPriceEditText?.addTextChangedListener { this.calculateTaxAndPrice() }

        productPriceLayout = findViewById(R.id.create_product_price_til)
        productPriceEditText = findViewById(R.id.create_product_price_tiet)
        productPriceEditText?.addTextChangedListener { this.calculateTaxAndPrice() }

        productDiscountLayout = findViewById(R.id.create_product_discount_tel)
        productDiscountEditText = findViewById(R.id.create_product_discount_tiet)

        productFinalPriceLayout = findViewById(R.id.create_product_final_price_til)
        productFinalPriceEditText = findViewById(R.id.create_product_final_price_tiet)

        productIGSTLayout = findViewById(R.id.create_product_igst_til)
        productIGSTEditText = findViewById(R.id.create_product_igst_tiet)
        productIGSTEditText?.addTextChangedListener { this.calculateTaxAndPrice() }

        productCGSTLayout = findViewById(R.id.create_product_cgst_til)
        productCGSTEditText = findViewById(R.id.create_product_cgst_tiet)
        productCGSTEditText?.addTextChangedListener { this.calculateTaxAndPrice() }

        productSGSTLayout = findViewById(R.id.create_product_sgst_til)
        productSGSTEditText = findViewById(R.id.create_product_sgst_tiet)
        productSGSTEditText?.addTextChangedListener { this.calculateTaxAndPrice() }

        productVATLayout = findViewById(R.id.create_product_vat_til)
        productVATEditText = findViewById(R.id.create_product_vat_tiet)
        productVATEditText?.addTextChangedListener { this.calculateTaxAndPrice() }

        productCESSLayout = findViewById(R.id.create_product_cess_til)
        productCESSEditText = findViewById(R.id.create_product_cess_tiet)
        productCESSEditText?.addTextChangedListener { this.calculateTaxAndPrice() }

        productTotalTaxLayout = findViewById(R.id.create_product_total_tax_til)
        productTotalTaxEditText = findViewById(R.id.create_product_total_tax_tiet)

        taxIncludedCheckBox = findViewById(R.id.create_product_is_tax_included_check_box)
        taxIncludedCheckBox?.isSelected = true

        productTaxFinalPriceLayout = findViewById(R.id.create_product_tax_finial_price_til)
        productTaxFinalPriceEditText = findViewById(R.id.create_product_tax_finial_price_tiet)

        loadProductPreFilledData()
        backButton = findViewById(R.id.create_product_category_header_back)
        backButton?.setOnClickListener { onBackPressed() }
        populateExistingProduct()
    }

    fun populateExistingProduct(){
        if(ProductHandler.shared().repository.selectedProduct.value != null){
            this.product = ProductHandler.shared().repository.selectedProduct.value!!
            productNameEditText?.setText(product!!.Name!!)
            productDescriptionEditText?.setText(product!!.Description!!)
            productMRPEditText?.setText(product!!.MRP!!.toString())
            productCostPriceEditText?.setText(product!!.CostPrice!!.toString())
            productPriceEditText?.setText(product!!.Price!!.toString())
            productIGSTEditText?.setText(product!!.IGST!!.toString())
            productCGSTEditText?.setText(product!!.CGST!!.toString())
            productSGSTEditText?.setText(product!!.SGST!!.toString())
            productVATEditText?.setText(product!!.VAT!!.toString())
            productCESSEditText?.setText(product!!.CESS!!.toString())
            productTotalTaxEditText?.setText("RS " + product!!.Tax.toString())
            productDiscountEditText?.setText("RS " + product!!.Discount)
            productFinalPriceEditText?.setText("RS " + product!!.FinalPrice)
            productTaxFinalPriceEditText?.setText("RS " + product!!.FinalPrice)
            if(product!!.Image.isNotEmpty()){
                picasso.load(product!!.Image.first()).into(imageView)
            }
        }
    }

    fun onClickAddImage(){
        getImageUrl {
            fileUri = it
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
                calculateTaxAndPrice()
            }
            1 -> {
                productInfoCard?.visibility = View.GONE
                productPriceCard?.visibility = View.GONE
                productTaxCard?.visibility = View.VISIBLE
                stepsPosition = 2
                productStepView?.done(false)
                productStepView?.go(stepsPosition, true)
                saveButton?.text = "Finish"
                calculateTaxAndPrice()
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

    fun calculateTaxAndPrice(){
         prdName = productNameEditText?.text.toString()
         prdDescription = productDescriptionEditText?.text.toString()
        val prdMMRP = productMRPEditText?.text.toString()
        val prdCostPrice = productCostPriceEditText?.text.toString()
        val prdPrice = productPriceEditText?.text.toString()
        val prdIGST = productIGSTEditText?.text.toString()
        val prdCGST = productCGSTEditText?.text.toString()
        val prdSGST = productSGSTEditText?.text.toString()
        val prdVAT = productVATEditText?.text.toString()
        val prdCESS = productCESSEditText?.text.toString()
        var taxApplied = 0F

        if(taxIncludedCheckBox != null){
            isTaxIncluded = taxIncludedCheckBox!!.isSelected
        }

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
        if(prdPrice.isNotEmpty()){
            try {
                price = prdPrice.toFloat()
            }catch (error:Error){
                toast("Please enter valid Sale Price")
            }
        }


        if(prdSGST.isNotEmpty()){
            try {
                sgst = prdSGST.toFloat()
            }catch (error:Error){
                toast("Please enter valid SGST")
            }
        }

        if(prdCGST.isNotEmpty()){
            try {
                cgst = prdCGST.toFloat()
            }catch (error:Error){
                toast("Please enter valid CGST")
            }
        }

        if(prdIGST.isNotEmpty()){
            try {
                igst = prdIGST.toFloat()
            }catch (error:Error){
                toast("Please enter valid IGST")
            }
        }

        if(prdVAT.isNotEmpty()){
            try {
                vat = prdVAT.toFloat()
            }catch (error:Error){
                toast("Please enter valid VAT")
            }
        }

        if(prdCESS.isNotEmpty()){
            try {
                cess = prdCESS.toFloat()
            }catch (error:Error){
                toast("Please enter valid CESS")
            }
        }

        taxApplied += (price*(igst/100))
        taxApplied += (price*(cgst/100))
        taxApplied += (price*(sgst/100))
        taxApplied += (price*(vat/100))
        taxApplied += (price*(cess/100))
        totalTax = taxApplied
        productTotalTaxEditText?.setText("RS " + totalTax.toString())
        if(isTaxIncluded){
            finalPrice = price
        }else{
            finalPrice = price + totalTax
        }
        discount = mrp - finalPrice
        if(discount < 0){
            productDiscountEditText?.setText("RS 0")
        }else{
            productDiscountEditText?.setText("RS " + discount)
        }
        productFinalPriceEditText?.setText("RS " + finalPrice)
        productTaxFinalPriceEditText?.setText("RS " + finalPrice)

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
        if(prdName == ""){
            alert("Oops!","Please enter name of the Product")
            return
        }
        val request = JSONObject()
        val user = User()
        if(BusinessHandler.shared().repository.business != null) {
            val business = BusinessHandler.shared().repository.business
            request.put(Key.userId, user._id)
            request.put(Key.businessID, business!!.Id)
            request.put(Key.categoryId, selectedCategory!!.Id!!)
            request.put(Key.subCategoryID, selectedSubCategory!!.Id!!)
            request.put(Key.name, prdName)
            request.put(Key.description, prdDescription)
            request.put(Key.manageInventory, true)
            request.put(Key.taxIncluded, isTaxIncluded)
            request.put(Key.SGST, sgst)
            request.put(Key.CGST, cgst)
            request.put(Key.IGST, igst)
            request.put(Key.CESS, cess)
            request.put(Key.VAT, vat)
            request.put(Key.discount,discount)
            request.put(Key.MRP, mrp)
            request.put(Key.price, (price))
            request.put(Key.costPrice, (costPrice))
            request.put(Key.finalPrice, finalPrice)
            request.put(Key.tax, totalTax)
            if(this.product != null){
                ProductHandler.shared().onUpdateExistingProductCallBack = {
                    onCreateNewProduct(it)
                }
                request.put(Key._id,product!!.Id)
                ProductHandler.shared().productViewModel?.updateProduct(request)
            }else{
                ProductHandler.shared().onCreateProductCallBack = {
                    onCreateNewProduct(it)
                }
                ProductHandler.shared().productViewModel?.createNewProduct(request)
            }
        }
    }
    fun onCreateNewProduct(product:Product?){
        if(product != null){
            this.runOnUiThread {
                if(this.fileUri != null){
                    toastLong("Product Created, Uploading Image")
                    uploadImageInFirebase(product!!)
                }else{
                    this.onBackPressed()
                    this.onBackPressed()
                    this.onBackPressed()
                }
            }
        }else{
            this.runOnUiThread {
                toast("Oops! Something went wrong")
            }
        }
    }


    fun uploadImageInFirebase(product: Product){
        if (fileUri != null && BusinessHandler.shared().repository.business != null && BusinessHandler.shared().repository.business != null) {
            val fileName = product.Id +".png"
            val imageRef = App.applicationContext().productImageRef?.child(BusinessHandler.shared().repository.business!!.Id!!)?.child(product.Id!!)?.child(fileName)
            imageRef?.putFile(fileUri!!)?.addOnSuccessListener { taskSnapshot ->
                taskSnapshot.storage.downloadUrl.addOnSuccessListener {
                    ProductHandler.shared().onUpdateProductImageCallBack={updatedPrd->
                            this.runOnUiThread {
                                if(updatedPrd != null && updatedPrd!!.Id != null){
                                    this.onBackPressed()
                                    this.onBackPressed()
                                    this.onBackPressed()
                                    toast("Image Updated Successfully")
                                }else{
                                    toast("Image couldn't be updated")
                                }
                            }
                        }
                    val imageUrl = it.toString()
                    ProductHandler.shared().productViewModel?.updateProductImage(product,imageUrl)
                }
            }?.addOnFailureListener { e ->
                print(e.message)
                this.runOnUiThread {
                    toast("Oops! Failed to upload image at the moment")
                }
            }
        }else{
            super.onBackPressed()
        }
    }
}