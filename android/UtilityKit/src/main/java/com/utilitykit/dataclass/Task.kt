package com.utilitykit.dataclass

import android.content.ContentValues
import android.util.Log
import android.widget.Toast
import com.utilitykit.Constants.Key
import com.utilitykit.Constants.TaskPriority
import com.utilitykit.Constants.TaskStatus
import com.utilitykit.SocketUtill.SocketEvent
import com.utilitykit.SocketUtill.SocketManager
import com.utilitykit.jsonObject
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
    var adminUserProfiles: ArrayList<Profile> = arrayListOf(),
    var participantProfiles: ArrayList<Profile> = arrayListOf(),
    var dynamicLink: String = "",
    var createdAt: String = "",
    var updatedAt: String = ""
               )
{
    
    var data: ContentValues
        get()
        {
            var dict = ContentValues()
            dict.put(Key._id, _id)
            dict.put(Key.name, name)
            dict.put(Key.description, description)
            dict.put(Key.priority, priority.name)
            dict.put(Key.status, status.name)
            dict.put(Key.startDate, startDate)
            dict.put(Key.dueDate, dueDate)
            dict.put(Key.adminUserProfiles, getAdminProfilesInJsonArray().toString())
            dict.put(Key.adminUsers, arrayToString(adminUsers))
            dict.put(Key.participants, arrayToString(participants))
            dict.put(Key.participantsProfiles, getParticipantsProfilesInJsonArray().toString())
            dict.put(Key.dynamicLink, dynamicLink)
            dict.put(Key.createdAt, createdAt)
            dict.put(Key.updatedAt, updatedAt)
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
            dict.put(Key._id, _id)
            dict.put(Key.name, name)
            dict.put(Key.description, description)
            dict.put(Key.priority, priority)
            dict.put(Key.status, status.name)
            dict.put(Key.startDate, startDate)
            dict.put(Key.dueDate, dueDate)
            dict.put(Key.adminUserProfiles, adminUserProfiles)
            dict.put(Key.adminUsers, adminUsers)
            dict.put(Key.participants, participants)
            dict.put(Key.participantsProfiles, participantProfiles)
            dict.put(Key.dynamicLink, dynamicLink)
            dict.put(Key.createdAt, createdAt)
            dict.put(Key.updatedAt, updatedAt)
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
            this._id = info.getString(Key._id)
            this.name = info.getString(Key.name)
            this.description = info.getString(Key.description)
            this.priority = TaskPriority.valueOf(info.getString(Key.priority))
            this.status = TaskStatus.valueOf(info.getString(Key.status))
            this.startDate = info.getString(Key.startDate)
            this.dueDate = info.getString(Key.dueDate)
            this.dynamicLink = info.getString(Key.dynamicLink)
            val adminUserIds = info.getJSONArray(Key.adminUsers)
            val participantUserIds = info.getJSONArray(Key.participants)
            for (i in 0 until adminUserIds.length())
            {
                adminUsers.add(adminUserIds.get(i) as String)
            }
            for (i in 0 until participantUserIds.length())
            {
                participants.add(participantUserIds.get(i) as String)
            }
            val adminProfileJson = info.getJSONArray(Key.adminUserProfiles)
            val participantProfileJson = info.getJSONArray(Key.participantsProfiles)
            for (i in 0 until adminProfileJson.length())
            {
                adminUserProfiles.add(Profile(adminProfileJson.getJSONObject(i)))
            }
            for (i in 0 until participantProfileJson.length())
            {
                participantProfiles.add(Profile(participantProfileJson.getJSONObject(i)))
            }
            this.createdAt = info.getString(Key.createdAt)
            this.updatedAt = info.getString(Key.updatedAt)
        } catch (e: Exception)
        {
            print("TASK Exception ")
        }
    }
    
    constructor(info: ContentValues) : this()
    {
        try
        {
            this._id = info.getAsString(Key._id)
            this.name = info.getAsString(Key.name)
            this.description = info.getAsString(Key.description)
            this.priority = TaskPriority.valueOf(info.getAsString(Key.priority))
            this.status = TaskStatus.valueOf(info.getAsString(Key.status))
            this.startDate = info.getAsString(Key.startDate)
            this.dueDate = info.getAsString(Key.dueDate)
            this.dynamicLink = info.getAsString(Key.dynamicLink)
            adminUsers = stringToArray(info.getAsString(Key.adminUsers))
            participants = stringToArray(info.getAsString(Key.participants))
            val adminProfileJson = JSONArray(info.getAsString(Key.adminUserProfiles))
            val participantProfileJson = JSONArray(info.getAsString(Key.participantsProfiles))
            for (i in 0 until adminProfileJson.length())
            {
                adminUserProfiles.add(Profile(adminProfileJson.getJSONObject(i)))
            }
            for (i in 0 until participantProfileJson.length())
            {
                participantProfiles.add(Profile(participantProfileJson.getJSONObject(i)))
            }
            this.createdAt = info.getAsString(Key.createdAt)
            this.updatedAt = info.getAsString(Key.updatedAt)
        } catch (e: Exception)
        {
            print("TASK Exception ")
        }
    }
    
//    fun updateTaskStatus(activity: UtilityActivity)
//    {
//        val request = JSONObject()
//        val user = User()
//        request.put(Key.userId, user._id)
//        request.put(Key._id, _id)
//        request.put(Key.status, status)
//        SocketManager.onEvent = { event, data ->
//            Log.d("TASK", data.toString())
//            if (data.has(Key.acknowledged))
//            {
//                if (data.getBoolean(Key.acknowledged))
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
//        request.put(Key.userId, user._id)
//        request.put(Key._id, _id)
//        request.put(Key.priority, priority)
//        SocketManager.onEvent = { event, data ->
//            Log.d("TASK", data.toString())
//            if (data.has(Key.acknowledged))
//            {
//                if (data.getBoolean(Key.acknowledged))
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
    
