package com.solute.ui.business.stock

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.solute.R
import com.solute.ui.business.inventory.stock.ProductStockAdapter
import com.utilitykit.feature.product.handler.ProductHandler
import com.utilitykit.feature.product.model.Product
import com.utilitykit.feature.product.viewModel.ProductViewModalFactory
import com.utilitykit.feature.product.viewModel.ProductViewModel

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
    private lateinit var productViewModel: ProductViewModel
    var productStockdapter : ProductStockAdapter? = null
    var recycler : RecyclerView? = null
    var allProduct: ArrayList<Product> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_stock, container, false)
        recycler = view.findViewById(R.id.fragment_stock_recycler)
        productViewModel = ViewModelProvider(
            this,
            ProductViewModalFactory(ProductHandler.shared().repository)
        ).get(
            ProductViewModel::class.java
        )
        productViewModel.allProduct.observe(viewLifecycleOwner) {
            allProduct = it as ArrayList<Product>
                loadStock()
        }
        ProductHandler.shared().setup(productViewModel)
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