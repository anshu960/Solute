package com.solute.ui.business.inventory.subCategory.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.friendly.framework.feature.product.handler.ProductHandler
import com.friendly.framework.feature.product.model.Product
import com.friendly.framework.feature.productSubCategory.handler.ProductSubCategoryHandler
import com.friendly.framework.feature.productSubCategory.model.ProductSubCategory
import com.solute.R
import com.solute.ui.business.product.BusinessProductAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ProductSubCategoryDetailsFragment : Fragment() {
    var recycler: RecyclerView? = null
    var selectedSubCategory: ProductSubCategory? = null
    var allProduct: ArrayList<Product> = ArrayList()
    var productAdapter: BusinessProductAdapter? = null
    var title: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ProductHandler.shared().viewModel?.allProduct?.observe(this) { products ->
            CoroutineScope(Job() + Dispatchers.Main).launch {
                allProduct = products
                reloadData()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =
            inflater.inflate(R.layout.fragment_product_sub_category_details, container, false)
        recycler = view.findViewById(R.id.sub_category_details_recycler)
        selectedSubCategory =
            ProductSubCategoryHandler.shared().repository.selectedSubCategory.value
        ProductHandler.shared().viewModel?.loadProduct()
        reloadData()
        return view
    }

    fun reloadData() {
        this.productAdapter = BusinessProductAdapter(requireContext(), null, allProduct)
        this.recycler?.layoutManager = GridLayoutManager(requireContext(), 2)
        recycler?.adapter = this.productAdapter
    }
}