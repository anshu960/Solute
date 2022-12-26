package com.utilitykit.feature.business.viewModel

import androidx.lifecycle.ViewModel
import com.utilitykit.feature.business.repository.BusinessRepository
import androidx.lifecycle.ViewModelProvider
import com.utilitykit.feature.business.repository.AuthRepository

class AuthViewModalFactory(private val repository: AuthRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AuthViewModel(repository) as T
    }
}