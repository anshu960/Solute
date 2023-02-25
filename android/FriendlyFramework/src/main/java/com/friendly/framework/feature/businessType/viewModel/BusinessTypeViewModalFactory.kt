package com.friendly.framework.feature.businessType.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.friendly.framework.feature.businessType.repository.BusinessTypeRepository

class BusinessTypeViewModalFactory(private val businessTypeRepository: BusinessTypeRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return BusinessTypeViewModel(businessTypeRepository) as T
    }
}