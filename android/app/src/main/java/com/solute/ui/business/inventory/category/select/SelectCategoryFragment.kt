package com.solute.ui.business.inventory.category.select

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.friendly.framework.feature.productCategory.handler.ProductCategoryHandler
import com.friendly.framework.feature.productCategory.model.ProductCategory
import com.google.android.material.textfield.TextInputEditText
import com.solute.R
import com.solute.navigation.AppNavigator
import com.solute.ui.business.inventory.category.ProductCategoryAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SelectCategoryFragment : Fragment() {
    var recycler: RecyclerView? = null
    var allCategoory: ArrayList<ProductCategory> = ArrayList()
    var productCategoryAdapter: ProductCategoryAdapter? = null

    var searchView: SearchView? = null
    var name: TextInputEditText? = null
    var createBtn: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ProductCategoryHandler.shared().viewModel?.allCategory?.observe(this) {cats->
            CoroutineScope(Job() + Dispatchers.Main).launch {
                if (!cats.isNullOrEmpty()) {
                    allCategoory = cats as ArrayList<ProductCategory>
                    reload()
                }
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

    private fun reload() {
        this.productCategoryAdapter = ProductCategoryAdapter(requireContext(), this, allCategoory) { category ->
                AppNavigator.shared().goBack()
                ProductCategoryHandler.shared().onSelectCategory?.let { it1 -> it1(category) }
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