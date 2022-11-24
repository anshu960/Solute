package com.solute.ui.business.inventory.subCategory

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.solute.R
import com.solute.ui.business.inventory.category.ProductSubCategoryAdapter
import com.utilitykit.feature.productCategory.handler.ProductCategoryHandler
import com.utilitykit.feature.productCategory.viewModel.ProductCategoryViewModalFactory
import com.utilitykit.feature.productCategory.viewModel.ProductCategoryViewModel
import com.utilitykit.feature.productSubCategory.handler.ProductSubCategoryHandler
import com.utilitykit.feature.productSubCategory.model.ProductSubCategory
import com.utilitykit.feature.productSubCategory.viewModel.ProductSubCategoryViewModalFactory
import com.utilitykit.feature.productSubCategory.viewModel.ProductSubCategoryViewModel

class FragmentProductSubCategory : Fragment() {

    var recycler: RecyclerView? = null
    private lateinit var productCategoryViewModel: ProductCategoryViewModel
    private lateinit var productSubCategoryViewModel: ProductSubCategoryViewModel
    var allSubCategoory: ArrayList<ProductSubCategory> = ArrayList()
    var productSubCategoryAdapter: ProductSubCategoryAdapter? = null
    var createNewSubCategoryBtn: FloatingActionButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Sub Category
        productCategoryViewModel = ViewModelProvider(
            this,
            ProductCategoryViewModalFactory(ProductCategoryHandler.shared().repository)
        ).get(
            ProductCategoryViewModel::class.java
        )
        ProductCategoryHandler.shared().setup(productCategoryViewModel!!)
        productSubCategoryViewModel = ViewModelProvider(
            this,
            ProductSubCategoryViewModalFactory(ProductSubCategoryHandler.shared().repository)
        ).get(
            ProductSubCategoryViewModel::class.java
        )
        ProductSubCategoryHandler.shared().setup(productSubCategoryViewModel!!)
        productSubCategoryViewModel.allSubCategory.observe(this) {
            if (!it.isNullOrEmpty()) {
                allSubCategoory = it as ArrayList<ProductSubCategory>
            }
            loadSubCategory()
        }
        ProductCategoryHandler.shared().productCategoryViewModel?.loadCategory()
        productSubCategoryViewModel.loadSubCategory()
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
            val intent = Intent(this.context, CreateProductSubCategoryActivity::class.java)
            this.context?.startActivity(intent)
        }
        return view
    }

    fun loadSubCategory() {
        this.productSubCategoryAdapter =
            this.context?.let { ProductSubCategoryAdapter(it, this, allSubCategoory) }
        this.recycler?.layoutManager = LinearLayoutManager(this.context)
        recycler?.adapter = this.productSubCategoryAdapter
    }
}