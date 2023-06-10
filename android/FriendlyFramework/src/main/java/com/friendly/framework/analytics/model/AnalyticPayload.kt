package com.friendly.framework.analytics.model

enum class ActionType(val raw: String){
    VIEW("VIEW"),
    VIEW_SCREEN("VIEW_SCREEN"),
    LOAD("LOAD"),
    CREATE("CREATE"),
    UPDATE("UPDATE"),
    DELETE("DELETE"),
    RETRIEVE("RETRIEVE"),
    SEARCH("SEARCH"),
}

data class AnalyticPayload (val id:String,val businessId:String,val actionType:ActionType)