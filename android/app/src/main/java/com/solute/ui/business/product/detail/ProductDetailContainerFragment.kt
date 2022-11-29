package com.solute.ui.business.product.detail

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.solute.R
import com.solute.ui.business.BusinessActivity
import com.utilitykit.feature.business.handler.BusinessHandler
import com.utilitykit.feature.product.handler.ProductHandler


class ProductDetailContainerFragment : Fragment() {
    val activity = BusinessHandler.shared().activity as? BusinessActivity
    var navHostFragment : NavHostFragment? = null
    var bottomNavigationView :  BottomNavigationView? = null
    var navController : NavController? = null
    private lateinit var appBarConfiguration: AppBarConfiguration

    var deleteButton : FloatingActionButton? = null
    var fabButton : FloatingActionButton? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_product_detail_container, container, false)
        deleteButton = view.findViewById(R.id.activity_product_delete_fab)
        deleteButton?.setOnClickListener { onClickDeleteProduct() }
        fabButton = view.findViewById(R.id.activity_product_details_fab)
        navHostFragment = childFragmentManager.findFragmentById(R.id.activity_product_details_container) as NavHostFragment
        navController = navHostFragment?.navController
        // Setup the bottom navigation view with navController
        bottomNavigationView = view.findViewById(R.id.activity_product_details_menu)
        bottomNavigationView?.setupWithNavController(navController!!)
        // Setup the ActionBar with navController and 3 top level destinations
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.product_details_menu_details,R.id.product_details_menu_price, R.id.product_details_menu_stock)
        )
        fabButton?.setOnClickListener { onClickEdit() }
        return view
    }

    fun onSupportNavigateUp(): Boolean {
        return navController!!.navigateUp(appBarConfiguration)
    }

    fun onClickDeleteProduct(){
        AlertDialog.Builder(activity)
            .setMessage("Are you sure you want to delete ?")
            .setCancelable(false)
            .setPositiveButton("Yes",
                DialogInterface.OnClickListener { dialog, id ->
                    deleteProduct()
                })
            .setNegativeButton("No", null)
            .show()
    }

    fun onClickEdit(){
        activity?.navController?.navigate(R.id.business_product_create)
    }

    fun deleteProduct(){
        if(ProductHandler.shared().repository.selectedProduct.value != null){
            ProductHandler.shared().onDeleteProductCallBack={
                if(it != null){
                    activity?.runOnUiThread {
                       activity?.toastLong("Product Deleted Successfully");
                        activity?.onBackPressed()
                    }
                }
            }
            ProductHandler.shared().viewModel?.deleteProduct(ProductHandler.shared().repository.selectedProduct.value!!)
        }
    }

}