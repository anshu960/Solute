package com.solute.ui.onboarding.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.friendly.framework.Defaults
import com.friendly.framework.constants.KeyConstant
import com.friendly.framework.feature.business.handler.BusinessHandler
import com.google.android.material.textfield.TextInputEditText
import com.hbb20.CountryCodePicker
import com.solute.R
import com.solute.app.App
import com.solute.navigation.AppNavigator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class LoginFragment : Fragment() {
    var selectedCountryCode = ""
    var verifyBtn : Button? = null
    var phoneField :TextInputEditText? = null
    var countryCodePicker:CountryCodePicker? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        verifyBtn = view.findViewById(R.id.login_verify_btn)
        verifyBtn?.setOnClickListener { onClickLogin() }
        phoneField = view.findViewById(R.id.mobile_text_field)
        countryCodePicker = view.findViewById(R.id.countrycode)
        val countryCodePicker = view.findViewById<CountryCodePicker>(R.id.countrycode)
        countryCodePicker.setDefaultCountryUsingNameCode("DE")
        countryCodePicker.setOnCountryChangeListener {
            print(countryCodePicker.selectedCountryCode)
            this.selectedCountryCode = countryCodePicker.selectedCountryCode
        }
        BusinessHandler.shared().viewModal?.clearAll()
        FirebaseAuthHelper.shared().onAuthStateChange={ state, message->
            CoroutineScope(Job() + Dispatchers.Main).launch {
                if(state == AUTH_STATE.ERROR){
                    App.shared().mainActivity?.stopActivityIndicator()
                    App.shared().mainActivity?.toast(message)
                } else if(state == AUTH_STATE.SENDING_OTP){
                    App.shared().mainActivity?.startActivityIndicator("Sending OTP")
                }else if(state == AUTH_STATE.OTP_SENT){
                    App.shared().mainActivity?.runOnUiThread {
                        App.shared().mainActivity?.stopActivityIndicator()
                        App.shared().mainActivity?.toast("OTP Sent")
                        AppNavigator.shared().gotToOtp()
                    }
                }
            }
        }
        App.shared().mainActivity?.hideSideMenu()
        checkAndClearData()
        return view
    }

    fun checkAndClearData(){
        val userCred = Defaults.shared().string(KeyConstant.loginDetails)
        if(!userCred.isNullOrEmpty()){
            Defaults.shared().remove(KeyConstant.loginDetails)
            BusinessHandler.shared().viewModal?.clearAll()
        }
    }


    fun onClickLogin(){
        val phoneNumber = phoneField?.text.toString()
        if(phoneNumber.length < 10){
            App.shared().mainActivity?.alert("Oops!","Please enter your mobile number")
        }else{
            FirebaseAuthHelper.shared().phoneNumber = phoneNumber
            FirebaseAuthHelper.shared().dialCode = getDialCode()
            FirebaseAuthHelper.shared().sendOtp()
        }
    }
    fun getDialCode():String{
        if(selectedCountryCode == ""){
            this.selectedCountryCode = countryCodePicker!!.selectedCountryCode
        }
        return this.selectedCountryCode
    }
}