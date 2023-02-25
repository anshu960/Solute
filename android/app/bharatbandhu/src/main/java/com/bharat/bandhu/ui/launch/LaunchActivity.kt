package com.bharat.bandhu.ui.launch

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.bharat.bandhu.R
import com.bharat.bandhu.ui.MainActivity
import com.bharat.bandhu.ui.onboard.LoginActivity
import com.bharat.bandhu.ui.onboard.register.SignupActivity
import com.friendly.framework.Defaults
import com.friendly.framework.UtilityActivity
import com.friendly.framework.constants.KeyConstant
import com.friendly.framework.dataclass.FriendlyUser
import com.friendly.framework.socket.SocketService

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

        SocketService.shared().currentActivity = this
        SocketService.shared().connect()
        setContentView(R.layout.activity_launch)
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
}