package com.friendly.framework.feature.customer.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.friendly.framework.feature.customer.repository.CustomerRepository

class CustomerViewModalFactory(private val customerRepository: CustomerRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CustomerViewModel(customerRepository) as T
    }
}