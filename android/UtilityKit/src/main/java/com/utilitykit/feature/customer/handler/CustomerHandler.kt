package com.utilitykit.feature.customer.handler

import com.google.gson.Gson
import com.utilitykit.Constants.Key
import com.utilitykit.UtilityActivity
import com.utilitykit.dataclass.User
import com.utilitykit.feature.business.handler.BusinessHandler
import com.utilitykit.feature.cart.model.Sale
import com.utilitykit.feature.customer.model.Customer
import com.utilitykit.feature.customer.repository.CustomerRepository
import com.utilitykit.feature.customer.viewModel.CustomerViewModel
import com.utilitykit.feature.invoice.model.CustomerInvoice
import com.utilitykit.socket.SocketEvent
import com.utilitykit.socket.SocketService
import io.socket.emitter.Emitter
import org.json.JSONObject

class CustomerHandler {

    var viewModel: CustomerViewModel? = null
    val repository = CustomerRepository()
    var activity = UtilityActivity()
    val gson = Gson()
    init {
        instance = this
    }
    companion object{
        private var instance: CustomerHandler? = null
        fun shared() : CustomerHandler {
            if(instance != null){
                return instance as CustomerHandler
            }else{
                return CustomerHandler()
            }
        }
    }


    fun setup(model:CustomerViewModel){
        viewModel = model
    }
    fun fetchAllCustomer(){
        val user = User()
        if(BusinessHandler.shared().repository.business != null){
            var request = JSONObject()
            request.put(Key.userId,user._id)
            request.put(Key.businessID, BusinessHandler.shared().repository.business!!.Id)
            SocketService.shared().send(SocketEvent.RETRIVE_CUSTOMER,request)
        }
    }

    val onCreateCustomer = Emitter.Listener {
        if (it.isNotEmpty()) {
            val jsonData = it.first() as JSONObject
            if(jsonData.has(Key.payload)){
                val customerData = jsonData.getJSONObject(Key.payload)
                val newCustomer = gson.fromJson(customerData.toString(),Customer::class.java)
                viewModel?.insert(newCustomer)
            }
        }
    }

    val onFetchAllCustomer = Emitter.Listener {
        if (it.isNotEmpty()) {
            val anyData = it.first() as JSONObject
            if (anyData.has(Key.payload)){
                val payload = anyData.getJSONArray(Key.payload)
                if(payload.length() > 0) {
                    for (i in 0 until payload.length()) {
                        val customerData = payload.get(i)
                        val newCustomer = gson.fromJson(customerData.toString(),Customer::class.java)
                        viewModel?.insert(newCustomer)
                    }
                }
            }
        }
    }

    fun searchCustomerByMobile(mobile:String){
        repository.allCustomer.value?.forEach {
            if(it.MobileNumber != null && it.MobileNumber!!.contains(mobile)){
                repository.customerLiveData.postValue(it)
            }
        }
    }
    fun searchCustomerById(id:String){
        repository.allCustomer.value?.forEach {
            if(it.MobileNumber != null && it.Id!! == id){
                repository.customerLiveData.postValue(it)
            }
        }
    }


}