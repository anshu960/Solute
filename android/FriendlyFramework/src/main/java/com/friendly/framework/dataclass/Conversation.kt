package com.friendly.framework.dataclass

import android.content.ContentValues
import android.util.Log
import com.friendly.framework.constants.KeyConstant
import com.friendly.framework.json
import org.json.JSONArray
import org.json.JSONObject


data class Conversation(var _id : String="",
                        var fromUserId : String = "",
                        var toUserId : String = "",
                        var message : Message = Message(),
                        var adminUsers : JSONArray = JSONArray(),
                        var participants : JSONArray = JSONArray(),
                        var adminUserProfiles : JSONArray = JSONArray(),
                        var participantsProfiles : JSONArray = JSONArray(),
                        var groupName:String = "",
                        var profilePicture : String = "",
                        var groupDescription:String="",
                        var statusDescription:String="",
                        var ownerUserID : String = "",
                        var isGroup :Boolean = false,
                        var createdAt:String="",
                        var updatedAt:String=""
 )
{

    var data : ContentValues get(){
        var dict = ContentValues()
        var adminProfileStr = adminUserProfiles.toString()
        var participantsProfileStr = participantsProfiles.toString()
        dict.put(KeyConstant._id ,_id)
        dict.put(KeyConstant.fromUserId, fromUserId)
        dict.put(KeyConstant.toUserId,toUserId)
        dict.put(KeyConstant.message,message.data.json()) //message.data.json()
        dict.put(KeyConstant.adminUsers,adminUsers.toString())
        dict.put(KeyConstant.participants,participants.toString())
        dict.put(KeyConstant.adminUserProfiles,adminProfileStr)
        dict.put(KeyConstant.participantsProfiles,participantsProfileStr)
        dict.put(KeyConstant.groupName,groupName)
        dict.put(KeyConstant.profilePicture,profilePicture)
        dict.put(KeyConstant.groupDescription,groupDescription)
        dict.put(KeyConstant.statusDescription,statusDescription)
        dict.put(KeyConstant.ownerUserID,ownerUserID)
        dict.put(KeyConstant.isGroup,if(isGroup)  1 else 0)
        dict.put(KeyConstant.profiles,getAllProfilesInJsonString())
        dict.put(KeyConstant.createdAt,createdAt)
        dict.put(KeyConstant.updatedAt,updatedAt)
        return dict
    } set(value) {
        data = value // parses the string and assigns values to other properties
    }

    fun getAllProfilesInJsonString():String{
        var profiles = ""

        return profiles
    }

    constructor(info:Any):this(){
        try{
            val data = JSONObject(info.toString())
            _id = data.getString(KeyConstant._id)
            fromUserId = data.getString(KeyConstant.fromUserId)
            toUserId = data.getString(KeyConstant.toUserId)
            adminUsers = data.getJSONArray(KeyConstant.adminUsers)
            participantsProfiles = data.getJSONArray(KeyConstant.participantsProfiles)
            adminUserProfiles = data.getJSONArray(KeyConstant.adminUserProfiles)
            isGroup = data.getBoolean(KeyConstant.isGroup)
            groupName = data.getString(KeyConstant.groupName)
            profilePicture = data.getString(KeyConstant.profilePicture)
            groupDescription = data.getString(KeyConstant.groupDescription)
            statusDescription = data.getString(KeyConstant.statusDescription)
            createdAt = data.getString(KeyConstant.createdAt)
            updatedAt = data.getString(KeyConstant.updatedAt)
            participants = data.getJSONArray(KeyConstant.participants)
            if(data.has(KeyConstant.lastMessageDetails)){
                message = Message(data.getJSONObject(KeyConstant.lastMessageDetails))
            }
        }catch (e:Exception){
            print(data.toString())
            e.message?.let { Log.d("Conversation Data Model", it) }
        }
    }

    constructor(data:JSONObject):this(){
        try {
            _id = data.getString(KeyConstant._id)
            fromUserId = data.getString(KeyConstant.fromUserId)
            toUserId = data.getString(KeyConstant.toUserId)
            adminUsers = data.getJSONArray(KeyConstant.adminUsers)
            participants = data.getJSONArray(KeyConstant.participants)
            participantsProfiles = data.getJSONArray(KeyConstant.participantsProfiles)
            adminUserProfiles = data.getJSONArray(KeyConstant.adminUserProfiles)
            isGroup = data.getBoolean(KeyConstant.isGroup)
            groupName = data.getString(KeyConstant.groupName)
            profilePicture = data.getString(KeyConstant.profilePicture)
            groupDescription = data.getString(KeyConstant.groupDescription)
            statusDescription = data.getString(KeyConstant.statusDescription)
            createdAt = data.getString(KeyConstant.createdAt)
            updatedAt = data.getString(KeyConstant.updatedAt)
//            Log.d("Message",data.getString(KeyConstant.message))
        }catch (e:Exception){
        print("")
        }
    }

    constructor(info:ContentValues):this(){
        try {
            _id = info.getAsString(KeyConstant._id)
            fromUserId = info.getAsString(KeyConstant.fromUserId)
            toUserId = info.getAsString(KeyConstant.toUserId)
            adminUsers = JSONArray(info.getAsString(KeyConstant.adminUsers))
            participants = JSONArray(info.getAsString(KeyConstant.participants))
            adminUserProfiles = JSONArray(info.getAsString(KeyConstant.adminUserProfiles))
            participantsProfiles = JSONArray(info.getAsString(KeyConstant.participantsProfiles))
            isGroup = info.getAsBoolean(KeyConstant.isGroup)
            groupName = info.getAsString(KeyConstant.groupName)
            profilePicture = info.getAsString(KeyConstant.profilePicture)
            groupDescription = info.getAsString(KeyConstant.groupDescription)
            statusDescription = info.getAsString(KeyConstant.statusDescription)
            createdAt = data.getAsString(KeyConstant.createdAt)
            updatedAt = data.getAsString(KeyConstant.updatedAt)
        }catch (e:Exception){
            print("")
        }
    }

    fun getOtherUserFriendlyProfile():FriendlyProfile{
        var user = FriendlyUser()
        for (i in 0 until participantsProfiles.length()) {
            val item = participantsProfiles.getJSONObject(i)
            val _id = item.getString(KeyConstant._id)
            if(_id != "" && user._id != _id){
                return FriendlyProfile(item)
            }
        }
        return FriendlyProfile()
    }
    
    
    fun getOtherUserId():String{
        val user = FriendlyUser()
        var otherUserId = ""
        for (i in 0 until participantsProfiles.length()) {
            val item = participantsProfiles.getJSONObject(i)
            val _id = item.getString(KeyConstant._id)
            if(_id != "" && user._id != _id){
                return _id
            }
        }
        return otherUserId
    }

     fun getAll(completion:()->Unit){
        var request  = JSONObject()
        request.put(KeyConstant._id, FriendlyUser()._id)
//        ServiceManager().makeServiceCall(Server.getConversation, request) {
//            var conversations : ArrayList<Conversation> = ArrayList()
//            try {
//                fun JSONArray.toMutableList(): MutableList<Any> = MutableList(length(), this::get)
//                val payload = it.json.getJSONArray(KeyConstant.payload).toMutableList()
//                Log.d("Payload Length",payload.count().toString())
//                payload.forEach{
//                    val conversation = Conversation(it)
//                    if (conversation.message.content != ""){
//                        conversation.message.content = Encryption().decryptMessage(conversation.message.content,conversation)
//                    }
//                    conversations.add(conversation)
//                    val conversationData = conversation.data
//                    SQLite.db.replace(TableNames.conversation,null,conversationData)
//                    val data = conversation.data
//                    val dataStr = data.json()
//                    print(dataStr)
//                }
//                completion()
//            }catch (e:Exception){
//                Log.d("Conversation","Exception occured while parsing the conversation")
//                completion()
//            }
//        }
    }
    fun checkUpdatedFriendlyProfile(){

    }


}