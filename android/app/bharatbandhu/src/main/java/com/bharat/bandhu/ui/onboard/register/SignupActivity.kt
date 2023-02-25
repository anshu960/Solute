package com.bharat.bandhu.ui.onboard.register

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.View
import com.bharat.bandhu.R
import com.bharat.bandhu.ui.App
import com.bharat.bandhu.ui.MainActivity
import com.friendly.framework.Defaults
import com.friendly.framework.UtilityViewController
import com.friendly.framework.constants.Country
import com.friendly.framework.constants.KeyConstant
import com.friendly.framework.socket.SocketEvent
import com.friendly.framework.socket.SocketService
import com.google.android.material.textfield.TextInputEditText
import org.json.JSONObject


class SignupActivity : UtilityViewController() {

    var encodedImage = ""
    var gender = ""
    var userId = ""
    var signup_name : TextInputEditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        intent.getStringExtra(KeyConstant.userId)!!.also { userId = it }
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
        val info =  Defaults.shared().json(KeyConstant.loginDetails)
        var request = JSONObject()
        request.put(KeyConstant.name,name)
        request.put(KeyConstant.userId,userId)
        var deviceID = Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)
        if(deviceID == ""){
            deviceID = "Device_ID_Not_Found"
        }
        request.put(KeyConstant.mobileNumber,info.getString(KeyConstant.mobileNumber))
        request.put(KeyConstant.dialCode,info.getString(KeyConstant.dialCode))
        request.put(KeyConstant.deviceId,deviceID)
        request.put(KeyConstant.countryCode, Country().getCountryCode())
        request.put(KeyConstant.fcmToken,deviceID)
        request.put(KeyConstant.gender,gender)
        request.put(KeyConstant.imageData,encodedImage)
        request.put("AppID","com.bharat.bandhu")
        val roleType = JSONObject()
        roleType.put(KeyConstant._id ,"61acee7871a83e09a12a1668")
        request.put(KeyConstant.roleType,roleType)
        this.startActivityIndicator("Trying to create account")
        SocketService.shared().onEvent= { event, data ->
            runOnUiThread {
                this.stopActivityIndicator()
                val response = data
                val msg =  response.getString(KeyConstant.message)
                val payload = response.getJSONObject(KeyConstant.payload)
                if(msg != "" && payload.length() > 0){
                    Defaults.shared().store(KeyConstant.loginDetails,payload)
//                    val intent = Intent(applicationContext,AddProfilePicActivity::class.java)
                    val intent = Intent(applicationContext, MainActivity::class.java)
                    this.startActivity(intent)
                }else{
//                    this.alert("Oopd!", Constants.defaultErrorMessage)
                }
            }
        }
        SocketService.shared().send(SocketEvent.authenticate, request)
    }
}
