package com.utilitykit.database


import android.content.ContentValues
import android.content.Context
import android.util.Log
import com.utility.Encryption
import com.utilitykit.Constants.TableNames
import com.utilitykit.Constants.TableScript
import com.utilitykit.UtilityKitApp
import com.utilitykit.dataclass.*
import com.utilitykit.feature.cart.model.Sale
import com.utilitykit.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class Database {

    init {
        instance = this
    }

    companion object{
        private var instance: Database? = null
        fun shared() : Database {
            if(instance != null){
                return instance as Database
            }else{
                return Database()
            }
        }
    }

    fun setUp(context: Context) {
        shared().createTables()
    }

    fun createTables(){
//        SQLite.execute( TableScript.createMessageTable)
//        SQLite.execute(TableScript.createConversationTable)
//        SQLite.execute(TableScript.createProfilesTable)
//        SQLite.execute(TableScript.createFriendsTable)
//        SQLite.execute(TableScript.createfriendRequestTable)
//        SQLite.execute(TableScript.createBlockListTable)
//        SQLite.execute(TableScript.createFilesTable)
          SQLite.shared().execute(TableScript.createSaleTable)
    }

    fun storeSale(sale:Sale){

    }

    fun retriveConversationByID(conversationId:String):Conversation?{
        val selectQuery = "SELECT  * FROM ${TableNames.conversation} WHERE _id = '${conversationId}'"
        val rows = SQLite.shared().getRowsByQuery(selectQuery)
        if(rows.count() > 0){
            return  Conversation(rows.first())
        }
        return null
    }


    suspend fun retriveMessage(conversationId:String):Message = withContext(Dispatchers.Default) {
        val selectQuery = "SELECT  * FROM ${TableNames.message} WHERE ConversationId = '$conversationId' ORDER BY SentTime IS NULL OR SentTime=''"
        val row = SQLite.shared().getRowsByQuery(selectQuery)
        if(row.count() > 0){
            return@withContext Message(row.last())
        }else{
            return@withContext Message()
        }
    }

    fun retriveFileUrl(url:String):String{
        var localUrl = ""
        val selectQuery = "SELECT  * FROM ${TableNames.files} WHERE RemoteURL='${url}'"
        val row = SQLite.shared().getRow(selectQuery)
        if(row.containsKey("LocalURL")){
            localUrl = row.getAsString("LocalURL")
        }
        return localUrl
    }


    fun storeFileUrl(localUrl:String,remoteUrl:String){
        var contentValues = ContentValues()
        contentValues.put("LocalURL",localUrl)
        contentValues.put("RemoteURL",remoteUrl)
        SQLite.shared().insert(TableNames.files,contentValues)
    }
}