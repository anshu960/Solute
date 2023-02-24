package com.solute.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthProvider
import com.solute.MainActivity
import com.solute.R
import com.solute.ui.register.RegisterActivity
import com.utilitykit.Constants.Key
import com.utilitykit.UtilityActivity

class OtpActivity : UtilityActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    var dialCode : String = ""
    var otpText : EditText? = null
    var verifyBtn : Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp)
        auth = FirebaseAuth.getInstance()
        otpText = findViewById(R.id.otp_activity_otp_et)
        verifyBtn = findViewById(R.id.otp_activity_verify)
        verifyBtn?.setOnClickListener { onClickVerify() }
        initSetup()
    }

    fun initSetup(){
     FirebaseAuthHelper.shared().onAuthStateChange ={ state, message->
         runOnUiThread {
             if(state == AUTH_STATE.ERROR){
                 stopActivityIndicator()
                 toast(message)
             } else if(state == AUTH_STATE.OTP_VERIFYING){
                startActivityIndicator(message)
             }else if(state == AUTH_STATE.NEW_USER){
                 stopActivityIndicator()
                 val intent = Intent(this, RegisterActivity::class.java)
                 intent.putExtra(Key.userId, FirebaseAuthHelper.shared().firebaseUserId)
                 intent.putExtra(Key.mobileNumber, FirebaseAuthHelper.shared().phoneNumber)
                 intent.putExtra(Key.dialCode, FirebaseAuthHelper.shared().dialCode)
                 startActivity(intent)
             }else if(state == AUTH_STATE.LOGGED_IN){
                 stopActivityIndicator()
                 val intent = Intent(this, MainActivity::class.java)
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