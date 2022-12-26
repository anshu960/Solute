package com.utilitykit.database

import android.content.Context
import com.utilitykit.feature.business.handler.BusinessHandler

class DatabaseHandler {
    var appContext : Context? = null
    val database: UtilityKitDatabase by lazy {
        this.appContext?.let {
            UtilityKitDatabase.getDatabase(
                it
            )
        }!!
    }

    init {
        instance = this
    }
    companion object{
        private var instance: DatabaseHandler? = null
        fun shared() : DatabaseHandler {
            if(instance != null){
                return instance as DatabaseHandler
            }else{
                return DatabaseHandler()
            }
        }
    }

}