package com.friendly.framework.feature.business.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.friendly.framework.feature.business.model.Auth
import com.friendly.framework.feature.business.repository.AuthRepository
import kotlinx.coroutines.launch

class AuthViewModel(private val repository: AuthRepository) : ViewModel() {
    init {

    }

    val authUser: LiveData<Auth>
        get() = repository.authLiveData

    val allAuth: LiveData<ArrayList<Auth>>
        get() = repository.allAuth


}