package com.solute.ui.business

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.solute.R
import com.solute.databinding.ActivityBusinessBinding
import com.solute.ui.business.receipt.ReceiptDetailsActivity
import com.utilitykit.UtilityActivity
import com.utilitykit.feature.cart.handler.CartHandler
import com.utilitykit.feature.cart.viewModel.CartViewModalFactory
import com.utilitykit.feature.cart.viewModel.CartViewModel
import com.utilitykit.feature.customer.handler.CustomerHandler
import com.utilitykit.feature.customer.viewModel.CustomerViewModalFactory
import com.utilitykit.feature.customer.viewModel.CustomerViewModel

class BusinessActivity : UtilityActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityBusinessBinding
    private lateinit var cartViewModal: CartViewModel
    private lateinit var customerViewModal: CustomerViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBusinessBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarBusiness.toolbar)

//        binding.appBarBusiness.fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
//        }
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_business)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.business_home, R.id.business_sale, R.id.business_cart,R.id.business_stock
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        setUpRequiredModels()

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.business, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_business)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    fun setUpRequiredModels(){
        cartViewModal = ViewModelProvider(
            this,
            CartViewModalFactory(CartHandler.shared().repository)
        )[CartViewModel::class.java]
        CartHandler.shared().setup(cartViewModal)
        CartHandler.shared().activity = this
        CartHandler.shared().targetIntent = Intent(this, ReceiptDetailsActivity::class.java)
//        CartHandler.shared().viewModel?.cartCount?.observe(this){
//            bottomNavigationView?.getOrCreateBadge(R.id.navigation_business_main_cart)!!.number = it
//        }
        CartHandler.shared().viewModel?.resetCart()
        customerViewModal = ViewModelProvider(
            this,
            CustomerViewModalFactory(CustomerHandler.shared().repository)
        )[CustomerViewModel::class.java]
        customerViewModal.fetchAllCustomer()
        CustomerHandler.shared().setup(customerViewModal)

    }
}