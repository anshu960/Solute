package com.bharat.bandhu.ui

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.bharat.bandhu.R
import com.bharat.bandhu.ui.home.account.AccountFragment
import com.bharat.bandhu.ui.home.product.ProductListFragment
import com.friendly.framework.Defaults
import com.friendly.framework.UtilityActivity
import com.friendly.framework.app.FriendlyFrameworkApp
import com.friendly.framework.constants.KeyConstant
import com.friendly.framework.dataclass.FriendlyUser
import com.friendly.framework.feature.business.handler.BusinessHandler
import com.friendly.framework.feature.business.model.Business
import com.friendly.framework.feature.customer.handler.CustomerHandler
import com.friendly.framework.feature.customer.model.Customer
import com.friendly.framework.feature.mediaFile.handler.MediaFileHandler
import com.friendly.framework.socket.SocketEvent
import com.friendly.framework.socket.SocketService
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.json.JSONObject


class MainActivity : UtilityActivity() {

    lateinit var bottomNav : BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        FriendlyFrameworkApp.shared().setUp(this)
        MediaFileHandler.shared().viewModel?.retrieve()
        loadFragment(ProductListFragment())
        bottomNav = findViewById(R.id.bottomNav) as BottomNavigationView
        this.title = "Products"
        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> {
                    loadFragment(ProductListFragment())
                    this.title = "Products"
                    true
                }
                R.id.navigation_account -> {
                    loadFragment(AccountFragment())
                    this.title = "Account"
                    true
                }
                else->{
                    loadFragment(ProductListFragment())
                    this.title = "Products"
                    true
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
    }

    private  fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container,fragment)
        transaction.commit()
    }


}