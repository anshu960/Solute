package com.solute.ui.business.product.detail

import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.solute.R


class ProductDetailActivity : AppCompatActivity() {
    var navHostFragment : NavHostFragment? = null
    var bottomNavigationView :  BottomNavigationView? = null
    var navController : NavController? = null
    private lateinit var appBarConfiguration: AppBarConfiguration

    var backButton : ImageButton? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)
        backButton = findViewById(R.id.activity_product_details_header_back)
        backButton?.setOnClickListener { onBackPressed() }
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
    }
    override fun onSupportNavigateUp(): Boolean {
        return navController!!.navigateUp(appBarConfiguration)
    }


}