package com.solute.ui.business.product.create

import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import androidx.activity.OnBackPressedCallback
import androidx.cardview.widget.CardView
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.friendly.framework.constants.KeyConstant
import com.friendly.framework.dataclass.FriendlyUser
import com.friendly.framework.feature.business.handler.BusinessHandler
import com.friendly.framework.feature.mediaFile.handler.MediaFileHandler
import com.friendly.framework.feature.product.handler.ProductHandler
import com.friendly.framework.feature.product.model.Product
import com.friendly.framework.feature.product.viewModel.ProductViewModalFactory
import com.friendly.framework.feature.product.viewModel.ProductViewModel
import com.friendly.framework.feature.productCategory.handler.ProductCategoryHandler
import com.friendly.framework.feature.productCategory.model.ProductCategory
import com.friendly.framework.feature.productSubCategory.handler.ProductSubCategoryHandler
import com.friendly.framework.feature.productSubCategory.model.ProductSubCategory
import com.friendly.frameworkt.feature.business.handler.AuthHandler
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.shuhart.stepview.StepView
import com.solute.app.App
import com.solute.R
import com.squareup.picasso.Picasso
import org.json.JSONObject

class CreateProductFragment : Fragment() {

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
    var product: Product? = null

    private var stepsPosition = 0

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
    var viewModal: ProductViewModel? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                onBackPressed()
            }
        })
        loadProductPreFilledData()
        populateExistingProduct()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_create_product, container, false)
        imageView = view.findViewById(R.id.create_product_image)
        imageView?.setOnClickListener { onClickAddImage() }
        productStepView = view.findViewById(R.id.create_product_step_view) as StepView
        productInfoCard = view.findViewById(R.id.create_product_info_card)
        productPriceCard = view.findViewById(R.id.create_product_price_card)
        productTaxCard = view.findViewById(R.id.create_product_tax_card)
        productFinishCard = view.findViewById(R.id.create_product_finish_card)
        saveButton = view.findViewById(R.id.create_product_save_btn)
        saveButton?.setOnClickListener { onClickSave() }
        productInfoCard?.visibility = View.VISIBLE
        productPriceCard?.visibility = View.GONE
        productTaxCard?.visibility = View.GONE
        productFinishCard?.visibility = View.GONE
        saveButton?.text = "Next"
        categoryLayout = view.findViewById(R.id.create_product_category_til)
        categoryEditText = view.findViewById(R.id.create_product_category_tiet)
        categoryEditText?.setOnClickListener { showCategorySellection() }
        subCategoryLayout = view.findViewById(R.id.create_product_sub_category_til)
        subCategoryEditText = view.findViewById(R.id.create_product_sub_category_tiet)
        subCategoryEditText?.setOnClickListener { showSubCategorySellection() }
        productNameLayout = view.findViewById(R.id.create_product_name_til)
        productNameEditText = view.findViewById(R.id.create_product_name_tiet)

        productDescriptionLayout = view.findViewById(R.id.create_product_description_til)
        productDescriptionEditText = view.findViewById(R.id.create_product_description_tiet)

        productMRPLayout = view.findViewById(R.id.create_product_mrp_til)
        productMRPEditText = view.findViewById(R.id.create_product_mrp_tiet)
        productMRPEditText?.addTextChangedListener { this.calculateTaxAndPrice() }

        productCostPriceLayout = view.findViewById(R.id.create_product_cost_price_til)
        productCostPriceEditText = view.findViewById(R.id.create_product_cost_price_tiet)
        productCostPriceEditText?.addTextChangedListener { this.calculateTaxAndPrice() }

        productPriceLayout = view.findViewById(R.id.create_product_price_til)
        productPriceEditText = view.findViewById(R.id.create_product_price_tiet)
        productPriceEditText?.addTextChangedListener { this.calculateTaxAndPrice() }

        productDiscountLayout = view.findViewById(R.id.create_product_discount_tel)
        productDiscountEditText = view.findViewById(R.id.create_product_discount_tiet)

        productFinalPriceLayout = view.findViewById(R.id.create_product_final_price_til)
        productFinalPriceEditText = view.findViewById(R.id.create_product_final_price_tiet)

        productIGSTLayout = view.findViewById(R.id.create_product_igst_til)
        productIGSTEditText = view.findViewById(R.id.create_product_igst_tiet)
        productIGSTEditText?.addTextChangedListener { this.calculateTaxAndPrice() }

        productCGSTLayout = view.findViewById(R.id.create_product_cgst_til)
        productCGSTEditText = view.findViewById(R.id.create_product_cgst_tiet)
        productCGSTEditText?.addTextChangedListener { this.calculateTaxAndPrice() }

        productSGSTLayout = view.findViewById(R.id.create_product_sgst_til)
        productSGSTEditText = view.findViewById(R.id.create_product_sgst_tiet)
        productSGSTEditText?.addTextChangedListener { this.calculateTaxAndPrice() }

        productVATLayout = view.findViewById(R.id.create_product_vat_til)
        productVATEditText = view.findViewById(R.id.create_product_vat_tiet)
        productVATEditText?.addTextChangedListener { this.calculateTaxAndPrice() }

        productCESSLayout = view.findViewById(R.id.create_product_cess_til)
        productCESSEditText = view.findViewById(R.id.create_product_cess_tiet)
        productCESSEditText?.addTextChangedListener { this.calculateTaxAndPrice() }

        productTotalTaxLayout = view.findViewById(R.id.create_product_total_tax_til)
        productTotalTaxEditText = view.findViewById(R.id.create_product_total_tax_tiet)

        taxIncludedCheckBox = view.findViewById(R.id.create_product_is_tax_included_check_box)
        taxIncludedCheckBox?.isSelected = true

        productTaxFinalPriceLayout = view.findViewById(R.id.create_product_tax_finial_price_til)
        productTaxFinalPriceEditText = view.findViewById(R.id.create_product_tax_finial_price_tiet)

        return view
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
            productTotalTaxEditText?.setText(getString(R.string.price_string,product!!.Tax))
            productDiscountEditText?.setText(getString(R.string.price_string,product!!.Discount))
            productFinalPriceEditText?.setText(getString(R.string.price_string,product!!.FinalPrice))
            productTaxFinalPriceEditText?.setText(getString(R.string.price_string,product!!.FinalPrice))
            if(product!!.Image.isNotEmpty()){
                picasso.load(product!!.Image.first()).into(imageView)
            }
            if(ProductCategoryHandler.shared().repository.allCategory.value != null){
                ProductCategoryHandler.shared().repository.allCategory.value!!.forEach {
                    if(it.Id == product!!.CategoryID){
                        categoryEditText?.setText(it.Name)
                    }
                }
            }
            if(ProductSubCategoryHandler.shared().repository.allSubCategory.value != null){
                ProductSubCategoryHandler.shared().repository.allSubCategory.value!!.forEach {
                    if(it.Id == product!!.SubCategoryID){
                        subCategoryEditText?.setText(it.Name)
                    }
                }
            }
        }
        if(selectedCategory != null){
            categoryEditText?.setText(selectedCategory?.Name)
            subCategoryEditText?.setText(selectedSubCategory?.Name)
        }
    }

    fun onClickAddImage(){
        App.shared().mainActivity?.getImageUrl {
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
                saveButton?.text = getString(R.string.next)
                calculateTaxAndPrice()
            }
            1 -> {
                productInfoCard?.visibility = View.GONE
                productPriceCard?.visibility = View.GONE
                productTaxCard?.visibility = View.VISIBLE
                stepsPosition = 2
                productStepView?.done(false)
                productStepView?.go(stepsPosition, true)
                saveButton?.text = getString(R.string.finish)
                calculateTaxAndPrice()
            }
            2 -> {
               saveProductInSever()
            }
        }
    }

     fun onBackPressed() {
        when (stepsPosition) {
            0 -> {
                App.shared().mainActivity?.runOnUiThread {
                    App.shared().mainActivity?.navController?.navigate(R.id.inventory_product)
                }
            }
            1 -> {
                productInfoCard?.visibility = View.VISIBLE
                productPriceCard?.visibility = View.GONE
                productTaxCard?.visibility = View.GONE
                stepsPosition = 0
                productStepView?.done(false)
                productStepView?.go(stepsPosition, true)
                saveButton?.text = getString(R.string.next)
            }
            2 -> {
                productInfoCard?.visibility = View.GONE
                productPriceCard?.visibility = View.VISIBLE
                productTaxCard?.visibility = View.GONE
                stepsPosition = 1
                productStepView?.done(false)
                productStepView?.go(stepsPosition, true)
                saveButton?.text = getString(R.string.next)
            }
            3 -> {
                productInfoCard?.visibility = View.GONE
                productPriceCard?.visibility = View.GONE
                productTaxCard?.visibility = View.VISIBLE
                stepsPosition = 2
                productStepView?.done(false)
                productStepView?.go(stepsPosition, true)
                saveButton?.text = getString(R.string.next)
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
                App.shared().mainActivity?.toast("Please enter valid MRP")
            }
        }
        if(prdCostPrice.isNotEmpty()){
            try {
                costPrice = prdCostPrice.toFloat()
            }catch (error:Error){
                App.shared().mainActivity?.toast("Please enter valid Cost Price")
            }
        }
        if(prdPrice.isNotEmpty()){
            try {
                price = prdPrice.toFloat()
            }catch (error:Error){
                App.shared().mainActivity?.toast("Please enter valid Sale Price")
            }
        }


        if(prdSGST.isNotEmpty()){
            try {
                sgst = prdSGST.toFloat()
            }catch (error:Error){
                App.shared().mainActivity?.toast("Please enter valid SGST")
            }
        }

        if(prdCGST.isNotEmpty()){
            try {
                cgst = prdCGST.toFloat()
            }catch (error:Error){
                App.shared().mainActivity?.toast("Please enter valid CGST")
            }
        }

        if(prdIGST.isNotEmpty()){
            try {
                igst = prdIGST.toFloat()
            }catch (error:Error){
                App.shared().mainActivity?.toast("Please enter valid IGST")
            }
        }

        if(prdVAT.isNotEmpty()){
            try {
                vat = prdVAT.toFloat()
            }catch (error:Error){
                App.shared().mainActivity?.toast("Please enter valid VAT")
            }
        }

        if(prdCESS.isNotEmpty()){
            try {
                cess = prdCESS.toFloat()
            }catch (error:Error){
                App.shared().mainActivity?.toast("Please enter valid CESS")
            }
        }

        taxApplied += (price*(igst/100))
        taxApplied += (price*(cgst/100))
        taxApplied += (price*(sgst/100))
        taxApplied += (price*(vat/100))
        taxApplied += (price*(cess/100))
        totalTax = taxApplied
        productTotalTaxEditText?.setText(getString(R.string.price_string,totalTax))
        if(isTaxIncluded){
            finalPrice = price
        }else{
            finalPrice = price + totalTax
        }
        discount = mrp - finalPrice
        if(discount < 0){
            productDiscountEditText?.setText(getString(R.string.price_string,0F))
        }else{
            productDiscountEditText?.setText(getString(R.string.price_string,discount))
        }
        productFinalPriceEditText?.setText(getString(R.string.price_string,finalPrice))
        productTaxFinalPriceEditText?.setText(getString(R.string.price_string,finalPrice))

    }

    fun loadProductPreFilledData(){
        //Product Model
        ProductHandler.shared().activity = App.shared().mainActivity
    }


    fun showCategorySellection(){
        ProductCategoryHandler.shared().onSelectCategory={
            Handler(Looper.getMainLooper()).postDelayed({
                this.selectedCategory = it
                categoryEditText?.setText(it.Name)
                ProductCategoryHandler.shared().repository.selectedCategoryLiveData.postValue(it)
            }, 500)
        }
        ProductCategoryHandler.shared().onCreateNewCategory={
            activity?.runOnUiThread {
                activity?.onBackPressed()
            }
            Handler(Looper.getMainLooper()).postDelayed({
                this.selectedCategory = it
                categoryEditText?.setText(it.Name)
                ProductCategoryHandler.shared().repository.selectedCategoryLiveData.postValue(it)
            }, 500)
        }
        App.shared().mainActivity?.navController?.navigate(R.id.business_select_category)
    }

    fun showSubCategorySellection(){
        App.shared().mainActivity?.navController?.navigate(R.id.business_select_product_sub_category)
        ProductSubCategoryHandler.shared().onSelectSubCategory={
            Handler(Looper.getMainLooper()).postDelayed({
                this.selectedSubCategory = it
                subCategoryEditText?.setText(it.Name)
            }, 500)
        }
        ProductSubCategoryHandler.shared().onCreateNewSubCategory={
            activity?.runOnUiThread {
                App.shared().mainActivity?.onBackPressed()
            }
            Handler(Looper.getMainLooper()).postDelayed({
                this.selectedSubCategory = it
                subCategoryEditText?.setText(it.Name)
            }, 500)
        }
    }

    fun saveProductInSever(){
        if(prdName == ""){
            App.shared().mainActivity?.alert("Oops!","Please enter name of the Product")
            return
        }
        val request = JSONObject()
        val user = FriendlyUser()
        val business = BusinessHandler.shared().repository.business
        request.put(KeyConstant.userId, user._id)
        request.put(KeyConstant.businessID, business.value?.Id)
        request.put(KeyConstant.categoryId, selectedCategory?.Id)
        request.put(KeyConstant.subCategoryID, selectedSubCategory?.Id)
        request.put(KeyConstant.name, prdName)
        request.put(KeyConstant.description, prdDescription)
        request.put(KeyConstant.manageInventory, true)
        request.put(KeyConstant.taxIncluded, isTaxIncluded)
        request.put(KeyConstant.SGST, sgst)
        request.put(KeyConstant.CGST, cgst)
        request.put(KeyConstant.IGST, igst)
        request.put(KeyConstant.CESS, cess)
        request.put(KeyConstant.VAT, vat)
        request.put(KeyConstant.discount,discount)
        request.put(KeyConstant.MRP, mrp)
        request.put(KeyConstant.price, (price))
        request.put(KeyConstant.costPrice, (costPrice))
        request.put(KeyConstant.finalPrice, finalPrice)
        request.put(KeyConstant.tax, totalTax)
        request.put(KeyConstant.deviceId, AuthHandler.shared().deviceId)
        if(this.product != null && !this.product!!.Id.isEmpty()){
            ProductHandler.shared().onUpdateExistingProductCallBack = {
                onCreateNewProduct(it)
            }
            request.put(KeyConstant._id,product!!.Id)
            ProductHandler.shared().viewModel?.updateProduct(request)
        }else{
            ProductHandler.shared().onCreateProductCallBack = {
                onCreateNewProduct(it)
            }
            ProductHandler.shared().viewModel?.createNewProduct(request)
        }
    }
    fun onCreateNewProduct(product:Product?){
        if(product != null){
            App.shared().mainActivity?.runOnUiThread {
                if(this.fileUri != null){
                    App.shared().mainActivity?.toastLong("Product Created, Uploading Image")
                    uploadImageInFirebase(product)
                }else{
                    this.onBackPressed()
                    this.onBackPressed()
                    this.onBackPressed()
                    this.activity?.onBackPressed()
                }
            }
        }else{
            App.shared().mainActivity?.runOnUiThread  {
                App.shared().mainActivity?.toast("Oops! Something went wrong")
            }
        }
    }


    fun uploadImageInFirebase(product: Product){
        if (fileUri != null && BusinessHandler.shared().repository.business.value != null) {
            val fileName = product.Id +".png"
            val imageRef = App.shared().productImageRef?.child(BusinessHandler.shared().repository.business.value!!.Id)?.child(product.Id!!)?.child(fileName)
            imageRef?.putFile(fileUri!!)?.addOnSuccessListener { taskSnapshot ->
                taskSnapshot.storage.downloadUrl.addOnSuccessListener {
                    MediaFileHandler.shared().onCreateNew={
                        App.shared().mainActivity?.runOnUiThread  {
                            this.stepsPosition = 0
                            this.onBackPressed()
                            App.shared().mainActivity?.toast("Image Updated Successfully")
                        }
                    }
                    val imageUrl = it.toString()
                    ProductHandler.shared().viewModel?.updateProductImage(product,imageUrl)
                }
            }?.addOnFailureListener { e ->
                print(e.message)
                App.shared().mainActivity?.runOnUiThread {
                    App.shared().mainActivity?.toast("Oops! Failed to upload image at the moment")
                }
            }
        }else{
            activity?.onBackPressed()
        }
    }
}