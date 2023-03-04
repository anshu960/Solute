package com.solute

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.friendly.framework.Defaults
import com.friendly.framework.UtilityActivity
import com.friendly.framework.app.FriendlyFrameworkApp
import com.friendly.framework.constants.KeyConstant
import com.friendly.framework.feature.business.handler.BusinessHandler
import com.friendly.framework.socket.SocketService
import com.friendly.framework.socket.repository.CONNECTION_STATUS
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.firebase.dynamiclinks.PendingDynamicLinkData
import com.google.firebase.dynamiclinks.ktx.dynamicLinks
import com.google.firebase.ktx.Firebase
import com.solute.app.App
import com.solute.databinding.ActivityMainBinding

class MainActivity : UtilityActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var navView:NavigationView
    lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    var floatingButton: FloatingActionButton? = null
    lateinit var mAdView: AdView
    lateinit var binding : ActivityMainBinding
    var networkStatusCard : CardView? = null
    var networkStatus : TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        App.shared().mainActivity = this
        BusinessHandler.shared().mainActivity = this
        networkStatusCard = findViewById(R.id.connection_status_card_view)
        networkStatus = findViewById(R.id.connection_status_text)
        mAdView = findViewById(R.id.addView)
        floatingButton?.setOnClickListener {
            gotToSelectBusinessType()
        }
        setSupportActionBar(binding.appBar.toolBarMain)
        val drawerLayout: DrawerLayout = binding.drawerLayoutMain
        navView = binding.navView
        navController = findNavController(R.id.nav_host_fragment_main)
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.business_home,
                R.id.business_list,
                R.id.business_sale,
                R.id.business_cart,
                R.id.business_stock,
                R.id.business_invoice,
                R.id.inventory_product,
                R.id.inventory_category,
                R.id.inventory_sub_category,
                R.id.business_employee,
                R.id.business_customers
            ), drawerLayout
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.setHomeButtonEnabled(true)

        App.shared().checkForAppUpdate(this)
        SocketService.shared().currentActivity = this
        SocketService.shared().verifyIfConnectedOrNot()
        SocketService.shared().repository.socketConnectionStatus.observe(this) {connectionStatus->
            when (connectionStatus) {
                CONNECTION_STATUS.CONNECTED -> {
                    networkStatusCard?.visibility = View.VISIBLE
                    networkStatus?.text = "Connected"
                    networkStatusCard?.setCardBackgroundColor(ContextCompat.getColor(this, R.color.colorThemePrimary))
                }
                CONNECTION_STATUS.CONNECTING -> {
                    networkStatus?.text = "Connecting"
                    networkStatusCard?.setCardBackgroundColor(ContextCompat.getColor(this, R.color.darkGray))
                    networkStatusCard?.visibility = View.VISIBLE
                }
                else -> { // Note the block
                    networkStatus?.text = "You are Offline"
                    networkStatusCard?.setCardBackgroundColor(ContextCompat.getColor(this, R.color.red))
                    networkStatusCard?.visibility = View.VISIBLE
                }
            }
        }
        FriendlyFrameworkApp.shared().setUp(this)
        checkDynamicLink()
        checkUserState()
    }

    fun checkDynamicLink() {
        Firebase.dynamicLinks
            .getDynamicLink(intent)
            .addOnSuccessListener(this) { pendingDynamicLinkData: PendingDynamicLinkData? ->
                // Get deep link from result (may be null if no link is found)
                var deepLink: Uri? = null
                if (pendingDynamicLinkData != null) {
                    deepLink = pendingDynamicLinkData.link
                }
            }
            .addOnFailureListener(this) { e -> Log.w(TAG, "getDynamicLink:onFailure", e) }
    }

    fun hideSideMenu(){
        binding.appBar.toolBarMain.visibility = View.GONE
    }
    fun showSideMenu(){
        binding.appBar.toolBarMain.visibility = View.VISIBLE
    }


    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration)
    }

    fun checkUserState() {
        val seenAppStory = Defaults.shared().string(KeyConstant.appStorySeen)
        val loginDetails = Defaults.shared().string(KeyConstant.loginDetails)
        val inflater = navController.navInflater
        val graph = inflater.inflate(R.navigation.main_navigation)
        if (seenAppStory.isEmpty()) {
            graph.setStartDestination(R.id.app_story)
            mAdView?.visibility = View.GONE
        } else if (loginDetails.isEmpty()) {
            graph.setStartDestination(R.id.got_to_login)
            mAdView?.visibility = View.GONE
        } else {
            val adRequest = AdRequest.Builder().build()
            mAdView.loadAd(adRequest)
            mAdView?.visibility = View.VISIBLE
            graph.setStartDestination(R.id.business_list)
        }
        navController.setGraph(graph, intent.extras)
    }

    fun navigateToHome() {
        navController?.navigate(R.id.got_to_home)
    }
    fun navigateToSelectCustomer(){
        navController?.navigate(R.id.business_select_customer)
    }

    fun navigateToSelectBusinessType() {
        navController?.navigate(R.id.navigation_select_business_type)
    }

    fun gotToProfile() {
        navController?.navigate(R.id.navigation_profile)
    }

    fun gotToCreateBusiness() {
        navController?.navigate(R.id.navigation_create_business)
    }

    fun gotToCart(){
        navController?.navigate(R.id.business_cart)
    }

    fun gotToSelectBusinessType() {
        navController?.navigate(R.id.navigation_select_business_type)
    }

    fun gotToLogin() {
        navController.navigate(R.id.got_to_login)
    }

    fun gotToOtp() {
        navController.navigate(R.id.enter_otp)
    }

    fun gotToRegister() {
        navController.navigate(R.id.register)
    }

    fun goToBusinessHome() {
        navController?.navigate(R.id.business_home)
    }
    fun goToReceiptDetails() {
        navController?.navigate(R.id.business_details_invoice_details)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return false
    }

    fun setBusinessMenu(){
        navView.menu.clear()
        MenuInflater(this).inflate(R.menu.business,navView.menu)
    }
    fun setMainMenu(){
        navView.menu.clear()
        MenuInflater(this).inflate(R.menu.main_menu,navView.menu)
    }


}