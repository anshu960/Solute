package com.friendly.framework.dataclass

import android.content.ContentValues
import com.friendly.framework.constants.KeyConstant
import org.json.JSONArray
import org.json.JSONObject

class Experience(
    var _id: String = "",
    var userId: String = "",
    var company: Company = Company(),
    var designation: Designation = Designation(),
    var technologies: ArrayList<Technology> = ArrayList<Technology>(),
    var description: String = "",
    var fromDate: String = "",
    var toDate: String = "",
    var working: Boolean = false,
    var createdAt: String = "",
    var updatedAt: String = ""
                )
{
    var data: ContentValues
        get()
        {
            var dict = ContentValues()
            dict.put(KeyConstant._id, _id)
            dict.put(KeyConstant.userId, userId)
            dict.put(KeyConstant.createdAt, createdAt)
            dict.put(KeyConstant.updatedAt, updatedAt)
            return dict
        }
        set(value)
        {
            data = value
        }
    
    
    constructor(info: JSONObject) : this()
    {
        try
        {
            this._id = info.getString(KeyConstant._id)
            this.userId = info.getString(KeyConstant.userId)
            this.company = Company(info.getJSONObject(KeyConstant.company))
            this.designation = Designation(info.getJSONObject(KeyConstant.designation))
            if(info.get(KeyConstant.technologies) is JSONArray){
                val allTechnologies = info.getJSONArray(KeyConstant.technologies)
                val technologyJson = allTechnologies.getJSONObject(0)
                val newTechnology = Technology(technologyJson)
                this.technologies.add(newTechnology)
            }
            this.description = info.getString(KeyConstant.description)
            this.fromDate = info.getString(KeyConstant.fromDate)
            this.toDate = info.getString(KeyConstant.toDate)
            this.working = info.getBoolean(KeyConstant.working)
            this.createdAt = info.getString(KeyConstant.createdAt)
            this.updatedAt = info.getString(KeyConstant.updatedAt)
        } catch (e: java.lang.Exception)
        {
            print("Exception while extracting experience")
        }
    }
    
    constructor(info: ContentValues) : this()
    {
        try
        {
            this._id = info.getAsString(KeyConstant._id)
            this.userId = info.getAsString(KeyConstant.userId)
            val profileData = info.getAsString(KeyConstant.profile)
            this.createdAt = info.getAsString(KeyConstant.createdAt)
            this.updatedAt = info.getAsString(KeyConstant.updatedAt)
        } catch (e: java.lang.Exception)
        {
        
        }
    }
    
    
}