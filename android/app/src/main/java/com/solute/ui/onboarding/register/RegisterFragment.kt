package com.solute.ui.onboarding.register

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.friendly.framework.Defaults
import com.friendly.framework.constants.Country
import com.friendly.framework.constants.KeyConstant
import com.friendly.framework.socket.SocketEvent
import com.friendly.framework.socket.SocketService
import com.google.android.material.textfield.TextInputEditText
import com.solute.MainActivity
import com.solute.R
import com.solute.app.App
import com.solute.ui.onboarding.login.FirebaseAuthHelper
import org.json.JSONObject

class RegisterFragment : Fragment() {

    var encodedImage = ""
    var gender = ""

    var signup_name : TextInputEditText? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_register, container, false)
        signup_name = view.findViewById(R.id.signup_name)
        val registerButton : Button = view.findViewById(R.id.signup_register_btn)
        registerButton?.setOnClickListener { onClickRegister() }
        return view
    }
    fun onClickRegister(){
        val name = signup_name!!.text.toString()
        if(name == ""){
            App.shared().mainActivity?.alert("Oops","You missed Display Name, Please provide your Name")
        }else{
            Log.d("Signup","Trying Email Id Signup")
            App.shared().mainActivity?.startActivityIndicator()
            authenticateUser(name)
        }
    }
    fun authenticateUser(name:String){
        val info =  Defaults.shared().json(KeyConstant.loginDetails)
        var request = JSONObject()
        request.put(KeyConstant.name,name)
        request.put(KeyConstant.userId,FirebaseAuthHelper.shared().firebaseUserId)
        request.put(KeyConstant.mobileNumber,info.getString(KeyConstant.mobileNumber))
        request.put(KeyConstant.dialCode,info.getString(KeyConstant.dialCode))
        request.put(KeyConstant.countryCode,
            App.shared().mainActivity?.let { Country().getCountryCode(it) })
        request.put(KeyConstant.gender,gender)
        request.put(KeyConstant.imageData,encodedImage)
        val roleType = JSONObject()
        roleType.put(KeyConstant._id ,"61acee7871a83e09a12a1668")
        request.put(KeyConstant.roleType,roleType)
        App.shared().mainActivity?.startActivityIndicator("Trying to create account")
        SocketService.shared().onEvent= { event, data ->
            App.shared().mainActivity?.runOnUiThread {
                App.shared().mainActivity?.stopActivityIndicator()
                val response = data
                val msg =  response.getString(KeyConstant.message)
                val payload = response.getJSONObject(KeyConstant.payload)
                if(msg != "" && payload.length() > 0){
                    Defaults.shared().store(KeyConstant.loginDetails,payload)
                    App.shared().mainActivity?.navigateToHome()
                    val intent = Intent(App.shared().mainActivity, MainActivity::class.java)
                    this.startActivity(intent)
                }else{
                    App.shared().mainActivity?.alert("Oopd!", "Something went wrong, please try after some time")
                }
            }
        }
        SocketService.shared().send(SocketEvent.authenticate, request)
    }

}