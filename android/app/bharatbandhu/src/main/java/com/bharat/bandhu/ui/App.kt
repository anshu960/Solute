package com.bharat.bandhu.ui

import android.app.Application
import android.content.Context
import android.provider.ContactsContract
import android.provider.Settings
import androidx.fragment.app.Fragment
import com.friendly.framework.Defaults
import com.friendly.framework.UtilityViewController
import com.friendly.framework.constants.KeyConstant
import com.friendly.framework.database.DatabaseHandler
import com.friendly.framework.dataclass.FriendlyProfile
import com.friendly.framework.socket.SocketService
import com.friendly.frameworkt.feature.business.handler.AuthHandler
import com.google.firebase.FirebaseApp
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage

class App: Application() {

    var activity: UtilityViewController? = null
    init {
        instance = this
    }

    // Get a non-default Storage bucket
    var storage : FirebaseStorage? = null
    var storageRef : StorageReference? = null
    var imagesRef: StorageReference? = null
    var videosRef: StorageReference? = null
    var profilePicRef: StorageReference? = null
    var imagePathRef: StorageReference? = null
    var videoPathRef: StorageReference? = null

    fun setUpFirebaseStorage(){
        storage = Firebase.storage("gs://friendly-221119.appspot.com/")
        storageRef = storage?.reference?.child("chats")
        imagesRef = storageRef?.child("images")
        videosRef = storageRef?.child("videos")
        profilePicRef = storage?.reference?.child("ProfilePictures")
    }

    override fun onCreate() {
        super.onCreate()
        AuthHandler.shared().deviceId = Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)
        FirebaseApp.initializeApp(/*context=*/this)

        FirebaseApp.initializeApp(this)
        DatabaseHandler.shared().appContext = this
        Defaults.shared().setup(this)
        Defaults.shared().store(KeyConstant.deviceId, AuthHandler.shared().deviceId)
//        Analytics.initFirebaseSetup(this)
//        Analytics().logAppLaunch()
//        getAndUpdateToken()
        setUpFirebaseStorage()
        SocketService.shared().verifyIfConnectedOrNot()
    }
    fun getAndUpdateToken(){
        val messaging = FirebaseMessaging.getInstance()
        messaging.isAutoInitEnabled = true
        val token = FirebaseMessaging.getInstance().token.toString()
        if (token != null) {
//            Defaults.store(KeyConstant.fcmToken, token)
        }

    }

    companion object{
        var context : Context? = null
        private var instance: App? = null
        fun applicationContext() : App {
            return instance as App
        }
    }

    fun sync(){
//        FriendlyProfile().downloadSentFriendRequest()
//        FriendlyProfile().downloadReceivedFriendRequest()
    }

    fun getCurrentActivity():UtilityViewController?{
//        val am = context!!.getSystemService(ACTIVITY_SERVICE) as ActivityManager
//        val cn = am.getRunningTasks(1)[0].topActivity

        return activity
    }
}
