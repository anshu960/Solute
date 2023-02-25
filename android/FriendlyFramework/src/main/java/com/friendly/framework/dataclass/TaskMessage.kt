package com.friendly.framework.dataclass

import android.content.ContentValues
import com.friendly.framework.constants.ContentType
import com.friendly.framework.constants.KeyConstant
import org.json.JSONArray
import org.json.JSONObject

data class TaskMessage(
    var _id: String = "",
    var taskId: String = "",
    var messageID: Int = 0,
    var fromID: String = "",
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
    
    val tag = "TaskMessage"
    var data : ContentValues get(){
        var dict  = ContentValues()
        dict.put(KeyConstant._id, _id)
        dict.put(KeyConstant.messageID, messageID)
        dict.put(KeyConstant.taskID, taskId)
        dict.put(KeyConstant.fromUserId, fromID)
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
            this.taskId = info.getString(KeyConstant.taskID)
            this.messageID = info.getInt(KeyConstant.messageID)
            this.fromID = info.getString(KeyConstant.fromUserId)
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
            taskId = info.getString(KeyConstant.taskID)
            messageID = info.getInt(KeyConstant.messageID)
            fromID = info.getString(KeyConstant.fromUserId)
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
            taskId = info.getAsString(KeyConstant.taskID)
            messageID = info.getAsInteger(KeyConstant.messageID)
            fromID = info.getAsString(KeyConstant.fromUserId)
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
    
    
    fun downloadMessages(_id: String, completion: () -> Unit){
        var request = JSONObject()
        request.put(KeyConstant.conversationID, _id)
        fun JSONArray.toMutableList(): MutableList<Any> = MutableList(length(), this::get)
//        ServiceManager().makeServiceCall(Server.getMessage, request){
//            print("Messages = " + it.json)
//            if(it.json.has(KeyConstant.payload)){
//                val payload = it.json.getJSONArray(KeyConstant.payload).toMutableList()
//                payload.forEach{
//                    val msg = Message(it)
//                    SQLite.insert(TableNames.message, msg.data)
//                }
//            }
//            completion()
//        }
    }
    //
//    fun send(participants:ArrayList<String>, completion: () -> Unit){
//        var request = JSONObject()
//        request.put(KeyConstant.fromUserId, App.user._id)
//        request.put(KeyConstant.content, this.content)
//        request.put(KeyConstant.contentType, contentType.name)
//        request.put(KeyConstant.messageID, this.messageID)
//        request.put(KeyConstant.time, sentTime)
//        request.put(KeyConstant.taskID,this.taskId)
//        request.put(KeyConstant.participants, JSONArray(participants))
//        SocketManager.send(SocketEvent.sendTaskMessage, request)
//    }
//
//    fun uploadImage(completion: ((Boolean) -> Unit)){
//        val imagePathRef = App.applicationContext().storageRef?.child("${taskId}/${messageID}.jpg")
//        val imageLocalFileUrl = Uri.parse(content)
//        val uploadTask = imagePathRef!!.putFile(imageLocalFileUrl)
//        uploadTask.addOnProgressListener {
//            Log.d("CHAT_FILE_UPLOAD", it.totalByteCount.toString())
//            Log.d("CHAT_FILE_UPLOAD", it.bytesTransferred.toString())
//            val progress = (100.0 * it.bytesTransferred) / it.totalByteCount
//            Log.d("CHAT_FILE_UPLOADED", it.bytesTransferred.toString())
//        }
//
//        val urlTask = uploadTask?.continueWithTask(Continuation<UploadTask.TaskSnapshot, Task<Uri>> { task ->
//            if (!task.isSuccessful) {
//                task.exception?.let {
//                    completion(false)
//                    throw it
//                }
//            }
//            return@Continuation imagePathRef!!.downloadUrl
//        })?.addOnCompleteListener { task ->
//            if (task.isSuccessful) {
//                val downloadUri = task.result
//                content = downloadUri.toString()
//                SocketManager.send(SocketEvent.updateMessage, data.jsonObject())
//                completion(true)
//            } else {
//                // Handle failures
//            }
//        }?.addOnFailureListener{
//            print("failed")
//            completion(false)
//        }
//    }
//
//
//    fun getContentTypeDescription():String{
//        if(contentType == ContentType.IMAGE){
//            return "Photo"
//        }else{
//            return ""
//        }
//    }
    
}