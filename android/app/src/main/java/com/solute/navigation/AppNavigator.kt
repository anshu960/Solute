package com.solute.navigation

import android.view.View
import androidx.navigation.NavController
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.solute.R
import com.solute.app.App
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class AppNavigator {
    init {
        instance = this
    }
    companion object{
        private var instance: AppNavigator? = null
        fun shared() : AppNavigator {
            if(instance != null){
                return instance as AppNavigator
            }else{
                return AppNavigator()
            }
        }
    }
    var navController: NavController? = null
    var mAdView: AdView? = null
    private fun reloadAdd(){
        if(mAdView?.visibility == View.VISIBLE){
            val adRequest = AdRequest.Builder().build()
            mAdView?.loadAd(adRequest)
        }
    }
    fun goBack(){
        CoroutineScope(Job() + Dispatchers.Main).launch {
            App.shared().mainActivity?.onBackPressed()
            reloadAdd()
        }
    }
    fun navigateToHome() {
        CoroutineScope(Job() + Dispatchers.Main).launch {
            navController?.navigate(R.id.got_to_home)
            reloadAdd()
        }
    }

    fun navigateToCustomerHome() {
        CoroutineScope(Job() + Dispatchers.Main).launch {
//            navController?.navigate(R.id.got_to_home)
            reloadAdd()
        }
    }

    fun navigateToSale() {
        CoroutineScope(Job() + Dispatchers.Main).launch {
            navController?.navigate(R.id.fragment_business_sale)
            reloadAdd()
        }
    }

    fun navigateToInventoryProduct() {
        CoroutineScope(Job() + Dispatchers.Main).launch {
            navController?.navigate(R.id.inventory_product)
            reloadAdd()
        }
    }

    fun actionCreateProductSuccess(){
        CoroutineScope(Job() + Dispatchers.Main).launch {
            navController?.navigate(R.id.action_product_create_success)
            reloadAdd()
        }
    }
    fun navigateToInventoryCategory() {
        CoroutineScope(Job() + Dispatchers.Main).launch {
            navController?.navigate(R.id.inventory_category)
            reloadAdd()
        }
    }

    fun navigateToInventorySubCategory() {
        CoroutineScope(Job() + Dispatchers.Main).launch {
            navController?.navigate(R.id.inventory_sub_category)
            reloadAdd()
        }
    }

    fun navigateToBusinessStock() {
        CoroutineScope(Job() + Dispatchers.Main).launch {
            navController?.navigate(R.id.business_stock)
            reloadAdd()
        }
    }

    fun navigateToMyBusinessProfile(){
        CoroutineScope(Job() + Dispatchers.Main).launch {
            navController?.navigate(R.id.my_business_profile)
            reloadAdd()
        }
    }


    fun navigateToEmployeeList() {
        CoroutineScope(Job() + Dispatchers.Main).launch {
            navController?.navigate(R.id.business_employee)
            reloadAdd()
        }
    }
    fun navigateToInvoices(){
        CoroutineScope(Job() + Dispatchers.Main).launch {
            navController?.navigate(R.id.business_invoice)
            reloadAdd()
        }
    }
    fun navigateToSelectCustomer(){
        CoroutineScope(Job() + Dispatchers.Main).launch {
            navController?.navigate(R.id.business_select_customer)
            reloadAdd()
        }
    }

    fun navigateToSelectBusinessType() {
        CoroutineScope(Job() + Dispatchers.Main).launch {
            navController?.navigate(R.id.navigation_select_business_type)
            reloadAdd()
        }
    }

    fun gotToProfile() {
        CoroutineScope(Job() + Dispatchers.Main).launch {
            navController?.navigate(R.id.navigation_profile)
            reloadAdd()
        }
    }

    fun gotToCreateBusiness() {
        CoroutineScope(Job() + Dispatchers.Main).launch {
            navController?.navigate(R.id.navigation_create_business)
            reloadAdd()
        }
    }

    fun gotToCart(){
        CoroutineScope(Job() + Dispatchers.Main).launch {
            navController?.navigate(R.id.fragment_business_cart)
            reloadAdd()
        }
    }

    fun gotToSelectBusinessType() {
        CoroutineScope(Job() + Dispatchers.Main).launch {
            navController?.navigate(R.id.navigation_select_business_type)
            reloadAdd()
        }
    }

    fun gotToLogin() {
        CoroutineScope(Job() + Dispatchers.Main).launch {
            navController?.navigate(R.id.got_to_login)
            reloadAdd()
        }
    }

    fun gotToOtp() {
        CoroutineScope(Job() + Dispatchers.Main).launch {
            navController?.navigate(R.id.enter_otp)
            reloadAdd()
        }
    }

    fun goToSelectUserType(){
        CoroutineScope(Job() + Dispatchers.Main).launch {
            navController?.navigate(R.id.select_user_type)
            reloadAdd()
        }
    }

    fun gotToRegister() {
        CoroutineScope(Job() + Dispatchers.Main).launch {
            navController?.navigate(R.id.register)
            reloadAdd()
        }
    }

    fun navigateToBusinessDashboard() {
        CoroutineScope(Job() + Dispatchers.Main).launch {
            navController?.navigate(R.id.business_dashboard)
            reloadAdd()
        }
    }

    fun navigateToCustomerList() {
        CoroutineScope(Job() + Dispatchers.Main).launch {
            navController?.navigate(R.id.business_customers)
            reloadAdd()
        }
    }

    fun navigateToBusinessList(){
        CoroutineScope(Job() + Dispatchers.Main).launch {
            navController?.navigate(R.id.business_list)
            reloadAdd()
        }
    }


    fun goToReceiptDetails() {
        CoroutineScope(Job() + Dispatchers.Main).launch {
            navController?.navigate(R.id.go_to_new_invoice_details)
            reloadAdd()
        }
    }
    fun goToCategoryDetails() {
        CoroutineScope(Job() + Dispatchers.Main).launch {
            navController?.navigate(R.id.fragment_category_details)
            reloadAdd()
        }
    }
    fun goToCreateProductCategory(){
        CoroutineScope(Job() + Dispatchers.Main).launch {
            navController?.navigate(R.id.fragment_create_product_category)
            reloadAdd()
        }
    }
    fun goToCreateProductSubCategory(){
        CoroutineScope(Job() + Dispatchers.Main).launch {
            navController?.navigate(R.id.fragment_create_product_sub_category)
            reloadAdd()
        }
    }
    fun goToCreateProductSubCategoryDetails(){
        CoroutineScope(Job() + Dispatchers.Main).launch {
            navController?.navigate(R.id.fragment_product_sub_category_details)
            reloadAdd()
        }
    }

    fun gotToCreateProductInventory(){
        CoroutineScope(Job() + Dispatchers.Main).launch {
            navController?.navigate(R.id.fragment_create_product_inventory)
            reloadAdd()
        }
    }
    fun gotToCreateProductInventoryMeasurementUnitSelection(){
        CoroutineScope(Job() + Dispatchers.Main).launch {
            navController?.navigate(R.id.select_measurement_unit)
            reloadAdd()
        }
    }



}