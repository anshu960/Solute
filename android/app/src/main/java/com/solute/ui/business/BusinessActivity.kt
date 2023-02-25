package com.solute.ui.business

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.friendly.framework.UtilityActivity
import com.friendly.framework.feature.business.handler.BusinessHandler
import com.friendly.framework.feature.cart.handler.CartHandler
import com.friendly.framework.feature.cart.viewModel.CartViewModalFactory
import com.friendly.framework.feature.cart.viewModel.CartViewModel
import com.friendly.framework.feature.customer.handler.CustomerHandler
import com.friendly.framework.feature.customer.viewModel.CustomerViewModalFactory
import com.friendly.framework.feature.customer.viewModel.CustomerViewModel
import com.friendly.framework.feature.invoice.handler.InvoiceHandler
import com.friendly.framework.feature.invoice.viewModel.InvoiceViewModalFactory
import com.friendly.framework.feature.invoice.viewModel.InvoiceViewModel
import com.friendly.framework.feature.mediaFile.handler.MediaFileHandler
import com.friendly.framework.feature.product.handler.ProductHandler
import com.friendly.framework.feature.product.viewModel.ProductViewModalFactory
import com.friendly.framework.feature.product.viewModel.ProductViewModel
import com.friendly.framework.feature.productCategory.handler.ProductCategoryHandler
import com.friendly.framework.feature.productCategory.viewModel.ProductCategoryViewModalFactory
import com.friendly.framework.feature.productCategory.viewModel.ProductCategoryViewModel
import com.friendly.framework.feature.productSubCategory.handler.ProductSubCategoryHandler
import com.friendly.framework.feature.productSubCategory.viewModel.ProductSubCategoryViewModalFactory
import com.friendly.framework.feature.productSubCategory.viewModel.ProductSubCategoryViewModel
import com.friendly.framework.feature.sync.SyncHandler
import com.google.android.material.navigation.NavigationView
import com.solute.R
import com.solute.databinding.ActivityBusinessBinding
import com.squareup.picasso.Picasso

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class BusinessActivity : UtilityActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityBusinessBinding
    private lateinit var productViewModal: ProductViewModel
    private lateinit var cartViewModal: CartViewModel
    private lateinit var invoiceViewModal: InvoiceViewModel
    private lateinit var customerViewModal: CustomerViewModel
    private lateinit var productCategoryViewModel: ProductCategoryViewModel
    private lateinit var productSubCategoryViewModel: ProductSubCategoryViewModel

    val picasso = Picasso.get()
    private lateinit var navView: NavigationView
    lateinit var navController: NavController

    var businessIconImage : ImageView? = null
    var businessName : TextView? = null
    var businessDescription : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBusinessBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarBusiness.toolbar)
        BusinessHandler.shared().activity = this
//        binding.appBarBusiness.fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
//        }
        val drawerLayout: DrawerLayout = binding.drawerLayout
         navView = binding.navView
         navController = findNavController(R.id.nav_host_fragment_content_business)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.business_home,
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
        setUpRequiredModels()
        val headerView: View = navView.getHeaderView(0)
        businessIconImage = headerView.findViewById(R.id.nav_header_business_img)
        businessName = headerView.findViewById(R.id.nav_header_business_name)
        businessDescription = headerView.findViewById(R.id.nav_header_business_description)
        businessName?.text = BusinessHandler.shared().repository.business.value?.Name
        businessDescription?.text = BusinessHandler.shared().repository.business.value?.Address
        if(BusinessHandler.shared().repository.business.value?.Id != null){
            MediaFileHandler.shared().viewModel?.loadFor(BusinessHandler.shared().repository.business.value!!.Id){
                CoroutineScope(Job() + Dispatchers.Main).launch {
                    picasso.load(it.first().FileURL).into(businessIconImage)
                }
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.business, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        if(item.itemId == R.id.action_settings){
//            toastLong("settings")
//        }
        return when(item.itemId){
            R.id.action_settings -> {
                navController.navigate(R.id.my_business_profile)
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    fun onClickSettings(){
        toastLong("Settings")
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_business)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    fun setUpRequiredModels(){
        //Product
        productViewModal = ViewModelProvider(this,
            ProductViewModalFactory(ProductHandler.shared().repository)
        )[ProductViewModel::class.java]
        productViewModal.fetchAllProduct()
        ProductHandler.shared().setup(productViewModal)
        //Cart
        cartViewModal = ViewModelProvider(
            this,
            CartViewModalFactory(CartHandler.shared().repository)
        )[CartViewModel::class.java]
        CartHandler.shared().setup(cartViewModal)
        CartHandler.shared().activity = this
        CartHandler.shared().viewModel?.resetCart()
        //Invoice
        invoiceViewModal = ViewModelProvider(
            this,
            InvoiceViewModalFactory(InvoiceHandler.shared().repository)
        )[InvoiceViewModel::class.java]
        invoiceViewModal.fetchAllInvoice()
        //Custommer
        customerViewModal = ViewModelProvider(
            this,
            CustomerViewModalFactory(CustomerHandler.shared().repository)
        )[CustomerViewModel::class.java]
        CustomerHandler.shared().setup(customerViewModal)
        customerViewModal.fetchAllCustomer()
        //category
        productCategoryViewModel = ViewModelProvider(
            this,
            ProductCategoryViewModalFactory(ProductCategoryHandler.shared().repository)
        ).get(
            ProductCategoryViewModel::class.java
        )
        ProductCategoryHandler.shared().setup(productCategoryViewModel)
        productCategoryViewModel.loadCategory()
        //sub category
        productSubCategoryViewModel = ViewModelProvider(
            this,
            ProductSubCategoryViewModalFactory(ProductSubCategoryHandler.shared().repository)
        ).get(
            ProductSubCategoryViewModel::class.java
        )
        ProductSubCategoryHandler.shared().setup(productSubCategoryViewModel)
        productSubCategoryViewModel.loadSubCategory()

        productSubCategoryViewModel.loadSubCategory()
        //Sync Everything
        SyncHandler.shared().syncAllBusinessData()
    }
    fun goToCart(){
        navController.navigate(R.id.business_cart)
    }
}
