package com.friendly.framework.dataclass

import android.util.Log
import com.friendly.framework.constants.KeyConstant
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
            this._id = info.getString(KeyConstant._id)
            this.taskID = info.getString(KeyConstant.taskID)
            this.userID = info.getString(KeyConstant.userId)
            this.name = info.getString(KeyConstant.name)
            this.fileURL = info.getString(KeyConstant.fileURL)
            this.createdAt = info.getString(KeyConstant.createdAt)
            this.updatedAt = info.getString(KeyConstant.updatedAt)
        } catch (e: java.lang.Exception)
        {
            Log.d("DocumentTask:EXCEPTION", e.localizedMessage)
        }
    }
    
}


