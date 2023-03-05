package com.solute.ui.business.inventory.category.details

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
import com.friendly.framework.feature.productCategory.handler.ProductCategoryHandler
import com.friendly.framework.feature.productCategory.model.ProductCategory
import com.solute.R
import com.solute.ui.business.product.BusinessProductAdapter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CategoryDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CategoryDetailsFragment : Fragment() {
    var recycler : RecyclerView? = null
    var selectedCategory : ProductCategory? = null
    var allProduct: ArrayList<Product> = ArrayList()
    var productAdapter : BusinessProductAdapter? = null
    var title : TextView? = null
    var countTxt : TextView? = null
    var backButton : ImageButton? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ProductHandler.shared().repository.allProduct.observe(this) { products ->
            products.forEach {
                if (selectedCategory != null) {
                    if (it.CategoryID == selectedCategory!!.Id) {
                        allProduct.add(it)
                    }
                }
            }
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_category_details, container, false)
        recycler = view.findViewById(R.id.category_details_recycler)
        selectedCategory = ProductCategoryHandler.shared().repository.selectedCategory.value
//        this.title = view.findViewById(R.id.category_details_title)
        this.title?.text = selectedCategory?.Name
//        this.countTxt = view.findViewById(R.id.category_details_count)
        this.countTxt?.text = "Total ${allProduct.count()} Product"
        this.productAdapter =   BusinessProductAdapter(requireContext(),null,allProduct)
        this.recycler?.layoutManager = GridLayoutManager(requireContext(),2)
        recycler?.adapter = this.productAdapter
//        this.backButton = view.findViewById(R.id.category_details_header_back)
        ProductHandler.shared().viewModel?.loadProduct()
        return view
    }
}