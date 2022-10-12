package com.solute.ui.business.inventory.category

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.solute.R
import com.utilitykit.feature.productCategory.handler.ProductCategoryHandler
import com.utilitykit.feature.productCategory.model.ProductCategory
import com.utilitykit.feature.productCategory.viewModel.ProductCategoryViewModalFactory
import com.utilitykit.feature.productCategory.viewModel.ProductCategoryViewModel


/**
 * A simple [Fragment] subclass.
 * Use the [ProductCategoryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProductCategoryFragment : Fragment() {

    var recycler : RecyclerView? = null
    private lateinit var productCategoryViewModel: ProductCategoryViewModel
    var allCategoory: ArrayList<ProductCategory> = ArrayList()
    var productCategoryAdapter : ProductCategoryAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        productCategoryViewModel = ViewModelProvider(
            this,
            ProductCategoryViewModalFactory(ProductCategoryHandler.shared().repository)
        ).get(
            ProductCategoryViewModel::class.java
        )
        ProductCategoryHandler.shared().setup(productCategoryViewModel!!)

        productCategoryViewModel.allCategory.observe(this){
            allCategoory = it as ArrayList<ProductCategory>
                loadCategory()
        }
        ProductCategoryHandler.shared().fetchAllProductCategory()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_product_category, container, false)
        recycler = view.findViewById(R.id.product_category_fragment_recycler)
        return view
    }

    fun loadCategory(){
        this.productCategoryAdapter = this.context?.let { ProductCategoryAdapter(it,this,allCategoory) }
        this.recycler?.layoutManager = LinearLayoutManager(this.context)
        recycler?.adapter = this.productCategoryAdapter
    }
}