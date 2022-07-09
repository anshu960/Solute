package com.utilitykit

import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import com.utilitykit.Constants.Server
import com.utilitykit.database.Database
import com.utilitykit.database.SQLite
import com.utilitykit.dataclass.FriendRequest
import com.utilitykit.dataclass.Profile
import com.utilitykit.dataclass.Task
import com.utilitykit.dataclass.User
import io.socket.client.IO
import io.socket.client.Socket
import java.net.URISyntaxException

class UtilityKitApp :Application(){

    var activity: UtilityViewController? = null
    var fragment: Fragment? = null
    var socketUrl = ""
    var selectedProfiles: ArrayList<Profile> = ArrayList<Profile>()
    var selectedTask: Task? = null
    private var mSocket: Socket? = null
    init {
        instance = this
    }


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
        SQLite.init(this)
        Database.init(this)
        Defaults.init(this)
        if(BuildConfig.DEBUG){
            socketUrl = Server.host
        }else{
            socketUrl = Server.host
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