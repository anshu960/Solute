package com.solute.ui.business.inventory.subCategory.select

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import com.solute.R
import com.solute.ui.business.inventory.category.ProductCategoryAdapter
import com.solute.ui.business.inventory.category.ProductSubCategoryAdapter
import com.utilitykit.Constants.Key.Companion.category
import com.utilitykit.Constants.Key.Companion.search
import com.utilitykit.feature.business.handler.BusinessHandler
import com.utilitykit.feature.productCategory.handler.ProductCategoryHandler
import com.utilitykit.feature.productCategory.model.ProductCategory
import com.utilitykit.feature.productCategory.viewModel.ProductCategoryViewModalFactory
import com.utilitykit.feature.productCategory.viewModel.ProductCategoryViewModel
import com.utilitykit.feature.productSubCategory.handler.ProductSubCategoryHandler
import com.utilitykit.feature.productSubCategory.model.ProductSubCategory
import com.utilitykit.feature.productSubCategory.viewModel.ProductSubCategoryViewModalFactory
import com.utilitykit.feature.productSubCategory.viewModel.ProductSubCategoryViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SelectProductSubCategoryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SelectProductSubCategoryFragment : Fragment() {
    var recycler: RecyclerView? = null
    private lateinit var viewModal: ProductSubCategoryViewModel
    var allSubCategoory: ArrayList<ProductSubCategory> = ArrayList()
    var productSubCategoryAdapter: ProductSubCategoryAdapter? = null

    var searchView: SearchView? = null
    var name: TextInputEditText? = null
    var createBtn: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModal = ViewModelProvider(
            this,
            ProductSubCategoryViewModalFactory(ProductSubCategoryHandler.shared().repository)
        ).get(
            ProductSubCategoryViewModel::class.java
        )
        ProductSubCategoryHandler.shared().setup(viewModal!!)
        viewModal.allSubCategory.observe(this) {
            if (!it.isNullOrEmpty()) {
                allSubCategoory = it as ArrayList<ProductSubCategory>
                reload()
            }
        }
        ProductCategoryHandler.shared().fetchAllProductCategory()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_select_product_sub_category, container, false)
        name = view.findViewById(R.id.select_product_sub_category_name_tiet)
        searchView = view.findViewById(R.id.select_product_sub_category_search)
        recycler = view.findViewById(R.id.select_product_sub_category_recycler)
        createBtn = view.findViewById(R.id.select_product_sub_category_save_btn)
        createBtn?.setOnClickListener { onClickCreate() }
        if(ProductCategoryHandler.shared().repository.selectedCategory.value != null){
            viewModal.loadSubCategory(ProductCategoryHandler.shared().repository.selectedCategory.value!!)
        }else{
            viewModal.loadSubCategory()
        }

        searchView?.isIconified = false
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String): Boolean {
                search(newText)
                return false
            }
        })
        return view
    }
    fun search(query: String) {
        if (query.isNotEmpty()) {
            allSubCategoory = arrayListOf()
            viewModal.allSubCategory.value?.forEach {
                if (it.Name?.contains(query) == true) {
                    allSubCategoory.add(it)
                }
            }
        } else if (viewModal.allSubCategory.value != null) {
            allSubCategoory = viewModal.allSubCategory.value as ArrayList<ProductSubCategory>
        }
        reload()
    }
    fun reload() {
        this.productSubCategoryAdapter = this.context?.let {
            ProductSubCategoryAdapter(it, this, allSubCategoory) { subCategory ->
                BusinessHandler.shared().activity.onBackPressed()
                ProductSubCategoryHandler.shared().onSelectSubCategory?.let { it1 -> it1(subCategory) }
            }
        }
        this.recycler?.layoutManager = LinearLayoutManager(this.context)
        recycler?.adapter = this.productSubCategoryAdapter
    }

    fun onClickCreate(){
        if(!name?.text.isNullOrEmpty() && ProductCategoryHandler.shared().repository.selectedCategory.value != null){
            viewModal.createNewSubCategory(name?.text.toString(),ProductCategoryHandler.shared().repository.selectedCategory.value!!)
        }
    }
}