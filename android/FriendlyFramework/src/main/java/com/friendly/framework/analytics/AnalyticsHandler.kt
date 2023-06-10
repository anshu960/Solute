package com.friendly.framework.analytics

import com.friendly.framework.analytics.model.ActionType
import com.friendly.framework.analytics.model.AnalyticPayload
import com.friendly.framework.constants.KeyConstant
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.logEvent
import org.json.JSONObject

class AnalyticsHandler {
     var firebaseAnalytics: FirebaseAnalytics? = null
    init {
        instance = this
    }
    companion object{
        private var instance: AnalyticsHandler? = null
        fun shared() : AnalyticsHandler {
            if(instance != null){
                return instance as AnalyticsHandler
            }else{
                return AnalyticsHandler()
            }
        }
    }

    fun preparePayload(event: String,payload: JSONObject):AnalyticPayload{
        val analyticPayload = JSONObject()
        var id = ""
        var businessId = ""
        var name = ""

        if(payload.has(KeyConstant._id)){
            id = payload.getString(KeyConstant._id)
        }
        if(payload.has(KeyConstant.businessID)){
            businessId = payload.getString(KeyConstant.businessID)
        }
        if(payload.has(KeyConstant.name)){
            name = payload.getString(KeyConstant.name)
        }
        if(event.contains("RETRIVE") || event.contains("RETRIEVE")){
            return AnalyticPayload(id,businessId,ActionType.RETRIEVE)
        }else if(event.contains("CREATE")){
            return AnalyticPayload(id,businessId,ActionType.CREATE)
        }else if(event.contains("UPDATE")){
            return AnalyticPayload(id,businessId,ActionType.UPDATE)
        }else if(event.contains("DELETE")){
            return AnalyticPayload(id,businessId,ActionType.DELETE)
        }else if(event.lowercase().contains("find")){
            return AnalyticPayload(id,businessId,ActionType.DELETE)
        }
        return AnalyticPayload(id,businessId,ActionType.VIEW)
    }

    fun logEvent(name:String, event: String, payload: JSONObject){
        val analyticPayload = preparePayload(event,payload)
        firebaseAnalytics?.logEvent(name) {
            param("ID", analyticPayload.id)
            param("NAME", name)
            param("BUSINESS_ID", analyticPayload.businessId)
            param("ACTION_TYPE", analyticPayload.actionType.raw)
        }
    }
}