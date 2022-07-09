package com.utilitykit.dataclass

import android.content.ContentValues
import android.util.Log
import com.utilitykit.Constants.Key
import org.json.JSONObject

class TaskAttachment( var _id: String = "",
                      var taskAttachmentID : Int = 0,
                      var taskId: String = "",
                      var fileUrl: String = "",
                      var userId:String = "",
                      var isUploaded : Int = 0,
                      var createdAt: String = "",
                      var updatedAt: String = ""
                    )
{
    
    
    var data: ContentValues
        get()
        {
            var dict = ContentValues()
            dict.put(Key._id, _id)
            dict.put(Key.taskAttachmentID, taskAttachmentID)
            dict.put(Key.taskID, taskId)
            dict.put(Key.fileURL, fileUrl)
            dict.put(Key.userId, userId)
            dict.put(Key.isUploaded, isUploaded)
            dict.put(Key.createdAt, createdAt)
            dict.put(Key.updatedAt, updatedAt)
            return dict
        }
        set(value)
        {
            data = value // parses the string and assigns values to other properties
        }
    
    
    
    constructor(info: JSONObject) : this()
    {
        try
        {
            this._id = info.getString(Key._id)
            this.taskId = info.getString(Key.taskID)
            this.taskAttachmentID = info.getInt(Key.taskAttachmentID)
            this.userId = info.getString(Key.userId)
            this.fileUrl = info.getString(Key.fileURL)
            this.isUploaded = info.getInt(Key.isUploaded)
            this.createdAt = info.getString(Key.createdAt)
            this.updatedAt = info.getString(Key.updatedAt)
        } catch (e: java.lang.Exception)
        {
        Log.d("TaskAttachment",e.localizedMessage)
        }
    }
    
    constructor(info: ContentValues) : this()
    {
        try
        {
            this._id = info.getAsString(Key._id)
            this.taskId = info.getAsString(Key.taskID)
            this.userId = info.getAsString(Key.userId)
            this.taskAttachmentID = info.getAsInteger(Key.taskAttachmentID)
            this.fileUrl = info.getAsString(Key.fileURL)
            this.isUploaded = info.getAsInteger(Key.isUploaded)
            this.createdAt = info.getAsString(Key.createdAt)
            this.updatedAt = info.getAsString(Key.updatedAt)
        } catch (e: java.lang.Exception)
        {
            Log.d("TaskAttachment",e.localizedMessage)
        }
    }
    
    
}