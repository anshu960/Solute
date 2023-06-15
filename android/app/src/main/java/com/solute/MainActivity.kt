package com.solute

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
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
import com.friendly.framework.dataclass.FriendlyUser
import com.friendly.framework.feature.business.handler.BusinessHandler
import com.friendly.framework.feature.mediaFile.handler.MediaFileHandler
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
import com.solute.app.Membership
import com.solute.databinding.ActivityMainBinding
import com.solute.deepLink.DeepLinkHandler
import com.solute.navigation.AppNavigator
import com.solute.pdf.pdfService.AppPermission.Companion.permissionGranted
import com.solute.pdf.pdfService.AppPermission.Companion.requestPermission
import com.solute.ui.address.AddressUtill
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainActivity : UtilityActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var navView:NavigationView
    lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    var floatingButton: FloatingActionButton? = null
    lateinit var mAdView: AdView
    lateinit var binding : ActivityMainBinding
    var networkStatusCard : CardView? = null
    var networkStatus : TextView? = null
    var drawerMenuHeaderImg : ImageView? = null
    var drawerMenuHeaderName : TextView? = null
    var drawerMenuHeaderDescription : TextView? = null

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
            AppNavigator.shared().gotToSelectBusinessType()
        }
        setSupportActionBar(binding.appBar.toolBarMain)
        val drawerLayout: DrawerLayout = binding.drawerLayoutMain
        navView = binding.navView
        navController = findNavController(R.id.nav_host_fragment_main)
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.fragment_business_home,
                R.id.business_dashboard,
                R.id.my_business_profile,
                R.id.business_list,
                R.id.fragment_business_sale,
                R.id.fragment_business_cart,
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
        drawerMenuHeaderImg = binding.navView.getHeaderView(0).findViewById(R.id.nav_header_business_img)
        drawerMenuHeaderName = binding.navView.getHeaderView(0).findViewById(R.id.nav_header_business_name)
        drawerMenuHeaderDescription = binding.navView.getHeaderView(0).findViewById(R.id.nav_header_business_description)
        AppNavigator.shared().navController = this.navController
        BusinessHandler.shared().repository.business.observe(this){
            drawerMenuHeaderName?.text = it?.Name
            drawerMenuHeaderDescription?.text = AddressUtill().formatToDisplay(it.Address)
            if(it != null){
                MediaFileHandler.shared().viewModel?.loadFor(it.Id){
                    CoroutineScope(Job() + Dispatchers.Main).launch {
                        Picasso.get().load(it.first().FileURL).into(drawerMenuHeaderImg)
                    }
                }
            }
        }
        App.shared().checkForAppUpdate(this)
        SocketService.shared().currentActivity = this
        SocketService.shared().verifyIfConnectedOrNot()
        networkStatusCard?.visibility = View.GONE
        SocketService.shared().repository.socketConnectionStatus.observe(this) {connectionStatus->
            when (connectionStatus) {
                CONNECTION_STATUS.CONNECTED -> {
                    networkStatusCard?.visibility = View.GONE
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
        if (!permissionGranted(this)) requestPermission(this)
    }

    fun checkDynamicLink() {
        Firebase.dynamicLinks
            .getDynamicLink(intent)
            .addOnSuccessListener(this) { pendingDynamicLinkData: PendingDynamicLinkData? ->
                // Get deep link from result (may be null if no link is found)
                var deepLink: Uri? = null
                if (pendingDynamicLinkData != null && pendingDynamicLinkData.link != null) {
                    DeepLinkHandler().processPendingLink(pendingDynamicLinkData.link)
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

    public fun goToHome(item: MenuItem) {
        val drawer : DrawerLayout? = findViewById(R.id.drawer_layout_main);
        drawer?.closeDrawer(GravityCompat.START);
        AppNavigator.shared().navigateToHome()
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
            Membership().isMembershipActive {
                if(it){
                    mAdView?.visibility = View.GONE
                }else{
                    val adRequest = AdRequest.Builder().build()
                    mAdView.loadAd(adRequest)
                    mAdView?.visibility = View.VISIBLE
                }

            }
            graph.setStartDestination(R.id.fragment_business_home)
        }
        navController.setGraph(graph, intent.extras)
    }



    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return true
    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        MenuInflater(this).inflate(R.menu.business,navView.menu)
//        return  true
//    }

    fun setBusinessMenu(){
        navView.menu.clear()
        MenuInflater(this).inflate(R.menu.business,navView.menu)
    }
    fun setMainMenu(){
        navView.menu.clear()
        MenuInflater(this).inflate(R.menu.main_menu,navView.menu)
        val user = FriendlyUser()
        drawerMenuHeaderName?.text = user.name
        drawerMenuHeaderDescription?.text = user.status
        MediaFileHandler.shared().viewModel?.loadFor(user._id){
            CoroutineScope(Job() + Dispatchers.Main).launch {
                Picasso.get().load(it.first().FileURL).into(drawerMenuHeaderImg)
            }
        }
    }


}