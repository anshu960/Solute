package com.solute.ui.business.inventory

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.friendly.framework.database.DatabaseHandler
import com.friendly.framework.feature.business.handler.BusinessHandler
import com.friendly.framework.feature.cart.model.Sale
import com.friendly.framework.feature.product.handler.ProductHandler
import com.friendly.framework.feature.product.model.Product
import com.friendly.framework.feature.sync.BusinessAnalytics
import com.friendly.framework.feature.sync.SyncHandler
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.solute.R
import com.solute.navigation.AppNavigator
import com.solute.ui.business.inventory.analytics.AnalyticsAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


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
        if(!BusinessHandler.shared().repository.business.value?.Id.isNullOrEmpty()){
            val businessId = BusinessHandler.shared().repository.business.value!!.Id
            CoroutineScope(Job() + Dispatchers.IO).launch {
                val allAnalyticsFromDb = DatabaseHandler.shared().database.saleDao().getAllItemsForBusiness(businessId)
                if(allAnalyticsFromDb.isNotEmpty()){
                    SyncHandler.shared().updateAnalyticsToShow(allAnalyticsFromDb as ArrayList<Sale>)
                }
            }
        }
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
//                val intent = Intent(this.context,CreateProductActivity::class.java)
//                startActivity(intent)
            }
            2->{
                AppNavigator.shared().goToCreateProductCategory()
            }
            3->{
                AppNavigator.shared().goToCreateProductSubCategory()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        loadAnalytics()
    }

}