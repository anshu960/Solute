package com.utilitykit

import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import com.utilitykit.Constants.Server
import com.utilitykit.dataclass.Profile
import com.utilitykit.dataclass.User
import io.socket.client.IO
import io.socket.client.Socket
import java.net.URISyntaxException

class UtilityKitApp :Application(){

    var activity: UtilityViewController? = null
    var fragment: Fragment? = null
    var socketUrl = ""
    var appContext : Context? = null
    private var mSocket: Socket? = null
    init {
        instance = this
    }

    companion object{
        var user : User = User()
        var profile: Profile = Profile()
        var context : Context? = null
        var fragment : Fragment? = null
        private var instance: UtilityKitApp? = null
        fun applicationContext() : UtilityKitApp {
            return instance as UtilityKitApp
        }
    }

    fun setUp(context: Context,isDebug:Boolean){
        appContext = context
        Defaults.init(appContext!!)
        socketUrl = if(isDebug){
            Server.devHost
//            Server.prodDost
        }else{
//            Server.prodDost
            Server.devHost
        }
        try {
            mSocket = IO.socket(socketUrl)

        } catch (e: URISyntaxException) {
            throw RuntimeException(e)
        }
    }
    fun getMSocket():Socket?{
        return mSocket
    }



}