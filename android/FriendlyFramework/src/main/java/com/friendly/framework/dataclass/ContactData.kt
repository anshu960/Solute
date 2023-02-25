package com.friendly.framework.dataclass

import android.content.ContentValues
import com.friendly.framework.constants.KeyConstant
import com.friendly.framework.json
import org.json.JSONObject

data class ContactData(
    var _id: String = "",
    var userId: String = "",
    var contactId: Long = 0,
    var name: String = "",
    var countryCode: String = "",
    var mobileNumber: String = "",
    var profilePicture: String = "",
    var profile: FriendlyProfile = FriendlyProfile(),
    var  category: Int = 0,
    var createdAt: String = "",
    var updatedAt: String = "",
    var isSelected : Boolean = false,
){
    var data : ContentValues
        get(){
            var dict = ContentValues()
            dict.put(KeyConstant._id, _id)
            dict.put(KeyConstant.userId, userId)
            dict.put(KeyConstant.id, contactId)
            dict.put(KeyConstant.name, name)
            dict.put(KeyConstant.mobileNumber, mobileNumber)
            dict.put(KeyConstant.countryCode, countryCode)
            dict.put(KeyConstant.profilePicture, profilePicture)
            dict.put(KeyConstant.profile, profile.data.json())
            dict.put(KeyConstant.category, category)
            dict.put(KeyConstant.createdAt, createdAt)
            dict.put(KeyConstant.updatedAt, updatedAt)
            return dict
        }
        set(value) {data = value}

    var json : JSONObject
        get(){
            var dict = JSONObject()
            dict.put(KeyConstant._id, _id)
            dict.put(KeyConstant.userId, userId)
            dict.put(KeyConstant.id, contactId)
            dict.put(KeyConstant.name, name)
            dict.put(KeyConstant.mobileNumber, mobileNumber)
            dict.put(KeyConstant.countryCode, countryCode)
            dict.put(KeyConstant.profilePicture, profilePicture)
            dict.put(KeyConstant.profile, profile.data.json())
            dict.put(KeyConstant.category, category)
            dict.put(KeyConstant.createdAt, createdAt)
            dict.put(KeyConstant.updatedAt, updatedAt)
            return dict
        }
        set(value) {json = value}

    var newRequest : JSONObject
        get(){
            var dict = JSONObject()
            dict.put(KeyConstant.userId, userId)
            if(_id != ""){
                dict.put(KeyConstant._id, _id)
            }
            dict.put(KeyConstant.id, contactId)
            dict.put(KeyConstant.name, name)
            dict.put(KeyConstant.mobileNumber, mobileNumber)
            dict.put(KeyConstant.countryCode, countryCode)
            dict.put(KeyConstant.profilePicture, profilePicture)
            dict.put(KeyConstant.profile, profile.data.json())
            dict.put(KeyConstant.category, category)
            dict.put(KeyConstant.createdAt, createdAt)
            dict.put(KeyConstant.updatedAt, updatedAt)
            return dict
        }
        set(value) {newRequest = value}

    constructor(info: ContentValues) : this() {
        try {
            this._id = info.getAsString(KeyConstant._id)
            this.contactId = info.getAsLong(KeyConstant.id)
            this.userId = info.getAsString(KeyConstant.userId)
            this.name = info.getAsString(KeyConstant.name)
            this.mobileNumber = info.getAsString(KeyConstant.mobileNumber)
            this.profilePicture = info.getAsString(KeyConstant.profilePicture)
            val profileData = info.getAsString(KeyConstant.profile)
            if(profileData.contains("{")){
                this.profile =  FriendlyProfile(JSONObject(info.getAsString(KeyConstant.profile)))
            }
            this.countryCode = info.getAsString(KeyConstant.countryCode)
            this.category = info.getAsInteger(KeyConstant.category)
            this.createdAt = info.getAsString(KeyConstant.createdAt)
            this.updatedAt = info.getAsString(KeyConstant.updatedAt)
        } catch (e: java.lang.Exception) {

        }
    }

    constructor(info: JSONObject) : this() {
        try {
            this._id = info.getString(KeyConstant._id)
            this.contactId = info.getLong(KeyConstant.id)
            this.userId = info.getString(KeyConstant.userId)
            this.name = info.getString(KeyConstant.name)
            this.mobileNumber = info.getString(KeyConstant.mobileNumber)
            this.profilePicture = info.getString(KeyConstant.profilePicture)
            val profileData = info.getString(KeyConstant.profile)
            if(profileData.contains("{")){
                this.profile =  FriendlyProfile(JSONObject(info.getString(KeyConstant.profilePicture)))
            }
            this.countryCode = info.getString(KeyConstant.countryCode)
            this.category = info.getInt(KeyConstant.category)
            this.createdAt = info.getString(KeyConstant.createdAt)
            this.updatedAt = info.getString(KeyConstant.updatedAt)
        } catch (e: java.lang.Exception) {
            print("Exception extracting contacts from json object")
        }
    }
}