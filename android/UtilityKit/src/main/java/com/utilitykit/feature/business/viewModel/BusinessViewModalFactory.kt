package com.utilitykit.feature.business.viewModel

import androidx.lifecycle.ViewModel
import com.utilitykit.feature.business.repository.BusinessRepository
import androidx.lifecycle.ViewModelProvider

class BusinessViewModalFactory(private val businessRepository: BusinessRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return BusinessViewModel(businessRepository) as T
    }
}