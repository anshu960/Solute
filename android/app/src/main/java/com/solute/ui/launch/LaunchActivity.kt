package com.solute.ui.launch

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.multidex.BuildConfig
import com.solute.MainActivity
import com.solute.R
import com.solute.ui.login.LoginActivity
import com.solute.ui.register.RegisterActivity
import com.utilitykit.Constants.Key
import com.utilitykit.Defaults
import com.utilitykit.SocketUtill.SocketManager
import com.utilitykit.UtilityActivity
import com.utilitykit.UtilityKitApp
import com.utilitykit.database.Database
import com.utilitykit.dataclass.User

class LaunchActivity : UtilityActivity() {
    private var mDelayHandler: Handler? = null
    private val SPLASH_DELAY: Long = 2000 //2 seconds
    internal val mRunnable: Runnable = Runnable {
        if (!isFinishing) {
//            val isAccepted = Defaults.string(Key.isAccepted)
            val isAccepted = "true"
            if(isAccepted != ""){
                val userDetails = Defaults.string(Key.loginDetails)
                if(userDetails != null && userDetails != ""){
                    val user = User()
                    if (user.name != "" && user.userID != "" && user._id != ""){
                        if(user.profilePic == ""){
//                            val intent = Intent(applicationContext, AddProfilePicActivity::class.java)
                            val intent = Intent(applicationContext, MainActivity::class.java)
                            this.startActivity(intent)
                        }else{
                            val intent = Intent(applicationContext, MainActivity::class.java)
                            this.startActivity(intent)
                        }
                    }else{
                        val intent = Intent(applicationContext, RegisterActivity::class.java)
                        intent.putExtra(Key.userId,user.userID)
                        intent.putExtra(Key._id,user._id)
                        this.startActivity(intent)
                    }
                }else{
                    val intent = Intent(applicationContext, LoginActivity::class.java)
                    startActivity(intent)
                }
            }else{
//                val intent = Intent(applicationContext, TermsAndConditionActivity::class.java)
//                this.startActivity(intent)
            }
            finish()
        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        UtilityKitApp().setUp(this)
        SocketManager.currentActivity = this
        SocketManager.connect()
        setContentView(R.layout.activity_launch)
        Database.init(this)
        checkUpdatesAndClearDatabase()
        mDelayHandler = Handler()
        //Navigate with delay
        mDelayHandler!!.postDelayed(mRunnable, SPLASH_DELAY)

    }




    public override fun onDestroy() {
        if (mDelayHandler != null) {
            mDelayHandler!!.removeCallbacks(mRunnable)
        }
        super.onDestroy()
    }

    fun checkUpdatesAndClearDatabase(){
        val previousVersion = Defaults.string(Key.versionName)
        val currentVersionName = BuildConfig.VERSION_NAME
        if(previousVersion == "" && currentVersionName != ""){
//            Database.shared.clearDatabase()
            Defaults.store(com.utilitykit.Constants.Key.versionName, currentVersionName)
        }else if(previousVersion != currentVersionName){
//            com.utilitykit.database.Database.shared.clearDatabase()
            Defaults.store(com.utilitykit.Constants.Key.versionName, currentVersionName)
        }
    }
}