package com.utilitykit.feature.address.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.utilitykit.feature.address.repository.AddressRepository

class  AddressViewModalFactory(private val repository: AddressRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AddressViewModel(repository) as T
    }
}
