package com.solute.ui.business.profile.self

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.solute.R
import com.solute.ui.business.BusinessActivity
import com.utilitykit.feature.business.handler.BusinessHandler

class SelfBusinessProfileFragment : Fragment() {
    val activity = BusinessHandler.shared().activity as? BusinessActivity
    var navHostFragment : NavHostFragment? = null
    var bottomNavigationView :  BottomNavigationView? = null
    var navController : NavController? = null
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_self_business_profile, container, false)
        navHostFragment = childFragmentManager.findFragmentById(R.id.self_business_fragment_container_view) as NavHostFragment
        navController = navHostFragment?.navController
        bottomNavigationView = view.findViewById(R.id.self_business_menu_view)
        bottomNavigationView?.setupWithNavController(navController!!)
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.business_profile_info,R.id.business_profile_address,R.id.business_profile_finance)
        )
        appBarConfiguration = AppBarConfiguration(setOf())
        return view
    }
    fun onSupportNavigateUp(): Boolean {
        return navController!!.navigateUp(appBarConfiguration)
    }


}
