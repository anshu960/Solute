package com.friendly.framework.dataclass

import com.friendly.framework.constants.KeyConstant
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
            this._id = info.getString(KeyConstant._id)
            this.name = info.getString(KeyConstant.name)
            this.createdAt = info.getString(KeyConstant.createdAt)
            this.updatedAt = info.getString(KeyConstant.updatedAt)
        } catch (e: java.lang.Exception)
        {
        
        }
    }
    
}