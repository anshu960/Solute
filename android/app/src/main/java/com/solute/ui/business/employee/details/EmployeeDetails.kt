package com.solute.ui.business.employee.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.solute.R

class EmployeeDetailsActivity : AppCompatActivity() {
    var backButton : ImageButton? = null
    var navHostFragment : NavHostFragment? = null
    var bottomNavigationView :  BottomNavigationView? = null
    var navController : NavController? = null
    private lateinit var appBarConfiguration: AppBarConfiguration
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employee_details)
        backButton = findViewById(R.id.employee_details_activity_toolbar_back_btn)
        backButton?.setOnClickListener { this.finish() }
        navHostFragment = supportFragmentManager.findFragmentById(
            R.id.activity_employee_details_container
        ) as NavHostFragment
        navController = navHostFragment!!.navController
        // Setup the bottom navigation view with navController
        bottomNavigationView = findViewById(R.id.employee_details_activity_bottom_menu)
        bottomNavigationView?.setupWithNavController(navController!!)
        // Setup the ActionBar with navController and 3 top level destinations
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.employee_details_menu_info)
        )
    }
    override fun onSupportNavigateUp(): Boolean {
        return navController!!.navigateUp(appBarConfiguration)
    }
}