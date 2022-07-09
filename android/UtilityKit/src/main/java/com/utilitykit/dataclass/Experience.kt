package com.utilitykit.dataclass

import android.content.ContentValues
import com.utilitykit.Constants.Key
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
            dict.put(Key._id, _id)
            dict.put(Key.userId, userId)
            dict.put(Key.createdAt, createdAt)
            dict.put(Key.updatedAt, updatedAt)
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
            this._id = info.getString(Key._id)
            this.userId = info.getString(Key.userId)
            this.company = Company(info.getJSONObject(Key.company))
            this.designation = Designation(info.getJSONObject(Key.designation))
            if(info.get(Key.technologies) is JSONArray){
                val allTechnologies = info.getJSONArray(Key.technologies)
                val technologyJson = allTechnologies.getJSONObject(0)
                val newTechnology = Technology(technologyJson)
                this.technologies.add(newTechnology)
            }
            this.description = info.getString(Key.description)
            this.fromDate = info.getString(Key.fromDate)
            this.toDate = info.getString(Key.toDate)
            this.working = info.getBoolean(Key.working)
            this.createdAt = info.getString(Key.createdAt)
            this.updatedAt = info.getString(Key.updatedAt)
        } catch (e: java.lang.Exception)
        {
            print("Exception while extracting experience")
        }
    }
    
    constructor(info: ContentValues) : this()
    {
        try
        {
            this._id = info.getAsString(Key._id)
            this.userId = info.getAsString(Key.userId)
            val profileData = info.getAsString(Key.profile)
            this.createdAt = info.getAsString(Key.createdAt)
            this.updatedAt = info.getAsString(Key.updatedAt)
        } catch (e: java.lang.Exception)
        {
        
        }
    }
    
    
}