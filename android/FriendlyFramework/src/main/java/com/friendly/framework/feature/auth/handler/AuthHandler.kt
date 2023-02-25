package com.friendly.frameworkt.feature.business.handler

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.friendly.framework.UtilityActivity
import com.friendly.framework.constants.KeyConstant
import com.friendly.framework.dataclass.FriendlyUser
import com.friendly.framework.feature.business.repository.AuthRepository
import com.friendly.framework.feature.business.viewModel.AuthViewModel
import com.friendly.framework.socket.SocketEvent
import com.friendly.framework.socket.SocketService
import com.google.gson.Gson
import org.json.JSONObject


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
        val user = FriendlyUser()
        request.put(KeyConstant.userId,user._id)
        SocketService.shared().send(SocketEvent.RETRIVE_BUSINESS,request)
    }



}