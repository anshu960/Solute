package com.utilitykit.dataclass

import android.content.ContentValues
import com.utilitykit.Constants.Key
import com.utilitykit.json
import org.json.JSONObject

data class ContactData(
        var _id: String = "",
        var userId: String = "",
        var contactId: Long = 0,
        var name: String = "",
        var countryCode: String = "",
        var mobileNumber: String = "",
        var profilePicture: String = "",
        var profile: Profile = Profile(),
        var  category: Int = 0,
        var createdAt: String = "",
        var updatedAt: String = "",
        var isSelected : Boolean = false,
){
    var data : ContentValues
        get(){
            var dict = ContentValues()
            dict.put(Key._id, _id)
            dict.put(Key.userId, userId)
            dict.put(Key.id, contactId)
            dict.put(Key.name, name)
            dict.put(Key.mobileNumber, mobileNumber)
            dict.put(Key.countryCode, countryCode)
            dict.put(Key.profilePicture, profilePicture)
            dict.put(Key.profile, profile.data.json())
            dict.put(Key.category, category)
            dict.put(Key.createdAt, createdAt)
            dict.put(Key.updatedAt, updatedAt)
            return dict
        }
        set(value) {data = value}

    var json : JSONObject
        get(){
            var dict = JSONObject()
            dict.put(Key._id, _id)
            dict.put(Key.userId, userId)
            dict.put(Key.id, contactId)
            dict.put(Key.name, name)
            dict.put(Key.mobileNumber, mobileNumber)
            dict.put(Key.countryCode, countryCode)
            dict.put(Key.profilePicture, profilePicture)
            dict.put(Key.profile, profile.data.json())
            dict.put(Key.category, category)
            dict.put(Key.createdAt, createdAt)
            dict.put(Key.updatedAt, updatedAt)
            return dict
        }
        set(value) {json = value}

    var newRequest : JSONObject
        get(){
            var dict = JSONObject()
            dict.put(Key.userId, userId)
            if(_id != ""){
                dict.put(Key._id, _id)
            }
            dict.put(Key.id, contactId)
            dict.put(Key.name, name)
            dict.put(Key.mobileNumber, mobileNumber)
            dict.put(Key.countryCode, countryCode)
            dict.put(Key.profilePicture, profilePicture)
            dict.put(Key.profile, profile.data.json())
            dict.put(Key.category, category)
            dict.put(Key.createdAt, createdAt)
            dict.put(Key.updatedAt, updatedAt)
            return dict
        }
        set(value) {newRequest = value}

    constructor(info: ContentValues) : this() {
        try {
            this._id = info.getAsString(Key._id)
            this.contactId = info.getAsLong(Key.id)
            this.userId = info.getAsString(Key.userId)
            this.name = info.getAsString(Key.name)
            this.mobileNumber = info.getAsString(Key.mobileNumber)
            this.profilePicture = info.getAsString(Key.profilePicture)
            val profileData = info.getAsString(Key.profile)
            if(profileData.contains("{")){
                this.profile =  Profile(JSONObject(info.getAsString(Key.profile)))
            }
            this.countryCode = info.getAsString(Key.countryCode)
            this.category = info.getAsInteger(Key.category)
            this.createdAt = info.getAsString(Key.createdAt)
            this.updatedAt = info.getAsString(Key.updatedAt)
        } catch (e: java.lang.Exception) {

        }
    }

    constructor(info: JSONObject) : this() {
        try {
            this._id = info.getString(Key._id)
            this.contactId = info.getLong(Key.id)
            this.userId = info.getString(Key.userId)
            this.name = info.getString(Key.name)
            this.mobileNumber = info.getString(Key.mobileNumber)
            this.profilePicture = info.getString(Key.profilePicture)
            val profileData = info.getString(Key.profile)
            if(profileData.contains("{")){
                this.profile =  Profile(JSONObject(info.getString(Key.profilePicture)))
            }
            this.countryCode = info.getString(Key.countryCode)
            this.category = info.getInt(Key.category)
            this.createdAt = info.getString(Key.createdAt)
            this.updatedAt = info.getString(Key.updatedAt)
        } catch (e: java.lang.Exception) {
            print("Exception extracting contacts from json object")
        }
    }
}