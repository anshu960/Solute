package com.bharat.bandhu.ui.launch

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.bharat.bandhu.R
import com.bharat.bandhu.ui.BusinessConstants
import com.bharat.bandhu.ui.MainActivity
import com.bharat.bandhu.ui.onboard.LoginActivity
import com.bharat.bandhu.ui.onboard.register.SignupActivity
import com.friendly.framework.Defaults
import com.friendly.framework.UtilityActivity
import com.friendly.framework.constants.KeyConstant
import com.friendly.framework.dataclass.FriendlyUser
import com.friendly.framework.feature.business.handler.BusinessHandler
import com.friendly.framework.feature.business.model.Business
import com.friendly.framework.feature.mediaFile.handler.MediaFileHandler
import com.friendly.framework.feature.mediaFile.network.MediaFileNetwork
import com.friendly.framework.socket.SocketService
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.gson.Gson

class LaunchActivity : UtilityActivity() {
    private var mDelayHandler: Handler? = null
    private val SPLASH_DELAY: Long = 2000 //2 seconds
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
//                            val intent = Intent(applicationContext, AddProfilePicActivity::class.java)
                            val intent = Intent(applicationContext, MainActivity::class.java)
                            this.startActivity(intent)
                        }else{
                            val intent = Intent(applicationContext, MainActivity::class.java)
                            this.startActivity(intent)
                        }
                    }else{
                        val intent = Intent(applicationContext, SignupActivity::class.java)
                        intent.putExtra(KeyConstant.userId,user.userID)
                        intent.putExtra(KeyConstant._id,user._id)
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
        BusinessHandler.shared().repository.businessLiveData.postValue(
            Gson().fromJson(
                BusinessConstants().json,
                Business::class.java))
        SocketService.shared().verifyIfConnectedOrNot()
        setContentView(R.layout.activity_launch)
        mDelayHandler = Handler()
        //Navigate with delay
        mDelayHandler!!.postDelayed(mRunnable, SPLASH_DELAY)
        val remoteConfig: FirebaseRemoteConfig = Firebase.remoteConfig

    }





    public override fun onDestroy() {
        if (mDelayHandler != null) {
            mDelayHandler!!.removeCallbacks(mRunnable)
        }
        super.onDestroy()
    }
}