package com.solute.ui.onboarding

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import com.friendly.framework.UtilityActivity
import com.solute.R
import com.solute.ui.onboarding.login.FirebaseAuthHelper

class OnBoardingActivity : UtilityActivity() {
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding)
        val navHostFragment = supportFragmentManager.findFragmentById(
            R.id.on_boarding_nav_container
        ) as NavHostFragment
        navController = navHostFragment.navController

        // Setup the bottom navigation view with navController
//        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.on_boarding_nav_container)
//        bottomNavigationView.setupWithNavController(navController)

        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.got_to_login, R.id.enter_otp,R.id.register,R.id.app_story)
        )
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration)
    }
    override fun onResume() {
        super.onResume()
    }


}