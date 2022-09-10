package com.solute.ui.business

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.solute.R
import com.utilitykit.database.Database
import com.utilitykit.feature.product.handler.ProductHandler

class BusinessMainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_business_main)

        val navHostFragment = supportFragmentManager.findFragmentById(
            R.id.business_main_nav_container
        ) as NavHostFragment
        navController = navHostFragment.navController

        // Setup the bottom navigation view with navController
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.business_main_bottom_nav)
        bottomNavigationView.setupWithNavController(navController)

        // Setup the ActionBar with navController and 3 top level destinations
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.navigation_business_main_product, R.id.navigation_business_main_cart,R.id.navigation_business_main_invoice,)
        )

        ProductHandler.shared().repository.cartCount.observe(this){
                bottomNavigationView.getOrCreateBadge(R.id.navigation_business_main_cart).number = it
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration)
    }

}