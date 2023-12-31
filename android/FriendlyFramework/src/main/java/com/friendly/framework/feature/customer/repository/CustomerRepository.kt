package com.friendly.framework.feature.customer.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.friendly.framework.feature.customer.model.Customer

class CustomerRepository {
    val allCustomerLiveData = MutableLiveData<ArrayList<Customer>>()
    val allCustomer : LiveData<ArrayList<Customer>>
        get() = allCustomerLiveData

    val customerLiveData = MutableLiveData<Customer>()
    val customer : LiveData<Customer>
        get() = customerLiveData

}