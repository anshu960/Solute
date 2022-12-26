package com.utilitykit.feature.business.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.utilitykit.Constants.Key
import com.utilitykit.database.DatabaseHandler
import com.utilitykit.socket.SocketEvent
import com.utilitykit.dataclass.User
import com.utilitykit.feature.business.handler.BusinessHandler
import com.utilitykit.feature.business.model.Auth
import com.utilitykit.feature.business.model.Business
import com.utilitykit.feature.business.repository.AuthRepository
import com.utilitykit.feature.business.repository.BusinessRepository
import com.utilitykit.feature.businessType.handler.BusinessTypeHandler
import com.utilitykit.feature.sync.BusinessAnalytics
import com.utilitykit.socket.SocketService
import kotlinx.coroutines.launch
import org.json.JSONObject

class AuthViewModel(private val repository: AuthRepository) : ViewModel() {
    init {
        viewModelScope.launch {

        }
    }

    val authUser: LiveData<Auth>
        get() = repository.authLiveData

    val allAuth: LiveData<ArrayList<Auth>>
        get() = repository.allAuth


}