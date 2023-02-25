package com.solute.ui.launch

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.multidex.BuildConfig
import com.friendly.framework.Defaults
import com.friendly.framework.UtilityActivity
import com.friendly.framework.constants.KeyConstant
import com.friendly.framework.dataclass.FriendlyUser
import com.friendly.framework.socket.SocketService
import com.solute.R
import com.solute.ui.network.ConnectingActivity


class LaunchActivity : UtilityActivity() {
    private var mDelayHandler: Handler? = null
    private val SPLASH_DELAY: Long = 500 //2 seconds
    internal val mRunnable: Runnable = Runnable {
        if (!isFinishing) {
//            val isAccepted = Defaults.string(KeyConstant.isAccepted)
            val isAccepted = "true"
            if(isAccepted != ""){
                val userDetails = Defaults.shared().string(KeyConstant.loginDetails)
                if(userDetails != null && userDetails != ""){
                    val user = FriendlyUser()
                    if (user.name != "" && user.userID != "" && user._id != ""){
                        if(user.profilePic == ""){
                            val intent = Intent(applicationContext, ConnectingActivity::class.java)
                            this.startActivity(intent)
                        }else{
                            val intent = Intent(applicationContext, ConnectingActivity::class.java)
                            this.startActivity(intent)
                        }
                    }else{
                        val intent = Intent(applicationContext, ConnectingActivity::class.java)
                        intent.putExtra(KeyConstant.userId,user.userID)
                        intent.putExtra(KeyConstant._id,user._id)
                        this.startActivity(intent)
                    }
                }else{
                    val intent = Intent(applicationContext, ConnectingActivity::class.java)
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
        SocketService.shared().currentActivity = this
        SocketService.shared().connect()
        setContentView(R.layout.activity_launch)
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
        val previousVersion = Defaults.shared().string(KeyConstant.versionName)
        val currentVersionName = BuildConfig.VERSION_NAME
        if(previousVersion == "" && currentVersionName != ""){
//            Database.shared.clearDatabase()
            Defaults.shared().store(KeyConstant.versionName, currentVersionName)
        }else if(previousVersion != currentVersionName){
//            com.utilitykit.database.Database.shared.clearDatabase()
            Defaults.shared().store(KeyConstant.versionName, currentVersionName)
        }
    }
}