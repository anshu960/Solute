package com.solute

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.navigateUp
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.solute.ui.business.create.SelectBusinessTypeActivity
import com.solute.ui.network.ConnectingActivity
import com.utilitykit.UtilityActivity
import com.utilitykit.socket.SocketService

class MainActivity : UtilityActivity() {
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    var floatingButton : FloatingActionButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navHostFragment = supportFragmentManager.findFragmentById(
            R.id.activity_home_fragment_container
        ) as NavHostFragment
        navController = navHostFragment.navController

        // Setup the bottom navigation view with navController
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.business_home_bottom_nav)
        bottomNavigationView.setupWithNavController(navController)

        // Setup the ActionBar with navController and 3 top level destinations
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.navigation_business_home_business, R.id.navigation_business_home_more)
        )
        floatingButton = findViewById(R.id.activity_mmain_floating_btn)
        floatingButton?.setOnClickListener {
            val intent = Intent(this,SelectBusinessTypeActivity::class.java)
            startActivity(intent)
        }
        App.applicationContext().checkForAppUpdate(this)
//        if(SocketService.shared().repository.socketConnectionStatus.value == 0){
//            val intent = Intent(this,ConnectingActivity::class.java)
//            intent.flags = FLAG_ACTIVITY_NEW_TASK or FLAG_ACTIVITY_CLEAR_TASK
//            startActivity(intent)
//        }
        SocketService.shared().verifyIfConnectedOrNot()
        SocketService.shared().repository.socketConnectionStatus.observe(this){
            if(it != null && it == 0){
                val intent = Intent(this,ConnectingActivity::class.java)
                intent.flags = FLAG_ACTIVITY_NEW_TASK or FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        }

    }
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration)
    }

    override fun onBackPressed() {

    }

    override fun onResume() {
        super.onResume()

    }

    override fun onDestroy() {
        super.onDestroy()
        SocketService.shared().repository.isCrashed.postValue(true)
    }
}