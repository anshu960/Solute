package com.utilitykit.dataclass

import android.util.Log
import com.utilitykit.Constants.Key
import org.json.JSONObject

data class DocumentTask(
    var _id: String = "",
    var taskID: String = "",
    var userID: String = "",
    var name: String = "",
    var fileURL: String = "",
    var createdAt: String = "",
    var updatedAt: String = ""
                       )
{
    constructor(info: JSONObject) : this()
    {
        try
        {
            this._id = info.getString(Key._id)
            this.taskID = info.getString(Key.taskID)
            this.userID = info.getString(Key.userId)
            this.name = info.getString(Key.name)
            this.fileURL = info.getString(Key.fileURL)
            this.createdAt = info.getString(Key.createdAt)
            this.updatedAt = info.getString(Key.updatedAt)
        } catch (e: java.lang.Exception)
        {
            Log.d("DocumentTask:EXCEPTION", e.localizedMessage)
        }
    }
    
}


