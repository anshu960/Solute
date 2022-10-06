package com.utilitykit.feature.customer.handler

import com.google.gson.Gson
import com.utilitykit.Constants.Key
import com.utilitykit.UtilityActivity
import com.utilitykit.feature.cart.model.Sale
import com.utilitykit.feature.customer.model.Customer
import com.utilitykit.feature.customer.repository.CustomerRepository
import com.utilitykit.feature.customer.viewModel.CustomerViewModel
import com.utilitykit.feature.invoice.model.CustomerInvoice
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
    val onCreateCustomer = Emitter.Listener {
        if (it.isNotEmpty()) {
            val jsonData = it.first() as JSONObject
            if(jsonData.has(Key.payload)){
                val customerData = jsonData.getJSONObject(Key.payload)
                val newCustomer = gson.fromJson(customerData.toString(),Customer::class.java)
                repository.customerLiveData.postValue(newCustomer)
            }
        }
    }

    val onFetchAllCustomer = Emitter.Listener {
        if (it.isNotEmpty()) {
            val anyData = it.first() as JSONObject
            if (anyData.has(Key.payload)){
                val payload = anyData.getJSONArray(Key.payload)
                var allCustomers : ArrayList<Customer> = arrayListOf()
                if(payload.length() > 0) {
                    for (i in 0 until payload.length()) {
                        val customerData = payload.get(i)
                        val newCustomer = gson.fromJson(customerData.toString(),Customer::class.java)
                        allCustomers.add(newCustomer)
                    }
                    repository.allCustomerLiveData.postValue(allCustomers)
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