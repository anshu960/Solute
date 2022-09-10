package com.solute.ui.business.cart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.solute.R
import com.solute.ui.business.product.BusinessProductAdapter
import com.utilitykit.feature.cart.helper.CartHelper
import com.utilitykit.feature.cart.viewModel.CartViewModalFactory
import com.utilitykit.feature.cart.viewModel.CartViewModel
import com.utilitykit.feature.product.handler.ProductHandler
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = this.context?.let { BusinessProductAdapter(it,this ,allProduct) }
        cartViewModel = CartHelper.shared().viewModel
        cartViewModel?.cartProducts?.observe(this) {
            allProduct = it as ArrayList<Product>
            this.reload()
        }
    }

    fun reload() {
        if(CartHelper.shared().repository.cartProducts != null && CartHelper.shared().repository.cartProducts.value != null){
            allProduct = CartHelper.shared().repository.cartProducts.value as ArrayList<Product>
        }
        this.recyclerView!!.layoutManager = LinearLayoutManager(this.context)
        adapter = this.context?.let { BusinessProductAdapter(it,this ,allProduct) }
        this.recyclerView!!.adapter = this.adapter
    }

    fun populateData(){

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_business_cart, container, false)
        recyclerView = view.findViewById(R.id.business_fragment_recycler)
        reload()
        return view
    }
}