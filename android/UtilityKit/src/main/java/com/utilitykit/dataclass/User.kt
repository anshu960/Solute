package com.utilitykit.dataclass

import android.content.ContentValues
import com.utilitykit.Constants.Key
import com.utilitykit.Defaults
import com.utilitykit.jsonObject
import org.json.JSONObject

class User{
    var userID : String = ""
    var _id : String = ""
    var name : String = ""
    var email : String = ""
    var mobile : String = ""
    var dialCode : String = ""
    var status : String = ""
    var profilePic : String = ""
    var fcmToken : String = ""
    var friendList : String = ""
    var sentRequest : String = ""
    var receivedRequest :String = ""
    var blockList : String = ""
    var gender : String = ""
    var countryCode : String = ""
    var lastSeen : Double = 0.0
    var data:ContentValues
        get() {
            var dict  = ContentValues()
            dict.put(Key.userId,this.userID)
            dict.put(Key._id,this._id)
            dict.put(Key.name,name)
            dict.put(Key.profilePicture,profilePic)
            dict.put(Key.emailId,email)
            dict.put(Key.mobileNumber,mobile)
            dict.put(Key.dialCode,dialCode)
            dict.put(Key.fcmToken,fcmToken)
            dict.put(Key.friendList,friendList)
            dict.put(Key.sentRequest,sentRequest)
            dict.put(Key.receivedRequest,receivedRequest)
            dict.put(Key.blockList,blockList)
            dict.put(Key.lastSeen,lastSeen)
            dict.put(Key.gender,gender)
            dict.put(Key.countryCode,countryCode)
            dict.put(Key.status,status)
            return dict
        }
        set(value) {
            data = value // parses the string and assigns values to other properties
        }

    init {
        val info =  Defaults.json(Key.loginDetails)
        try {
            this.userID = info.getString(Key.userId)
            this._id = info.getString(Key._id)
            this.name = info.getString(Key.name)
            this.email = info.getString(Key.emailId)
            this.mobile = info.getString(Key.mobileNumber)
            this.dialCode = info.getString(Key.dialCode)
            this.fcmToken = info.getString(Key.fcmToken)
            this.status = info.getString(Key.status)
            this.profilePic = info.getString(Key.profilePicture)
            this.friendList = info.getString(Key.friendList)
            this.sentRequest = info.getString(Key.sentRequest)
            this.receivedRequest = info.getString(Key.receivedRequest)
            this.blockList = info.getString(Key.blockList)
            this.lastSeen = info.getDouble(Key.lastSeen)
            this.gender = info.getString(Key.gender)
            this.countryCode = info.getString(Key.countryCode)
        }
        catch (e:Exception){
        print(e.localizedMessage)
        }

    }

    fun updateFcmToServer(){
//        if(this.userID != ""){
//            val profile = Profile(info: this.data)
//            val request : [String:Any) = .put(Key.userId:userID,Key.profile:profile.data)
//            ServiceManager().makeServiceCall(toApi: Server.updateFcm, requestData: request) { (response, res) in
//            //print(response)
//        }
//        }
    }

    fun updateInLocalStorage(){
        Defaults.store(Key.loginDetails,this.data.jsonObject())
    }

    fun updateProfilePicToServer(){
//        let profile = Profile(info: this.data)
//        let usrId = this.userID
//                if this.profilePic != "",userID != ""{
//        let request : [String:Any) = .put(Key.userId:usrId,Key.profile:profile.data)
//        ServiceManager().makeServiceCall(Server.updateProfilePicToConv,request) { (response, res) in
//        print(response)
//    }
//    }
    }

    fun updateLastSeen(){
//        let request : [String:Any) = .put(Key.userId:userID,Key.lastSeen:NSDate().timeIntervalSince1970)
//        ServiceManager().makeServiceCall(toApi: Server.updateLastSeen, requestData: request) { (response, result) in
//        print(response)
    //}
    }

    fun getAllFriends(completion:(ArrayList<Profile>)->Unit){
            var request  = JSONObject()
            request.put(Key._id,_id)
//            ServiceManager().makeServiceCall(Server.getFriendList, request) {
//                var friends : ArrayList<Profile> = ArrayList()
//                try {
//                    fun JSONArray.toMutableList(): MutableList<Any> = MutableList(length(), this::get)
//                    val payload = it.json.getJSONArray(Key.payload).toMutableList()
//                    Log.d("Payload Length",payload.count().toString())
//                    payload.forEach{
//                        val friend = Profile(it)
//                        friends.add(friend)
//                        SQLite.db.replace(TableNames.friends,null,friend.data)
//                    }
//                    completion(friends)
//                }catch (e: Exception){
//                    Log.d("Getting Friends error","Exception occured")
//                    completion(friends)
//                }
//            }
        }
    
    fun getAllUsers(completion:(ArrayList<Profile>)->Unit){
        var request  = JSONObject()
        request.put(Key._id,_id)
//        ServiceManager().makeServiceCall(Server.getAllUsers, request) {
//            var friends : ArrayList<Profile> = ArrayList()
//            try {
//                fun JSONArray.toMutableList(): MutableList<Any> = MutableList(length(), this::get)
//                val payload = it.json.getJSONArray(Key.payload).toMutableList()
//                Log.d("Payload Length",payload.count().toString())
//                payload.forEach{
//                    val friend = Profile(it)
//                    friends.add(friend)
////                    SQLite.db.replace(TableNames.us,null,friend.data)
//                }
//                completion(friends)
//            }catch (e: Exception){
//                Log.d("Getting Friends error","Exception occured")
//                completion(friends)
//            }
//        }
    }

}
