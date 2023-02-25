package com.friendly.framework.dataclass

import android.content.ContentValues
import com.friendly.framework.constants.KeyConstant
import com.friendly.framework.json
import org.json.JSONObject

class Friend(
    var _id : String = "",
    var fromUserId:String = "",
    var toUserId:String = "",
    var sentTime :  String = "",
    var deliveredTime : String = "",
    var readTime : String = "",
    var acceptedTime : String = "",
    var profile : FriendlyProfile = FriendlyProfile()
                   ) {
    
    var data: ContentValues
        get() {
            var dict: ContentValues = ContentValues()
            dict.put(KeyConstant._id, _id)
            dict.put(KeyConstant.fromUserId, fromUserId)
            dict.put(KeyConstant.toUserId, toUserId)
            dict.put(KeyConstant.sentTime, sentTime)
            dict.put(KeyConstant.deliveredTime, deliveredTime)
            dict.put(KeyConstant.readTime, readTime)
            dict.put(KeyConstant.acceptedTime, acceptedTime)
            dict.put(KeyConstant.profile, profile.data.json())
            return dict
        }
        set(value) {
            data = value // parses the string and assigns values to other properties
        }
    
    constructor(info: Any) : this() {
        try {
            val obj = JSONObject(info.toString())
            this._id = obj.getString(KeyConstant._id)
            this.fromUserId = obj.getString(KeyConstant.fromUserId)
            this.toUserId = obj.getString(KeyConstant.toUserId)
            this.sentTime = obj.getString(KeyConstant.sentTime)
            this.deliveredTime = obj.getString(KeyConstant.deliveredTime)
            this.readTime = obj.getString(KeyConstant.readTime)
            this.acceptedTime = obj.getString(KeyConstant.acceptedTime)
            this.profile = FriendlyProfile(JSONObject(obj.getString(KeyConstant.profile)))
        } catch (e: Exception) {
        
        }
    }
    
    constructor(info: JSONObject) : this() {
        try {
            this._id = info.getString(KeyConstant._id)
            this.fromUserId = info.getString(KeyConstant.fromUserId)
            this.toUserId = info.getString(KeyConstant.toUserId)
            this.sentTime = info.getString(KeyConstant.sentTime)
            this.deliveredTime = info.getString(KeyConstant.deliveredTime)
            this.readTime = info.getString(KeyConstant.readTime)
            this.acceptedTime = info.getString(KeyConstant.acceptedTime)
            if(info.has(KeyConstant.fromUser) && info.has(KeyConstant.toUser)){
                val fromUser = info.getJSONObject(KeyConstant.fromUser)
                val toUser = info.getJSONObject(KeyConstant.toUser)
                if(FriendlyUser()._id == fromUserId){
                    profile = FriendlyProfile(toUser)
                }else{
                    profile = FriendlyProfile(fromUser)
                }
            }else{
                this.profile = FriendlyProfile(info.getString(KeyConstant.profile))
            }
        } catch (e: Exception) {
            print("exception")
        }
    }
    
    constructor(info: ContentValues) : this() {
        try {
            this._id = info.getAsString(KeyConstant._id)
            this.fromUserId = info.getAsString(KeyConstant.fromUserId)
            this.toUserId = info.getAsString(KeyConstant.toUserId)
            this.sentTime = info.getAsString(KeyConstant.sentTime)
            this.deliveredTime = info.getAsString(KeyConstant.deliveredTime)
            this.readTime = info.getAsString(KeyConstant.readTime)
            this.acceptedTime = info.getAsString(KeyConstant.acceptedTime)
            this.profile = FriendlyProfile(JSONObject(info.getAsString(KeyConstant.profile)))
        } catch (e: Exception) {
        
        }
    }
    
    
    fun acceptFriendRequest(completion:((Boolean,String)->Unit)) {
        var request = JSONObject()
        request.put(KeyConstant.toUserId, toUserId)
        request.put(KeyConstant.fromUserId, this.fromUserId)
//        ServiceManager().makeServiceCall(Server.acceptFriendRequest, request) {
//            try {
//                val payload = it.json.getBoolean(KeyConstant.payload)
//                val msg = it.json.getString(KeyConstant.message)
//                completion(payload, msg)
//            } catch (e: Exception) {
//                completion(false, Constants.defaultErrorMessage)
//            }
//        }
    }
    
    
    fun downloadFriendFriendRequest() {
        var request = JSONObject()
//        request.put(KeyConstant._id, BusinessHandler.shared().context.user._id)
//        ServiceManager().makeServiceCall(Server.allFriendRequest, request) {
//            try {
//                fun JSONArray.toMutableList(): MutableList<Any> = MutableList(length(), this::get)
//                val payload = it.json.getJSONArray(KeyConstant.payload).toMutableList()
//                payload.forEach {
//                    val profile = FriendlyProfile(it)
//                    SQLite.db.replace(TableNames.friendRequest, null, profile.data)
//                }
//            }catch (e:Exception){
//
//            }
//        }
    }
}

