package com.solute.ui.business.product.detail.productPriceDetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.solute.R
import com.utilitykit.Constants.Key.Companion.productName
import com.utilitykit.feature.product.handler.ProductHandler



class ProductPriceDetailsFragment : Fragment() {

    var productPrice : TextView? = null
    var productMRP : TextView? = null
    var productCostPrice : TextView? = null
    var productIGSTPercent : TextView? = null
    var productIGST : TextView? = null
    var productCGSTPercent : TextView? = null
    var productCGST : TextView? = null
    var productSGSTPercent : TextView? = null
    var productSGST : TextView? = null
    var productVATPercent : TextView? = null
    var productVAT : TextView? = null
    var productCESSPercent : TextView? = null
    var productCESS : TextView? = null
    var productDiscount : TextView? = null
    var productFinalPrice : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_product_price_details, container, false)
        productPrice = view.findViewById(R.id.fragment_product_detail_price)
        productMRP = view.findViewById(R.id.fragment_product_detail_mrp)
        productCostPrice = view.findViewById(R.id.fragment_product_detail_cost_price)
        productIGSTPercent = view.findViewById(R.id.fragment_product_detail_igst_percent)
        productIGST = view.findViewById(R.id.fragment_product_detail_igst)
        productCGSTPercent = view.findViewById(R.id.fragment_product_detail_cgst_percent)
        productCGST = view.findViewById(R.id.fragment_product_detail_cgst)
        productSGSTPercent = view.findViewById(R.id.fragment_product_detail_sgst_percent)
        productSGST = view.findViewById(R.id.fragment_product_detail_sgst)
        productVATPercent = view.findViewById(R.id.fragment_product_detail_vat_percent)
        productVAT = view.findViewById(R.id.fragment_product_detail_vat)
        productCESSPercent = view.findViewById(R.id.fragment_product_detail_cess_percent)
        productCESS = view.findViewById(R.id.fragment_product_detail_cess)
        productDiscount = view.findViewById(R.id.fragment_product_detail_discount)
        productFinalPrice = view.findViewById(R.id.fragment_product_detail_final_price)
        loadData()
        return view
    }

    fun loadData(){
        if(ProductHandler.shared().repository.selectedProduct != null && ProductHandler.shared().repository.selectedProduct.value != null){
            val product = ProductHandler.shared().repository.selectedProduct.value!!
            productPrice?.text = product.Price.toString()
            productMRP?.text = product.MRP.toString()
            productCostPrice?.text = product.CostPrice.toString()
            productIGSTPercent?.text = product.IGST.toString() + " %"
            if(product.IGST != null){
                productIGST?.text = "RS " + Math.round((product.IGST!!/product.Price!!)*100).toString()
            }
            productCGSTPercent?.text = product.CGST.toString() + " %"
            if(product.CGST != null){
                productCGST?.text = "RS " + Math.round((product.CGST!!/product.Price!!)*100).toString()
            }
            productSGSTPercent?.text = product.SGST.toString() + " %"
            if(product.SGST != null){
                productSGST?.text = "RS " + Math.round((product.SGST!!/product.Price!!)*100).toString()
            }
            productVATPercent?.text = product.VAT.toString() + " %"
            if(product.VAT != null){
                productVAT?.text = "RS " + Math.round((product.VAT!!/product.Price!!)*100).toString()
            }
            productCESSPercent?.text = product.CESS.toString() + " %"
            if(product.CESS != null){
                productCESS?.text = "RS " + Math.round((product.CESS!!/product.Price!!)*100).toString()
            }
            productDiscount?.text = product.Discount.toString()
            productFinalPrice?.text = product.FinalPrice.toString()
        }
    }
}