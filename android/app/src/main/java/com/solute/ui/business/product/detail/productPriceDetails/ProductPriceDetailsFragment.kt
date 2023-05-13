package com.solute.ui.business.product.detail.productPriceDetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.friendly.framework.feature.product.handler.ProductHandler
import com.solute.R




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
        if(ProductHandler.shared().repository.selectedProduct.value != null){
            val product = ProductHandler.shared().repository.selectedProduct.value!!
            productPrice?.text = product.ProductPrice?.Price.toString()
            productMRP?.text = product.ProductPrice?.MRP.toString()
            productCostPrice?.text = product.ProductPrice?.CostPrice.toString()
            productIGSTPercent?.text = product.ProductPrice?.IGST.toString() + " %"
            if(product.ProductPrice?.IGST != null){
                productIGST?.text = "RS " + Math.round((product.ProductPrice?.IGST!!/100)*product.ProductPrice!!.Price!!).toString()
            }
            productCGSTPercent?.text = product.ProductPrice?.CGST.toString() + " %"
            if(product.ProductPrice?.CGST != null){
                productCGST?.text = "RS " + Math.round((product.ProductPrice?.CGST!!/100)*product.ProductPrice?.Price!!).toString()
            }
            productSGSTPercent?.text = product.ProductPrice?.SGST.toString() + " %"
            if(product.ProductPrice?.SGST != null){
                productSGST?.text = "RS " + Math.round((product.ProductPrice?.SGST!!/100)*product.ProductPrice?.Price!!).toString()
            }
            productVATPercent?.text = product.ProductPrice?.VAT.toString() + " %"
            if(product.ProductPrice?.VAT != null){
                productVAT?.text = "RS " + Math.round((product.ProductPrice?.VAT!!/100)*product.ProductPrice?.Price!!).toString()
            }
            productCESSPercent?.text = product.ProductPrice?.CESS.toString() + " %"
            if(product.ProductPrice?.CESS != null){
                productCESS?.text = "RS " + Math.round((product.ProductPrice?.CESS!!/100)*product.ProductPrice?.Price!!).toString()
            }
            productDiscount?.text = product.ProductPrice?.Discount.toString()
            productFinalPrice?.text = product.ProductPrice?.FinalPrice.toString()
        }
    }
}