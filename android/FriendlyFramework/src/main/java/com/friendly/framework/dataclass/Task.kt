package com.friendly.framework.dataclass

import android.content.ContentValues
import com.friendly.framework.constants.KeyConstant
import com.friendly.framework.constants.TaskPriority
import com.friendly.framework.constants.TaskStatus
import com.friendly.framework.jsonObject
import org.json.JSONArray
import org.json.JSONObject

data class Task(
    var _id: String = "",
    var name: String = "",
    var description: String = "",
    var priority: TaskPriority = TaskPriority.NOT_DECIDED,
    var status: TaskStatus = TaskStatus.PENDING,
    var startDate: String = "",
    var dueDate: String = "",
    var adminUsers: ArrayList<String> = arrayListOf(),
    var participants: ArrayList<String> = arrayListOf(),
    var adminUserProfiles: ArrayList<FriendlyProfile> = arrayListOf(),
    var participantProfiles: ArrayList<FriendlyProfile> = arrayListOf(),
    var dynamicLink: String = "",
    var createdAt: String = "",
    var updatedAt: String = ""
               )
{
    
    var data: ContentValues
        get()
        {
            var dict = ContentValues()
            dict.put(KeyConstant._id, _id)
            dict.put(KeyConstant.name, name)
            dict.put(KeyConstant.description, description)
            dict.put(KeyConstant.priority, priority.name)
            dict.put(KeyConstant.status, status.name)
            dict.put(KeyConstant.startDate, startDate)
            dict.put(KeyConstant.dueDate, dueDate)
            dict.put(KeyConstant.adminUserProfiles, getAdminProfilesInJsonArray().toString())
            dict.put(KeyConstant.adminUsers, arrayToString(adminUsers))
            dict.put(KeyConstant.participants, arrayToString(participants))
            dict.put(KeyConstant.participantsProfiles, getParticipantsProfilesInJsonArray().toString())
            dict.put(KeyConstant.dynamicLink, dynamicLink)
            dict.put(KeyConstant.createdAt, createdAt)
            dict.put(KeyConstant.updatedAt, updatedAt)
            return dict
        }
        set(value)
        {
            data = value // parses the string and assigns values to other properties
        }
    
    var json: JSONObject
        get()
        {
            var dict = JSONObject()
            dict.put(KeyConstant._id, _id)
            dict.put(KeyConstant.name, name)
            dict.put(KeyConstant.description, description)
            dict.put(KeyConstant.priority, priority)
            dict.put(KeyConstant.status, status.name)
            dict.put(KeyConstant.startDate, startDate)
            dict.put(KeyConstant.dueDate, dueDate)
            dict.put(KeyConstant.adminUserProfiles, adminUserProfiles)
            dict.put(KeyConstant.adminUsers, adminUsers)
            dict.put(KeyConstant.participants, participants)
            dict.put(KeyConstant.participantsProfiles, participantProfiles)
            dict.put(KeyConstant.dynamicLink, dynamicLink)
            dict.put(KeyConstant.createdAt, createdAt)
            dict.put(KeyConstant.updatedAt, updatedAt)
            return dict
        }
        set(value)
        {
            json = value // parses the string and assigns values to other properties
        }
    
    fun arrayToString(array: ArrayList<String>): String
    {
        var output = ""
        array.forEach { it -> output += it;output += "," }
        return output
    }
    
    fun stringToArray(string: String): ArrayList<String>
    {
        var output: ArrayList<String> = arrayListOf()
        var separateItems = string.split(",")
        separateItems.forEach {
            if (!it.isBlank())
            {
                output.add(it)
            }
        }
        return output
    }
    
    fun getParticipantsProfilesInJsonArray(): ArrayList<JSONObject>
    {
        var allProfileData = ArrayList<JSONObject>()
        participantProfiles.forEach {
            allProfileData.add(it.data.jsonObject())
        }
        return allProfileData
    }
    
    fun getAdminProfilesInJsonArray(): ArrayList<JSONObject>
    {
        var allProfileData = ArrayList<JSONObject>()
        adminUserProfiles.forEach {
            allProfileData.add(it.data.jsonObject())
        }
        return allProfileData
    }
    

    
    constructor(info: JSONObject) : this()
    {
        try
        {
            this._id = info.getString(KeyConstant._id)
            this.name = info.getString(KeyConstant.name)
            this.description = info.getString(KeyConstant.description)
            this.priority = TaskPriority.valueOf(info.getString(KeyConstant.priority))
            this.status = TaskStatus.valueOf(info.getString(KeyConstant.status))
            this.startDate = info.getString(KeyConstant.startDate)
            this.dueDate = info.getString(KeyConstant.dueDate)
            this.dynamicLink = info.getString(KeyConstant.dynamicLink)
            val adminUserIds = info.getJSONArray(KeyConstant.adminUsers)
            val participantUserIds = info.getJSONArray(KeyConstant.participants)
            for (i in 0 until adminUserIds.length())
            {
                adminUsers.add(adminUserIds.get(i) as String)
            }
            for (i in 0 until participantUserIds.length())
            {
                participants.add(participantUserIds.get(i) as String)
            }
            val adminProfileJson = info.getJSONArray(KeyConstant.adminUserProfiles)
            val participantProfileJson = info.getJSONArray(KeyConstant.participantsProfiles)
            for (i in 0 until adminProfileJson.length())
            {
                adminUserProfiles.add(FriendlyProfile(adminProfileJson.getJSONObject(i)))
            }
            for (i in 0 until participantProfileJson.length())
            {
                participantProfiles.add(FriendlyProfile(participantProfileJson.getJSONObject(i)))
            }
            this.createdAt = info.getString(KeyConstant.createdAt)
            this.updatedAt = info.getString(KeyConstant.updatedAt)
        } catch (e: Exception)
        {
            print("TASK Exception ")
        }
    }
    
    constructor(info: ContentValues) : this()
    {
        try
        {
            this._id = info.getAsString(KeyConstant._id)
            this.name = info.getAsString(KeyConstant.name)
            this.description = info.getAsString(KeyConstant.description)
            this.priority = TaskPriority.valueOf(info.getAsString(KeyConstant.priority))
            this.status = TaskStatus.valueOf(info.getAsString(KeyConstant.status))
            this.startDate = info.getAsString(KeyConstant.startDate)
            this.dueDate = info.getAsString(KeyConstant.dueDate)
            this.dynamicLink = info.getAsString(KeyConstant.dynamicLink)
            adminUsers = stringToArray(info.getAsString(KeyConstant.adminUsers))
            participants = stringToArray(info.getAsString(KeyConstant.participants))
            val adminProfileJson = JSONArray(info.getAsString(KeyConstant.adminUserProfiles))
            val participantProfileJson = JSONArray(info.getAsString(KeyConstant.participantsProfiles))
            for (i in 0 until adminProfileJson.length())
            {
                adminUserProfiles.add(FriendlyProfile(adminProfileJson.getJSONObject(i)))
            }
            for (i in 0 until participantProfileJson.length())
            {
                participantProfiles.add(FriendlyProfile(participantProfileJson.getJSONObject(i)))
            }
            this.createdAt = info.getAsString(KeyConstant.createdAt)
            this.updatedAt = info.getAsString(KeyConstant.updatedAt)
        } catch (e: Exception)
        {
            print("TASK Exception ")
        }
    }
    
//    fun updateTaskStatus(activity: UtilityActivity)
//    {
//        val request = JSONObject()
//        val user = User()
//        request.put(KeyConstant.userId, user._id)
//        request.put(KeyConstant._id, _id)
//        request.put(KeyConstant.status, status)
//        SocketManager.onEvent = { event, data ->
//            Log.d("TASK", data.toString())
//            if (data.has(KeyConstant.acknowledged))
//            {
//                if (data.getBoolean(KeyConstant.acknowledged))
//                {
//                    activity.runOnUiThread {
//                        Toast.makeText(
//                            activity,
//                            "Task status updated successfully",
//                            Toast.LENGTH_SHORT
//                                      ).show()
//                    }
//                }
//            }
//        }
//        SocketManager.send(SocketEvent.updateTaskStatus, request)
//    }
//
//    fun updateTaskPriority(activity: UtilityActivity)
//    {
//        val request = JSONObject()
//        val user = User()
//        request.put(KeyConstant.userId, user._id)
//        request.put(KeyConstant._id, _id)
//        request.put(KeyConstant.priority, priority)
//        SocketManager.onEvent = { event, data ->
//            Log.d("TASK", data.toString())
//            if (data.has(KeyConstant.acknowledged))
//            {
//                if (data.getBoolean(KeyConstant.acknowledged))
//                {
//                    activity.runOnUiThread {
//                        Toast.makeText(
//                            activity,
//                            "Task priority updated successfully",
//                            Toast.LENGTH_SHORT
//                                      ).show()
//                    }
//                }
//            }
//        }
//        SocketManager.send(SocketEvent.updateTaskPriority, request)
//    }
    
}
    
