package com.friendly.framework.feature.invoice.handler

import com.friendly.framework.UtilityActivity
import com.friendly.framework.analytics.AnalyticsHandler
import com.friendly.framework.analytics.event.AnalyticEvent
import com.friendly.framework.analytics.model.ActionType
import com.friendly.framework.constants.KeyConstant
import com.friendly.framework.feature.business.handler.BusinessHandler
import com.friendly.framework.feature.business.model.Business
import com.friendly.framework.feature.customer.handler.CustomerHandler
import com.friendly.framework.feature.customer.model.Customer
import com.friendly.framework.feature.invoice.model.CustomerInvoice
import com.friendly.framework.feature.invoice.repository.InvoiceRepository
import com.friendly.framework.feature.invoice.viewModel.InvoiceViewModel
import com.friendly.framework.feature.sale.model.Sale
import com.friendly.framework.socket.SocketEvent
import com.friendly.framework.socket.SocketService
import com.google.gson.Gson
import io.socket.emitter.Emitter
import org.json.JSONObject

class InvoiceHandler {

    var viewModel: InvoiceViewModel? = null
    val repository = InvoiceRepository()
    var activity = UtilityActivity()
    var onCreateNewCustomerInvoiceResponse :((invoice:CustomerInvoice)->Unit)? = null
    var onRetriveSingleInvoiceCallBack :  ((invoice: CustomerInvoice, customer: Customer?, business: Business?)->Unit)? = null
    val gson = Gson()
    var invoiceNumber: Long = 0

    init {
        instance = this
    }
    companion object{
        private var instance: InvoiceHandler? = null
        fun shared() : InvoiceHandler {
            if(instance != null){
                return instance as InvoiceHandler
            }else{
                return InvoiceHandler()
            }
        }
    }

    fun retrieveSingleInvoice(id:Long){
        val request = JSONObject()
        request.put(KeyConstant.invoiceNumber,id)
        SocketService.shared().send(SocketEvent.RETRIVE_SINGLE_INVOICE,request)
    }

    var onRetrieveSingleInvoice = Emitter.Listener {
        if (it.isNotEmpty()) {
            val anyData = it.first() as JSONObject
            if (anyData.has(KeyConstant.payload)){
                val newInvoice = gson.fromJson(anyData.getJSONObject(KeyConstant.payload).toString(),CustomerInvoice::class.java)
                var customer: Customer? = null
                var business: Business? = null
                if(anyData.has(KeyConstant.customer)){
                    customer = gson.fromJson(anyData.getJSONObject(KeyConstant.customer).toString(),Customer::class.java)
                }
                if(anyData.has(KeyConstant.business)){
                    business = gson.fromJson(anyData.getJSONObject(KeyConstant.business).toString(), Business::class.java)
                }
                onRetriveSingleInvoiceCallBack?.let { it1 -> it1(newInvoice,customer,business) }
            }
        }
    }

    val onCreateCustomerInvoice = Emitter.Listener { it ->
        if (it.isNotEmpty())
        {
            val anyData = it.first() as JSONObject
            sendCreateInvoiceEvent(anyData)
            if (anyData.has(KeyConstant.payload)){
                val payload = anyData.getJSONObject(KeyConstant.payload)
                val allSalesData = anyData.getJSONArray(KeyConstant.sales)
                val customerInvoice = gson.fromJson(payload.toString(), CustomerInvoice::class.java)
                viewModel?.insert(customerInvoice)
//                customerInvoice.sales.forEach {sale->
//                    viewModel?.insertSale(sale)
//                }

                CustomerHandler.shared().repository.customerLiveData.postValue(null)
                CustomerHandler.shared().onCreateNewCustomer = null
                BusinessHandler.shared().activity.runOnUiThread {
                    if(payload.has(KeyConstant._id) && allSalesData.length() > 0){
                        onCreateNewCustomerInvoiceResponse?.let { it1 -> it1(customerInvoice) }
                    }
                }
            }
        }
    }

    fun setup(model:InvoiceViewModel){
        viewModel = model
    }
    val retriveInvoice = Emitter.Listener {
        if (it.isNotEmpty())
        {
            val anyData = it.first() as JSONObject
            if (anyData.has(KeyConstant.payload)){
                val payload = anyData.getJSONArray(KeyConstant.payload)
                if(payload.length() > 0){
                    for (i in 0 until payload.length())
                    {
                        val invoiceJson = payload.get(i)
                        val newInvoice = gson.fromJson(invoiceJson.toString(),CustomerInvoice::class.java)
                        viewModel?.insert(newInvoice)
//                        newInvoice.sales.forEach {sale->
//                            viewModel?.insertSale(sale)
//                        }
                    }
                }
            }
        }
    }

    val retriveSales = Emitter.Listener {
        if (it.isNotEmpty())
        {
            val anyData = it.first() as JSONObject
            var allSalesFromServer : ArrayList<Sale> = arrayListOf()
            if (anyData.has(KeyConstant.payload)){
                val payload = anyData.getJSONArray(KeyConstant.payload)
                if(payload.length() > 0){
                    for (i in 0 until payload.length())
                    {
                        val invoiceSaleJson = payload.get(i)
                        val newSale = gson.fromJson(invoiceSaleJson.toString(),Sale::class.java)
                        allSalesFromServer.add(newSale)
                        viewModel?.insertSale(newSale)
                    }
                    repository.allSalesLiveData.postValue(allSalesFromServer)
                }
            }
        }
    }

    fun sendCreateInvoiceEvent(data:JSONObject){
        AnalyticsHandler.shared().logEvent(
            AnalyticEvent.CREATE_INVOICE, ActionType.CREATE.raw,
            data
        )
    }



}