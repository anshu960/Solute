package com.friendly.framework.dataclass

import android.content.ContentValues
import android.util.Log
import com.friendly.framework.constants.KeyConstant
import org.json.JSONObject

class Attachment( var _id: String = "",
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
            this.userId = info.getString(KeyConstant.userId)
            this.fileUrl = info.getString(KeyConstant.fileURL)
            this.isUploaded = info.getInt(KeyConstant.isUploaded)
            this.createdAt = info.getString(KeyConstant.createdAt)
            this.updatedAt = info.getString(KeyConstant.updatedAt)
        } catch (e: java.lang.Exception)
        {
            Log.d("TaskAttachment", e.localizedMessage)
        }
    }
    
    constructor(task: TaskAttachment) : this()
    {
            this._id = task._id
            this.userId = task.userId
            this.fileUrl = task.fileUrl
            this.isUploaded = task.isUploaded
            this.createdAt = task.createdAt
            this.updatedAt = task.updatedAt
    }
    
    constructor(info: ContentValues) : this()
    {
        try
        {
            this._id = info.getAsString(KeyConstant._id)
            this.userId = info.getAsString(KeyConstant.userId)
            this.fileUrl = info.getAsString(KeyConstant.fileURL)
            this.isUploaded = info.getAsInteger(KeyConstant.isUploaded)
            this.createdAt = info.getAsString(KeyConstant.createdAt)
            this.updatedAt = info.getAsString(KeyConstant.updatedAt)
        } catch (e: java.lang.Exception)
        {
            Log.d("TaskAttachment", e.localizedMessage)
        }
    }
    
    
}