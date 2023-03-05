package com.solute.ui.business.inventory.category

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.friendly.framework.feature.productCategory.handler.ProductCategoryHandler
import com.friendly.framework.feature.productCategory.model.ProductCategory
import com.friendly.framework.feature.productCategory.viewModel.ProductCategoryViewModalFactory
import com.friendly.framework.feature.productCategory.viewModel.ProductCategoryViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.solute.R
import com.solute.navigation.AppNavigator


/**
 * A simple [Fragment] subclass.
 * Use the [ProductCategoryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProductCategoryFragment : Fragment() {

    var recycler : RecyclerView? = null
    var allCategoory: ArrayList<ProductCategory> = ArrayList()
    var productCategoryAdapter : ProductCategoryAdapter? = null
    var createNewCategoryBtn : FloatingActionButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ProductCategoryHandler.shared().viewModel?.allCategory?.observe(this){
            if(!it.isNullOrEmpty()){
                allCategoory = it as ArrayList<ProductCategory>
                loadCategory()
            }
        }
        ProductCategoryHandler.shared().viewModel?.loadCategory()
        ProductCategoryHandler.shared().fetchAllProductCategory()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_product_category, container, false)
        recycler = view.findViewById(R.id.product_category_fragment_recycler)
        createNewCategoryBtn = view.findViewById(R.id.product_category_add_button)
        createNewCategoryBtn?.setOnClickListener {
            AppNavigator.shared().goToCreateProductCategory()
        }
        ProductCategoryHandler.shared().viewModel?.loadCategory()
        return view
    }

    fun loadCategory(){
        this.productCategoryAdapter = this.context?.let { ProductCategoryAdapter(it,this,allCategoory,null) }
        this.recycler?.layoutManager = LinearLayoutManager(this.context)
        recycler?.adapter = this.productCategoryAdapter
    }
}