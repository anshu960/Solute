package com.utilitykit

import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import com.utilitykit.Constants.Server
import com.utilitykit.database.UtilityKitDatabase
import com.utilitykit.dataclass.Profile
import com.utilitykit.dataclass.User
import com.utilitykit.socket.SocketService
import io.socket.client.IO
import io.socket.client.Socket
import java.net.URISyntaxException

//class UtilityKitApp :Application(){
//
//    var activity: UtilityViewController? = null
//    var fragment: Fragment? = null
//    var socketUrl = ""
//    var appContext : Context? = null
//    val database: UtilityKitDatabase by lazy {
//        this.appContext?.let {
//            UtilityKitDatabase.getDatabase(
//                it
//            )
//        }!!
//    }
//    init {
//        instance = this
//    }
//
//    companion object{
//        var user : User = User()
//        var profile: Profile = Profile()
//        var context : Context? = null
//        var fragment : Fragment? = null
//        private var instance: UtilityKitApp? = null
//        fun applicationContext() : UtilityKitApp {
//            return instance as UtilityKitApp
//        }
//    }
//
//    fun setUp(context: Context){
//        appContext = context
//    }
//
//    override fun onCreate() {
//        super.onCreate()
//        SocketService.shared().connect()
//    }
//
//}