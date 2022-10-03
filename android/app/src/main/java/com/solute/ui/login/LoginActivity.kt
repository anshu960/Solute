package com.solute.ui.login

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import com.hbb20.CountryCodePicker
import com.solute.MainActivity
import com.solute.R
import com.solute.ui.register.RegisterActivity
import com.utilitykit.Constants.Key
import com.utilitykit.Defaults
import com.utilitykit.SocketUtill.SocketEvent
import com.utilitykit.SocketUtill.SocketManager
import com.utilitykit.UtilityActivity
import com.utilitykit.database.Database
import com.utilitykit.database.SQLite
import org.json.JSONObject
import java.util.concurrent.TimeUnit

class LoginActivity : UtilityActivity() {

    private lateinit var auth: FirebaseAuth
    private var phoneNumber = ""
    var selectedCountryCode = ""
    private var otp = ""
    private var verificationInProgress = false
    private var storedVerificationId: String? = ""
    private lateinit var resendToken: PhoneAuthProvider.ForceResendingToken
    private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    var verifyBtn : Button? = null
    var resendBtn : Button? = null
    var otp1 : EditText? = null
    var otp2 : EditText? = null
    var otp3 : EditText? = null
    var otp4 : EditText? = null
    var otp5 : EditText? = null
    var otp6 : EditText? = null
    private var otpTextViews: Array<EditText>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth = FirebaseAuth.getInstance()
        initiatePhoneSetup()
        findViewById<ConstraintLayout>(R.id.login_otp_layout).visibility = View.GONE
        findViewById<Button>(R.id.login_resend_otp_btn).visibility = View.GONE
        verifyBtn = findViewById(R.id.login_verify_btn)
        verifyBtn?.setOnClickListener { onClickLogin() }
        resendBtn = findViewById(R.id.login_resend_otp_btn)
        resendBtn?.setOnClickListener { onClickResend() }
        otp1 = findViewById(R.id.otp_text_view_1)
        otp2 = findViewById(R.id.otp_text_view_2)
        otp3 = findViewById(R.id.otp_text_view_3)
        otp4 = findViewById(R.id.otp_text_view_4)
        otp5 = findViewById(R.id.otp_text_view_5)
        otp6 = findViewById(R.id.otp_text_view_6)

