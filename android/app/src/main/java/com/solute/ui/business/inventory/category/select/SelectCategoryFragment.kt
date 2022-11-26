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
import com.google.android.material.textfield.TextInputEditText
import com.solute.R
import com.solute.ui.business.inventory.category.ProductCategoryAdapter
import com.utilitykit.feature.business.handler.BusinessHandler
import com.utilitykit.feature.productCategory.handler.ProductCategoryHandler
import com.utilitykit.feature.productCategory.model.ProductCategory
import com.utilitykit.feature.productCategory.viewModel.ProductCategoryViewModalFactory
import com.utilitykit.feature.productCategory.viewModel.ProductCategoryViewModel


/**
 * A simple [Fragment] subclass.
 * Use the [SelectCategoryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SelectCategoryFragment : Fragment() {
    var recycler: RecyclerView? = null
    private lateinit var viewModal: ProductCategoryViewModel
    var allCategoory: ArrayList<ProductCategory> = ArrayList()
    var productCategoryAdapter: ProductCategoryAdapter? = null

    var searchView: SearchView? = null
    var name: TextInputEditText? = null
    var createBtn: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModal = ViewModelProvider(
            this,
            ProductCategoryViewModalFactory(ProductCategoryHandler.shared().repository)
        ).get(
            ProductCategoryViewModel::class.java
        )
        ProductCategoryHandler.shared().setup(viewModal!!)
        viewModal.allCategory.observe(this) {
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
        viewModal.loadCategory()
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
            viewModal.allCategory.value?.forEach {
                if (it.Name?.contains(query) == true) {
                    allCategoory.add(it)
                }
            }
        } else if (viewModal.allCategory.value != null) {
            allCategoory = viewModal.allCategory.value as ArrayList<ProductCategory>
        }
        reload()
    }

    fun reload() {
        this.productCategoryAdapter = this.context?.let {
            ProductCategoryAdapter(it, this, allCategoory) { category ->
                BusinessHandler.shared().activity.onBackPressed()
                ProductCategoryHandler.shared().onSelectCategory?.let { it1 -> it1(category) }
            }
        }
        this.recycler?.layoutManager = LinearLayoutManager(this.context)
        recycler?.adapter = this.productCategoryAdapter
    }

    fun onClickCreate(){
        if(!name?.text.isNullOrEmpty()){
            viewModal.createNewCategory(name?.text.toString())
        }
    }
}