package com.friendly.framework.feature.customer.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.friendly.framework.constants.KeyConstant
import com.friendly.framework.constants.Server
import com.friendly.framework.database.DatabaseHandler
import com.friendly.framework.dataclass.FriendlyUser
import com.friendly.framework.feature.business.handler.BusinessHandler
import com.friendly.framework.feature.customer.event.CustomerEvent
import com.friendly.framework.feature.customer.model.Customer
import com.friendly.framework.feature.customer.network.CustomerNetwork
import com.friendly.framework.feature.customer.network.CustomerNetworkInterface
import com.friendly.framework.feature.customer.repository.CustomerRepository
import com.friendly.framework.socket.SocketEvent
import com.friendly.framework.socket.SocketService
import com.friendly.frameworkt.feature.business.handler.AuthHandler
import com.google.gson.Gson
import com.google.gson.JsonArray
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.RequestBody
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CustomerViewModel(private val customerRepository: CustomerRepository) : ViewModel() {
    val gson = Gson()
    private val scope = CoroutineScope(Job() + Dispatchers.IO)

    val allCustomer: LiveData<ArrayList<Customer>>
        get() = customerRepository.allCustomer

    val customer: LiveData<Customer>
        get() = customerRepository.customer

    fun loadCustomer() {
        if (!BusinessHandler.shared().repository.business.value?.Id.isNullOrEmpty()) {
            val businessId = BusinessHandler.shared().repository.business.value!!.Id
            if (!BusinessHandler.shared().repository.business.value?.Id.isNullOrEmpty()) {
                CoroutineScope(Job() + Dispatchers.IO).launch {
                    val theCustomer = DatabaseHandler.shared().database.customerDao().getAllItemsForBusiness(businessId)
                    CoroutineScope(Job() + Dispatchers.Main).launch {
                        customerRepository.allCustomerLiveData.postValue(theCustomer as ArrayList<Customer>?)
                    }
                }
            }
        }
    }

    fun getCustomerById(id: String, completion: (customer: Customer?) -> Unit) {
            CoroutineScope(Job() + Dispatchers.IO).launch {
                val theCustomer = DatabaseHandler.shared().database.customerDao().findCustomerById(id)
                CoroutineScope(Job() + Dispatchers.Main).launch {
                    completion(theCustomer)
                }
            }
    }

    fun fetchAllCustomer() {
        val user = FriendlyUser()
        var request = JSONObject()
        request.put(KeyConstant.userId, user._id)
        request.put(KeyConstant.businessID, BusinessHandler.shared().repository.business.value?.Id)
        request.put(KeyConstant.deviceId, AuthHandler.shared().deviceId)
        SocketService.shared().send(SocketEvent.RETRIVE_CUSTOMER, request)
    }

    fun createNewCustomer(name: String, dialCode : String?,mobile: String, email: String, barcode: String) {
        val user = FriendlyUser()
        var request = JSONObject()
        request.put(KeyConstant.userId, user._id)
        request.put(KeyConstant.businessID, BusinessHandler.shared().repository.business.value?.Id)
        request.put(KeyConstant.name, name)
        request.put(KeyConstant.dialCode, dialCode)
        request.put(KeyConstant.mobileNumber, mobile)
        request.put(KeyConstant.emailId, email)
        request.put(KeyConstant.barcode, barcode)
        request.put(KeyConstant.deviceId, AuthHandler.shared().deviceId)
        SocketService.shared().send(SocketEvent.CREATE_CUSTOMER, request)
    }

    fun updateCustomer(customer: Customer) {
        val user = FriendlyUser()
        var request = JSONObject()
        request.put(KeyConstant.userId, user._id)
        request.put(KeyConstant.businessID, BusinessHandler.shared().repository.business.value?.Id)
        request.put(KeyConstant.name, customer.Name)
        request.put(KeyConstant.mobileNumber, customer.MobileNumber)
        request.put(KeyConstant.emailId, customer.EmailID)
        request.put(KeyConstant.barcode, customer.Barcode)
        request.put(KeyConstant.deviceId, AuthHandler.shared().deviceId)
        SocketService.shared().send(SocketEvent.UPDATE_CUSTOMER, request)
    }

    fun insert(customer: Customer) {
        CoroutineScope(Job() + Dispatchers.IO).launch {
            DatabaseHandler.shared().database.customerDao()
                .insert(customer)
        }
    }

    fun getInvoiceCount(id: String, completion: (count: Int) -> Unit) {
        if (!BusinessHandler.shared().repository.business.value?.Id.isNullOrEmpty()) {
            scope.launch {
                val totalInvoiceCount =
                    DatabaseHandler.shared().database.customerDao().getInvoiceCount(id)
                if (totalInvoiceCount != null) {
                    completion(totalInvoiceCount)
                }
            }
        }
    }

    fun getTotalInvoiceValue(id: String, completion: (count: Float) -> Unit) {
        if (!BusinessHandler.shared().repository.business.value?.Id.isNullOrEmpty()) {
            scope.launch {
                val totalInvoiceValue =
                    DatabaseHandler.shared().database.customerDao().getTotalInvoiceValue(id)
                if (totalInvoiceValue != null) {
                    completion(totalInvoiceValue)
                }
            }
        }
    }

    fun findCustomerByMobile(mobile: String){
        val request = JSONObject()
        request.put(KeyConstant.businessID,BusinessHandler.shared().repository.business.value?.Id)
        request.put(KeyConstant.mobileNumber,mobile)
        val retrofit = CustomerNetwork.shared().buildService(CustomerNetworkInterface::class.java)
        val bodyRequest: RequestBody =
            RequestBody.create(MediaType.parse("application/json"), request.toString())
        retrofit.findCustomerByMobile(bodyRequest).enqueue(
            object : Callback<ResponseBody> {
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    print(t.message)
                    customerRepository.customerLiveData.postValue(null)
                }
                override fun onResponse( call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    try {
                        if (response.isSuccessful) {
                            val obj = JSONObject(response.body()!!.string())
                            if(obj.has(KeyConstant.payload)){
                                val payload = obj.getJSONArray(KeyConstant.payload)
                                if(payload.length() > 0){
                                    val customerJson = payload[0].toString()
                                    val customerData = gson.fromJson(customerJson,Customer::class.java)
                                    customerRepository.customerLiveData.postValue(customerData)
                                }
                            }else{
                                customerRepository.customerLiveData.postValue(null)
                            }
                        } else {
                            val obj = JSONObject(response.errorBody()!!.string())
                            print(obj)
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                        customerRepository.customerLiveData.postValue(null)
                    }
                }
            }
        )

    }
}