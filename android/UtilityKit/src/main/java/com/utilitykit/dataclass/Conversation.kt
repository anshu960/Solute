package com.utilitykit.dataclass

import android.content.ContentValues
import android.util.Log
import com.utilitykit.Constants.Key
import com.utilitykit.json
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
        dict.put(Key._id ,_id)
        dict.put(Key.fromUserId, fromUserId)
        dict.put(Key.toUserId,toUserId)
        dict.put(Key.message,message.data.json()) //message.data.json()
        dict.put(Key.adminUsers,adminUsers.toString())
        dict.put(Key.participants,participants.toString())
        dict.put(Key.adminUserProfiles,adminProfileStr)
        dict.put(Key.participantsProfiles,participantsProfileStr)
        dict.put(Key.groupName,groupName)
        dict.put(Key.profilePicture,profilePicture)
        dict.put(Key.groupDescription,groupDescription)
        dict.put(Key.statusDescription,statusDescription)
        dict.put(Key.ownerUserID,ownerUserID)
        dict.put(Key.isGroup,if(isGroup)  1 else 0)
        dict.put(Key.profiles,getAllProfilesInJsonString())
        dict.put(Key.createdAt,createdAt)
        dict.put(Key.updatedAt,updatedAt)
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
            _id = data.getString(Key._id)
            fromUserId = data.getString(Key.fromUserId)
            toUserId = data.getString(Key.toUserId)
            adminUsers = data.getJSONArray(Key.adminUsers)
            participantsProfiles = data.getJSONArray(Key.participantsProfiles)
            adminUserProfiles = data.getJSONArray(Key.adminUserProfiles)
            isGroup = data.getBoolean(Key.isGroup)
            groupName = data.getString(Key.groupName)
            profilePicture = data.getString(Key.profilePicture)
            groupDescription = data.getString(Key.groupDescription)
            statusDescription = data.getString(Key.statusDescription)
            createdAt = data.getString(Key.createdAt)
            updatedAt = data.getString(Key.updatedAt)
            participants = data.getJSONArray(Key.participants)
            if(data.has(Key.lastMessageDetails)){
                message = Message(data.getJSONObject(Key.lastMessageDetails))
            }
        }catch (e:Exception){
            print(data.toString())
            e.message?.let { Log.d("Conversation Data Model", it) }
        }
    }

    constructor(data:JSONObject):this(){
        try {
            _id = data.getString(Key._id)
            fromUserId = data.getString(Key.fromUserId)
            toUserId = data.getString(Key.toUserId)
            adminUsers = data.getJSONArray(Key.adminUsers)
            participants = data.getJSONArray(Key.participants)
            participantsProfiles = data.getJSONArray(Key.participantsProfiles)
            adminUserProfiles = data.getJSONArray(Key.adminUserProfiles)
            isGroup = data.getBoolean(Key.isGroup)
            groupName = data.getString(Key.groupName)
            profilePicture = data.getString(Key.profilePicture)
            groupDescription = data.getString(Key.groupDescription)
            statusDescription = data.getString(Key.statusDescription)
            createdAt = data.getString(Key.createdAt)
            updatedAt = data.getString(Key.updatedAt)
//            Log.d("Message",data.getString(Key.message))
        }catch (e:Exception){
        print("")
        }
    }

    constructor(info:ContentValues):this(){
        try {
            _id = info.getAsString(Key._id)
            fromUserId = info.getAsString(Key.fromUserId)
            toUserId = info.getAsString(Key.toUserId)
            adminUsers = JSONArray(info.getAsString(Key.adminUsers))
            participants = JSONArray(info.getAsString(Key.participants))
            adminUserProfiles = JSONArray(info.getAsString(Key.adminUserProfiles))
            participantsProfiles = JSONArray(info.getAsString(Key.participantsProfiles))
            isGroup = info.getAsBoolean(Key.isGroup)
            groupName = info.getAsString(Key.groupName)
            profilePicture = info.getAsString(Key.profilePicture)
            groupDescription = info.getAsString(Key.groupDescription)
            statusDescription = info.getAsString(Key.statusDescription)
            createdAt = data.getAsString(Key.createdAt)
            updatedAt = data.getAsString(Key.updatedAt)
        }catch (e:Exception){
            print("")
        }
    }

    fun getOtherUserProfile():Profile{
        var user = User()
        for (i in 0 until participantsProfiles.length()) {
            val item = participantsProfiles.getJSONObject(i)
            val _id = item.getString(Key._id)
            if(_id != "" && user._id != _id){
                return Profile(item)
            }
        }
        return Profile()
    }
    
    
    fun getOtherUserId():String{
        val user = User()
        var otherUserId = ""
        for (i in 0 until participantsProfiles.length()) {
            val item = participantsProfiles.getJSONObject(i)
            val _id = item.getString(Key._id)
            if(_id != "" && user._id != _id){
                return _id
            }
        }
        return otherUserId
    }

     fun getAll(completion:()->Unit){
        var request  = JSONObject()
        request.put(Key._id,User()._id)
//        ServiceManager().makeServiceCall(Server.getConversation, request) {
//            var conversations : ArrayList<Conversation> = ArrayList()
//            try {
//                fun JSONArray.toMutableList(): MutableList<Any> = MutableList(length(), this::get)
//                val payload = it.json.getJSONArray(Key.payload).toMutableList()
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
    fun checkUpdatedProfile(){

    }


}