package com.utilitykit.SocketUtill.handler

import android.content.Context
import android.util.Log
import androidx.fragment.app.Fragment
import com.utilitykit.SocketUtill.SocketEvent
import com.utilitykit.SocketUtill.SocketManager
import com.utilitykit.UtilityKitApp
import com.utilitykit.dataclass.FriendRequest
import com.utilitykit.dataclass.Profile
import com.utilitykit.dataclass.User
import io.socket.emitter.Emitter
import org.json.JSONObject

class BusinessHandler {
    init {
        instance = this
    }
    companion object{
        private var instance: BusinessHandler? = null
        fun shared() : BusinessHandler {
            if(instance != null){
                return instance as BusinessHandler
            }else{
                return BusinessHandler()
            }
        }
    }
     val retriveBusiness = Emitter.Listener {
        Log.d(SocketManager.TAG,"Event Received ${it.toString()}")
        if (it.count() > 0)
        {
            val anyData = it.first()
            if(anyData is JSONObject){
                SocketManager.onEvent(SocketEvent.onEvent, anyData)
            }
        }
    }
}