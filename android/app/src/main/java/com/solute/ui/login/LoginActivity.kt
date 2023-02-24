package com.solute.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.hbb20.CountryCodePicker
import com.solute.R
import com.utilitykit.UtilityActivity
import com.utilitykit.feature.business.handler.BusinessHandler

class LoginActivity : UtilityActivity() {

    private lateinit var auth: FirebaseAuth
    var selectedCountryCode = ""
    var verifyBtn : Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth = FirebaseAuth.getInstance()
        FirebaseAuthHelper.shared().setUp()
        verifyBtn = findViewById(R.id.login_verify_btn)
        verifyBtn?.setOnClickListener { onClickLogin() }
        FirebaseAuthHelper.shared().activity = this
        inits()
    }

    override fun onResume() {
        super.onResume()
        FirebaseAuthHelper.shared().activity = this
    }

    fun inits(){
        val countryCodePicker = findViewById<CountryCodePicker>(R.id.countrycode)
        countryCodePicker.setDefaultCountryUsingNameCode("DE")
        countryCodePicker.setOnCountryChangeListener {
            print(countryCodePicker.selectedCountryCode)
            this.selectedCountryCode = countryCodePicker.selectedCountryCode
        }
        BusinessHandler.shared().businessViewModel?.clearAll()
        FirebaseAuthHelper.shared().onAuthStateChange={ state, message->
            if(state == AUTH_STATE.ERROR){
                this.stopActivityIndicator()
                toast(message)
            } else if(state == AUTH_STATE.SENDING_OTP){
                startActivityIndicator("Sending OTP")
            }else if(state == AUTH_STATE.OTP_SENT){
                this.runOnUiThread {
                    this.stopActivityIndicator()
                    toast("OTP Sent")
                    val intent = Intent(this,OtpActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }

    fun onClickLogin(){
        val phoneNumber = findViewById<TextInputEditText>(R.id.mobile_text_field).text.toString()
        if(phoneNumber.length < 10){
            this.alert("Oops!","Please enter your mobile number")
        }else{
            FirebaseAuthHelper.shared().phoneNumber = phoneNumber
            FirebaseAuthHelper.shared().dialCode = getDialCode()
            FirebaseAuthHelper.shared().sendOtp()
        }
    }
    fun getDialCode():String{
        if(selectedCountryCode == ""){
            val countryCodePicker = findViewById<CountryCodePicker>(R.id.countrycode)
            this.selectedCountryCode = countryCodePicker.selectedCountryCode
        }
        return this.selectedCountryCode
    }
}
