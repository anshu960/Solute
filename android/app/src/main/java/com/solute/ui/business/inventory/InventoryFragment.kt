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

    var recycler : RecyclerView? = null
    var floatingAddButton : FloatingActionButton? = null
    var countLabel : TextView? = null
    var selectedSegment = 0
    var allProduct: ArrayList<Product> = ArrayList()
    var allAnalytics: ArrayList<BusinessAnalytics> = ArrayList()

    var analyticsAdapter : AnalyticsAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        val view = inflater.inflate(R.layout.fragment_inventory, container, false)
        recycler = view.findViewById(R.id.inventory_fragment_recycler)
        floatingAddButton = view.findViewById(R.id.inventory_fragment_float_btn)
        floatingAddButton?.setOnClickListener { onClickAddButton() }
        SyncHandler.shared().syncAllBusinessData()
        return view
    }

    fun loadAnalytics(){
        this.analyticsAdapter = this.context?.let { AnalyticsAdapter(it,this,allAnalytics) }
        this.recycler?.layoutManager = GridLayoutManager(this.context,2)
        recycler?.adapter = this.analyticsAdapter
        countLabel?.visibility = View.GONE
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

    override fun onResume() {
        super.onResume()
        loadAnalytics()
    }

}