package com.friendly.framework.dataclass

import android.content.ContentValues
import com.friendly.framework.constants.KeyConstant
import org.json.JSONObject

class FriendlyProfile(
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
            dict.put(KeyConstant._id, _id)
            dict.put(KeyConstant.userId, userID)
            dict.put(KeyConstant.name, name)
            dict.put(KeyConstant.emailId, emailID)
            dict.put(KeyConstant.mobileNumber, mobileNumber)
            dict.put(KeyConstant.fcmToken, fcmToken)
            dict.put(KeyConstant.profilePicture, profilePic)
            dict.put(KeyConstant.lastSeen, lastSeen)
            dict.put(KeyConstant.gender, gender)
            dict.put(KeyConstant.countryCode, countryCode)
            dict.put(KeyConstant.status, status)
            return dict
        }
        set(value) {
            data = value // parses the string and assigns values to other properties
        }

    constructor(info: Any) : this() {
        try {
            val obj = JSONObject(info.toString())
            this._id = obj.getString(KeyConstant._id)
            this.userID = obj.getString(KeyConstant.userId)
            this.mobileNumber = obj.getString(KeyConstant.mobileNumber)
            this.name = obj.getString(KeyConstant.name)
            this.profilePic = obj.getString(KeyConstant.profilePicture)
            this.fcmToken = obj.getString(KeyConstant.fcmToken)
            this.status = obj.getString(KeyConstant.status)
            this.emailID = obj.getString(KeyConstant.emailId)
            this.gender = obj.getString(KeyConstant.gender)
            this.countryCode = obj.getString(KeyConstant.countryCode)
            
        } catch (e: Exception) {
            print("xxxProfile Exception any ")
            print("xxxProfile Exception any value obj")
        }
//        this.lastSeen = info.get(KeyConstant.lastSeen)
    }

    constructor(info: JSONObject) : this() {
        try {
            this._id = info.getString(KeyConstant._id)
            this.userID = info.getString(KeyConstant.userId)
            this.name = info.getString(KeyConstant.name)
            this.mobileNumber = info.getString(KeyConstant.mobileNumber)
            this.profilePic = info.getString(KeyConstant.profilePicture)
            this.status = info.getString(KeyConstant.status)
            this.gender = info.getString(KeyConstant.gender)
            this.countryCode = info.getString(KeyConstant.countryCode)
            this.fcmToken = info.getString(KeyConstant.fcmToken)
            this.emailID = info.getString(KeyConstant.emailId)
        } catch (e: Exception) {
            print("Profile Exception ")
        }

//        this.lastSeen = info.ge(KeyConstant.lastSeen)
    }

    constructor(info: ContentValues) : this() {
        try {
            this._id = info.getAsString(KeyConstant._id)
            this.userID = info.getAsString(KeyConstant.userId)
            this.mobileNumber = info.getAsString(KeyConstant.mobileNumber)
            this.profilePic = info.getAsString(KeyConstant.profilePicture)
            this.fcmToken = info.getAsString(KeyConstant.fcmToken)
            this.name = info.getAsString(KeyConstant.name)
            this.emailID = info.getAsString(KeyConstant.emailId)
            this.countryCode = info.getAsString(KeyConstant.countryCode)
            this.gender = info.getAsString(KeyConstant.gender)
            this.countryCode = info.getAsString(KeyConstant.countryCode)
        } catch (e: Exception) {
            print("Profile Exception ")
        }
    }

    fun sendFriendRequest(completion:((Boolean,String)->Unit)) {
        var request = JSONObject()
//        request.put(KeyConstant.userId, UtilityKitApp.user.userID)
        request.put(KeyConstant.toUserId, this.userID)

//        ServiceManager().makeServiceCall(Server.sendFriendRequest, request) {
//                try {
//                    val payload = it.json.getBoolean(KeyConstant.payload)
//                    val msg = it.json.getString(KeyConstant.message)
//                    completion(payload,msg)
//                }catch (e:Exception){
//                    completion(false,Constants.defaultErrorMessage)
//                }
//            }
        }


    fun acceptFriendRequest(completion:((Boolean,String)->Unit)) {
        var request = JSONObject()
//        request.put(KeyConstant.userId, UtilityKitApp.user.userID)
        request.put(KeyConstant.toUserId, this.userID)
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

    fun downloadSentFriendRequest() {
        var request = JSONObject()
//        request.put(KeyConstant.userId, UtilityKitApp.user.userID)
//        ServiceManager().makeServiceCall(Server.getSentRequest, request) {
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

    fun downloadReceivedFriendRequest() {
        var request = JSONObject()
//        request.put(KeyConstant.userId, UtilityKitApp.user.userID)
//        ServiceManager().makeServiceCall(Server.getSentRequest, request) {
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
