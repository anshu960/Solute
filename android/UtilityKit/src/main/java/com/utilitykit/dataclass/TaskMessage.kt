package com.utilitykit.dataclass

import android.content.ContentValues
import com.solute.constants.ContentType
import com.utilitykit.Constants.Key
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
        dict.put(Key._id, _id)
        dict.put(Key.messageID, messageID)
        dict.put(Key.taskID, taskId)
        dict.put(Key.fromUserId, fromID)
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
            this.taskId = info.getString(Key.taskID)
            this.messageID = info.getInt(Key.messageID)
            this.fromID = info.getString(Key.fromUserId)
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
            taskId = info.getString(Key.taskID)
            messageID = info.getInt(Key.messageID)
            fromID = info.getString(Key.fromUserId)
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
            taskId = info.getAsString(Key.taskID)
            messageID = info.getAsInteger(Key.messageID)
            fromID = info.getAsString(Key.fromUserId)
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
    
    
    fun downloadMessages(_id: String, completion: () -> Unit){
        var request = JSONObject()
        request.put(Key.conversationID, _id)
        fun JSONArray.toMutableList(): MutableList<Any> = MutableList(length(), this::get)
//        ServiceManager().makeServiceCall(Server.getMessage, request){
//            print("Messages = " + it.json)
//            if(it.json.has(Key.payload)){
//                val payload = it.json.getJSONArray(Key.payload).toMutableList()
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
//        request.put(Key.fromUserId, App.user._id)
//        request.put(Key.content, this.content)
//        request.put(Key.contentType, contentType.name)
//        request.put(Key.messageID, this.messageID)
//        request.put(Key.time, sentTime)
//        request.put(Key.taskID,this.taskId)
//        request.put(Key.participants, JSONArray(participants))
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