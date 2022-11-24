package com.solute.ui.business.product.detail

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.solute.R
import com.solute.ui.business.BusinessActivity
import com.utilitykit.UtilityActivity
import com.utilitykit.feature.business.handler.BusinessHandler
import com.utilitykit.feature.product.handler.ProductHandler


class ProductDetailActivity : UtilityActivity() {
    val activity = BusinessHandler.shared().activity as? BusinessActivity
    var navHostFragment : NavHostFragment? = null
    var bottomNavigationView :  BottomNavigationView? = null
    var navController : NavController? = null
    private lateinit var appBarConfiguration: AppBarConfiguration

    var backButton : ImageButton? = null
    var deleteButton : ImageButton? = null
    var fabButton : FloatingActionButton? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)
        backButton = findViewById(R.id.activity_product_details_header_back)
        backButton?.setOnClickListener { onBackPressed() }
        deleteButton = findViewById(R.id.activity_product_details_delete_btn)
        deleteButton?.setOnClickListener { onClickDeleteProduct() }
        fabButton = findViewById(R.id.activity_product_details_fab)

        navHostFragment = supportFragmentManager.findFragmentById(
            R.id.activity_product_details_container
        ) as NavHostFragment
        navController = navHostFragment!!.navController
        // Setup the bottom navigation view with navController
        bottomNavigationView = findViewById<BottomNavigationView>(R.id.activity_product_details_menu)
        bottomNavigationView?.setupWithNavController(navController!!)
        // Setup the ActionBar with navController and 3 top level destinations
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.product_details_menu_details,R.id.product_details_menu_price, R.id.product_details_menu_stock)
        )
//        bottomNavigationView?.setOnNavigationItemSelectedListener { item ->
//            when (item.itemId) {
//                R.id.product_details_menu_details -> {
//                    fabButton?.visibility = View.VISIBLE
//                }
//                R.id.product_details_menu_price -> {
//                    fabButton?.visibility = View.VISIBLE
//                }
//                R.id.product_details_menu_stock -> {
//                    fabButton?.visibility = View.GONE
//                }
//            }
//            true
//        }
        fabButton?.setOnClickListener { onClickEdit() }
    }
    override fun onSupportNavigateUp(): Boolean {
        return navController!!.navigateUp(appBarConfiguration)
    }

    fun onClickDeleteProduct(){
        AlertDialog.Builder(this)
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
                    this.runOnUiThread {
                        toastLong("Product Deleted Successfully");
                        super.onBackPressed()
                    }
                }
            }
            ProductHandler.shared().productViewModel?.deleteProduct(ProductHandler.shared().repository.selectedProduct.value!!)
        }
    }

}