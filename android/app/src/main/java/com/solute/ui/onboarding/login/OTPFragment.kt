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
        return view
    }
    fun initSetup(){
        FirebaseAuthHelper.shared().onAuthStateChange ={ state, message->
            FirebaseAuthHelper.shared().activity?.runOnUiThread {
                if(state == AUTH_STATE.ERROR){
                    FirebaseAuthHelper.shared().activity?.stopActivityIndicator()
                    FirebaseAuthHelper.shared().activity?.toast(message)
                } else if(state == AUTH_STATE.OTP_VERIFYING){
                    FirebaseAuthHelper.shared().activity?.startActivityIndicator(message)
                }else if(state == AUTH_STATE.NEW_USER){
                    FirebaseAuthHelper.shared().activity?.stopActivityIndicator()
                    FirebaseAuthHelper.shared().activity?.gotToRegister()
                }else if(state == AUTH_STATE.LOGGED_IN){
                    FirebaseAuthHelper.shared().activity?.stopActivityIndicator()
                    val intent = Intent(FirebaseAuthHelper.shared().activity, MainActivity::class.java)
                    startActivity(intent)
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