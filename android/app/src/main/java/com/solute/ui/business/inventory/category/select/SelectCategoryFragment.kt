package com.solute.ui.business.inventory.category.select

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
import com.friendly.framework.feature.productCategory.model.ProductCategory
import com.friendly.framework.feature.productCategory.viewModel.ProductCategoryViewModalFactory
import com.friendly.framework.feature.productCategory.viewModel.ProductCategoryViewModel
import com.google.android.material.textfield.TextInputEditText
import com.solute.R
import com.solute.app.App
import com.solute.ui.business.inventory.category.ProductCategoryAdapter

class SelectCategoryFragment : Fragment() {
    var recycler: RecyclerView? = null
    var allCategoory: ArrayList<ProductCategory> = ArrayList()
    var productCategoryAdapter: ProductCategoryAdapter? = null

    var searchView: SearchView? = null
    var name: TextInputEditText? = null
    var createBtn: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ProductCategoryHandler.shared().viewModel?.allCategory?.observe(this) {
            if (!it.isNullOrEmpty()) {
                allCategoory = it as ArrayList<ProductCategory>
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
        val view = inflater.inflate(R.layout.fragment_select_category, container, false)
        name = view.findViewById(R.id.select_category_name_tiet)
        searchView = view.findViewById(R.id.select_category_search)
        recycler = view.findViewById(R.id.select_category_recycler)
        createBtn = view.findViewById(R.id.select_category_create_btn)
        createBtn?.setOnClickListener { onClickCreate() }
        ProductCategoryHandler.shared().viewModel?.loadCategory()
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
            allCategoory = arrayListOf()
            ProductCategoryHandler.shared().viewModel?.allCategory?.value?.forEach {
                if (it.Name?.contains(query) == true) {
                    allCategoory.add(it)
                }
            }
        } else if (ProductCategoryHandler.shared().viewModel?.allCategory?.value != null) {
            allCategoory = ProductCategoryHandler.shared().viewModel?.allCategory?.value as ArrayList<ProductCategory>
        }
        reload()
    }

    fun reload() {
        this.productCategoryAdapter = this.context?.let {
            ProductCategoryAdapter(it, this, allCategoory) { category ->
                App.shared().mainActivity?.onBackPressed()
                ProductCategoryHandler.shared().onSelectCategory?.let { it1 -> it1(category) }
            }
        }
        this.recycler?.layoutManager = LinearLayoutManager(this.context)
        recycler?.adapter = this.productCategoryAdapter
    }

    fun onClickCreate(){
        if(!name?.text.isNullOrEmpty()){
            ProductCategoryHandler.shared().viewModel?.createNewCategory(name?.text.toString())
        }
    }
}