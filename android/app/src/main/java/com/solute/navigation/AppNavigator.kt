package com.solute.navigation

import androidx.navigation.NavController
import com.friendly.framework.feature.address.handler.AddressHandler
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

    fun goBack(){
        CoroutineScope(Job() + Dispatchers.Main).launch {
            App.shared().mainActivity?.onBackPressed()
        }
    }
    fun navigateToHome() {
        CoroutineScope(Job() + Dispatchers.Main).launch {
            navController?.navigate(R.id.got_to_home)
        }
    }

    fun navigateToCustomerHome() {
        CoroutineScope(Job() + Dispatchers.Main).launch {
//            navController?.navigate(R.id.got_to_home)
        }
    }

    fun navigateToSale() {
        CoroutineScope(Job() + Dispatchers.Main).launch {
            navController?.navigate(R.id.fragment_business_sale)
        }
    }

    fun navigateToInventoryProduct() {
        CoroutineScope(Job() + Dispatchers.Main).launch {
            navController?.navigate(R.id.inventory_product)
        }
    }

    fun actionCreateProductSuccess(){
        CoroutineScope(Job() + Dispatchers.Main).launch {
            navController?.navigate(R.id.action_product_create_success)
        }

    }
    fun navigateToInventoryCategory() {
        CoroutineScope(Job() + Dispatchers.Main).launch {
            navController?.navigate(R.id.inventory_category)
        }
    }

    fun navigateToInventorySubCategory() {
        CoroutineScope(Job() + Dispatchers.Main).launch {
            navController?.navigate(R.id.inventory_sub_category)
        }
    }

    fun navigateToBusinessStock() {
        CoroutineScope(Job() + Dispatchers.Main).launch {
            navController?.navigate(R.id.business_stock)
        }
    }

    fun navigateToMyBusinessProfile(){
        CoroutineScope(Job() + Dispatchers.Main).launch {
            navController?.navigate(R.id.my_business_profile)
        }
    }


    fun navigateToEmployeeList() {
        CoroutineScope(Job() + Dispatchers.Main).launch {
            navController?.navigate(R.id.business_employee)
        }
    }
    fun navigateToInvoices(){
        CoroutineScope(Job() + Dispatchers.Main).launch {
            navController?.navigate(R.id.business_invoice)
        }
    }
    fun navigateToSelectCustomer(){
        CoroutineScope(Job() + Dispatchers.Main).launch {
            navController?.navigate(R.id.business_select_customer)
        }
    }

    fun navigateToSelectBusinessType() {
        CoroutineScope(Job() + Dispatchers.Main).launch {
            navController?.navigate(R.id.navigation_select_business_type)
        }
    }

    fun gotToProfile() {
        CoroutineScope(Job() + Dispatchers.Main).launch {
            navController?.navigate(R.id.navigation_profile)
        }
    }

    fun gotToCreateBusiness() {
        CoroutineScope(Job() + Dispatchers.Main).launch {
            navController?.navigate(R.id.navigation_create_business)
        }
    }

    fun gotToCart(){
        CoroutineScope(Job() + Dispatchers.Main).launch {
            navController?.navigate(R.id.fragment_business_cart)
        }
    }

    fun gotToSelectBusinessType() {
        CoroutineScope(Job() + Dispatchers.Main).launch {
            navController?.navigate(R.id.navigation_select_business_type)
        }
    }

    fun gotToLogin() {
        CoroutineScope(Job() + Dispatchers.Main).launch {
            navController?.navigate(R.id.got_to_login)
        }
    }

    fun gotToOtp() {
        CoroutineScope(Job() + Dispatchers.Main).launch {
            navController?.navigate(R.id.enter_otp)
        }
    }

    fun goToSelectUserType(){
        CoroutineScope(Job() + Dispatchers.Main).launch {
            navController?.navigate(R.id.select_user_type)
        }
    }

    fun gotToRegister() {
        CoroutineScope(Job() + Dispatchers.Main).launch {
            navController?.navigate(R.id.register)
        }
    }

    fun navigateToBusinessDashboard() {
        CoroutineScope(Job() + Dispatchers.Main).launch {
            navController?.navigate(R.id.business_dashboard)
        }
    }

    fun navigateToCustomerList() {
        CoroutineScope(Job() + Dispatchers.Main).launch {
            navController?.navigate(R.id.business_customers)
        }
    }

    fun navigateToBusinessList(){
        CoroutineScope(Job() + Dispatchers.Main).launch {
            navController?.navigate(R.id.business_list)
        }
    }


    fun goToReceiptDetails() {
        CoroutineScope(Job() + Dispatchers.Main).launch {
            navController?.navigate(R.id.go_to_new_invoice_details)
        }
    }
    fun goToCategoryDetails() {
        CoroutineScope(Job() + Dispatchers.Main).launch {
            navController?.navigate(R.id.fragment_category_details)
        }
    }
    fun goToCreateProductCategory(){
        CoroutineScope(Job() + Dispatchers.Main).launch {
            navController?.navigate(R.id.fragment_create_product_category)
        }
    }
    fun goToCreateProductSubCategory(){
        CoroutineScope(Job() + Dispatchers.Main).launch {
            navController?.navigate(R.id.fragment_create_product_sub_category)
        }
    }
    fun goToCreateProductSubCategoryDetails(){
        CoroutineScope(Job() + Dispatchers.Main).launch {
            navController?.navigate(R.id.fragment_product_sub_category_details)
        }
    }

    fun gotToCreateProductInventory(){
        CoroutineScope(Job() + Dispatchers.Main).launch {
            navController?.navigate(R.id.fragment_create_product_inventory)
        }
    }


}