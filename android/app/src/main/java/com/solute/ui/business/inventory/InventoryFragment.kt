package com.solute.ui.business.inventory

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButtonToggleGroup
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.solute.R
import com.solute.ui.business.inventory.category.CreateCategoryActivity
import com.solute.ui.business.inventory.product.ProductAdapter
import com.utilitykit.feature.product.handler.ProductHandler
import com.utilitykit.feature.product.model.Product
import com.utilitykit.feature.product.viewModel.ProductViewModalFactory
import com.utilitykit.feature.product.viewModel.ProductViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [InventoryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class InventoryFragment : Fragment() {

    var segmentButton : MaterialButtonToggleGroup? = null
    var recycler : RecyclerView? = null
    var floatingAddButton : FloatingActionButton? = null

    var selectedSegment = 0
    private lateinit var productViewModel: ProductViewModel
    var allProduct: ArrayList<Product> = ArrayList()
    var productAdapter : ProductAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        productViewModel = ViewModelProvider(
            this,
            ProductViewModalFactory(ProductHandler.shared().repository)
        ).get(
            ProductViewModel::class.java
        )
        productViewModel.allProduct.observe(this) {
            allProduct = it as ArrayList<Product>
            if(selectedSegment == 0){
                loadProducts()
            }
        }
        ProductHandler.shared().setup(productViewModel)
        ProductHandler.shared().fetchAllProduct()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_inventory, container, false)
        recycler = view.findViewById(R.id.inventory_fragment_recycler)
        segmentButton = view.findViewById(R.id.inventory_fragment_segment)
        floatingAddButton = view.findViewById(R.id.inventory_fragment_float_btn)
        floatingAddButton?.setOnClickListener { onClickAddButton() }
        setupSegment()
        return view
    }
    fun setupSegment(){
        segmentButton?.addOnButtonCheckedListener { toggleButton, checkedId, isChecked ->
            if(isChecked){
                when (checkedId) {
                    R.id.inventory_fragment_menu_btn -> {
                        selectedSegment = 0
                        loadProducts()
                    }
                    R.id.inventory_fragment_category_btn -> {
                        selectedSegment = 1
                        loadCategory()
                    }
                    R.id.inventory_fragment_sub_category_btn -> {
                        selectedSegment = 2
                        loadSubCategory()
                    }
                    R.id.inventory_fragment_stock_btn -> {
                        selectedSegment = 3
                        loadStock()
                    }
                }
            }
        }
        if(selectedSegment == 0){
            segmentButton?.check(R.id.inventory_fragment_menu_btn)
            loadProducts()
        }
    }
    fun loadProducts(){
        this.productAdapter = this.context?.let { ProductAdapter(it,this,allProduct) }
        this.recycler?.layoutManager = LinearLayoutManager(this.context)
        recycler?.adapter = this.productAdapter
    }
    fun loadCategory(){

    }
    fun loadSubCategory(){

    }
    fun loadStock(){

    }
    fun onClickAddButton(){
        when(this.selectedSegment){
            0->{

            }
            1->{
                val intent = Intent(this.context,CreateCategoryActivity::class.java)
                startActivity(intent)
            }
        }
    }


}