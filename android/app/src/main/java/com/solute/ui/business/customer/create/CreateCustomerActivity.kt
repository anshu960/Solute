package com.solute.ui.business.customer.create

import android.animation.ValueAnimator
import android.app.Activity
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.animation.LinearInterpolator
import android.widget.Button
import android.widget.EditText
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.animation.doOnEnd
import com.google.android.material.textfield.TextInputEditText
import com.solute.R
import com.utilitykit.UtilityActivity
import com.utilitykit.feature.customer.handler.CustomerHandler

class CreateCustomerActivity : UtilityActivity() {
    private var darkPriorityBar = false
    var layout : ConstraintLayout? = null
    var cancelButton : Button? = null
    var saveButton : Button? = null
    var searchButton : Button? = null
    var mobileNumber : EditText? = null
    var customerName : TextInputEditText? = null
    var customerEmail : EditText? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_customer)
        layout = findViewById(R.id.activity_create_customer_layout)
        overridePendingTransition(0, 0)
        layout?.setOnClickListener { slideDown() }
        searchButton = findViewById(R.id.create_customer_activity_search_btn)
        saveButton = findViewById(R.id.create_customer_activity_save_btn)
        cancelButton = findViewById(R.id.create_customer_activity_cancel_btn)
        cancelButton?.setOnClickListener { slideDown() }
//        title = "Add new or existing customer"
        title = ""
        mobileNumber = findViewById(R.id.create_customer_activity_phone_et)
        customerName = findViewById(R.id.create_customer_activity_name_tiet)
        customerEmail = findViewById(R.id.create_customer_activity_email_tiet)
        searchButton?.setOnClickListener { searchCustomer() }
        saveButton?.setOnClickListener { createCustomer() }
        configureWindow()
        CustomerHandler.shared().repository.customerLiveData.observe(this){
            if(it != null && it.Id != null && !it.Id!!.isEmpty()){
                this.slideDown()
            }
        }
    }

    fun createCustomer(){
        val mobileNumber = mobileNumber?.text.toString()
        val name = customerName?.text.toString()
        val email = customerEmail?.text.toString()
        var isValid = true
        if(mobileNumber.isEmpty() || mobileNumber.length < 10){
            isValid = false
            alert("Oops!","Please enter a valid mobile number")
        }
        if( name.isEmpty() && name.length < 3){
            isValid = false
            alert("Oops!","Please enter a valid name of customer")
        }
        if(isValid){
            CustomerHandler.shared().viewModel?.createNewCustomer(name,mobileNumber,email)
        }
    }

    fun searchCustomer(){
        val mobileNumber = mobileNumber?.text.toString()
        if(mobileNumber.isEmpty() || mobileNumber.length < 10){
            alert("Oops!","Please enter a valid mobile number")
        }else{
            CustomerHandler.shared().searchCustomerByMobile(mobileNumber)
        }
    }



    fun configureWindow(){
        // Set the Status bar appearance for different API levels
        if (Build.VERSION.SDK_INT in 19..20) {
            setWindowFlag(this, true)
        }
        if (Build.VERSION.SDK_INT >= 19) {
            window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }
        if (Build.VERSION.SDK_INT >= 21) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                // If you want dark status bar, set darkStatusBar to true
                if (darkPriorityBar) {
                    this.window.decorView.systemUiVisibility =
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                }
                this.window.statusBarColor = Color.TRANSPARENT
                setWindowFlag(this, false)
            }
        }
        slideUp()
    }


    fun slideUp()
    {
        val valueAnimator = ValueAnimator.ofFloat(2000F, 0F)
        valueAnimator.addUpdateListener {
            val value = it.animatedValue as Float
            layout?.translationY = value
        }
        valueAnimator.interpolator = LinearInterpolator()
        valueAnimator.duration = 400
        valueAnimator.doOnEnd {
            layout?.clearAnimation()
        }
        valueAnimator.start()
    }

    fun slideDown()
    {
        val valueAnimator = ValueAnimator.ofFloat(0F, 2000F)
        valueAnimator.addUpdateListener {
            val value = it.animatedValue as Float
            layout?.translationY = value
        }
        valueAnimator.interpolator = LinearInterpolator()
        valueAnimator.duration = 400
        valueAnimator.doOnEnd {
            super.onBackPressed()
            layout?.clearAnimation()
        }
        valueAnimator.start()
    }
    private fun setWindowFlag(activity: Activity, on: Boolean) {
        val win = activity.window
        val winParams = win.attributes
        if (on) {
            winParams.flags = winParams.flags or WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
        } else {
            winParams.flags = winParams.flags and WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS.inv()
        }
        win.attributes = winParams
    }


    override fun onBackPressed() {
        slideDown()
    }
}