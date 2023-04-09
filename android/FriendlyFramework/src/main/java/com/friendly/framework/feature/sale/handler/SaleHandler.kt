package com.friendly.framework.feature.sale.handler

import com.friendly.framework.database.DatabaseHandler
import com.friendly.framework.feature.business.handler.BusinessHandler
import com.friendly.framework.feature.sale.model.Sale
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class SaleHandler {
    init {
        instance = this
    }
    companion object{
        private var instance: SaleHandler? = null
        fun shared() : SaleHandler {
            if(instance != null){
                return instance as SaleHandler
            }else{
                return SaleHandler()
            }
        }
    }
    fun getTodaySaleQuantityData(completion:(ArrayList<Int>,MaxValue:Int)->Unit){
        var graphData = arrayListOf<Int>()
        val calendar = Calendar.getInstance()
        calendar.time = Date()
        val today = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        var maxValue = 0
        CoroutineScope(Job() + Dispatchers.IO).launch {
            var allSales = arrayListOf<Sale>()
            if(BusinessHandler.shared().viewModal?.selectedBusiness?.value?.Id != ""){
                val businessId = BusinessHandler.shared().viewModal!!.selectedBusiness!!.value!!.Id
                val startDate = today+"T00:00:00.001+00:00"
                val endDate = today+"T24:00:00.000+00:00"
                val entries = DatabaseHandler.shared().database.customerInvoiceDao().getAllItemsForBusiness(businessId,startDate,endDate)
                entries.forEach {
                    allSales.addAll(it.sales)
                }
                var splitted = allSales.chunked((allSales.count()/4).toInt())
                splitted.forEach {salesChunk->
                    var totalQuantity = 0
                    salesChunk.forEach {
                        totalQuantity += it.Quantity!!
                    }
                    if(totalQuantity > maxValue){
                        maxValue = totalQuantity
                    }
                    graphData.add(totalQuantity)
                }
                completion(graphData,maxValue)
            }
        }
    }

    fun getTodaySaleValueData(completion:(ArrayList<Int>,MaxValue:Int)->Unit){
        var graphData = arrayListOf<Int>()
        val calendar = Calendar.getInstance()
        calendar.time = Date()
        val today = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        var maxValue = 0
        CoroutineScope(Job() + Dispatchers.IO).launch {
            var allSales = arrayListOf<Sale>()
            if(BusinessHandler.shared().viewModal?.selectedBusiness?.value?.Id != ""){
                val businessId = BusinessHandler.shared().viewModal!!.selectedBusiness!!.value!!.Id
                val startDate = today+"T00:00:00.001+00:00"
                val endDate = today+"T24:00:00.000+00:00"
                val entries = DatabaseHandler.shared().database.customerInvoiceDao().getAllItemsForBusiness(businessId,startDate,endDate)
                entries.forEach {
                    allSales.addAll(it.sales)
                }
                var splitted = allSales.chunked((allSales.count()/4).toInt())
                splitted.forEach {salesChunk->
                    var totalValue = 0F
                    salesChunk.forEach {
                        totalValue += it.FinalPrice!!
                    }
                    if(totalValue > maxValue){
                        maxValue = totalValue.toInt()
                    }
                    graphData.add(totalValue.toInt())
                }
                completion(graphData,maxValue)
            }
        }
    }

    fun getWeekSaleQuantityData(completion:(ArrayList<Int>,MaxValue:Int)->Unit){
        var graphData = arrayListOf<Int>()
        val calendar = Calendar.getInstance()
        calendar.time = Date()
        val today = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        calendar.add(Calendar.DAY_OF_MONTH, -6)
        val startDate =  SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(calendar.time)
        var maxValue = 0
        CoroutineScope(Job() + Dispatchers.IO).launch {
            var allSales = arrayListOf<Sale>()
            if(BusinessHandler.shared().viewModal?.selectedBusiness?.value?.Id != ""){
                val businessId = BusinessHandler.shared().viewModal!!.selectedBusiness!!.value!!.Id
                val startDateQuery = startDate+"T00:00:00.001+00:00"
                val endDate = today+"T24:00:00.000+00:00"
                val entries = DatabaseHandler.shared().database.customerInvoiceDao().getAllItemsForBusiness(businessId,startDateQuery,endDate)
                entries.forEach {
                    allSales.addAll(it.sales)
                }
                var splitted = allSales.chunked((allSales.count()/4).toInt())
                splitted.forEach {salesChunk->
                    var totalQuantity = 0
                    salesChunk.forEach {
                        totalQuantity += it.Quantity!!
                    }
                    if(totalQuantity > maxValue){
                        maxValue = totalQuantity
                    }
                    graphData.add(totalQuantity)
                }
                completion(graphData,maxValue)
            }
        }
    }

    fun getWeekSaleValueData(completion:(ArrayList<Int>,MaxValue:Int)->Unit){
        var graphData = arrayListOf<Int>()
        val calendar = Calendar.getInstance()
        calendar.time = Date()
        val today = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        calendar.add(Calendar.DAY_OF_MONTH, -6)
        val startDate =  SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(calendar.time)
        var maxValue = 0
        CoroutineScope(Job() + Dispatchers.IO).launch {
            var allSales = arrayListOf<Sale>()
            if(BusinessHandler.shared().viewModal?.selectedBusiness?.value?.Id != ""){
                val businessId = BusinessHandler.shared().viewModal!!.selectedBusiness!!.value!!.Id
                val startDateQuery = startDate+"T00:00:00.001+00:00"
                val endDate = today+"T24:00:00.000+00:00"
                val entries = DatabaseHandler.shared().database.customerInvoiceDao().getAllItemsForBusiness(businessId,startDateQuery,endDate)
                entries.forEach {
                    allSales.addAll(it.sales)
                }
                var splitted = allSales.chunked((allSales.count()/4).toInt())
                splitted.forEach {salesChunk->
                    var totalValue = 0F
                    salesChunk.forEach {
                        totalValue += it.FinalPrice!!
                    }
                    if(totalValue > maxValue){
                        maxValue = totalValue.toInt()
                    }
                    graphData.add(totalValue.toInt())
                }
                completion(graphData,maxValue)
            }
        }
    }

    fun getLastMonthSaleQuantityData(completion:(ArrayList<Int>,MaxValue:Int)->Unit){
        var graphData = arrayListOf<Int>()
        val calendar = Calendar.getInstance()
        calendar.time = Date()
        val today = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        calendar.add(Calendar.DAY_OF_MONTH, -29)
        val startDate =  SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(calendar.time)
        var maxValue = 0
        CoroutineScope(Job() + Dispatchers.IO).launch {
            var allSales = arrayListOf<Sale>()
            if(BusinessHandler.shared().viewModal?.selectedBusiness?.value?.Id != ""){
                val businessId = BusinessHandler.shared().viewModal!!.selectedBusiness!!.value!!.Id
                val startDateQuery = startDate+"T00:00:00.001+00:00"
                val endDate = today+"T24:00:00.000+00:00"
                val entries = DatabaseHandler.shared().database.customerInvoiceDao().getAllItemsForBusiness(businessId,startDateQuery,endDate)
                entries.forEach {
                    allSales.addAll(it.sales)
                }
                var splitted = allSales.chunked((allSales.count()/4).toInt())
                splitted.forEach {salesChunk->
                    var totalQuantity = 0
                    salesChunk.forEach {
                        totalQuantity += it.Quantity!!
                    }
                    if(totalQuantity > maxValue){
                        maxValue = totalQuantity
                    }
                    graphData.add(totalQuantity)
                }
                completion(graphData,maxValue)
            }
        }
    }

    fun getLastMonthSaleValueData(completion:(ArrayList<Int>,MaxValue:Int)->Unit){
        var graphData = arrayListOf<Int>()
        val calendar = Calendar.getInstance()
        calendar.time = Date()
        val today = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        calendar.add(Calendar.DAY_OF_MONTH, -29)
        val startDate =  SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(calendar.time)
        var maxValue = 0
        CoroutineScope(Job() + Dispatchers.IO).launch {
            var allSales = arrayListOf<Sale>()
            if(BusinessHandler.shared().viewModal?.selectedBusiness?.value?.Id != ""){
                val businessId = BusinessHandler.shared().viewModal!!.selectedBusiness!!.value!!.Id
                val startDateQuery = startDate+"T00:00:00.001+00:00"
                val endDate = today+"T24:00:00.000+00:00"
                val entries = DatabaseHandler.shared().database.customerInvoiceDao().getAllItemsForBusiness(businessId,startDateQuery,endDate)
                entries.forEach {
                    allSales.addAll(it.sales)
                }
                var splitted = allSales.chunked((allSales.count()/4).toInt())
                splitted.forEach {salesChunk->
                    var totalValue = 0F
                    salesChunk.forEach {
                        totalValue += it.FinalPrice!!
                    }
                    if(totalValue > maxValue){
                        maxValue = totalValue.toInt()
                    }
                    graphData.add(totalValue.toInt())
                }
                completion(graphData,maxValue)
            }
        }
    }

    fun getYearSaleQuantityData(completion:(ArrayList<Int>,MaxValue:Int)->Unit){
        var graphData = arrayListOf<Int>()
        val calendar = Calendar.getInstance()
        calendar.time = Date()
        val today = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        calendar.add(Calendar.DAY_OF_MONTH, -365)
        val startDate =  SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(calendar.time)
        var maxValue = 0
        CoroutineScope(Job() + Dispatchers.IO).launch {
            var allSales = arrayListOf<Sale>()
            if(BusinessHandler.shared().viewModal?.selectedBusiness?.value?.Id != ""){
                val businessId = BusinessHandler.shared().viewModal!!.selectedBusiness!!.value!!.Id
                val startDateQuery = startDate+"T00:00:00.001+00:00"
                val endDate = today+"T24:00:00.000+00:00"
                val entries = DatabaseHandler.shared().database.customerInvoiceDao().getAllItemsForBusiness(businessId,startDateQuery,endDate)
                entries.forEach {
                    allSales.addAll(it.sales)
                }
                var splitted = allSales.chunked((allSales.count()/4).toInt())
                splitted.forEach {salesChunk->
                    var totalQuantity = 0
                    salesChunk.forEach {
                        totalQuantity += it.Quantity!!
                    }
                    if(totalQuantity > maxValue){
                        maxValue = totalQuantity
                    }
                    graphData.add(totalQuantity)
                }
                completion(graphData,maxValue)
            }
        }
    }

    fun getYearSaleValueData(completion:(ArrayList<Int>,MaxValue:Int)->Unit){
        var graphData = arrayListOf<Int>()
        val calendar = Calendar.getInstance()
        calendar.time = Date()
        val today = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        calendar.add(Calendar.DAY_OF_MONTH, -365)
        val startDate =  SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(calendar.time)
        var maxValue = 0
        CoroutineScope(Job() + Dispatchers.IO).launch {
            var allSales = arrayListOf<Sale>()
            if(BusinessHandler.shared().viewModal?.selectedBusiness?.value?.Id != ""){
                val businessId = BusinessHandler.shared().viewModal!!.selectedBusiness!!.value!!.Id
                val startDateQuery = startDate+"T00:00:00.001+00:00"
                val endDate = today+"T24:00:00.000+00:00"
                val entries = DatabaseHandler.shared().database.customerInvoiceDao().getAllItemsForBusiness(businessId,startDateQuery,endDate)
                entries.forEach {
                    allSales.addAll(it.sales)
                }
                var splitted = allSales.chunked((allSales.count()/4).toInt())
                splitted.forEach {salesChunk->
                    var totalValue = 0F
                    salesChunk.forEach {
                        totalValue += it.FinalPrice!!
                    }
                    if(totalValue > maxValue){
                        maxValue = totalValue.toInt()
                    }
                    graphData.add(totalValue.toInt())
                }
                completion(graphData,maxValue)
            }
        }
    }
}