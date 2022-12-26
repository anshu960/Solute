package com.utilitykit.feature.customer.viewModel

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
import com.utilitykit.feature.customer.model.Customer
import com.utilitykit.feature.customer.repository.CustomerRepository
import com.utilitykit.socket.SocketService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject

class CustomerViewModel (private val customerRepository: CustomerRepository):ViewModel(){
    val gson = Gson()

    init {
        viewModelScope.launch (Dispatchers.IO){

        }
    }

    val allCustomer : LiveData<ArrayList<Customer>>
        get() = customerRepository.allCustomer

    val customer : LiveData<Customer>
        get() = customerRepository.customer

    fun loadCustomer(){
        if(!BusinessHandler.shared().repository.business.value?.Id.isNullOrEmpty()){
            val businessId = BusinessHandler.shared().repository.business.value!!.Id
            DatabaseHandler.shared().database.customerDao().getAllItemsForBusiness(businessId).observe(BusinessHandler.shared().activity){
                customerRepository.allCustomerLiveData.postValue(it as ArrayList<Customer>?)
            }
        }
    }
    fun getCustomerById(id:String, completion:(customer: Customer) -> Unit){
        if(!BusinessHandler.shared().repository.business.value?.Id.isNullOrEmpty()){
            DatabaseHandler.shared().database.customerDao().findCustomerById(id).observe(BusinessHandler.shared().activity){
                completion(it)
            }
        }
    }

    fun fetchAllCustomer(){
        val user = User()
        var request = JSONObject()
        request.put(Key.userId,user._id)
        request.put(Key.businessID,BusinessHandler.shared().repository.business.value?.Id)
        request.put(Key.deviceId, AuthHandler.shared().deviceId)
        SocketService.shared().send(SocketEvent.RETRIVE_CUSTOMER,request)
    }
    fun createNewCustomer(name:String,mobile:String,email:String,barcode:String){
        val user = User()
        var request = JSONObject()
        request.put(Key.userId,user._id)
        request.put(Key.businessID,BusinessHandler.shared().repository.business.value?.Id)
        request.put(Key.name,name)
        request.put(Key.mobileNumber, mobile)
        request.put(Key.emailId,email)
        request.put(Key.barcode,barcode)
        request.put(Key.deviceId, AuthHandler.shared().deviceId)
        SocketService.shared().send(SocketEvent.CREATE_CUSTOMER,request)
    }
    fun updateCustomer(customer: Customer){
        val user = User()
        var request = JSONObject()
        request.put(Key.userId,user._id)
        request.put(Key.businessID,BusinessHandler.shared().repository.business.value?.Id)
        request.put(Key.name,customer.Name)
        request.put(Key.mobileNumber, customer.MobileNumber)
        request.put(Key.emailId,customer.EmailID)
        request.put(Key.barcode,customer.Barcode)
        request.put(Key.deviceId, AuthHandler.shared().deviceId)
        SocketService.shared().send(SocketEvent.UPDATE_CUSTOMER,request)
    }

    fun insert(customer : Customer){
        viewModelScope.launch{
            DatabaseHandler.shared().database.customerDao()
                .insert(customer)
        }
    }

    fun getInvoiceCount(id:String, completion:(count: Int) -> Unit){
        if(!BusinessHandler.shared().repository.business.value?.Id.isNullOrEmpty()){
            DatabaseHandler.shared().database.customerDao().getInvoiceCount(id).observe(BusinessHandler.shared().activity){
                if(it != null){
                    completion(it)
                }else{
                    completion(0)
                }
            }
        }
    }

    fun getTotalInvoiceValue(id:String, completion:(count: Float) -> Unit){
        if(!BusinessHandler.shared().repository.business.value?.Id.isNullOrEmpty()){
            DatabaseHandler.shared().database.customerDao().getTotalInvoiceValue(id).observe(BusinessHandler.shared().activity){
                if(it != null){
                    completion(it)
                }else{
                    completion(0F)
                }
            }
        }
    }
}