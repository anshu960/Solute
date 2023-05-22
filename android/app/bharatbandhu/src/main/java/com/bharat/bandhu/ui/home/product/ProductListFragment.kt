package com.bharat.bandhu.ui.home.product

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bharat.bandhu.R
import com.friendly.framework.Defaults
import com.friendly.framework.constants.KeyConstant
import com.friendly.framework.feature.customer.handler.CustomerHandler
import com.friendly.framework.feature.product.handler.ProductHandler
import com.friendly.framework.feature.product.model.Product
import com.google.gson.Gson
import com.solute.ui.business.product.BusinessProductAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProductListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProductListFragment : Fragment() {
    var recyclerView: RecyclerView? = null
    private var adapter: BusinessProductAdapter? = null
    var allProduct: ArrayList<Product> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ProductHandler.shared().repository.allProduct.observe(this){ products->
            CoroutineScope(Job() + Dispatchers.Main).launch {
                if(products != null){
                    allProduct = products
                    loadProductsInUI()
                }else{

                }
            }
        }
        ProductHandler.shared().viewModel?.fetchAllProduct()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_product_list, container, false)
        recyclerView = view.findViewById(R.id.product_list_recycler)
        loadProductsInUI()
        return view
    }

    fun loadProductsInUI(){
        if(ProductHandler.shared().repository.productLiveData.value != null){
            allProduct = ProductHandler.shared().repository.productLiveData.value as ArrayList<Product>
        }
        this.recyclerView!!.layoutManager = GridLayoutManager(this.context,2)
        adapter = this.context?.let { BusinessProductAdapter(it,this ,allProduct) }
        this.recyclerView!!.adapter = this.adapter
    }

}