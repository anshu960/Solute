package com.utilitykit.feature.customer.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.utilitykit.feature.customer.model.Customer

class CustomerRepository {
    val allCustomerLiveData = MutableLiveData<List<Customer>>()
    val allCustomer : LiveData<List<Customer>>
        get() = allCustomerLiveData

    val customerLiveData = MutableLiveData<Customer>()
    val customer : LiveData<Customer>
        get() = customerLiveData

}