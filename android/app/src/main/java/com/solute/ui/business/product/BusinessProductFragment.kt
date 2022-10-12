package com.solute.ui.business.product

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.solute.R
import com.solute.ui.business.BusinessActivity
import com.solute.ui.businessList.BusinessListAdapter
import com.utilitykit.feature.business.handler.BusinessHandler
import com.utilitykit.feature.product.handler.ProductHandler
import com.utilitykit.feature.product.model.Product
import com.utilitykit.feature.product.viewModel.ProductViewModalFactory
import com.utilitykit.feature.product.viewModel.ProductViewModel


/**
 * A simple [Fragment] subclass.
 * Use the [BusinessProductFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BusinessProductFragment : Fragment() {

    var recyclerView: RecyclerView? = null
    private lateinit var productViewModel: ProductViewModel
    private var adapter: BusinessProductAdapter? = null
    var allProduct: ArrayList<Product> = ArrayList()
    var cartButton : FloatingActionButton? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = this.context?.let { BusinessProductAdapter(it,this ,allProduct) }
        productViewModel = ViewModelProvider(
            this,
            ProductViewModalFactory(ProductHandler.shared().repository)
        ).get(
            ProductViewModel::class.java
        )
        productViewModel.allProduct.observe(this, {
            allProduct = it as ArrayList<Product>
            this.reload()
        })
        ProductHandler.shared().setup(productViewModel)
        ProductHandler.shared().fetchAllProduct()
    }

    fun reload() {
        if(ProductHandler.shared().repository.productLiveData.value != null){
            allProduct = ProductHandler.shared().repository.productLiveData.value as ArrayList<Product>
        }
        this.recyclerView!!.layoutManager = GridLayoutManager(this.context,2)
        adapter = this.context?.let { BusinessProductAdapter(it,this ,allProduct) }
        this.recyclerView!!.adapter = this.adapter
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_business_product, container, false)
        recyclerView = view.findViewById(R.id.fragment_business_product_recycler)
        cartButton = view.findViewById(R.id.fragment_business_product_cart_btn)
        cartButton?.setOnClickListener { onClickCart() }
        reload()
        return view
    }

    fun onClickCart(){
        val activity = requireActivity()
        if(activity is BusinessActivity){
            activity.goToCart()
        }
    }


}