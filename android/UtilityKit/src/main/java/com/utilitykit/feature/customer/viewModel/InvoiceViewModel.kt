package com.utilitykit.feature.customer.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.utilitykit.Constants.Key
import com.utilitykit.socket.SocketEvent
import com.utilitykit.dataclass.User
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
//            fetchAllCustomer()
        }
    }

    val allCustomer : LiveData<List<Customer>>
        get() = customerRepository.allCustomer

    val customer : LiveData<Customer>
        get() = customerRepository.customer



    fun fetchAllCustomer(){
        val user = User()
        if(BusinessHandler.shared().repository.business != null){
            var request = JSONObject()
            request.put(Key.userId,user._id)
            request.put(Key.businessID,BusinessHandler.shared().repository.business!!.Id)
            SocketService.shared().send(SocketEvent.RETRIVE_CUSTOMER,request)
        }
    }
    fun createNewCustomer(name:String,mobile:String,email:String){
        val user = User()
        if(BusinessHandler.shared().repository.business != null){
            var request = JSONObject()
            request.put(Key.userId,user._id)
            request.put(Key.businessID,BusinessHandler.shared().repository.business!!.Id)
            request.put(Key.name,name)
            request.put(Key.mobileNumber, mobile)
            request.put(Key.emailId,email)
            SocketService.shared().send(SocketEvent.CREATE_CUSTOMER,request)
        }
    }
}