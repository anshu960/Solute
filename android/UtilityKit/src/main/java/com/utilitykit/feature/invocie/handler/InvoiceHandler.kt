package com.utilitykit.feature.invoice.handler

import android.content.Intent
import com.google.gson.Gson
import com.utilitykit.Constants.Key
import com.utilitykit.UtilityActivity
import com.utilitykit.UtilityKitApp
import com.utilitykit.feature.cart.model.Sale
import com.utilitykit.feature.customer.model.Customer
import com.utilitykit.feature.invoice.model.CustomerInvoice
import com.utilitykit.feature.invoice.repository.InvoiceRepository
import com.utilitykit.feature.invoice.viewModel.InvoiceViewModel
import io.socket.emitter.Emitter
import org.json.JSONObject

class InvoiceHandler {

    var viewModel: InvoiceViewModel? = null
    val repository = InvoiceRepository()
    var activity = UtilityActivity()
    var onCreateNewCustomerInvoiceResponse : ((data: JSONObject)->Unit)? = null
    val gson = Gson()
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


    fun setup(model:InvoiceViewModel){
        viewModel = model
    }
    val retriveInvoice = Emitter.Listener {
        if (it.isNotEmpty())
        {
            val anyData = it.first() as JSONObject
            if (anyData.has(Key.payload)){
                val payload = anyData.getJSONArray(Key.payload)
                if(payload.length() > 0){
                    for (i in 0 until payload.length())
                    {
                        val invoiceJson = payload.get(i)
                        val newInvoice = gson.fromJson(invoiceJson.toString(),CustomerInvoice::class.java)
                        viewModel?.insert(newInvoice)
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
            if (anyData.has(Key.payload)){
                val payload = anyData.getJSONArray(Key.payload)
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



}