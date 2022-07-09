package com.utilitykit.dataclass

import android.content.ContentValues
import com.utilitykit.Constants.Key
import com.utilitykit.UtilityKitApp

import com.utilitykit.json
import org.json.JSONObject

class Friend(
    var _id : String = "",
    var fromUserId:String = "",
    var toUserId:String = "",
    var sentTime :  String = "",
    var deliveredTime : String = "",
    var readTime : String = "",
    var acceptedTime : String = "",
    var profile : Profile = Profile()
                   ) {
    
    var data: ContentValues
        get() {
            var dict: ContentValues = ContentValues()
            dict.put(Key._id, _id)
            dict.put(Key.fromUserId, fromUserId)
            dict.put(Key.toUserId, toUserId)
            dict.put(Key.sentTime, sentTime)
            dict.put(Key.deliveredTime, deliveredTime)
            dict.put(Key.readTime, readTime)
            dict.put(Key.acceptedTime, acceptedTime)
            dict.put(Key.profile, profile.data.json())
            return dict
        }
        set(value) {
            data = value // parses the string and assigns values to other properties
        }
    
    constructor(info: Any) : this() {
        try {
            val obj = JSONObject(info.toString())
            this._id = obj.getString(Key._id)
            this.fromUserId = obj.getString(Key.fromUserId)
            this.toUserId = obj.getString(Key.toUserId)
            this.sentTime = obj.getString(Key.sentTime)
            this.deliveredTime = obj.getString(Key.deliveredTime)
            this.readTime = obj.getString(Key.readTime)
            this.acceptedTime = obj.getString(Key.acceptedTime)
            this.profile = Profile(JSONObject(obj.getString(Key.profile)))
        } catch (e: Exception) {
        
        }
    }
    
    constructor(info: JSONObject) : this() {
        try {
            this._id = info.getString(Key._id)
            this.fromUserId = info.getString(Key.fromUserId)
            this.toUserId = info.getString(Key.toUserId)
            this.sentTime = info.getString(Key.sentTime)
            this.deliveredTime = info.getString(Key.deliveredTime)
            this.readTime = info.getString(Key.readTime)
            this.acceptedTime = info.getString(Key.acceptedTime)
            if(info.has(Key.fromUser) && info.has(Key.toUser)){
                val fromUser = info.getJSONObject(Key.fromUser)
                val toUser = info.getJSONObject(Key.toUser)
                if(User()._id == fromUserId){
                    profile = Profile(toUser)
                }else{
                    profile = Profile(fromUser)
                }
            }else{
                this.profile = Profile(info.getString(Key.profile))
            }
        } catch (e: Exception) {
            print("exception")
        }
    }
    
    constructor(info: ContentValues) : this() {
        try {
            this._id = info.getAsString(Key._id)
            this.fromUserId = info.getAsString(Key.fromUserId)
            this.toUserId = info.getAsString(Key.toUserId)
            this.sentTime = info.getAsString(Key.sentTime)
            this.deliveredTime = info.getAsString(Key.deliveredTime)
            this.readTime = info.getAsString(Key.readTime)
            this.acceptedTime = info.getAsString(Key.acceptedTime)
            this.profile = Profile(JSONObject(info.getAsString(Key.profile)))
        } catch (e: Exception) {
        
        }
    }
    
    
    fun acceptFriendRequest(completion:((Boolean,String)->Unit)) {
        var request = JSONObject()
        request.put(Key.toUserId, toUserId)
        request.put(Key.fromUserId, this.fromUserId)
//        ServiceManager().makeServiceCall(Server.acceptFriendRequest, request) {
//            try {
//                val payload = it.json.getBoolean(Key.payload)
//                val msg = it.json.getString(Key.message)
//                completion(payload, msg)
//            } catch (e: Exception) {
//                completion(false, Constants.defaultErrorMessage)
//            }
//        }
    }
    
    
    fun downloadFriendFriendRequest() {
        var request = JSONObject()
        request.put(Key._id, UtilityKitApp.user._id)
//        ServiceManager().makeServiceCall(Server.allFriendRequest, request) {
//            try {
//                fun JSONArray.toMutableList(): MutableList<Any> = MutableList(length(), this::get)
//                val payload = it.json.getJSONArray(Key.payload).toMutableList()
//                payload.forEach {
//                    val profile = Profile(it)
//                    SQLite.db.replace(TableNames.friendRequest, null, profile.data)
//                }
//            }catch (e:Exception){
//
//            }
//        }
    }
}

