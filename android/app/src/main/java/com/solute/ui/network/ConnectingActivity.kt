package com.solute.ui.network

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.friendly.framework.Defaults
import com.friendly.framework.constants.KeyConstant
import com.friendly.framework.dataclass.FriendlyUser
import com.friendly.framework.socket.SocketService
import com.google.firebase.dynamiclinks.PendingDynamicLinkData
import com.google.firebase.dynamiclinks.ktx.dynamicLinks
import com.google.firebase.ktx.Firebase
import com.solute.MainActivity
import com.solute.R
import com.solute.ui.business.receipt.ReceiptDetailsActivity
import com.solute.ui.onboarding.OnBoardingActivity

class ConnectingActivity : AppCompatActivity() {
    var isNavigated = false
    var targetIntent : Intent? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_connecting)
        prepareIntent()
        checkConnectionStatus()
        SocketService.shared().repository.socketConnectionStatus.observe(this){
            if(it != null){
                if(it == 1){
                    onSocketConnected()
                }else{
                    reconnectSocket()
                }
            }
        }
        checkDynamicLink()
    }

    fun checkConnectionStatus(){
        if(SocketService.shared().repository.socketConnectionStatus.value == 1){
            onSocketConnected()
        }else{
            reconnectSocket()
        }
    }

    fun prepareIntent(){
        val userDetails = Defaults.shared().string(KeyConstant.loginDetails)
        if(userDetails != ""){
            val user = FriendlyUser()
            if (user.name != "" && user.userID != "" && user._id != ""){
                if(user.profilePic == ""){
                    targetIntent = Intent(applicationContext, MainActivity::class.java)
//                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                }else{
                    targetIntent = Intent(applicationContext, MainActivity::class.java)
//                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                }
            }else{
                targetIntent = Intent(applicationContext, OnBoardingActivity::class.java)
//                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                targetIntent?.putExtra(KeyConstant.userId,user.userID)
                targetIntent?.putExtra(KeyConstant._id,user._id)
            }
        }else{
            targetIntent = Intent(applicationContext, OnBoardingActivity::class.java)
//                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
    }
    fun reconnectSocket(){
        SocketService.shared().connect()
    }
    fun onSocketConnected(){
        if(!isNavigated){
            isNavigated = true
            Handler().postDelayed({
                targetIntent?.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(targetIntent)
            }, 1000)
        }
    }

    override fun onResume() {
        super.onResume()
        checkDynamicLink()
    }

    fun checkDynamicLink(){
        Firebase.dynamicLinks
            .getDynamicLink(intent)
            .addOnSuccessListener(this) { pendingDynamicLinkData: PendingDynamicLinkData? ->
                // Get deep link from result (may be null if no link is found)
                if (pendingDynamicLinkData != null && pendingDynamicLinkData?.link != null) {
                    val id: String = pendingDynamicLinkData!!.link.toString().split("=").last()
                    targetIntent = Intent(this,ReceiptDetailsActivity::class.java)
                    if(pendingDynamicLinkData?.link.toString().contains("receipt?id=")){
                        targetIntent?.putExtra(KeyConstant.invoiceId,id)
                    }else{
                        prepareIntent()
                        checkConnectionStatus()
                    }
                }else{
                    prepareIntent()
                    checkConnectionStatus()
                }
            }
            .addOnFailureListener(this) { e ->
                Log.w("DYNAMIC_LINK", "getDynamicLink:onFailure", e)
                prepareIntent()
                checkConnectionStatus()
            }
    }
}