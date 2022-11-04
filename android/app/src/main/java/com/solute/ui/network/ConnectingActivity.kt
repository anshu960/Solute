package com.solute.ui.network

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.solute.MainActivity
import com.solute.R
import com.solute.ui.login.LoginActivity
import com.solute.ui.register.RegisterActivity
import com.utilitykit.Constants.Key
import com.utilitykit.Defaults
import com.utilitykit.dataclass.User
import com.utilitykit.socket.SocketService

class ConnectingActivity : AppCompatActivity() {
    var isNavigated = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_connecting)
        if(SocketService.shared().repository.socketConnectionStatus.value == 1){
            onSocketConnected()
        }else{
            reconnectSocket()
        }
        SocketService.shared().repository.socketConnectionStatus.observe(this){
            if(it != null){
                if(it == 1){
                    onSocketConnected()
                }else{
                    reconnectSocket()
                }
            }
        }
    }
    fun reconnectSocket(){
        SocketService.shared().connect()
    }
    fun onSocketConnected(){
        if(!isNavigated){
            isNavigated = true
            Handler().postDelayed({
                val userDetails = Defaults.string(Key.loginDetails)
                if(userDetails != null && userDetails != ""){
                    val user = User()
                    if (user.name != "" && user.userID != "" && user._id != ""){
                        if(user.profilePic == ""){
                            val intent = Intent(applicationContext, MainActivity::class.java)
//                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            this.startActivity(intent)
                        }else{
                            val intent = Intent(applicationContext, MainActivity::class.java)
//                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            this.startActivity(intent)
                        }
                    }else{
                        val intent = Intent(applicationContext, RegisterActivity::class.java)
//                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        intent.putExtra(Key.userId,user.userID)
                        intent.putExtra(Key._id,user._id)
                        this.startActivity(intent)
                    }
                }else{
                    val intent = Intent(applicationContext, LoginActivity::class.java)
//                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                }
            }, 1000)
        }
    }
}