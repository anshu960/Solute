package com.solute

import android.app.Activity
import android.app.Application
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.fragment.app.Fragment
import com.friendly.framework.Defaults
import com.friendly.framework.UtilityActivity
import com.friendly.framework.UtilityViewController
import com.friendly.framework.constants.KeyConstant
import com.friendly.framework.database.DatabaseHandler
import com.friendly.framework.dataclass.FriendRequest
import com.friendly.framework.dataclass.FriendlyProfile
import com.friendly.framework.dataclass.FriendlyUser
import com.friendly.frameworkt.feature.business.handler.AuthHandler
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.appupdate.AppUpdateOptions
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.UpdateAvailability
import com.google.firebase.FirebaseApp
import com.google.firebase.appcheck.FirebaseAppCheck
import com.google.firebase.appcheck.playintegrity.PlayIntegrityAppCheckProviderFactory
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage


class App: Application() {
    var activity: UtilityViewController? = null
    private var activeActivity: Activity? = null
    var fragment: Fragment? = null
    init {
        instance = this
    }

    // Get a non-default Storage bucket
    var storage : FirebaseStorage? = null
    var storageRef : StorageReference? = null
    var imagesRef: StorageReference? = null
    var productImageRef: StorageReference? = null
    var videosRef: StorageReference? = null
    var profilePicRef: StorageReference? = null
    var videoPathRef: StorageReference? = null

    fun setUpFirebaseStorage(){
        storage = Firebase.storage("gs://fuelme-20ef9.appspot.com")
        storageRef = storage?.reference?.child("chats")
        imagesRef = storageRef?.child("images")
        productImageRef = storageRef?.child("product")
        videosRef = storageRef?.child("videos")
        profilePicRef = storage?.reference?.child("ProfilePictures")
    }

    override fun onCreate() {
        super.onCreate()
        AuthHandler.shared().deviceId = Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)
        FirebaseApp.initializeApp(/*context=*/this)
        val firebaseAppCheck = FirebaseAppCheck.getInstance()
        firebaseAppCheck.installAppCheckProviderFactory(
            PlayIntegrityAppCheckProviderFactory.getInstance()
        )
        FirebaseApp.initializeApp(this)
        DatabaseHandler.shared().appContext = this
        Defaults.shared().setup(this)
        Defaults.shared().store(KeyConstant.deviceId,AuthHandler.shared().deviceId)
//        Analytics.initFirebaseSetup(this)
//        Analytics().logAppLaunch()
//        getAndUpdateToken()
        setUpFirebaseStorage()
        setupActivityListener()
    }

    override fun startForegroundService(service: Intent?): ComponentName? {
        return super.startForegroundService(service)
    }



    fun getAndUpdateToken(){
        val messaging = FirebaseMessaging.getInstance()
        messaging.isAutoInitEnabled = true
        val token = FirebaseMessaging.getInstance().token.toString()
        Defaults.shared().store(KeyConstant.fcmToken, token)
    }

    companion object{
        var user  = FriendlyUser()
        var profile: FriendlyProfile = FriendlyProfile()
        var friendRequest: FriendRequest = FriendRequest()
        var context : Context? = null
        var fragment : Fragment? = null
        private var instance: App? = null

        fun applicationContext() : App {
            return instance as App
        }
    }

    fun sync(){
        FriendlyProfile().downloadSentFriendRequest()
        FriendlyProfile().downloadReceivedFriendRequest()
    }

    fun getCurrentActivity():UtilityViewController?{
//        val am = context!!.getSystemService(ACTIVITY_SERVICE) as ActivityManager
//        val cn = am.getRunningTasks(1)[0].topActivity

        return activity
    }

    fun checkForAppUpdate(activity: UtilityActivity){
        val appUpdateManager = AppUpdateManagerFactory.create(activity)
        val appUpdateInfoTask = appUpdateManager.appUpdateInfo
        appUpdateInfoTask.addOnSuccessListener { appUpdateInfo ->
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE) {
                val option = AppUpdateOptions.newBuilder(AppUpdateType.IMMEDIATE)
                    .setAllowAssetPackDeletion(true)
                    .build()
                appUpdateManager.startUpdateFlow(appUpdateInfo, activity,option) }
        }
    }

    private fun setupActivityListener() {
        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {}
            override fun onActivityStarted(activity: Activity) {}
            override fun onActivityResumed(activity: Activity) {
                activeActivity = activity
            }

            override fun onActivityPaused(activity: Activity) {
                activeActivity = null
            }

            override fun onActivityStopped(activity: Activity) {}
            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}
            override fun onActivityDestroyed(activity: Activity) {}
        })
    }

    fun getActiveActivity(): Activity? {
        return activeActivity
    }

}
