package com.friendly.framework.feature.invoice.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.friendly.framework.feature.cart.model.Sale
import com.friendly.framework.feature.invoice.model.CustomerInvoice

class InvoiceRepository {
    val allCustomerInvoiceLiveData = MutableLiveData<List<CustomerInvoice>>()
    val allCustomerInvoice : LiveData<List<CustomerInvoice>>
        get() = allCustomerInvoiceLiveData

    val filteredCustomerInvoiceLiveData = MutableLiveData<List<CustomerInvoice>>()
    val filteredCustomerInvoice : LiveData<List<CustomerInvoice>>
        get() = filteredCustomerInvoiceLiveData

    val customerInvoiceLiveData = MutableLiveData<CustomerInvoice>()
    val customerInvoice : LiveData<CustomerInvoice>
        get() = customerInvoiceLiveData

    val allSalesLiveData = MutableLiveData<List<Sale>>()
    val allSales : LiveData<List<Sale>>
        get() = allSalesLiveData

}