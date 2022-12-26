package com.utilitykit.dataclass
import android.content.ContentValues
import com.utilitykit.Constants.Key
import org.json.JSONObject

class WorkExperience(
    var _id : String = "",
    var userID:String = "",
    var fcmToken:String = "",
    var name : String = "",
    var status :  String = "",
    var emailID : String = "",
    var mobileNumber : String = "",
    var profilePic : String = "",
    var gender : String = "",
    var countryCode : String = "",
    var lastSeen : Double = 0.0) {
    
    var data: ContentValues
        get() {
            var dict: ContentValues = ContentValues()
            dict.put(Key._id, _id)
            dict.put(Key.userId, userID)
            dict.put(Key.name, name)
            dict.put(Key.emailId, emailID)
            dict.put(Key.mobileNumber, mobileNumber)
            dict.put(Key.fcmToken, fcmToken)
            dict.put(Key.profilePicture, profilePic)
            dict.put(Key.lastSeen, lastSeen)
            dict.put(Key.gender, gender)
            dict.put(Key.countryCode, countryCode)
            dict.put(Key.status, status)
            return dict
        }
        set(value) {
            data = value // parses the string and assigns values to other properties
        }
    
    constructor(info: Any) : this() {
        try {
            val obj = JSONObject(info.toString())
            this._id = obj.getString(Key._id)
            this.userID = obj.getString(Key.userId)
            this.mobileNumber = obj.getString(Key.mobileNumber)
            this.name = obj.getString(Key.name)
            this.profilePic = obj.getString(Key.profilePicture)
            this.fcmToken = obj.getString(Key.fcmToken)
            this.status = obj.getString(Key.status)
            this.emailID = obj.getString(Key.emailId)
            this.gender = obj.getString(Key.gender)
            this.countryCode = obj.getString(Key.countryCode)
            
        } catch (e: Exception) {
            print("xxxProfile Exception any ")
            print("xxxProfile Exception any value obj")
        }
//        this.lastSeen = info.get(Key.lastSeen)
    }
    
    constructor(info: JSONObject) : this() {
        try {
            this._id = info.getString(Key._id)
            this.userID = info.getString(Key.userId)
            this.name = info.getString(Key.name)
            this.mobileNumber = info.getString(Key.mobileNumber)
            this.profilePic = info.getString(Key.profilePicture)
            this.status = info.getString(Key.status)
            this.gender = info.getString(Key.gender)
            this.countryCode = info.getString(Key.countryCode)
            this.fcmToken = info.getString(Key.fcmToken)
            this.emailID = info.getString(Key.emailId)
        } catch (e: Exception) {
            print("Profile Exception ")
        }

//        this.lastSeen = info.ge(Key.lastSeen)
    }
    
    constructor(info: ContentValues) : this() {
        try {
            this._id = info.getAsString(Key._id)
            this.userID = info.getAsString(Key.userId)
            this.mobileNumber = info.getAsString(Key.mobileNumber)
            this.profilePic = info.getAsString(Key.profilePicture)
            this.fcmToken = info.getAsString(Key.fcmToken)
            this.name = info.getAsString(Key.name)
            this.emailID = info.getAsString(Key.emailId)
            this.countryCode = info.getAsString(Key.countryCode)
            this.gender = info.getAsString(Key.gender)
            this.countryCode = info.getAsString(Key.countryCode)
        } catch (e: Exception) {
            print("Profile Exception ")
        }
    }
    
    fun sendFriendRequest(completion:((Boolean,String)->Unit)) {
        var request = JSONObject()
//        request.put(Key.userId, UtilityKitApp.user.userID)
        request.put(Key.toUserId, this.userID)
        
//        ServiceManager().makeServiceCall(Server.sendFriendRequest, request) {
//            try {
//                val payload = it.json.getBoolean(Key.payload)
//                val msg = it.json.getString(Key.message)
//                completion(payload,msg)
//            }catch (e:Exception){
//                completion(false,Constants.defaultErrorMessage)
//            }
//        }
    }
    
    
    fun acceptFriendRequest(completion:((Boolean,String)->Unit)) {
        var request = JSONObject()
//        request.put(Key.userId, UtilityKitApp.user.userID)
        request.put(Key.toUserId, this.userID)
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
    
    fun downloadSentFriendRequest() {
        var request = JSONObject()
//        request.put(Key.userId, UtilityKitApp.user.userID)
//        ServiceManager().makeServiceCall(Server.getSentRequest, request) {
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
    
    fun downloadReceivedFriendRequest() {
        var request = JSONObject()
//        request.put(Key.userId, UtilityKitApp.user.userID)
//        ServiceManager().makeServiceCall(Server.getSentRequest, request) {
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
