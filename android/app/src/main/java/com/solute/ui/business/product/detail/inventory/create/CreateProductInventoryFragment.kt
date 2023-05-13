package com.solute.ui.business.product.detail.inventory.create

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import androidx.core.widget.addTextChangedListener
import com.friendly.framework.constants.KeyConstant
import com.friendly.framework.dataclass.FriendlyUser
import com.friendly.framework.feature.business.handler.BusinessHandler
import com.friendly.framework.feature.product.handler.ProductHandler
import com.friendly.framework.feature.product.model.Product
import com.friendly.frameworkt.feature.business.handler.AuthHandler
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.solute.R
import com.solute.app.App
import com.solute.app.ToastService
import org.json.JSONObject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CreateProductInventoryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CreateProductInventoryFragment : Fragment() {
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
    var isTaxIncluded = false
    var finalPrice = 0F
    var fileUri : Uri? = null
    var product: Product? = null

    private var stepsPosition = 0


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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_create_product_inventory, container, false)
        saveButton = view.findViewById(R.id.save_btn)
        saveButton?.setOnClickListener { onClickSave() }

        saveButton?.text = "Next"
        productNameLayout = view.findViewById(R.id.create_product_inventory_name_til)
        productNameEditText = view.findViewById(R.id.create_product_inventory_name_ti)

        productDescriptionLayout = view.findViewById(R.id.create_product_inventory_description_til)
        productDescriptionEditText = view.findViewById(R.id.create_product_inventory_description_ti)

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
        taxIncludedCheckBox?.isSelected = false
        taxIncludedCheckBox?.setOnCheckedChangeListener { buttonView, isChecked ->
            isTaxIncluded = isChecked
            calculateTaxAndPrice()
        }
        return view
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

        if(prdMMRP.isNotEmpty()){
            try {
                mrp = prdMMRP.toFloat()
            }catch (error:Error){
                ToastService.shared().toast("Please enter valid MRP")
            }
        }
        if(prdCostPrice.isNotEmpty()){
            try {
                costPrice = prdCostPrice.toFloat()
            }catch (error:Error){
                ToastService.shared().toast("Please enter valid Cost Price")
            }
        }
        if(prdPrice.isNotEmpty()){
            try {
                price = prdPrice.toFloat()
            }catch (error:Error){
                ToastService.shared().toast("Please enter valid Sale Price")
            }
        }


        if(prdSGST.isNotEmpty()){
            try {
                sgst = prdSGST.toFloat()
            }catch (error:Error){
                ToastService.shared().toast("Please enter valid SGST")
            }
        }

        if(prdCGST.isNotEmpty()){
            try {
                cgst = prdCGST.toFloat()
            }catch (error:Error){
                ToastService.shared().toast("Please enter valid CGST")
            }
        }

        if(prdIGST.isNotEmpty()){
            try {
                igst = prdIGST.toFloat()
            }catch (error:Error){
                ToastService.shared().toast("Please enter valid IGST")
            }
        }

        if(prdVAT.isNotEmpty()){
            try {
                vat = prdVAT.toFloat()
            }catch (error:Error){
                ToastService.shared().toast("Please enter valid VAT")
            }
        }

        if(prdCESS.isNotEmpty()){
            try {
                cess = prdCESS.toFloat()
            }catch (error:Error){
                ToastService.shared().toast("Please enter valid CESS")
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

    fun onClickSave(){
        if(prdName == ""){
            App.shared().mainActivity?.alert("Oops!","Please enter name of the Product")
            return
        }
        val request = JSONObject()
        val user = FriendlyUser()
        val business = BusinessHandler.shared().repository.business
        request.put(KeyConstant.userId, user._id)
        request.put(KeyConstant.businessID, business.value?.Id)
        request.put(KeyConstant.name, prdName)
        request.put(KeyConstant.description, prdDescription)
        request.put(KeyConstant.manageInventory, true)
        request.put(KeyConstant.taxIncluded, isTaxIncluded)
        val productPrice = JSONObject()
        productPrice.put(KeyConstant.SGST, sgst)
        productPrice.put(KeyConstant.CGST, cgst)
        productPrice.put(KeyConstant.IGST, igst)
        productPrice.put(KeyConstant.CESS, cess)
        productPrice.put(KeyConstant.VAT, vat)
        productPrice.put(KeyConstant.discount,discount)
        productPrice.put(KeyConstant.MRP, mrp)
        productPrice.put(KeyConstant.costPrice, (costPrice))
        productPrice.put(KeyConstant.finalPrice, finalPrice)
        productPrice.put(KeyConstant.tax, totalTax)
        productPrice.put(KeyConstant.price,price)
        request.put(KeyConstant.productPrice, productPrice)
        request.put(KeyConstant.deviceId, AuthHandler.shared().deviceId)
        if(this.product != null && !this.product!!.Id.isEmpty()){
            ProductHandler.shared().onUpdateExistingProductCallBack = {

            }
            request.put(KeyConstant._id,product!!.Id)
            ProductHandler.shared().viewModel?.updateProduct(request)
        }else{
            ProductHandler.shared().onCreateProductCallBack = {

            }
            ProductHandler.shared().viewModel?.createNewProduct(request)
        }
    }
}