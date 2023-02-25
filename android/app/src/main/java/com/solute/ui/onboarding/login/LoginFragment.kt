package com.solute.ui.onboarding.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.friendly.framework.feature.business.handler.BusinessHandler
import com.google.android.material.textfield.TextInputEditText
import com.hbb20.CountryCodePicker
import com.solute.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
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
        BusinessHandler.shared().businessViewModel?.clearAll()
        FirebaseAuthHelper.shared().onAuthStateChange={ state, message->
            if(state == AUTH_STATE.ERROR){
                FirebaseAuthHelper.shared().activity?.stopActivityIndicator()
                FirebaseAuthHelper.shared().activity?.toast(message)
            } else if(state == AUTH_STATE.SENDING_OTP){
                FirebaseAuthHelper.shared().activity?.startActivityIndicator("Sending OTP")
            }else if(state == AUTH_STATE.OTP_SENT){
                FirebaseAuthHelper.shared().activity?.runOnUiThread {
                    FirebaseAuthHelper.shared().activity?.stopActivityIndicator()
                    FirebaseAuthHelper.shared().activity?.toast("OTP Sent")
                    FirebaseAuthHelper.shared().activity?.gotToOtp()
                }
            }
        }
        return view
    }


    fun onClickLogin(){
        val phoneNumber = phoneField?.text.toString()
        if(phoneNumber.length < 10){
            FirebaseAuthHelper.shared().activity?.alert("Oops!","Please enter your mobile number")
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