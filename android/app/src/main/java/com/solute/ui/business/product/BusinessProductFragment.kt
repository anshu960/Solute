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
import com.friendly.framework.feature.business.handler.BusinessHandler
import com.friendly.framework.feature.product.handler.ProductHandler
import com.friendly.framework.feature.product.model.Product
import com.friendly.framework.feature.product.viewModel.ProductViewModalFactory
import com.friendly.framework.feature.product.viewModel.ProductViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.solute.R
import com.solute.app.App
import com.solute.ui.business.barcode.scanner.BarCodeScannerActivity


/**
 * A simple [Fragment] subclass.
 * Use the [BusinessProductFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BusinessProductFragment : Fragment() {

    var recyclerView: RecyclerView? = null
    private var adapter: BusinessProductAdapter? = null
    var allProduct: ArrayList<Product> = ArrayList()
    var cartButton : FloatingActionButton? = null
    var scanBtn : FloatingActionButton? = null
    var searchView : SearchView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = this.context?.let { BusinessProductAdapter(it,this ,allProduct) }
        ProductHandler.shared().viewModel?.allProduct?.observe(this) {
            allProduct = it as ArrayList<Product>
            this.reload()
        }
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
        ProductHandler.shared().viewModel?.loadProduct()
        reload()
        return view
    }

    fun onClickCart(){
        App.shared().mainActivity?.gotToCart()
    }
    fun onClickScan(){
        val intent = Intent(requireContext(), BarCodeScannerActivity::class.java)
        intent.putExtra("OPERATION","SALE")
        startActivity(intent)
    }



}