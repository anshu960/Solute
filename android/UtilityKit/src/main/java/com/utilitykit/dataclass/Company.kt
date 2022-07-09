package com.utilitykit.dataclass

import com.utilitykit.Constants.Key
import org.json.JSONObject

class Company(
    var _id: String = "",
    var name: String = "",
    var createdAt: String = "",
    var updatedAt: String = ""
             )
{
    constructor(info: JSONObject) : this()
    {
        try
        {
            this._id = info.getString(Key._id)
            this.name = info.getString(Key.name)
            this.createdAt = info.getString(Key.createdAt)
            this.updatedAt = info.getString(Key.updatedAt)
        } catch (e: java.lang.Exception)
        {
        
        }
    }
    
}