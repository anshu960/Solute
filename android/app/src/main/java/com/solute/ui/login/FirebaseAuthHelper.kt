package com.solute.ui.login

import android.util.Log
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import com.utilitykit.Constants.Key
import com.utilitykit.Defaults
import com.utilitykit.UtilityActivity
import com.utilitykit.feature.business.handler.AuthHandler
import com.utilitykit.socket.SocketEvent
import com.utilitykit.socket.SocketService
import org.json.JSONObject
import java.util.concurrent.TimeUnit

enum class AUTH_STATE{
    SENDING_OTP,OTP_SENT,OTP_VERIFYING,OTP_VERIFIED,CHECKING_ACCOUNT,LOGGED_IN,NEW_USER,ERROR
}
class FirebaseAuthHelper {
    init {
        instance = this
    }
    companion object{
        var instance: FirebaseAuthHelper? = null
        fun shared() : FirebaseAuthHelper {
            if(instance == null){
                instance = FirebaseAuthHelper()
                return instance as FirebaseAuthHelper
            }else{
                return instance as FirebaseAuthHelper
            }
        }
    }

    var activity : UtilityActivity? = null
    private lateinit var auth: FirebaseAuth
    private lateinit var resendToken: PhoneAuthProvider.ForceResendingToken
    private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks

    var onAuthStateChange : ((AUTH_STATE,String)->Unit)? = null

    var phoneNumber = ""
    var dialCode = ""
    var authVerificationId = ""
    var firebaseUserId = ""

    fun setUp(){
        auth = FirebaseAuth.getInstance()
        initiatePhoneSetup()
    }

    fun initiatePhoneSetup(){
        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                signInWithPhoneAuthCredential(credential)
            }
            override fun onVerificationFailed(e: FirebaseException) {
                onAuthStateChange?.let { e.localizedMessage?.let { it1 ->
                    it(AUTH_STATE.ERROR,
                        it1
                    )
                } }
            }
            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {
                authVerificationId = verificationId
                resendToken = token
                onAuthStateChange?.let { it(AUTH_STATE.OTP_SENT,"Otp Sent to mobile number") }
            }
        }
    }

    fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential){
        onAuthStateChange?.let { it(AUTH_STATE.OTP_VERIFYING,"Trying to verify OTP") }
        auth.signInWithCredential(credential)
            .addOnCompleteListener() { task ->
                if (task.isSuccessful) {
                    onAuthStateChange?.let { it(AUTH_STATE.OTP_VERIFIED,"OTP Verified") }
                    loginWithPhoneNumber()
                } else if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        onAuthStateChange?.let { it(AUTH_STATE.ERROR,"Verification code entered wasn't correct") }
                    }
            }
    }

    fun sendOtp(){
        onAuthStateChange?.let { it(AUTH_STATE.SENDING_OTP,"Sending OTP to given Number") }
        if(phoneNumber != "" && phoneNumber.count() >= 10){
            val phoneNumber = "+" + dialCode + phoneNumber.trim()
            Log.d("Firebase Auth","trying to send otp to $phoneNumber")
            val options =  PhoneAuthOptions.newBuilder(auth)
                .setPhoneNumber(phoneNumber)       // Phone number to verify
                .setTimeout(30L, TimeUnit.SECONDS) // Timeout and unit
                .setActivity(this.activity!!)                 // Activity (for callback binding)
                .setCallbacks(callbacks)          // OnVerificationStateChangedCallbacks
                .build();
            PhoneAuthProvider.verifyPhoneNumber(options)
        }else{
            onAuthStateChange?.let { it(AUTH_STATE.ERROR,"Please enter a valid phone number") }
        }
    }

    fun verifyOtp(otp:String){
        val credential = PhoneAuthProvider.getCredential(authVerificationId, otp)
        signInWithPhoneAuthCredential(credential)
    }

    fun loginWithPhoneNumber(){
        firebaseUserId = auth.currentUser?.uid ?: ""
        var request = JSONObject()
        request.put(Key.mobileNumber,this.phoneNumber)
        request.put(Key.dialCode,this.dialCode)
        request.put(Key.userId,firebaseUserId)
        request.put(Key.fcmToken, AuthHandler.shared().deviceId)
        onAuthStateChange?.let { it(AUTH_STATE.CHECKING_ACCOUNT,"Checking for existing account") }
        SocketService.shared().onEvent= { _, data ->
                if(data.has(Key.payload)){
                    var payload = data.getJSONObject(Key.payload)
                    if(payload.has(Key.name)){
                        Defaults.shared().store(Key.loginDetails, payload)
                        onAuthStateChange?.let { it(AUTH_STATE.LOGGED_IN,"Checking for existing account") }
                    }else{
                        payload.put(Key.userId,firebaseUserId)
                        payload.put(Key.mobileNumber,phoneNumber)
                        payload.put(Key.deviceId, AuthHandler.shared().deviceId)
                        payload.put(Key.fcmToken, AuthHandler.shared().deviceId)
                        payload.put(Key.dialCode,dialCode)
                        Defaults.shared().store(Key.loginDetails,payload)

                        onAuthStateChange?.let { it(AUTH_STATE.NEW_USER,"Checking for existing account") }
                    }
                }else{
                    onAuthStateChange?.let { it(AUTH_STATE.LOGGED_IN,"Something went wrong, PLease try after sometime") }
                }
        }
        SocketService.shared().send(SocketEvent.authenticate, request)
    }

}