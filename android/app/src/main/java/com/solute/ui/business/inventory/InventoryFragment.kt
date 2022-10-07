package com.solute.ui.business.inventory

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButtonToggleGroup
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.solute.R
import com.solute.ui.business.inventory.analytics.AnalyticsAdapter
import com.solute.ui.business.inventory.category.CreateCategoryActivity
import com.solute.ui.business.inventory.category.ProductCategoryAdapter
import com.solute.ui.business.inventory.category.ProductSubCategoryAdapter
import com.solute.ui.business.inventory.product.ProductAdapter
import com.solute.ui.business.inventory.stock.ProductStockAdapter
import com.solute.ui.business.inventory.subCategory.CreateProductSubCategoryActivity
import com.solute.ui.business.product.BusinessProductAdapter
import com.solute.ui.business.product.create.CreateProductActivity
import com.utilitykit.feature.business.handler.BusinessHandler
import com.utilitykit.feature.business.model.Business
import com.utilitykit.feature.product.handler.ProductHandler
import com.utilitykit.feature.product.model.Product
import com.utilitykit.feature.product.viewModel.ProductViewModalFactory
import com.utilitykit.feature.product.viewModel.ProductViewModel
import com.utilitykit.feature.productCategory.handler.ProductCategoryHandler
import com.utilitykit.feature.productCategory.model.ProductCategory
import com.utilitykit.feature.productCategory.viewModel.ProductCategoryViewModalFactory
import com.utilitykit.feature.productCategory.viewModel.ProductCategoryViewModel
import com.utilitykit.feature.productSubCategory.handler.ProductSubCategoryHandler
import com.utilitykit.feature.productSubCategory.model.ProductSubCategory
import com.utilitykit.feature.productSubCategory.viewModel.ProductSubCategoryViewModalFactory
import com.utilitykit.feature.productSubCategory.viewModel.ProductSubCategoryViewModel
import com.utilitykit.feature.sync.BusinessAnalytics
import com.utilitykit.feature.sync.SyncHandler

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
    var countLabel : TextView? = null

    var selectedSegment = 0
    private lateinit var productViewModel: ProductViewModel
    private lateinit var productCategoryViewModel: ProductCategoryViewModel
    private lateinit var productSubCategoryViewModel: ProductSubCategoryViewModel
    var allProduct: ArrayList<Product> = ArrayList()
    var allCategoory: ArrayList<ProductCategory> = ArrayList()
    var allSubCategoory: ArrayList<ProductSubCategory> = ArrayList()
    var allAnalytics: ArrayList<BusinessAnalytics> = ArrayList()

    var productAdapter : BusinessProductAdapter? = null
    var productStockdapter : ProductStockAdapter? = null
    var productCategoryAdapter : ProductCategoryAdapter? = null
    var productSubCategoryAdapter : ProductSubCategoryAdapter? = null
    var analyticsAdapter : AnalyticsAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Product
        productViewModel = ViewModelProvider(
            this,
            ProductViewModalFactory(ProductHandler.shared().repository)
        ).get(
            ProductViewModel::class.java
        )
        productViewModel.allProduct.observe(this) {
            allProduct = it as ArrayList<Product>
            if(selectedSegment == 1){
                loadProducts()
            }
        }
        ProductHandler.shared().setup(productViewModel)
        ProductHandler.shared().fetchAllProduct()
        //Category
        productCategoryViewModel = ViewModelProvider(
            this,
            ProductCategoryViewModalFactory(ProductCategoryHandler.shared().repository)
        ).get(
            ProductCategoryViewModel::class.java
        )
        ProductCategoryHandler.shared().setup(productCategoryViewModel!!)

        productCategoryViewModel.allCategory.observe(this){
            allCategoory = it as ArrayList<ProductCategory>
            if(selectedSegment == 1){
                loadCategory()
            }
        }
        ProductCategoryHandler.shared().fetchAllProductCategory()
        //Sub Category
        productSubCategoryViewModel = ViewModelProvider(
            this,
            ProductSubCategoryViewModalFactory(ProductSubCategoryHandler.shared().repository)
        ).get(
            ProductSubCategoryViewModel::class.java
        )
        ProductSubCategoryHandler.shared().setup(productSubCategoryViewModel!!)

        productSubCategoryViewModel.allSubCategory.observe(this){
            allSubCategoory = it as ArrayList<ProductSubCategory>
            if(selectedSegment == 2){
                loadSubCategory()
            }
        }
        ProductSubCategoryHandler.shared().fetchAllProductSubCategory()

        BusinessHandler.shared().repository.analyticsLiveData.observe(this){
            if(it != null){
                this.allAnalytics = it
                if(selectedSegment == 0){
                    loadAnalytics()
                }
            }
        }
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
        countLabel = view.findViewById(R.id.inventory_fragment_count)
        setupSegment()
        SyncHandler.shared().syncAllBusinessData()
        return view
    }
    fun setupSegment(){
        segmentButton?.addOnButtonCheckedListener { toggleButton, checkedId, isChecked ->
            if(isChecked){
                when (checkedId) {
                    R.id.inventory_fragment_analytics_btn -> {
                        selectedSegment = 0
                        loadAnalytics()
                    }
                    R.id.inventory_fragment_menu_btn -> {
                        selectedSegment = 1
                        loadProducts()
                    }
                    R.id.inventory_fragment_category_btn -> {
                        selectedSegment = 2
                        loadCategory()
                    }
                    R.id.inventory_fragment_sub_category_btn -> {
                        selectedSegment = 3
                        loadSubCategory()
                    }
                    R.id.inventory_fragment_stock_btn -> {
                        selectedSegment = 4
                        loadStock()
                    }
                    R.id.inventory_fragment_stock_btn -> {
                        selectedSegment = 5
                        loadStock()
                    }
                }
            }
        }
        if(selectedSegment == 0){
            segmentButton?.check(R.id.inventory_fragment_analytics_btn)
            loadAnalytics()
        }
    }
    fun loadAnalytics(){
        this.analyticsAdapter = this.context?.let { AnalyticsAdapter(it,this,allAnalytics) }
        this.recycler?.layoutManager = GridLayoutManager(this.context,2)
        recycler?.adapter = this.analyticsAdapter
        countLabel?.visibility = View.GONE
        floatingAddButton?.visibility = View.GONE
    }
    fun loadProducts(){
        this.productAdapter = this.context?.let { BusinessProductAdapter(it,this,allProduct) }
        this.recycler?.layoutManager = GridLayoutManager(this.context,2)
        recycler?.adapter = this.productAdapter
        countLabel?.text = "Total ${allProduct.count()} Product"
        countLabel?.visibility = View.VISIBLE
        floatingAddButton?.visibility = View.VISIBLE
    }
    fun loadCategory(){
        this.productCategoryAdapter = this.context?.let { ProductCategoryAdapter(it,this,allCategoory) }
        this.recycler?.layoutManager = LinearLayoutManager(this.context)
        recycler?.adapter = this.productCategoryAdapter
        countLabel?.text = "Total ${allCategoory.count()} Category"
        countLabel?.visibility = View.VISIBLE
        floatingAddButton?.visibility = View.VISIBLE
    }
    fun loadSubCategory(){
        this.productSubCategoryAdapter = this.context?.let { ProductSubCategoryAdapter(it,this,allSubCategoory) }
        this.recycler?.layoutManager = LinearLayoutManager(this.context)
        recycler?.adapter = this.productSubCategoryAdapter
        countLabel?.text = "Total ${allSubCategoory.count()} Sub Category"
        countLabel?.visibility = View.VISIBLE
        floatingAddButton?.visibility = View.VISIBLE
    }
    fun loadStock(){
        this.productStockdapter = this.context?.let { ProductStockAdapter(it,this,this.allProduct) }
        this.recycler?.layoutManager = LinearLayoutManager(this.context)
        recycler?.adapter = productStockdapter
        countLabel?.text = "Total ${allProduct.count()} Product"
        countLabel?.visibility = View.VISIBLE
        floatingAddButton?.visibility = View.GONE
    }
    fun onClickAddButton(){
        when(this.selectedSegment){
            1->{
                ProductHandler.shared().repository.selectedProductLiveData.postValue(null)
                val intent = Intent(this.context,CreateProductActivity::class.java)
                startActivity(intent)
            }
            2->{
                val intent = Intent(this.context,CreateCategoryActivity::class.java)
                startActivity(intent)
            }
            3->{
                val intent = Intent(this.context,CreateProductSubCategoryActivity::class.java)
                startActivity(intent)
            }
        }
    }


}