package com.utilitykit.feature.sync

import android.content.ContentValues
import android.util.Log
import com.google.gson.Gson
import com.utilitykit.Constants.Key
import com.utilitykit.Constants.Key.Companion.date
import com.utilitykit.Constants.TableNames
import com.utilitykit.SocketUtill.SocketEvent
import com.utilitykit.SocketUtill.SocketManager
import com.utilitykit.database.SQLite
import com.utilitykit.dataclass.User
import com.utilitykit.feature.business.handler.BusinessHandler
import com.utilitykit.feature.cart.model.Sale
import io.socket.emitter.Emitter
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

class SyncHandler {
    val gson = Gson()
    init {
        instance = this
    }
    companion object{
        private var instance: SyncHandler? = null
        fun shared() : SyncHandler {
            if(instance != null){
                return instance as SyncHandler
            }else{
                return SyncHandler()
            }
        }
    }

    fun syncAllBusinessData(){
        getAllSaleForBusiness()
    }

    fun getAllBusinessAnalyticsToShow():ArrayList<BusinessAnalytics>{
        var allAnalytics : ArrayList<BusinessAnalytics> = arrayListOf()
        allAnalytics.add(BusinessAnalytics("Today",0,"Total Sale Value"))
        allAnalytics.add(BusinessAnalytics("Today",0,"Total Sale Quantity"))
        allAnalytics.add(BusinessAnalytics("Yesterday",0,"Total Sale Value"))
        allAnalytics.add(BusinessAnalytics("Yesterday",0,"Total Sale Quantity"))
        allAnalytics.add(BusinessAnalytics("7 Days",0,"Total Sale Value"))
        allAnalytics.add(BusinessAnalytics("7 Days",0,"Total Sale Quantity"))
        allAnalytics.add(BusinessAnalytics("Month",0,"Total Sale Value"))
        allAnalytics.add(BusinessAnalytics("Month",0,"Total Sale Quantity"))
        allAnalytics.add(BusinessAnalytics("Year",0,"Total Sale Value"))
        allAnalytics.add(BusinessAnalytics("Year",0,"Total Sale Quantity"))
        return allAnalytics
    }

    fun getAllSaleForBusiness(){
        val calendar = Calendar.getInstance()
        calendar.time = Date()
        calendar.add(Calendar.DAY_OF_MONTH, -365)
        val today = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        val lastYear = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(calendar.getTime())

        val user = User()
        if(BusinessHandler.shared().repository.business != null){
            var request = JSONObject()
            request.put(Key.userId,user._id)
            request.put(Key.businessID,BusinessHandler.shared().repository.business!!.Id)
            request.put(Key.startDate,lastYear)
            request.put(Key.endDate,today)
            SocketManager.send(SocketEvent.RETRIVE_SALE,request)
        }
    }

    val onRetriveSale = Emitter.Listener {
        if (it.isNotEmpty())
        {
            val anyData = it.first() as JSONObject
            var allSalesFromServer : ArrayList<Sale> = arrayListOf()
            if (anyData.has(Key.payload)){
                val payload = anyData.getJSONArray(Key.payload)
                if(payload.length() > 0){
                    for (i in 0 until payload.length())
                    {
                        val invoiceSaleJson = payload.get(i)
                        val newSale = gson.fromJson(invoiceSaleJson.toString(), Sale::class.java)
                        val saleContentValue = gson.fromJson(invoiceSaleJson.toString(),ContentValues::class.java)
                        SQLite.shared().insert(TableNames.sale,saleContentValue)
                        allSalesFromServer.add(newSale)
                    }
                }
            }
        }
    }
}
