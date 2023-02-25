package com.friendly.framework.database

import android.content.Context

class DatabaseHandler {
    var appContext : Context? = null
    val database: FriendlyDatabase by lazy {
        this.appContext?.let {
            FriendlyDatabase.getDatabase(
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