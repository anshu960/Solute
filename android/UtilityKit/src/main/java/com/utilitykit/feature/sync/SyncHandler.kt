package com.utilitykit.feature.sync

import android.content.ContentValues
import com.google.gson.Gson
import com.utilitykit.Constants.Key
import com.utilitykit.UtilityKitApp
import com.utilitykit.socket.SocketEvent
import com.utilitykit.dataclass.User
import com.utilitykit.feature.business.handler.BusinessHandler
import com.utilitykit.feature.cart.model.Sale
import com.utilitykit.feature.customer.handler.CustomerHandler
import com.utilitykit.feature.product.handler.ProductHandler
import com.utilitykit.feature.product.model.ProductStock
import com.utilitykit.feature.productCategory.handler.ProductCategoryHandler
import com.utilitykit.feature.productSubCategory.handler.ProductSubCategoryHandler
import com.utilitykit.socket.SocketService
import io.socket.emitter.Emitter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class SyncHandler {

    val gson = Gson()

    init {
        instance = this
    }

    companion object {
        private var instance: SyncHandler? = null
        fun shared(): SyncHandler {
            if (instance != null) {
                return instance as SyncHandler
            } else {
                return SyncHandler()
            }
        }
    }

    fun clearBusinessAnalytics() {
        var allAnalytics: ArrayList<BusinessAnalytics> = arrayListOf()
        allAnalytics.add(BusinessAnalytics("Today", 0, "Total Sale Value ₹"))
        allAnalytics.add(BusinessAnalytics("Today", 0, "Total Sale Quantity"))
        allAnalytics.add(BusinessAnalytics("Yesterday", 0, "Total Sale Value ₹"))
        allAnalytics.add(BusinessAnalytics("Yesterday", 0, "Total Sale Quantity"))
        allAnalytics.add(BusinessAnalytics("7 Days", 0, "Total Sale Value ₹"))
        allAnalytics.add(BusinessAnalytics("7 Days", 0, "Total Sale Quantity"))
        allAnalytics.add(BusinessAnalytics("Month", 0, "Total Sale Value ₹"))
        allAnalytics.add(BusinessAnalytics("Month", 0, "Total Sale Quantity"))
        allAnalytics.add(BusinessAnalytics("Year", 0, "Total Sale Value ₹"))
        allAnalytics.add(BusinessAnalytics("Year", 0, "Total Sale Quantity"))
        BusinessHandler.shared().repository.analyticsLiveData.postValue(allAnalytics)
    }

    fun syncAllBusinessData() {
        ProductHandler.shared().repository.productLiveData.postValue(arrayListOf())
        ProductCategoryHandler.shared().repository.categoryLiveData.postValue(null)
        ProductSubCategoryHandler.shared().repository.subCategoryLiveData.postValue(null)
        getAllSaleForBusiness()
        getAllStockForBusiness()
        CustomerHandler.shared().fetchAllCustomer()
        ProductHandler.shared().fetchAllProduct()
        ProductCategoryHandler.shared().fetchAllProductCategory()
        ProductSubCategoryHandler.shared().fetchAllProductSubCategory()
    }

    fun updateAnalyticsToShow(allSales : ArrayList<Sale>) {
        var allAnalytics: ArrayList<BusinessAnalytics> = arrayListOf()
        GlobalScope.launch(Dispatchers.IO) {
            val calendar = Calendar.getInstance()
            calendar.time = Date()
            val today = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
            calendar.add(Calendar.DAY_OF_MONTH, -1)
            val yesterday =
                SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(calendar.getTime())
            calendar.add(Calendar.DAY_OF_MONTH, -1)
            val twoDayBack =
                SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(calendar.getTime())
            calendar.add(Calendar.DAY_OF_MONTH, -1)
            val threeDayBack =
                SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(calendar.getTime())
            calendar.add(Calendar.DAY_OF_MONTH, -1)
            val fourDayBack =
                SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(calendar.getTime())
            calendar.add(Calendar.DAY_OF_MONTH, -1)
            val fiveDayBack =
                SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(calendar.getTime())
            calendar.add(Calendar.DAY_OF_MONTH, -1)
            val sixDayBack =
                SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(calendar.getTime())
            calendar.add(Calendar.DAY_OF_MONTH, -6)
            val dateComponents = today.split("-")
            val month = dateComponents[0] + "-" + dateComponents[1]
            val year = dateComponents[0]

            var todaySaleQuantity = 0
            var todaySaleValue = 0F
            var yesterdaySaleQuantity = 0
            var yesterdaySaleValue = 0F
            var sevenDaySaleQuantity = 0
            var sevenDaySaleValue = 0F
            var monthSaleQuantity = 0
            var monthSaleValue = 0F
            var yearSaleQuantity = 0
            var yearSaleValue = 0F
            if (allSales.count() > 0) {
                allSales.forEach { sale ->
                    if (sale.Quantity != null && sale.CreatedAt != null && sale.CreatedAt!!.contains(
                            today
                        )
                    ) {
                        todaySaleQuantity += sale.Quantity!!
                        todaySaleValue += sale.FinalPrice!!
                    }
                    if (sale.Quantity != null && sale.CreatedAt != null && sale.CreatedAt!!.contains(
                            yesterday
                        )
                    ) {
                        yesterdaySaleQuantity += sale.Quantity!!
                        yesterdaySaleValue += sale.FinalPrice!!
                    }
                    if (sale.Quantity != null && sale.CreatedAt != null) {
                        if (sale.CreatedAt!!.contains(today) || sale.CreatedAt!!.contains(yesterday) || sale.CreatedAt!!.contains(
                                twoDayBack
                            ) || sale.CreatedAt!!.contains(threeDayBack) || sale.CreatedAt!!.contains(
                                fourDayBack
                            ) || sale.CreatedAt!!.contains(fiveDayBack) || sale.CreatedAt!!.contains(
                                sixDayBack
                            )
                        ) {
                            sevenDaySaleQuantity += sale.Quantity!!
                            sevenDaySaleValue += sale.FinalPrice!!
                        }
                    }
                    if (sale.Quantity != null && sale.CreatedAt != null && sale.CreatedAt!!.contains(
                            month
                        )
                    ) {
                        monthSaleQuantity += sale.Quantity!!
                        monthSaleValue += sale.FinalPrice!!
                    }
                    if (sale.Quantity != null && sale.CreatedAt != null && sale.CreatedAt!!.contains(
                            year
                        )
                    ) {
                        yearSaleQuantity += sale.Quantity!!
                        yearSaleValue += sale.FinalPrice!!
                    }
                }
            }
            allAnalytics.add(
                BusinessAnalytics(
                    "Today",
                    todaySaleValue.toInt(),
                    "Total Sale Value ₹"
                )
            )
            allAnalytics.add(BusinessAnalytics("Today", todaySaleQuantity, "Total Sale Quantity"))
            allAnalytics.add(
                BusinessAnalytics(
                    "Yesterday",
                    yesterdaySaleValue.toInt(),
                    "Total Sale Value ₹"
                )
            )
            allAnalytics.add(
                BusinessAnalytics(
                    "Yesterday",
                    yesterdaySaleQuantity,
                    "Total Sale Quantity"
                )
            )
            allAnalytics.add(
                BusinessAnalytics(
                    "7 Days",
                    sevenDaySaleValue.toInt(),
                    "Total Sale Value ₹"
                )
            )
            allAnalytics.add(
                BusinessAnalytics(
                    "7 Days",
                    sevenDaySaleQuantity,
                    "Total Sale Quantity"
                )
            )
            allAnalytics.add(
                BusinessAnalytics(
                    "Month",
                    monthSaleValue.toInt(),
                    "Total Sale Value ₹"
                )
            )
            allAnalytics.add(BusinessAnalytics("Month", monthSaleQuantity, "Total Sale Quantity"))
            allAnalytics.add(BusinessAnalytics("Year", yearSaleValue.toInt(), "Total Sale Value ₹"))
            allAnalytics.add(BusinessAnalytics("Year", yearSaleQuantity, "Total Sale Quantity"))
            BusinessHandler.shared().repository.analyticsLiveData.postValue(allAnalytics)
        }
    }


    fun getAllSaleForBusiness() {
        val lastSale = UtilityKitApp.applicationContext().database.saleDao().getRecentItem()
        val user = User()
        if (BusinessHandler.shared().repository.business != null) {
            var request = JSONObject()
            request.put(Key.userId, user._id)
            request.put(Key.businessID, BusinessHandler.shared().repository.business!!.Id)
            if (lastSale.value != null) {
                request.put(Key.lastSyncDate, lastSale.value!!.CreatedAt)
            }
            SocketService.shared().send(SocketEvent.RETRIVE_SALE, request)
        }
    }

    val onRetriveSale = Emitter.Listener {
        GlobalScope.launch(Dispatchers.Default) {
            if (it.isNotEmpty()) {
                val anyData = it.first() as JSONObject
                if (anyData.has(Key.payload)) {
                    val payload = anyData.getJSONArray(Key.payload)
                    if (payload.length() > 0) {
                        for (i in 0 until payload.length()) {
                            val invoiceSaleJson = payload.getJSONObject(i)
                            val newSale =
                                gson.fromJson(invoiceSaleJson.toString(), Sale::class.java)
                            UtilityKitApp.applicationContext().database.saleDao().insert(newSale)
                        }
                    }
                }
            }
        }
    }

    fun getAllStockForBusiness() {
        val user = User()
        val lastProductStock =
            UtilityKitApp.applicationContext().database.productStockDao().getRecentItem()
        if (BusinessHandler.shared().repository.business != null) {
            var request = JSONObject()
            request.put(Key.userId, user._id)
            request.put(Key.businessID, BusinessHandler.shared().repository.business!!.Id)
            request.put(Key.lastSyncDate, lastProductStock!!.value?.CreatedAt)
            SocketService.shared().send(SocketEvent.RETRIVE_ALL_STOCK_ENTRY, request)
        }
    }

    val onRetriveAllStockEntry = Emitter.Listener {
        GlobalScope.launch(Dispatchers.Default) {
            if (it.isNotEmpty()) {
                val anyData = it.first() as JSONObject
                if (anyData.has(Key.payload)) {
                    val payload = anyData.getJSONArray(Key.payload)
                    for (i in 0 until payload.length()) {
                        val newStockEntry = payload.getJSONObject(i)
                        val newStock =
                            gson.fromJson(newStockEntry.toString(), ProductStock::class.java)
                        ProductHandler.shared().productViewModel?.insertStock(newStock)
                    }
                }
            }
        }
    }


}

fun convertJsonToContentValue(json: JSONObject): ContentValues {
    var content = ContentValues()
    json.keys().forEach {
        val value = json.get(it) as Any
        if (value is String) {
            content.put(it, value)
        }
        if (value is Long) {
            content.put(it, value)
        }
        if (value is Int) {
            content.put(it, value)
        }
        if (value is Boolean) {
            content.put(it, value)
        }
    }
    return content
}