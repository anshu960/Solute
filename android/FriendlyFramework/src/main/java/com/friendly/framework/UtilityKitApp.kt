package com.friendly.framework
import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import com.friendly.framework.database.FriendlyDatabase
import com.friendly.framework.dataclass.FriendlyProfile
import com.friendly.framework.socket.SocketService

class UtilityKitApp :Application(){

    var activity: UtilityViewController? = null
    var fragment: Fragment? = null
    var socketUrl = ""
    var appContext : Context? = null
    val database: FriendlyDatabase by lazy {
        this.appContext?.let {
            FriendlyDatabase.getDatabase(
                it
            )
        }!!
    }
    init {
        instance = this
    }

    companion object{
        var user = FriendlyProfile()
        var profile: FriendlyProfile = FriendlyProfile()
        var context : Context? = null
        var fragment : Fragment? = null
        private var instance: UtilityKitApp? = null
        fun applicationContext() : UtilityKitApp {
            return instance as UtilityKitApp
        }
    }

    fun setUp(context: Context){
        appContext = context
    }

    override fun onCreate() {
        super.onCreate()
        SocketService.shared().connect()
    }

}