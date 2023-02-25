package com.solute.ui.business.inventory.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.friendly.framework.feature.business.handler.BusinessHandler
import com.friendly.framework.feature.product.handler.ProductHandler
import com.friendly.framework.feature.product.model.Product
import com.friendly.framework.feature.product.viewModel.ProductViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.solute.R
import com.solute.ui.business.BusinessActivity
import com.solute.ui.business.product.BusinessProductAdapter


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BusinessProductsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BusinessProductsFragment : Fragment() {
    var recycler : RecyclerView? = null
    private var viewModal: ProductViewModel? = null
    private var adapter: BusinessProductAdapter? = null
    var allProduct: ArrayList<Product> = ArrayList()
    var createNewProductBtn : FloatingActionButton? = null
    val activity = BusinessHandler.shared().activity as? BusinessActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = this.context?.let { BusinessProductAdapter(it,this ,allProduct) }
        viewModal = ProductHandler.shared().viewModel
        viewModal?.allProduct?.observe(this) {
            allProduct = it as ArrayList<Product>
            this.reload()
        }
        viewModal?.let { ProductHandler.shared().setup(it) }
        viewModal?.loadProduct()
        ProductHandler.shared().viewModel?.fetchAllProduct()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_business_products, container, false)
        recycler = view.findViewById(R.id.business_products_recycler)
        createNewProductBtn = view.findViewById(R.id.business_products_fab_button)
        createNewProductBtn?.setOnClickListener {
            ProductHandler.shared().repository.selectedProductLiveData.postValue(null)
            activity?.navController?.navigate(R.id.business_product_create)
        }
        reload()
        return view
    }

    fun reload() {
        if(ProductHandler.shared().repository.productLiveData.value != null){
            allProduct = ProductHandler.shared().repository.productLiveData.value as ArrayList<Product>
        }
        this.recycler!!.layoutManager = GridLayoutManager(this.context,2)
        adapter = this.context?.let { BusinessProductAdapter(it,this ,allProduct) }
        this.recycler!!.adapter = this.adapter
    }
}