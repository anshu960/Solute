package com.friendly.framework.feature.business.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.friendly.framework.feature.business.repository.BusinessRepository

class BusinessViewModalFactory(private val businessRepository: BusinessRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return BusinessViewModel(businessRepository) as T
    }
}