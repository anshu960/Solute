package com.friendly.framework.feature.customer.handler

import com.friendly.framework.UtilityActivity
import com.friendly.framework.constants.KeyConstant
import com.friendly.framework.dataclass.FriendlyUser
import com.friendly.framework.feature.business.handler.BusinessHandler
import com.friendly.framework.feature.customer.model.Customer
import com.friendly.framework.feature.customer.repository.CustomerRepository
import com.friendly.framework.feature.customer.viewModel.CustomerViewModel
import com.friendly.framework.socket.SocketEvent
import com.friendly.framework.socket.SocketService
import com.friendly.frameworkt.feature.business.handler.AuthHandler
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport
import com.google.gson.Gson
import io.socket.emitter.Emitter
import org.json.JSONObject

class CustomerHandler {

    var viewModel: CustomerViewModel? = null
    val repository = CustomerRepository()
    var activity = UtilityActivity()
    var onCreateNewCustomer : ((customer: Customer)->Unit)? = null
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
        val user = FriendlyUser()
        if(BusinessHandler.shared().repository.business.value != null){
            var request = JSONObject()
            request.put(KeyConstant.userId,user._id)
            request.put(KeyConstant.businessID, BusinessHandler.shared().repository.business.value?.Id)
            request.put(KeyConstant.deviceId, AuthHandler.shared().deviceId)
            SocketService.shared().send(SocketEvent.RETRIVE_CUSTOMER,request)
        }
    }

    val onCreateCustomer = Emitter.Listener {
        if (it.isNotEmpty()) {
            val jsonData = it.first() as JSONObject
            if(jsonData.has(KeyConstant.payload)){
                val customerData = jsonData.getJSONObject(KeyConstant.payload)
                val newCustomer = gson.fromJson(customerData.toString(),Customer::class.java)
                viewModel?.insert(newCustomer)
                repository.customerLiveData.postValue(newCustomer)
                onCreateNewCustomer?.let { it1 -> it1(newCustomer) }
            }
        }
    }

    val onUpdateCustomer = Emitter.Listener {
        if (it.isNotEmpty()) {
            val jsonData = it.first() as JSONObject
            if(jsonData.has(KeyConstant.payload)){
                val customerData = jsonData.getJSONObject(KeyConstant.payload)
                val newCustomer = gson.fromJson(customerData.toString(),Customer::class.java)
                viewModel?.insert(newCustomer)
                repository.customerLiveData.postValue(newCustomer)
                onCreateNewCustomer?.let { it1 -> it1(newCustomer) }
            }
        }
    }

    val onFetchAllCustomer = Emitter.Listener {
        if (it.isNotEmpty()) {
            val anyData = it.first() as JSONObject
            if (anyData.has(KeyConstant.payload)){
                val payload = anyData.getJSONArray(KeyConstant.payload)
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