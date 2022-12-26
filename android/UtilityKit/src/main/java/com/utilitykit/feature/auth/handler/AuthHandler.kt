package com.utilitykit.feature.business.handler

import android.content.Context
import android.provider.Settings
import android.provider.Settings.Secure
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.utilitykit.Constants.Key
import com.utilitykit.UtilityActivity
import com.utilitykit.dataclass.User
import com.utilitykit.feature.business.repository.AuthRepository
import com.utilitykit.feature.business.viewModel.AuthViewModel
import com.utilitykit.socket.SocketEvent
import com.utilitykit.socket.SocketService
import org.json.JSONObject
import java.security.AccessController.getContext


class AuthHandler {

    var viewModel: AuthViewModel? = null
    val repository = AuthRepository()
    val gson = Gson()
    var activity : AppCompatActivity = UtilityActivity()
    var mainActivity : AppCompatActivity = UtilityActivity()
    var context : Context? = null
    var deviceId = ""
    init {
        instance = this
    }
    companion object{
        private var instance: AuthHandler? = null
        fun shared() : AuthHandler {
            if(instance != null){
                return instance as AuthHandler
            }else{
                return AuthHandler()
            }
        }
    }

    fun setup(model:AuthViewModel){
        viewModel = model
    }

    fun fetchAllBusiness(){
        val request = JSONObject()
        val user = User()
        request.put(Key.userId,user._id)
        SocketService.shared().send(SocketEvent.RETRIVE_BUSINESS,request)
    }



}