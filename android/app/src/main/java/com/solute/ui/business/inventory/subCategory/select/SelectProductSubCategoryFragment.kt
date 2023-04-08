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
import com.friendly.framework.feature.business.handler.BusinessHandler
import com.friendly.framework.feature.productCategory.handler.ProductCategoryHandler
import com.friendly.framework.feature.productSubCategory.handler.ProductSubCategoryHandler
import com.friendly.framework.feature.productSubCategory.model.ProductSubCategory
import com.friendly.framework.feature.productSubCategory.viewModel.ProductSubCategoryViewModalFactory
import com.friendly.framework.feature.productSubCategory.viewModel.ProductSubCategoryViewModel
import com.google.android.material.textfield.TextInputEditText
import com.solute.R
import com.solute.app.App
import com.solute.navigation.AppNavigator
import com.solute.ui.business.inventory.category.ProductSubCategoryAdapter


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
    var allSubCategoory: ArrayList<ProductSubCategory> = ArrayList()
    var productSubCategoryAdapter: ProductSubCategoryAdapter? = null

    var searchView: SearchView? = null
    var name: TextInputEditText? = null
    var createBtn: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ProductSubCategoryHandler.shared().viewModel?.allSubCategory?.observe(this) {
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
            ProductSubCategoryHandler.shared().viewModel?.loadSubCategory(ProductCategoryHandler.shared().repository.selectedCategory.value!!)
        }else{
            ProductSubCategoryHandler.shared().viewModel?.loadSubCategory()
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
            ProductSubCategoryHandler.shared().viewModel?.allSubCategory?.value?.forEach {
                if (it.Name?.contains(query) == true) {
                    allSubCategoory.add(it)
                }
            }
        } else if (ProductSubCategoryHandler.shared().viewModel?.allSubCategory?.value != null) {
            allSubCategoory = ProductSubCategoryHandler.shared().viewModel?.allSubCategory?.value as ArrayList<ProductSubCategory>
        }
        reload()
    }
    fun reload() {
        this.productSubCategoryAdapter = this.context?.let {
            ProductSubCategoryAdapter(it, this, allSubCategoory) { subCategory ->
                AppNavigator.shared().goBack()
                ProductSubCategoryHandler.shared().onSelectSubCategory?.let { it1 -> it1(subCategory) }
            }
        }
        this.recycler?.layoutManager = LinearLayoutManager(this.context)
        recycler?.adapter = this.productSubCategoryAdapter
    }

    fun onClickCreate(){
        if(!name?.text.isNullOrEmpty() && ProductCategoryHandler.shared().repository.selectedCategory.value != null){
            ProductSubCategoryHandler.shared().viewModel?.createNewSubCategory(name?.text.toString(),ProductCategoryHandler.shared().repository.selectedCategory.value!!)
        }
    }
}