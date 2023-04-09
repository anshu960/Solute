package com.friendly.framework.feature.invoice.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.friendly.framework.constants.KeyConstant
import com.friendly.framework.database.DatabaseHandler
import com.friendly.framework.dataclass.FriendlyUser
import com.friendly.framework.feature.business.handler.BusinessHandler
import com.friendly.framework.feature.invoice.model.CustomerInvoice
import com.friendly.framework.feature.invoice.repository.InvoiceRepository
import com.friendly.framework.feature.sale.model.Sale
import com.friendly.framework.socket.SocketEvent
import com.friendly.framework.socket.SocketService
import com.friendly.frameworkt.feature.business.handler.AuthHandler
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport
import com.google.gson.Gson
import kotlinx.coroutines.*
import org.json.JSONArray
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

class InvoiceViewModel(private val invoiceRepository: InvoiceRepository) : ViewModel() {
    val gson = Gson()

    val customerInvoice: LiveData<CustomerInvoice>
        get() = invoiceRepository.customerInvoice

    val allCustomerInvoice: LiveData<List<CustomerInvoice>>
        get() = invoiceRepository.allCustomerInvoice
    val filteredCustomerInvoice: LiveData<List<CustomerInvoice>>
        get() = invoiceRepository.filteredCustomerInvoice
    val allSalesLiveData: LiveData<List<Sale>>
        get() = invoiceRepository.allSales

    fun loadInvoice() {
        if (!BusinessHandler.shared().repository.business.value?.Id.isNullOrEmpty()) {
            val businessId = BusinessHandler.shared().repository.business.value?.Id!!
            CoroutineScope(Job() + Dispatchers.IO).launch {
                invoiceRepository.allCustomerInvoiceLiveData.postValue(
                    DatabaseHandler.shared().database.customerInvoiceDao()
                        .getAllItemsForBusiness(businessId) as ArrayList<CustomerInvoice>?
                )
                invoiceRepository.filteredCustomerInvoiceLiveData.postValue(
                    DatabaseHandler.shared().database.customerInvoiceDao()
                        .getAllItemsForBusiness(businessId)
                )
            }
        }
    }


    fun fetchAllInvoice() {
        val user = FriendlyUser()
        var request = JSONObject()
        request.put(KeyConstant.userId, user._id)
        request.put(KeyConstant.businessID, BusinessHandler.shared().repository.business.value?.Id)
        request.put(KeyConstant.deviceId, AuthHandler.shared().deviceId)
        CoroutineScope(Job() + Dispatchers.IO).launch {
            if (BusinessHandler.shared().repository.business.value != null) {
                val latestInvoice = DatabaseHandler.shared().database.customerInvoiceDao()
                    .getRecentItemForBusiness(BusinessHandler.shared().repository.business.value!!.Id)
                if (latestInvoice?.updatedAt != null) {
                    request.put(KeyConstant.startDate, latestInvoice?.updatedAt)
                    request.put(KeyConstant.endDate, now())
                    SocketService.shared().send(SocketEvent.RETRIVE_INVOICE, request)
                } else {
                    var startDate = lastYear()
                    startDate += "T00:00:00.001+00:00"
                    var endDate = now()
                    endDate += "T24:00:00.000+00:00"
                    request.put(KeyConstant.startDate, startDate)
                    request.put(KeyConstant.endDate, endDate)
                    SocketService.shared().send(SocketEvent.RETRIVE_INVOICE, request)
                }
            }
        }

    }

    fun filter(invoiceNumber: Long) {
        val newFilteredList: ArrayList<CustomerInvoice> = arrayListOf()
        allCustomerInvoice.value?.forEach {
            if (it.invoiceNumber == invoiceNumber) {
                newFilteredList.add(it)
            }
        }
        invoiceRepository.filteredCustomerInvoiceLiveData.postValue(newFilteredList)
    }

    fun clearAllFilters() {
        invoiceRepository.filteredCustomerInvoiceLiveData.postValue(invoiceRepository.allCustomerInvoice.value)
    }

    fun fetchAllSales() {
        val user = FriendlyUser()
        if (customerInvoice.value != null) {
            var request = JSONObject()
            request.put(KeyConstant.userId, user._id)
            request.put(
                KeyConstant.businessID,
                BusinessHandler.shared().repository.business.value?.Id
            )
            request.put(KeyConstant.salesID, JSONArray(customerInvoice.value!!.sales))
            request.put(KeyConstant.deviceId, AuthHandler.shared().deviceId)
            SocketService.shared().send(SocketEvent.RETRIVE_SALES, request)
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

    fun insert(invoice: CustomerInvoice) {
        CoroutineScope(Job() + Dispatchers.IO).launch {
            DatabaseHandler.shared().database.customerInvoiceDao()
                .insert(invoice)
        }
    }

    fun insertSale(sale: Sale) {
        CoroutineScope(Job() + Dispatchers.IO).launch {
            DatabaseHandler.shared().database.saleDao()
                .insert(sale)
        }
    }


}