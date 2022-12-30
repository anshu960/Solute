package com.bharat.bandhu.ui.onboard.register

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.View
import com.bharat.bandhu.R
import com.bharat.bandhu.ui.App
import com.bharat.bandhu.ui.MainActivity
import com.google.android.material.textfield.TextInputEditText
import com.solute.utility.Country
import com.utilitykit.Constants.Constants
import com.utilitykit.Constants.Key
import com.utilitykit.Defaults
import com.utilitykit.socket.SocketEvent
import com.utilitykit.UtilityViewController
import com.utilitykit.socket.SocketService
import org.json.JSONObject


class SignupActivity : UtilityViewController() {

    var encodedImage = ""
    var gender = ""
    var userId = ""
    var signup_name : TextInputEditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        intent.getStringExtra(Key.userId)!!.also { userId = it }
        App.context = this
        signup_name = findViewById(R.id.signup_name)
    }



    fun onClickRegister(view: View){
        val name = signup_name!!.text.toString()
        if(name == ""){
            this.alert("Oops","You missed Display Name, Please provide your Name")
        }else{
            Log.d("Signup","Trying Email Id Signup")
            this.startActivityIndicator()
            authenticateUser(name)
        }
    }
    fun authenticateUser(name:String){
        val info =  Defaults.shared().json(Key.loginDetails)
        var request = JSONObject()
        request.put(Key.name,name)
        request.put(Key.userId,userId)
        var deviceID = Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)
        if(deviceID == ""){
            deviceID = "Device_ID_Not_Found"
        }
        request.put(Key.mobileNumber,info.getString(Key.mobileNumber))
        request.put(Key.dialCode,info.getString(Key.dialCode))
        request.put(Key.deviceId,deviceID)
        request.put(Key.countryCode, Country().getCountryCode())
        request.put(Key.fcmToken,deviceID)
        request.put(Key.gender,gender)
        request.put(Key.imageData,encodedImage)
        request.put("AppID","com.bharat.bandhu")
        val roleType = JSONObject()
        roleType.put(Key._id ,"61acee7871a83e09a12a1668")
        request.put(Key.roleType,roleType)
        this.startActivityIndicator("Trying to create account")
        SocketService.shared().onEvent= { event, data ->
            runOnUiThread {
                this.stopActivityIndicator()
                val response = data
                val msg =  response.getString(Key.message)
                val payload = response.getJSONObject(Key.payload)
                if(msg != "" && payload.length() > 0){
                    Defaults.shared().store(Key.loginDetails,payload)
//                    val intent = Intent(applicationContext,AddProfilePicActivity::class.java)
                    val intent = Intent(applicationContext, MainActivity::class.java)
                    this.startActivity(intent)
                }else{
                    this.alert("Oopd!", Constants.defaultErrorMessage)
                }
            }
        }
        SocketService.shared().send(SocketEvent.authenticate, request)
    }
}