        otpTextViews = arrayOf<EditText>(otp1!!, otp2!!, otp3!!, otp4!!,otp5!!,otp6!!)
        otp1!!.addTextChangedListener(PinTextWatcher(0))
        otp2!!.addTextChangedListener(PinTextWatcher(1))
        otp3!!.addTextChangedListener(PinTextWatcher(2))
        otp4!!.addTextChangedListener(PinTextWatcher(3))
        otp5!!.addTextChangedListener(PinTextWatcher(4))
        otp6!!.addTextChangedListener(PinTextWatcher(5))
        otp1!!.setOnKeyListener(PinOnKeyListener(0))
        otp2!!.setOnKeyListener(PinOnKeyListener(1))
        otp3!!.setOnKeyListener(PinOnKeyListener(2))
        otp4!!.setOnKeyListener(PinOnKeyListener(3))
        otp5!!.setOnKeyListener(PinOnKeyListener(4))
        otp6!!.setOnKeyListener(PinOnKeyListener(5))
        inits()
    }

    fun inits(){
        val countryCodePicker = findViewById<CountryCodePicker>(R.id.countrycode)
        countryCodePicker.setDefaultCountryUsingNameCode("DE")
        countryCodePicker.setOnCountryChangeListener {
            print(countryCodePicker.selectedCountryCode)
            this.selectedCountryCode = countryCodePicker.selectedCountryCode
        }

    }

    fun onClickLogin(){
        phoneNumber = findViewById<TextInputEditText>(R.id.mobile_text_field).text.toString()
//        if(phoneNumber == "6202143211"){
//            loginWithPhoneNumber("gLAnJJRN2dUaDmAK2IK0h3omlDs2")
//            return
//        }

        otp = otp1!!.text.toString() +  otp2!!.text.toString() +  otp3!!.text.toString() +  otp4!!.text.toString() +  otp5!!.text.toString() +  otp6!!.text.toString()
        if(phoneNumber.length < 10){
            this.alert("Oops!","Please enter your mobile number")
        }else if(otp.length == 6){
            if(storedVerificationId != ""){
                val credential = PhoneAuthProvider.getCredential(storedVerificationId!!, otp)
                signInWithPhoneAuthCredential(credential)
            }else{
                authenticateUsingPhoneNumber()
            }
        }else{
            authenticateUsingPhoneNumber()
        }
    }

    fun checkForWhiteList(){

    }

    fun onClickResend(){
        phoneNumber = findViewById<TextInputEditText>(R.id.mobile_text_field).text.toString()
//        otp = findViewById<TextInputEditText>(R.id.login_otp_layout).text.toString()
        authenticateUsingPhoneNumber()
    }



    fun initiatePhoneSetup(){
        // Initialize phone auth callbacks
        // [START phone_auth_callbacks]
        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                stopActivityIndicator()
                verificationInProgress = false
                signInWithPhoneAuthCredential(credential)
            }
            override fun onVerificationFailed(e: FirebaseException) {
                stopActivityIndicator()
                if (e is FirebaseAuthInvalidCredentialsException) {
                    Log.d("Firebase Auth",e.stackTraceToString())
//                    toast("Invalid phone number.")
                    toastLong("Invalid phone number.")
                } else if (e is FirebaseTooManyRequestsException) {
                    Log.d("Firebase Auth",e.stackTraceToString())
                    toastLong(e.localizedMessage)
                }else{
                    Log.d("Firebase Auth",e.stackTraceToString())
                    toastLong(e.localizedMessage)
                }
            }
            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {
                stopActivityIndicator()
                storedVerificationId = verificationId
                resendToken = token
                toastLong("OTP have been sent to your mobile number")
                findViewById<ConstraintLayout>(R.id.login_otp_layout).visibility = View.VISIBLE
                findViewById<Button>(R.id.login_verify_btn).text = "Verify"
                findViewById<Button>(R.id.login_resend_otp_btn).visibility = View.VISIBLE
            }
        }
    }

    fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential){
        this.startActivityIndicator(" Trying to Verify OTP")
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                this.stopActivityIndicator()
                if (task.isSuccessful) {
                    val user = task.result?.user
                    loginWithPhoneNumber(user!!.uid)
                } else {
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        this.alert("Oops!","Verification code entered wasn't correct")
                    }
                }
            }
    }

    fun authenticateUsingPhoneNumber(){
        this.startActivityIndicator("Sending OTP to given Number")
        if(phoneNumber != "" && phoneNumber.count() >= 10){
            val phoneNumber = "+" + this.getDialCode() + phoneNumber.trim()
            Log.d("Firebase Auth","trying to send otp to $phoneNumber")
            val options =  PhoneAuthOptions.newBuilder(auth)
                .setPhoneNumber(phoneNumber)       // Phone number to verify
                .setTimeout(30L, TimeUnit.SECONDS) // Timeout and unit
                .setActivity(this)                 // Activity (for callback binding)
                .setCallbacks(callbacks)          // OnVerificationStateChangedCallbacks
                .build();
            PhoneAuthProvider.verifyPhoneNumber(options)
        }else{
            this.alert("Oops!","Please enter a valid phone number")
        }

    }

    fun getDialCode():String{
        if(selectedCountryCode == ""){
            val countryCodePicker = findViewById<CountryCodePicker>(R.id.countrycode)
            this.selectedCountryCode = countryCodePicker.selectedCountryCode
        }
        return this.selectedCountryCode
    }

    fun loginWithPhoneNumber(uid:String){
        var request = JSONObject()
        request.put(Key.mobileNumber,phoneNumber)
        request.put(Key.dialCode,getDialCode())
        request.put(Key.userId,uid)
        val deviceID = Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)
        request.put(Key.deviceId,deviceID)
        request.put(Key.fcmToken,deviceID)
        SocketManager.joinRoom(uid)

        this.startActivityIndicator("Checking for existing accounts")
        SocketManager.onEvent= { event, data ->
            this.runOnUiThread {
                stopActivityIndicator()
                Log.d("LoginResponse",data.toString())
                if(data.has(Key.payload)){
                    var payload = data.getJSONObject(Key.payload)
                    if(payload.has(Key.name)){
                        SocketManager.joinRoom(payload.getString(Key._id))
                        Defaults.store(Key.loginDetails, payload)
                        val intent = Intent(applicationContext, MainActivity::class.java)
                        this.startActivity(intent)
                    }else{
                        payload.put(Key.userId,uid)
                        payload.put(Key.mobileNumber,phoneNumber)
                        payload.put(Key.deviceId,deviceID)
                        payload.put(Key.fcmToken,deviceID)
                        payload.put(Key.dialCode,selectedCountryCode)
                        Defaults.store(Key.loginDetails,payload)
                        val intent = Intent(applicationContext, RegisterActivity::class.java)
                        intent.putExtra(Key.userId,uid)
                        intent.putExtra(Key.mobileNumber,phoneNumber)
                        intent.putExtra(Key.dialCode,selectedCountryCode)
                        this.startActivity(intent)
                    }
                }else{
//                    this.alert("Oops!","Something went wrong, PLease try after sometime")
                }
            }
        }
        SocketManager.send(SocketEvent.authenticate, request)
    }

    fun onClickSignUp(view: View){
        val intent = Intent(applicationContext, RegisterActivity::class.java)
        startActivity(intent)
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (currentFocus != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }
        return super.dispatchTouchEvent(ev)
    }



    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
    }


    inner class PinTextWatcher internal constructor(private val currentIndex: Int) : TextWatcher
    {
        private var isFirst = false
        private var isLast = false
        private var newTypedString = ""
        private val isAllEditTextsFilled: Boolean
            get() {
                for (editText in otpTextViews!!)
                    if (editText.text.toString().trim { it <= ' ' }.length == 0)
                        return false
                return true
            }
        init {
            if (currentIndex == 0)
                this.isFirst = true
            else if (currentIndex == otpTextViews!!.size - 1)
                this.isLast = true
        }
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
        }
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            newTypedString = s.subSequence(start, start + count).toString().trim { it <= ' ' }
        }
        override fun afterTextChanged(s: Editable) {
            var text = newTypedString
            /* Detect paste event and set first char */
            if (text.length > 1)
                text = text[0].toString()
            otpTextViews!![currentIndex].removeTextChangedListener(this)
            otpTextViews!![currentIndex].setText(text)
//            otpTextViews!![currentIndex].setSelection(text.length)
            otpTextViews!![currentIndex].addTextChangedListener(this)
            if (text.length == 1)
                moveToNext()
            else if (text.length == 0)
                Log.e("d", "d")

        }
        private fun moveToNext() {
            if (!isLast)
                otpTextViews!![currentIndex + 1].requestFocus()
            if (isAllEditTextsFilled && isLast) { // isLast is optional
                hideKeyboard()
//                login_verify_btn.background= resources.getDrawable(R.drawable.)
                otpTextViews!![currentIndex].clearFocus()
            }
            else{
//                tv_verifynext.background= resources.getDrawable(R.drawable.drawable_unselectblue)
            }
        }
        private fun moveToPrevious() {
            if (!isFirst)
                otpTextViews!![currentIndex - 1].requestFocus()
        }
        private fun hideKeyboard() {
            if (currentFocus != null) {
                val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
            }
        }
    }
    inner class PinOnKeyListener internal constructor(private val currentIndex: Int) : View.OnKeyListener {
        override fun onKey(v: View, keyCode: Int, event: KeyEvent): Boolean {
            if (keyCode == KeyEvent.KEYCODE_DEL && event.action == KeyEvent.ACTION_DOWN) {
                if (otpTextViews!![currentIndex].text.toString().isEmpty() && currentIndex != 0)
                    otpTextViews!![currentIndex - 1].requestFocus()
            }
            return false
        }
    }

}
