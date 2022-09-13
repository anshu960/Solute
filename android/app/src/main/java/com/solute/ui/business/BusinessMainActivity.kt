package com.solute.ui.business

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.solute.R
import com.solute.ui.business.receipt.ReceiptDetailsActivity
import com.utilitykit.SocketUtill.SocketManager
import com.utilitykit.UtilityActivity
import com.utilitykit.feature.cart.handler.CartHandler
import com.utilitykit.feature.cart.viewModel.CartViewModalFactory
import com.utilitykit.feature.cart.viewModel.CartViewModel

class BusinessMainActivity : UtilityActivity() {
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var cartViewModal: CartViewModel
    var navHostFragment : NavHostFragment? = null
    var bottomNavigationView :  BottomNavigationView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_business_main)

        navHostFragment = supportFragmentManager.findFragmentById(
            R.id.business_main_nav_container
        ) as NavHostFragment
        navController = navHostFragment!!.navController
        // Setup the bottom navigation view with navController
        bottomNavigationView = findViewById<BottomNavigationView>(R.id.business_main_bottom_nav)
        bottomNavigationView?.setupWithNavController(navController)
        // Setup the ActionBar with navController and 3 top level destinations
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.navigation_business_main_product, R.id.navigation_business_main_cart,R.id.navigation_business_main_invoice,)
        )
        setUpRequiredModels()
    }

    override fun onResume() {
        super.onResume()
        SocketManager.verifyIfConnectedOrNot()
    }

    fun setUpRequiredModels(){
        cartViewModal = ViewModelProvider(
            this,
            CartViewModalFactory(CartHandler.shared().repository)
        )[CartViewModel::class.java]
        CartHandler.shared().setup(cartViewModal)
        CartHandler.shared().activity = this
        CartHandler.shared().targetIntent = Intent(this,ReceiptDetailsActivity::class.java)
        CartHandler.shared().viewModel?.cartCount?.observe(this){
            bottomNavigationView?.getOrCreateBadge(R.id.navigation_business_main_cart)!!.number = it
        }
        CartHandler.shared().viewModel?.resetCart()
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration)
    }

}