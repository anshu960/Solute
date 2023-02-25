package com.solute.ui.business.employee.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.friendly.framework.feature.employee.handler.EmployeeHandler
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.solute.R

class EmployeeDetailContainerFragment : Fragment() {
    var navHostFragment : NavHostFragment? = null
    var bottomNavigationView :  BottomNavigationView? = null
    var navController : NavController? = null
    private lateinit var appBarConfiguration: AppBarConfiguration
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_employee_detail_container, container, false)
        navHostFragment = childFragmentManager.findFragmentById(R.id.activity_employee_details_container) as NavHostFragment
        navController = navHostFragment?.navController
        // Setup the bottom navigation view with navController
        bottomNavigationView = view.findViewById(R.id.employee_details_activity_bottom_menu)
        bottomNavigationView?.setupWithNavController(navController!!)
        // Setup the ActionBar with navController and 3 top level destinations
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.employee_details_menu_info)
        )
        EmployeeHandler.shared().viewModel?.employee?.value?.let {
            EmployeeHandler.shared().viewModel?.loadAttendanceFor(
                it
            )
        }
        return view
    }
}