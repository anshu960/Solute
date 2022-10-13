package com.utilitykit.feature.invoice.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.utilitykit.Constants.Key
import com.utilitykit.socket.SocketEvent
import com.utilitykit.dataclass.User
import com.utilitykit.feature.business.handler.BusinessHandler
import com.utilitykit.feature.cart.model.Sale
import com.utilitykit.feature.invoice.model.CustomerInvoice
import com.utilitykit.feature.invoice.repository.InvoiceRepository
import com.utilitykit.socket.SocketService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
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

    fun fetchAllInvoice(){
        val user = User()
        if(BusinessHandler.shared().repository.business != null){
            var request = JSONObject()
            request.put(Key.userId,user._id)
            request.put(Key.businessID,BusinessHandler.shared().repository.business!!.Id)
            request.put(Key.startDate,now())
            request.put(Key.endDate,now())
            SocketService.shared().send(SocketEvent.RETRIVE_INVOICE,request)
        }
    }

    fun filter(invoiceNumber:Long){
        val newFilteredList : ArrayList<CustomerInvoice> = arrayListOf()
        allCustomerInvoice.value?.forEach {
            if(it.InvoiceNumber == invoiceNumber){
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
        if(BusinessHandler.shared().repository.business != null && customerInvoice.value != null){
            var request = JSONObject()
            request.put(Key.userId,user._id)
            request.put(Key.businessID,BusinessHandler.shared().repository.business!!.Id)
            request.put(Key.salesID,JSONArray(customerInvoice!!.value!!.SalesID))
            SocketService.shared().send(SocketEvent.RETRIVE_SALES,request)
        }
    }



    fun now(): String {
        return SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
    }
}