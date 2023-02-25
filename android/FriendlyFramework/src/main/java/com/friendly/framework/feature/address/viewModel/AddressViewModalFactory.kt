package com.friendly.framework.feature.address.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.friendly.framework.feature.address.repository.AddressRepository

class  AddressViewModalFactory(private val repository: AddressRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AddressViewModel(repository) as T
    }
}
