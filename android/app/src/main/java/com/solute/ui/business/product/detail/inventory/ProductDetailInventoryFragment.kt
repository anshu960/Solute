package com.solute.ui.business.product.detail.inventory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.solute.R
import com.solute.navigation.AppNavigator

class ProductDetailInventoryFragment : Fragment() {

    var recycler : RecyclerView? = null
    var fabBtn : FloatingActionButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_product_detail_inventory, container, false)
        recycler = view.findViewById(R.id.fragment_product_detail_recycle)
        fabBtn = view.findViewById(R.id.fragment_product_detail_fab)
        fabBtn?.setOnClickListener { AppNavigator.shared().gotToCreateProductInventory() }
        return  view
    }


}