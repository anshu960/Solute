package com.solute.ui.business.inventory.subCategory

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.friendly.framework.feature.productCategory.handler.ProductCategoryHandler
import com.friendly.framework.feature.productSubCategory.handler.ProductSubCategoryHandler
import com.friendly.framework.feature.productSubCategory.model.ProductSubCategory
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.solute.R
import com.solute.navigation.AppNavigator


class FragmentProductSubCategory : Fragment() {

    var recycler: RecyclerView? = null

    var allSubCategoory: ArrayList<ProductSubCategory> = ArrayList()
    var productSubCategoryAdapter: ProductSubCategoryAdapter? = null
    var createNewSubCategoryBtn: FloatingActionButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ProductSubCategoryHandler.shared().viewModel?.allSubCategory?.observe(this) {
            if (!it.isNullOrEmpty()) {
                allSubCategoory = it as ArrayList<ProductSubCategory>
            }
            loadSubCategory()
        }
        ProductCategoryHandler.shared().viewModel?.loadCategory()
        ProductSubCategoryHandler.shared().viewModel?.loadSubCategory()
        ProductSubCategoryHandler.shared().fetchAllProductSubCategory()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_product_sub_category, container, false)
        recycler = view.findViewById(R.id.product_sub_category_fragment_recycler)
        createNewSubCategoryBtn = view.findViewById(R.id.product_sub_category_fragment_add_btn)
        createNewSubCategoryBtn?.setOnClickListener {
           AppNavigator.shared().goToCreateProductSubCategory()
        }
        ProductSubCategoryHandler.shared().viewModel?.loadSubCategory()
        return view
    }

    fun loadSubCategory() {
        this.productSubCategoryAdapter =
            this.context?.let { ProductSubCategoryAdapter(it, this, allSubCategoory) }
        this.recycler?.layoutManager = LinearLayoutManager(this.context)
        recycler?.adapter = this.productSubCategoryAdapter
    }
}