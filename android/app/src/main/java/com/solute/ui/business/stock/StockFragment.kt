package com.solute.ui.business.stock

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.friendly.framework.feature.product.handler.ProductHandler
import com.friendly.framework.feature.product.model.Product
import com.friendly.framework.feature.product.viewModel.ProductViewModalFactory
import com.friendly.framework.feature.product.viewModel.ProductViewModel
import com.solute.R
import com.solute.ui.business.inventory.stock.ProductStockAdapter


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [StockFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StockFragment : Fragment() {
    var productStockdapter : ProductStockAdapter? = null
    var recycler : RecyclerView? = null
    var allProduct: ArrayList<Product> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ProductHandler.shared().viewModel?.allProduct?.observe(this) {
            allProduct = it as ArrayList<Product>
            loadStock()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_stock, container, false)
        recycler = view.findViewById(R.id.fragment_stock_recycler)
        ProductHandler.shared().viewModel?.fetchAllProduct()
        loadStock()
        return view
    }
    fun loadStock(){
        this.productStockdapter = this.context?.let { ProductStockAdapter(it,this,this.allProduct) }
        this.recycler?.layoutManager = LinearLayoutManager(this.context)
        recycler?.adapter = productStockdapter
    }
}