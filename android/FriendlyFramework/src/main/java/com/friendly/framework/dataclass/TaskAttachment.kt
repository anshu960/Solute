package com.friendly.framework.dataclass

import android.content.ContentValues
import android.util.Log
import com.friendly.framework.constants.KeyConstant
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
            dict.put(KeyConstant._id, _id)
            dict.put(KeyConstant.taskAttachmentID, taskAttachmentID)
            dict.put(KeyConstant.taskID, taskId)
            dict.put(KeyConstant.fileURL, fileUrl)
            dict.put(KeyConstant.userId, userId)
            dict.put(KeyConstant.isUploaded, isUploaded)
            dict.put(KeyConstant.createdAt, createdAt)
            dict.put(KeyConstant.updatedAt, updatedAt)
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
            this._id = info.getString(KeyConstant._id)
            this.taskId = info.getString(KeyConstant.taskID)
            this.taskAttachmentID = info.getInt(KeyConstant.taskAttachmentID)
            this.userId = info.getString(KeyConstant.userId)
            this.fileUrl = info.getString(KeyConstant.fileURL)
            this.isUploaded = info.getInt(KeyConstant.isUploaded)
            this.createdAt = info.getString(KeyConstant.createdAt)
            this.updatedAt = info.getString(KeyConstant.updatedAt)
        } catch (e: java.lang.Exception)
        {
        Log.d("TaskAttachment",e.localizedMessage)
        }
    }
    
    constructor(info: ContentValues) : this()
    {
        try
        {
            this._id = info.getAsString(KeyConstant._id)
            this.taskId = info.getAsString(KeyConstant.taskID)
            this.userId = info.getAsString(KeyConstant.userId)
            this.taskAttachmentID = info.getAsInteger(KeyConstant.taskAttachmentID)
            this.fileUrl = info.getAsString(KeyConstant.fileURL)
            this.isUploaded = info.getAsInteger(KeyConstant.isUploaded)
            this.createdAt = info.getAsString(KeyConstant.createdAt)
            this.updatedAt = info.getAsString(KeyConstant.updatedAt)
        } catch (e: java.lang.Exception)
        {
            Log.d("TaskAttachment",e.localizedMessage)
        }
    }
    
    
}