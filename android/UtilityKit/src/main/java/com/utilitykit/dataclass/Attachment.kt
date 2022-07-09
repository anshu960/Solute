package com.utilitykit.dataclass

import android.content.ContentValues
import android.util.Log
import com.utilitykit.Constants.Key
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
            dict.put(Key._id, _id)
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
            this.userId = info.getString(Key.userId)
            this.fileUrl = info.getString(Key.fileURL)
            this.isUploaded = info.getInt(Key.isUploaded)
            this.createdAt = info.getString(Key.createdAt)
            this.updatedAt = info.getString(Key.updatedAt)
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
            this._id = info.getAsString(Key._id)
            this.userId = info.getAsString(Key.userId)
            this.fileUrl = info.getAsString(Key.fileURL)
            this.isUploaded = info.getAsInteger(Key.isUploaded)
            this.createdAt = info.getAsString(Key.createdAt)
            this.updatedAt = info.getAsString(Key.updatedAt)
        } catch (e: java.lang.Exception)
        {
            Log.d("TaskAttachment", e.localizedMessage)
        }
    }
    
    
}