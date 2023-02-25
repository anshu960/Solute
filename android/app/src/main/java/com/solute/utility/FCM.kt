package com.solute.utility

import android.content.ContentValues
import android.util.Log
import com.friendly.framework.Defaults
import com.friendly.framework.constants.KeyConstant
import com.friendly.framework.dataclass.FriendlyUser
import com.solute.constants.Server
import org.json.JSONObject

class FCM {
    val tag = "FCM"
    fun sendFcm(to:String,title:String,msg:String,data:JSONObject){
        var notification = JSONObject()
        notification.put("title",title)
        notification.put("body",msg)
        notification.put("content_available",true)
        notification.put("priority","hight")
        notification.put("sound","default")
        if(to != ""){
            var header = ContentValues()
//            header.put("Authorization","key=${Server.fcmServerKey}")
            header.put("Content-Type","application/json")
            var requestData = JSONObject()
            requestData.put("to",to)
            requestData.put("notification",notification)
            requestData.put("data",data)
//            val service = ServiceManager()
            Log.d(tag,"FCM Url = "+Server.fcmServer)
            Log.d(tag,"FCM Request = "+requestData.toString())
//            service.makeServiceCall(Server.fcmServer,requestData,header){
//                Log.d(tag,"FCM Response = "+it.responseValue)
//            }
        }
    }

    fun updateFcmTokenToServer(){
        val fcmToken = Defaults.shared().string(KeyConstant.fcmToken)
        val user = FriendlyUser()

        if(fcmToken != "" && user._id != ""){
            val user = FriendlyUser()
            user.fcmToken = fcmToken
            val request = JSONObject()
            request.put(KeyConstant._id,user._id)
            request.put(KeyConstant.fcmToken,fcmToken)
//            ServiceManager().makeServiceCall(Server.updateFcm,request){
//                Log.d("FCM","FCM Update in Server Response = " + it.responseValue)
//            }
        }
    }
}