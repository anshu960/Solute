package com.solute.ui.onboarding.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.google.firebase.auth.PhoneAuthProvider
import com.solute.MainActivity
import com.solute.R
import com.solute.app.App

class OTPFragment : Fragment() {
    private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    var dialCode : String = ""
    var otpText : EditText? = null
    var verifyBtn : Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_o_t_p, container, false)
        otpText = view.findViewById(R.id.otp_activity_otp_et)
        verifyBtn = view.findViewById(R.id.otp_activity_verify)
        verifyBtn?.setOnClickListener { onClickVerify() }
        initSetup()
        App.shared().mainActivity?.showSideMenu()
        return view
    }
    fun initSetup(){
        FirebaseAuthHelper.shared().onAuthStateChange ={ state, message->
            App.shared().mainActivity?.runOnUiThread {
                if(state == AUTH_STATE.ERROR){
                    App.shared().mainActivity?.stopActivityIndicator()
                    App.shared().mainActivity?.toast(message)
                } else if(state == AUTH_STATE.OTP_VERIFYING){
                    App.shared().mainActivity?.startActivityIndicator(message)
                }else if(state == AUTH_STATE.NEW_USER){
                    App.shared().mainActivity?.stopActivityIndicator()
                    App.shared().mainActivity?.gotToRegister()
                }else if(state == AUTH_STATE.LOGGED_IN){
                    App.shared().mainActivity?.stopActivityIndicator()
                    App.shared().mainActivity?.navigateToHome()
                }
            }
        }
    }

    fun onClickVerify(){
        val otp = otpText?.text.toString()
        if(otp.trim().length == 6){
            FirebaseAuthHelper.shared().verifyOtp(otp)
        }
    }

}