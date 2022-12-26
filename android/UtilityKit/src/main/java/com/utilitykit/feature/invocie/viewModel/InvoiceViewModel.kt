package com.utilitykit.feature.invoice.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.utilitykit.Constants.Key
import com.utilitykit.database.DatabaseHandler
import com.utilitykit.socket.SocketEvent
import com.utilitykit.dataclass.User
import com.utilitykit.feature.business.handler.AuthHandler
import com.utilitykit.feature.business.handler.BusinessHandler
import com.utilitykit.feature.cart.model.Sale
import com.utilitykit.feature.invoice.model.CustomerInvoice
import com.utilitykit.feature.invoice.repository.InvoiceRepository
import com.utilitykit.socket.SocketService
import kotlinx.coroutines.*
import org.json.JSONArray
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class InvoiceViewModel (private val invoiceRepository: InvoiceRepository):ViewModel(){
    val gson = Gson()

    init {
        viewModelScope.launch (Dispatchers.IO){
//            fetchAllInvoice()
        }
    }

    val customerInvoice : LiveData<CustomerInvoice>
        get() = invoiceRepository.customerInvoice

    val allCustomerInvoice : LiveData<List<CustomerInvoice>>
        get() = invoiceRepository.allCustomerInvoice
    val filteredCustomerInvoice : LiveData<List<CustomerInvoice>>
        get() = invoiceRepository.filteredCustomerInvoice
    val allSalesLiveData : LiveData<List<Sale>>
        get() = invoiceRepository.allSales

    fun loadInvoice(){
        if(!BusinessHandler.shared().repository.business.value?.Id.isNullOrEmpty()){
            val businessId = BusinessHandler.shared().repository.business.value?.Id
            if (businessId != null) {
                DatabaseHandler.shared().database.customerInvoiceDao().getAllItemsForBusiness(businessId).observe(BusinessHandler.shared().activity){
                    invoiceRepository.allCustomerInvoiceLiveData.postValue(it as ArrayList<CustomerInvoice>?)
                    invoiceRepository.filteredCustomerInvoiceLiveData.postValue(it)
                }
            }
        }
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    fun fetchAllInvoice(){
        val user = User()
        var request = JSONObject()
        request.put(Key.userId,user._id)
        request.put(Key.businessID,BusinessHandler.shared().repository.business.value?.Id)
        request.put(Key.deviceId, AuthHandler.shared().deviceId)
        val getLastInvoice = GlobalScope.async{DatabaseHandler.shared().database.customerInvoiceDao().getRecentItemForBusiness(BusinessHandler.shared().repository.business.value!!.Id)}
        getLastInvoice.invokeOnCompletion {
            if (getLastInvoice.getCompleted()?.updatedAt != null) {
                val lastInvoice = getLastInvoice.getCompleted()
                request.put(Key.startDate,lastInvoice?.updatedAt)
                request.put(Key.endDate,now())
                SocketService.shared().send(SocketEvent.RETRIVE_INVOICE,request)
            } else {
                var startDate = lastYear()
                startDate += "T00:00:00.001+00:00"
                var endDate = now()
                endDate += "T24:00:00.000+00:00"
                request.put(Key.startDate,startDate)
                request.put(Key.endDate,endDate)
                SocketService.shared().send(SocketEvent.RETRIVE_INVOICE,request)
            }
        }



    }

    fun filter(invoiceNumber:Long){
        val newFilteredList : ArrayList<CustomerInvoice> = arrayListOf()
        allCustomerInvoice.value?.forEach {
            if(it.invoiceNumber == invoiceNumber){
                newFilteredList.add(it)
            }
        }
        invoiceRepository.filteredCustomerInvoiceLiveData.postValue(newFilteredList)
    }
    fun clearAllFilters(){
        invoiceRepository.filteredCustomerInvoiceLiveData.postValue(invoiceRepository.allCustomerInvoice.value)
    }

    fun fetchAllSales(){
        val user = User()
        if(customerInvoice.value != null){
            var request = JSONObject()
            request.put(Key.userId,user._id)
            request.put(Key.businessID,BusinessHandler.shared().repository.business.value?.Id)
            request.put(Key.salesID,JSONArray(customerInvoice!!.value!!.salesID))
            request.put(Key.deviceId, AuthHandler.shared().deviceId)
            SocketService.shared().send(SocketEvent.RETRIVE_SALES,request)
        }
    }



    fun now(): String {
        return SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
    }
    fun yesterday(): String {
        val calendar = Calendar.getInstance()
        calendar.time = Date()
        calendar.add(Calendar.DAY_OF_MONTH, -7)
        return SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(calendar.time)
    }
    fun lastYear(): String {
        val calendar = Calendar.getInstance()
        calendar.time = Date()
        calendar.add(Calendar.DAY_OF_MONTH, -365)
        return SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(calendar.time.date)
    }

    fun insert(invoice : CustomerInvoice){
        viewModelScope.launch{
            DatabaseHandler.shared().database.customerInvoiceDao()
                .insert(invoice)
        }
    }
    fun insertSale(sale : Sale){
        viewModelScope.launch{
            DatabaseHandler.shared().database.saleDao()
                .insert(sale)
        }
    }


}