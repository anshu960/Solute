package com.friendly.framework.dataclass

import android.content.ContentValues
import android.provider.ContactsContract
import com.friendly.framework.Defaults
import com.friendly.framework.constants.KeyConstant
import com.friendly.framework.jsonObject
import org.json.JSONObject

class FriendlyUser{
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
            dict.put(KeyConstant.userId,this.userID)
            dict.put(KeyConstant._id,this._id)
            dict.put(KeyConstant.name,name)
            dict.put(KeyConstant.profilePicture,profilePic)
            dict.put(KeyConstant.emailId,email)
            dict.put(KeyConstant.mobileNumber,mobile)
            dict.put(KeyConstant.dialCode,dialCode)
            dict.put(KeyConstant.fcmToken,fcmToken)
            dict.put(KeyConstant.friendList,friendList)
            dict.put(KeyConstant.sentRequest,sentRequest)
            dict.put(KeyConstant.receivedRequest,receivedRequest)
            dict.put(KeyConstant.blockList,blockList)
            dict.put(KeyConstant.lastSeen,lastSeen)
            dict.put(KeyConstant.gender,gender)
            dict.put(KeyConstant.countryCode,countryCode)
            dict.put(KeyConstant.status,status)
            return dict
        }
        set(value) {
            data = value // parses the string and assigns values to other properties
        }

    init {
        val info =  Defaults.shared().json(KeyConstant.loginDetails)
        try {
            this.userID = info.getString(KeyConstant.userId)
            this._id = info.getString(KeyConstant._id)
            this.name = info.getString(KeyConstant.name)
            this.email = info.getString(KeyConstant.emailId)
            this.mobile = info.getString(KeyConstant.mobileNumber)
            this.dialCode = info.getString(KeyConstant.dialCode)
            this.fcmToken = info.getString(KeyConstant.fcmToken)
            this.status = info.getString(KeyConstant.status)
            this.profilePic = info.getString(KeyConstant.profilePicture)
            this.friendList = info.getString(KeyConstant.friendList)
            this.sentRequest = info.getString(KeyConstant.sentRequest)
            this.receivedRequest = info.getString(KeyConstant.receivedRequest)
            this.blockList = info.getString(KeyConstant.blockList)
            this.lastSeen = info.getDouble(KeyConstant.lastSeen)
            this.gender = info.getString(KeyConstant.gender)
            this.countryCode = info.getString(KeyConstant.countryCode)
        }
        catch (e:Exception){
        print(e.localizedMessage)
        }

    }

    fun updateFcmToServer(){
//        if(this.userID != ""){
//            val profile = FriendlyProfile(info: this.data)
//            val request : [String:Any) = .put(KeyConstant.userId:userID,KeyConstant.profile:profile.data)
//            ServiceManager().makeServiceCall(toApi: Server.updateFcm, requestData: request) { (response, res) in
//            //print(response)
//        }
//        }
    }

    fun updateInLocalStorage(){
        Defaults.shared().store(KeyConstant.loginDetails,this.data.jsonObject())
    }

    fun updateProfilePicToServer(){
//        let profile = FriendlyProfile(info: this.data)
//        let usrId = this.userID
//                if this.profilePic != "",userID != ""{
//        let request : [String:Any) = .put(KeyConstant.userId:usrId,KeyConstant.profile:profile.data)
//        ServiceManager().makeServiceCall(Server.updateProfilePicToConv,request) { (response, res) in
//        print(response)
//    }
//    }
    }

    fun updateLastSeen(){
//        let request : [String:Any) = .put(KeyConstant.userId:userID,KeyConstant.lastSeen:NSDate().timeIntervalSince1970)
//        ServiceManager().makeServiceCall(toApi: Server.updateLastSeen, requestData: request) { (response, result) in
//        print(response)
    //}
    }

    fun getAllFriends(completion:(ArrayList<ContactsContract.Profile>)->Unit){
            var request  = JSONObject()
            request.put(KeyConstant._id,_id)
//            ServiceManager().makeServiceCall(Server.getFriendList, request) {
//                var friends : ArrayList<Profile> = ArrayList()
//                try {
//                    fun JSONArray.toMutableList(): MutableList<Any> = MutableList(length(), this::get)
//                    val payload = it.json.getJSONArray(KeyConstant.payload).toMutableList()
//                    Log.d("Payload Length",payload.count().toString())
//                    payload.forEach{
//                        val friend = FriendlyProfile(it)
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
    
    fun getAllUsers(completion:(ArrayList<ContactsContract.Profile>)->Unit){
        var request  = JSONObject()
        request.put(KeyConstant._id,_id)
//        ServiceManager().makeServiceCall(Server.getAllUsers, request) {
//            var friends : ArrayList<Profile> = ArrayList()
//            try {
//                fun JSONArray.toMutableList(): MutableList<Any> = MutableList(length(), this::get)
//                val payload = it.json.getJSONArray(KeyConstant.payload).toMutableList()
//                Log.d("Payload Length",payload.count().toString())
//                payload.forEach{
//                    val friend = FriendlyProfile(it)
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
