package com.utilitykit.database


import android.content.ContentValues
import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.utility.Encryption
import com.utilitykit.Constants.Key.Companion.product
import com.utilitykit.Constants.TableNames
import com.utilitykit.Constants.TableScript
import com.utilitykit.Defaults.init
import com.utilitykit.UtilityKitApp
import com.utilitykit.dataclass.*
import com.utilitykit.feature.cart.model.Sale
import com.utilitykit.feature.product.model.Product
import com.utilitykit.feature.product.model.ProductStock
import com.utilitykit.json
import com.utilitykit.jsonObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class Database {
    val gson = Gson()
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
        SQLite.shared().execute(TableScript.createSaleTable)
        SQLite.shared().execute(TableScript.createProductStockTable)
    }

   fun getLatestStockForProduct(product:Product):ProductStock?{
        var productStock : ProductStock? = null
        var selectQuery = "SELECT * FROM ${TableNames.productStock} WHERE ProductID = '${product.Id}'  ORDER BY CreatedAt DESC LIMIT 1"
        val row = SQLite.shared().getRowsByQuery(selectQuery)
        if(row.count() > 0){
            productStock = gson.fromJson(row.first().json(),ProductStock::class.java)
        }
       return productStock
   }

    fun getLatestStock():ProductStock?{
        var productStock : ProductStock? = null
        var selectQuery = "SELECT * FROM ${TableNames.productStock} ORDER BY CreatedAt DESC LIMIT 1"
        val row = SQLite.shared().getRowsByQuery(selectQuery)
        if(row.count() > 0){
            productStock = gson.fromJson(row.first().json(),ProductStock::class.java)
        }
        return productStock
    }

    fun getAllStockForProduct(product:Product):ArrayList<ProductStock>{
        var productStock : ArrayList<ProductStock> = arrayListOf()
        var selectQuery = "SELECT * FROM ${TableNames.productStock} WHERE ProductID = '${product.Id}' ORDER BY CreatedAt DESC"
        val row = SQLite.shared().getRowsByQuery(selectQuery)
        row.forEach {
            val newStock = gson.fromJson(it.json(),ProductStock::class.java)
            productStock.add(newStock)
        }
        return productStock
    }

    fun getLatestSale():Sale?{
        var lastSale : Sale? = null
        var selectQuery = "SELECT * FROM ${TableNames.sale} ORDER BY CreatedAt DESC LIMIT 1"
        val row = SQLite.shared().getRowsByQuery(selectQuery)
        if(row.count() > 0){
            lastSale = gson.fromJson(row.first().json(),Sale::class.java)
        }
        return lastSale
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