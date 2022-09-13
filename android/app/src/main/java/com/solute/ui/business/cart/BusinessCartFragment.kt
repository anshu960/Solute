package com.solute.ui.business.cart

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import com.solute.R
import com.solute.ui.business.customer.create.CreateCustomerActivity
import com.solute.ui.business.product.BusinessProductAdapter
import com.utilitykit.feature.cart.handler.CartHandler
import com.utilitykit.feature.cart.viewModel.CartViewModel
import com.utilitykit.feature.product.model.Product

/**
 * A simple [Fragment] subclass.
 * Use the [BusinessCartFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BusinessCartFragment : Fragment() {
    var recyclerView: RecyclerView? = null
    private var cartViewModel: CartViewModel? = null
    private var adapter: BusinessProductAdapter? = null
    var allProduct: ArrayList<Product> = ArrayList()

    var mrpValueTxt : TextView? = null
    var discountValueTxt: TextView? = null
    var subTotalValueTxt: TextView? = null
    var taxValueTxt: TextView? = null
    var instantDiscountValueTxt: TextInputEditText? = null
    var totalValueTxt: TextView? = null
    var saleButton: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = this.context?.let { BusinessProductAdapter(it, this, allProduct) }
        cartViewModel = CartHandler.shared().viewModel
        addListener()
    }

    fun reload() {
        if (CartHandler.shared().repository.cartProducts != null && CartHandler.shared().repository.cartProducts.value != null) {
            allProduct = CartHandler.shared().repository.cartProducts.value as ArrayList<Product>
        }
        this.recyclerView!!.layoutManager = LinearLayoutManager(this.context)
        adapter = this.context?.let { BusinessProductAdapter(it, this, allProduct) }
        this.recyclerView!!.adapter = this.adapter
        cartViewModel?.updatePricesInCart()
        if (CartHandler.shared().repository.instantDiscount != null && CartHandler.shared().repository.instantDiscount.value != null) {
            instantDiscountValueTxt?.setText(CartHandler.shared().repository.instantDiscount.value!!.toString())
        }
    }

    fun addListener() {
        cartViewModel?.cartProducts?.observe(this) {
            allProduct = it as ArrayList<Product>
            this.reload()
        }
        cartViewModel?.mrp?.observe(this) {
            this.mrpValueTxt?.text = it.toString()
        }
        cartViewModel?.discount?.observe(this) {
            this.discountValueTxt?.text = it.toString()
        }
        cartViewModel?.subtotal?.observe(this) {
            this.subTotalValueTxt?.text = it.toString()
        }
        cartViewModel?.tax?.observe(this) {
            this.taxValueTxt?.text = it.toString()
        }
        cartViewModel?.instantDiscount?.observe(this){
            cartViewModel?.updatePricesInCart()
        }
        cartViewModel?.totalAmount?.observe(this) {
            this.totalValueTxt?.text = it.toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_business_cart, container, false)
        mrpValueTxt = view.findViewById(R.id.business_fragment_mrp_txt)
        recyclerView = view.findViewById(R.id.business_fragment_recycler)
        discountValueTxt = view.findViewById(R.id.business_fragment_discount_txt)
        subTotalValueTxt = view.findViewById(R.id.business_fragment_subtotal_total_amount_txt)
        taxValueTxt = view.findViewById(R.id.business_fragment_tax_txt)
        instantDiscountValueTxt = view.findViewById(R.id.business_fragment_instant_discount_tit)
        totalValueTxt = view.findViewById(R.id.business_fragment_total_amount_txt)
        reload()
        instantDiscountValueTxt?.doOnTextChanged { text, start, before, count ->
            if (text != null && !text.isBlank()) {
                val newDiscount = text.trim().toString().toInt()
                cartViewModel?.updateInstantDiscount(newDiscount)
            }else{
                cartViewModel?.updateInstantDiscount(0)
            }
        }
        saleButton = view.findViewById(R.id.business_fragment_sale_btn)
        saleButton?.setOnClickListener { onClickSale() }
        return view
    }
    fun onClickSale(){
        val intent = Intent(this.context,CreateCustomerActivity::class.java)
        this.startActivity(intent)
//        cartViewModel?.createSaleAndGenerateReceipt()
    }
}