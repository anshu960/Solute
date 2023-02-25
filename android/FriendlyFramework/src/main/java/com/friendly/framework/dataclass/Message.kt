package com.friendly.framework.dataclass


import android.content.ContentValues
import com.friendly.framework.constants.ContentType
import com.friendly.framework.constants.KeyConstant
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
        dict.put(KeyConstant._id, _id)
        dict.put(KeyConstant.messageID, messageID)
        dict.put(KeyConstant.conversationID, conversationID)
        dict.put(KeyConstant.fromUserId, fromID)
        dict.put(KeyConstant.toUserId, toID)
        dict.put(KeyConstant.content, content)
        dict.put(KeyConstant.contentType, contentType.name)
        dict.put(KeyConstant.isSent,isSent)
        dict.put(KeyConstant.isReceived, isReceived)
        dict.put(KeyConstant.isUploaded, isUploaded)
        dict.put(KeyConstant.isDownloaded, isDownloaded)
        dict.put(KeyConstant.isDeleted, isDeleted)
        dict.put(KeyConstant.isRead, isRead)
        dict.put(KeyConstant.sentTime, sentTime)
        dict.put(KeyConstant.deliveredTime, deliveredTime)
        dict.put(KeyConstant.readTime, readTime)
        return dict
    }
    set(value) {data = value}

    constructor(data: Any):this(){
        try {
            val info = JSONObject(data.toString())
            this._id = info.getString(KeyConstant._id)
            content = info.getString(KeyConstant.content)
            val contentTypeFromServer = (if(info.has(KeyConstant.contentType)) info.getString(KeyConstant.contentType) else  ContentType.TEXT.name)
            contentType = ContentType.valueOf(contentTypeFromServer)
            this.conversationID = info.getString(KeyConstant.conversationID)
            this.messageID = info.getInt(KeyConstant.messageID)
            this.fromID = info.getString(KeyConstant.fromUserId)
            this.toID = info.getString(KeyConstant.toUserId)
            this.sentTime = info.getString(KeyConstant.sentTime)
            this.deliveredTime = info.getString(KeyConstant.deliveredTime)
            this.readTime = info.getString(KeyConstant.readTime)
            isUploaded = info.getInt(KeyConstant.isUploaded)
            isDownloaded = info.getInt(KeyConstant.isUploaded)
            isDeleted = info.getInt(KeyConstant.isDeleted)

            isSent = info.getInt(KeyConstant.isSent)
            isReceived = info.getInt(KeyConstant.isReceived)
            isRead = info.getInt(KeyConstant.isRead)
    }catch (e: Exception){
        content = ""
    }
    }
    constructor(info: JSONObject):this(){
        try{
            _id = info.getString(KeyConstant._id)
            content = info.getString(KeyConstant.content)
            val contentTypeFromServer = (if(info.has(KeyConstant.contentType)) info.getString(KeyConstant.contentType) else  ContentType.TEXT.name)
            contentType = ContentType.valueOf(contentTypeFromServer)
            conversationID = info.getString(KeyConstant.conversationID)
            messageID = info.getInt(KeyConstant.messageID)
            fromID = info.getString(KeyConstant.fromUserId)
            toID = info.getString(KeyConstant.toUserId)
            sentTime = info.getString(KeyConstant.sentTime)
            deliveredTime = info.getString(KeyConstant.deliveredTime)
            readTime = info.getString(KeyConstant.readTime)
            isUploaded = info.getInt(KeyConstant.isUploaded)
            isDownloaded = info.getInt(KeyConstant.isUploaded)
            isDeleted = info.getInt(KeyConstant.isDeleted)
            isSent = info.getInt(KeyConstant.isSent)
            isReceived = info.getInt(KeyConstant.isReceived)
            isRead = info.getInt(KeyConstant.isRead)
        }catch (e: Exception){
             content = info.toString()
             print(e.localizedMessage)
        }

    }
    constructor(info: ContentValues):this(){
        try{
            _id = info.getAsString(KeyConstant._id)
            content = info.getAsString(KeyConstant.content)
            val contentTypeFromServer = (if(info.containsKey(KeyConstant.contentType)) info.getAsString(KeyConstant.contentType) else  ContentType.TEXT.name)
            contentType = ContentType.valueOf(contentTypeFromServer)
            conversationID = info.getAsString(KeyConstant.conversationID)
            messageID = info.getAsInteger(KeyConstant.messageID)
            fromID = info.getAsString(KeyConstant.fromUserId)
            toID = info.getAsString(KeyConstant.toUserId)
            sentTime = info.getAsString(KeyConstant.sentTime)
            deliveredTime = info.getAsString(KeyConstant.deliveredTime)
            readTime = info.getAsString(KeyConstant.readTime)
            isUploaded = info.getAsInteger(KeyConstant.isUploaded)
            isDownloaded = info.getAsInteger(KeyConstant.isUploaded)
            isDeleted = info.getAsInteger(KeyConstant.isDeleted)
            isSent = info.getAsInteger(KeyConstant.isSent)
            isReceived = info.getAsInteger(KeyConstant.isReceived)
            isRead = info.getAsInteger(KeyConstant.isRead)
        }
        catch (e: Exception){
            print(e.localizedMessage)
            }
    }
    
    fun send(fcm: String, name: String, completion: () -> Unit){
        var request = JSONObject()
//        val conversation = Database.shared().retriveConversationByID(this.conversationID!!)
//        request.put(KeyConstant.fromUserId, UtilityKitApp.user._id)
//        request.put(KeyConstant.toUserId, conversation!!.getOtherUserId())
//        request.put(KeyConstant.content, this.content)
//        request.put(KeyConstant.contentType, contentType.name)
//        request.put(KeyConstant.messageID, this.messageID)
//        request.put(KeyConstant.time, sentTime)
//        request.put(KeyConstant.conversationID, this.conversationID)
//        request.put(KeyConstant.participants, conversation!!.participants)

//        if (SocketService.shared().isSocketConnected){
//            SocketService.shared().send(SocketEvent.sendMessage, request)
//            completion()
//        }else{
//            ServiceManager().makeServiceCall(Server.sendMessage, request) {
//                try {
//                    val payload = it.json.getJSONObject(KeyConstant.payload)
//                    val id = payload.getInt(KeyConstant.id)
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
