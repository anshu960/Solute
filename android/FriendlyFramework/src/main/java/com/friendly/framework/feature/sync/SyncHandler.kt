package com.friendly.framework.feature.sync

import com.friendly.framework.constants.KeyConstant
import com.friendly.framework.database.DatabaseHandler
import com.friendly.framework.dataclass.FriendlyUser
import com.friendly.framework.feature.business.handler.BusinessHandler
import com.friendly.framework.feature.cart.model.Sale
import com.friendly.framework.feature.customer.handler.CustomerHandler
import com.friendly.framework.feature.invoice.handler.InvoiceHandler
import com.friendly.framework.feature.mediaFile.handler.MediaFileHandler
import com.friendly.framework.feature.product.handler.ProductHandler
import com.friendly.framework.feature.product.model.ProductStock
import com.friendly.framework.feature.productCategory.handler.ProductCategoryHandler
import com.friendly.framework.feature.productSubCategory.handler.ProductSubCategoryHandler
import com.friendly.framework.socket.SocketEvent
import com.friendly.framework.socket.SocketService
import com.friendly.frameworkt.feature.business.handler.AuthHandler
import com.google.gson.Gson
import io.socket.emitter.Emitter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

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
        ProductHandler.shared().viewModel?.fetchAllProduct()
        ProductCategoryHandler.shared().fetchAllProductCategory()
        ProductSubCategoryHandler.shared().fetchAllProductSubCategory()
        InvoiceHandler.shared().viewModel?.fetchAllInvoice()
        MediaFileHandler.shared().viewModel?.retrieve()
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
        val lastSale = DatabaseHandler.shared().database.saleDao().getRecentItem()
        val user = FriendlyUser()
        var request = JSONObject()
        request.put(KeyConstant.userId, user._id)
        request.put(KeyConstant.deviceId, AuthHandler.shared().deviceId)
        request.put(KeyConstant.businessID, BusinessHandler.shared().repository.business.value?.Id)
        if (lastSale.value != null) {
            request.put(KeyConstant.lastSyncDate, lastSale.value!!.CreatedAt)
        }
        SocketService.shared().send(SocketEvent.RETRIVE_SALE, request)
    }

    val onRetriveSale = Emitter.Listener {
        GlobalScope.launch(Dispatchers.Default) {
            if (it.isNotEmpty()) {
                val anyData = it.first() as JSONObject
                if (anyData.has(KeyConstant.payload)) {
                    val payload = anyData.getJSONArray(KeyConstant.payload)
                    if (payload.length() > 0) {
                        for (i in 0 until payload.length()) {
                            val invoiceSaleJson = payload.getJSONObject(i)
                            val newSale =
                                gson.fromJson(invoiceSaleJson.toString(), Sale::class.java)
                            DatabaseHandler.shared().database.saleDao().insert(newSale)
                        }
                    }
                }
            }
        }
    }

    fun getAllStockForBusiness() {
        val user = FriendlyUser()
        val lastProductStock =
            DatabaseHandler.shared().database.productStockDao().getRecentItem()
        var request = JSONObject()
        request.put(KeyConstant.userId, user._id)
        request.put(KeyConstant.deviceId, AuthHandler.shared().deviceId)
        request.put(KeyConstant.businessID, BusinessHandler.shared().repository.business.value?.Id)
        request.put(KeyConstant.lastSyncDate, lastProductStock!!.value?.CreatedAt)
        SocketService.shared().send(SocketEvent.RETRIVE_ALL_STOCK_ENTRY, request)
    }

    val onRetriveAllStockEntry = Emitter.Listener {
        GlobalScope.launch(Dispatchers.Default) {
            if (it.isNotEmpty()) {
                val anyData = it.first() as JSONObject
                if (anyData.has(KeyConstant.payload)) {
                    val payload = anyData.getJSONArray(KeyConstant.payload)
                    for (i in 0 until payload.length()) {
                        val newStockEntry = payload.getJSONObject(i)
                        val newStock =
                            gson.fromJson(newStockEntry.toString(), ProductStock::class.java)
                        ProductHandler.shared().viewModel?.insertStock(newStock)
                    }
                }
            }
        }
    }
}