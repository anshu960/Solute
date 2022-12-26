package com.utilitykit.dataclass

import android.content.ContentValues
import com.solute.constants.ContentType
import com.utilitykit.Constants.Key

import org.json.JSONObject

data class Message(
    var _id: String = "",
    var conversationID: String = "",
    var messageID: Int = 0,
    var fromID: String = "",
    var toID: String = "",
    var content: String = "",
    var contentType: ContentType = ContentType.TEXT,
    var isSent: Int = 0,
    var isUploaded: Int = 0,
    var isDownloaded: Int = 0,
    var isDeleted: Int = 0,
    var isReceived: Int = 0,
    var isRead: Int = 0,
    var sentTime: String = "",
    var deliveredTime: String = "",
    var readTime: String = ""
){

    val tag = "Message"
    var data : ContentValues get(){
        var dict  = ContentValues()
        dict.put(Key._id, _id)
        dict.put(Key.messageID, messageID)
        dict.put(Key.conversationID, conversationID)
        dict.put(Key.fromUserId, fromID)
        dict.put(Key.toUserId, toID)
        dict.put(Key.content, content)
        dict.put(Key.contentType, contentType.name)
        dict.put(Key.isSent,isSent)
        dict.put(Key.isReceived, isReceived)
        dict.put(Key.isUploaded, isUploaded)
        dict.put(Key.isDownloaded, isDownloaded)
        dict.put(Key.isDeleted, isDeleted)
        dict.put(Key.isRead, isRead)
        dict.put(Key.sentTime, sentTime)
        dict.put(Key.deliveredTime, deliveredTime)
        dict.put(Key.readTime, readTime)
        return dict
    }
    set(value) {data = value}

    constructor(data: Any):this(){
        try {
            val info = JSONObject(data.toString())
            this._id = info.getString(Key._id)
            content = info.getString(Key.content)
            val contentTypeFromServer = (if(info.has(Key.contentType)) info.getString(Key.contentType) else  ContentType.TEXT.name)
            contentType = ContentType.valueOf(contentTypeFromServer)
            this.conversationID = info.getString(Key.conversationID)
            this.messageID = info.getInt(Key.messageID)
            this.fromID = info.getString(Key.fromUserId)
            this.toID = info.getString(Key.toUserId)
            this.sentTime = info.getString(Key.sentTime)
            this.deliveredTime = info.getString(Key.deliveredTime)
            this.readTime = info.getString(Key.readTime)
            isUploaded = info.getInt(Key.isUploaded)
            isDownloaded = info.getInt(Key.isUploaded)
            isDeleted = info.getInt(Key.isDeleted)

            isSent = info.getInt(Key.isSent)
            isReceived = info.getInt(Key.isReceived)
            isRead = info.getInt(Key.isRead)
    }catch (e: Exception){
        content = ""
    }
    }
    constructor(info: JSONObject):this(){
        try{
            _id = info.getString(Key._id)
            content = info.getString(Key.content)
            val contentTypeFromServer = (if(info.has(Key.contentType)) info.getString(Key.contentType) else  ContentType.TEXT.name)
            contentType = ContentType.valueOf(contentTypeFromServer)
            conversationID = info.getString(Key.conversationID)
            messageID = info.getInt(Key.messageID)
            fromID = info.getString(Key.fromUserId)
            toID = info.getString(Key.toUserId)
            sentTime = info.getString(Key.sentTime)
            deliveredTime = info.getString(Key.deliveredTime)
            readTime = info.getString(Key.readTime)
            isUploaded = info.getInt(Key.isUploaded)
            isDownloaded = info.getInt(Key.isUploaded)
            isDeleted = info.getInt(Key.isDeleted)
            isSent = info.getInt(Key.isSent)
            isReceived = info.getInt(Key.isReceived)
            isRead = info.getInt(Key.isRead)
        }catch (e: Exception){
             content = info.toString()
             print(e.localizedMessage)
        }

    }
    constructor(info: ContentValues):this(){
        try{
            _id = info.getAsString(Key._id)
            content = info.getAsString(Key.content)
            val contentTypeFromServer = (if(info.containsKey(Key.contentType)) info.getAsString(Key.contentType) else  ContentType.TEXT.name)
            contentType = ContentType.valueOf(contentTypeFromServer)
            conversationID = info.getAsString(Key.conversationID)
            messageID = info.getAsInteger(Key.messageID)
            fromID = info.getAsString(Key.fromUserId)
            toID = info.getAsString(Key.toUserId)
            sentTime = info.getAsString(Key.sentTime)
            deliveredTime = info.getAsString(Key.deliveredTime)
            readTime = info.getAsString(Key.readTime)
            isUploaded = info.getAsInteger(Key.isUploaded)
            isDownloaded = info.getAsInteger(Key.isUploaded)
            isDeleted = info.getAsInteger(Key.isDeleted)
            isSent = info.getAsInteger(Key.isSent)
            isReceived = info.getAsInteger(Key.isReceived)
            isRead = info.getAsInteger(Key.isRead)
        }
        catch (e: Exception){
            print(e.localizedMessage)
            }
    }
    
    fun send(fcm: String, name: String, completion: () -> Unit){
        var request = JSONObject()
//        val conversation = Database.shared().retriveConversationByID(this.conversationID!!)
//        request.put(Key.fromUserId, UtilityKitApp.user._id)
//        request.put(Key.toUserId, conversation!!.getOtherUserId())
//        request.put(Key.content, this.content)
//        request.put(Key.contentType, contentType.name)
//        request.put(Key.messageID, this.messageID)
//        request.put(Key.time, sentTime)
//        request.put(Key.conversationID, this.conversationID)
//        request.put(Key.participants, conversation!!.participants)

//        if (SocketService.shared().isSocketConnected){
//            SocketService.shared().send(SocketEvent.sendMessage, request)
//            completion()
//        }else{
//            ServiceManager().makeServiceCall(Server.sendMessage, request) {
//                try {
//                    val payload = it.json.getJSONObject(Key.payload)
//                    val id = payload.getInt(Key.id)
//                    if(id != 0){
//                        isSent = 1
//                        SQLite.insert(TableNames.message, data)
//                        FCM().sendFcm(fcm, name, content, this.data.jsonObject())
//                        completion()
//                    }else{
//                        completion()
//                    }
//                }catch (e: java.lang.Exception){
//                    Log.d(tag, e.localizedMessage)
//                    completion()
//                }
//            }
//        }

        }

    fun uploadImage(completion: ((Boolean) -> Unit)){

    }


    fun getContentTypeDescription():String{
        if(contentType == ContentType.IMAGE){
            return "Photo"
        }else{
            return ""
        }
    }

}
