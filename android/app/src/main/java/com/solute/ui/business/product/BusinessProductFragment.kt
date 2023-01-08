package com.solute.ui.business.product

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.solute.R
import com.solute.ui.business.BusinessActivity
import com.solute.ui.business.barcode.scanner.BarCodeScannerActivity
import com.utilitykit.feature.business.handler.BusinessHandler
import com.utilitykit.feature.product.handler.ProductHandler
import com.utilitykit.feature.product.model.Product
import com.utilitykit.feature.product.viewModel.ProductViewModalFactory
import com.utilitykit.feature.product.viewModel.ProductViewModel
import java.security.Key


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
    var scanBtn : FloatingActionButton? = null
    var searchView : SearchView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = this.context?.let { BusinessProductAdapter(it,this ,allProduct) }
        productViewModel = ViewModelProvider(
            this,
            ProductViewModalFactory(ProductHandler.shared().repository)
        ).get(
            ProductViewModel::class.java
        )
        productViewModel.allProduct.observe(this) {
            allProduct = it as ArrayList<Product>
            this.reload()
        }
        ProductHandler.shared().setup(productViewModel)
        ProductHandler.shared().viewModel?.fetchAllProduct()
    }

    fun reload() {
        if(ProductHandler.shared().repository.productLiveData.value != null){
            allProduct = ProductHandler.shared().repository.productLiveData.value as ArrayList<Product>
        }
        this.recyclerView!!.layoutManager = GridLayoutManager(this.context,2)
        adapter = this.context?.let { BusinessProductAdapter(it,this ,allProduct) }
        this.recyclerView!!.adapter = this.adapter
    }
    fun search(query:String){
        allProduct = arrayListOf()
        if(ProductHandler.shared().repository.productLiveData.value != null){
            val unFilteredList= ProductHandler.shared().repository.productLiveData.value as ArrayList<Product>
            unFilteredList.forEach {
                if(it.Name?.contains(query) == true || it.Description?.contains(query) == true){
                    allProduct.add(it)
                }
            }
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
        val view = inflater.inflate(R.layout.fragment_business_sale, container, false)
        recyclerView = view.findViewById(R.id.fragment_business_product_recycler)
        scanBtn = view.findViewById(R.id.fragment_business_product_scan_btn)
        scanBtn?.setOnClickListener {onClickScan()}
        cartButton = view.findViewById(R.id.fragment_business_product_cart_btn)
        cartButton?.setOnClickListener { onClickCart() }
        searchView = view.findViewById(R.id.fragment_business_product_search)
        searchView?.isIconified = false
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                // do something on text submit
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                // do something when text changes
                if(newText != ""){
                    search(newText)
                }else{
                    reload()
                }
                return false
            }
        })
        productViewModel.loadProduct()
        reload()
        return view
    }

    fun onClickCart(){
        val activity = BusinessHandler.shared().activity as? BusinessActivity
        activity?.goToCart()
    }
    fun onClickScan(){
        val intent = Intent(requireContext(), BarCodeScannerActivity::class.java)
        intent.putExtra("OPERATION","SALE")
        startActivity(intent)
    }



}