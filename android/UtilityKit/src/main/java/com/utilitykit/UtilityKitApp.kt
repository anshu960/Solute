package com.utilitykit

import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import androidx.multidex.BuildConfig
import com.utilitykit.Constants.Server
import com.utilitykit.database.Database
import com.utilitykit.database.SQLite
import com.utilitykit.dataclass.FriendRequest
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
        var friendRequest: FriendRequest = FriendRequest()
        var context : Context? = null
        var fragment : Fragment? = null
        private var instance: UtilityKitApp? = null
        fun applicationContext() : UtilityKitApp {
            return instance as UtilityKitApp
        }
    }
    override fun onCreate() {
        super.onCreate()
    }

    fun setUp(context: Context){
        appContext = context
        SQLite.init(appContext!!)
        Database.init(appContext!!)
        Defaults.init(appContext!!)
//        if(BuildConfig.DEBUG){
//            socketUrl = Server.devHost
//        }else{
            socketUrl = Server.prodDost
//        }
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