package com.utilitykit.feature.business.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.utilitykit.Constants.Key
import com.utilitykit.database.DatabaseHandler
import com.utilitykit.socket.SocketEvent
import com.utilitykit.dataclass.User
import com.utilitykit.feature.business.handler.AuthHandler
import com.utilitykit.feature.business.handler.BusinessHandler
import com.utilitykit.feature.business.model.Business
import com.utilitykit.feature.business.repository.BusinessRepository
import com.utilitykit.feature.businessType.handler.BusinessTypeHandler
import com.utilitykit.feature.sync.BusinessAnalytics
import com.utilitykit.socket.SocketService
import kotlinx.coroutines.launch
import org.json.JSONObject

class BusinessViewModel(private val bussinessRepository: BusinessRepository) : ViewModel() {
    init {
        viewModelScope.launch {

        }
    }

    val analytics: LiveData<ArrayList<BusinessAnalytics>>
        get() = bussinessRepository.analytics

    val selectedBusiness: LiveData<Business>
        get() = bussinessRepository.businessLiveData

    val allBusiness: LiveData<ArrayList<Business>>
        get() = bussinessRepository.allBusiness

    fun loadBusiness() {
        DatabaseHandler.shared().database.businessDao().getAllItems().observe(
            BusinessHandler.shared().mainActivity
        ) {
            if (!it.isNullOrEmpty()) {
                bussinessRepository.allBusiness.postValue(it as ArrayList<Business>)
            }
        }
    }

    fun createNewBusiness(
        name: String,
        gst: String,
        pan: String,
        address: String,
        email: String,
        mobile: String
    ) {
        val request = JSONObject()
        val user = User()
        request.put(Key.userId, user._id)
        request.put(Key.name, name)
        request.put(Key.gstNumber, gst)
        request.put(Key.panNumber, pan)
        request.put(Key.address, address)
        request.put(Key.emailId, email)
        request.put(Key.mobileNumber, mobile)
        request.put(Key.deviceId, AuthHandler.shared().deviceId)
        if (BusinessTypeHandler.shared().repository.businessType != null) {
            request.put(
                Key.businessTypeID,
                BusinessTypeHandler.shared().repository.businessType!!.Id
            )
        }
        SocketService.shared().send(SocketEvent.CREATE_BUSINESS, request)
    }

    fun insertDatabase(business: Business) {
        viewModelScope.launch {
            DatabaseHandler.shared().database.businessDao().insert(business)
        }
    }

    fun clearAll() {
        viewModelScope.launch {
            DatabaseHandler.shared().database.businessDao().clearAll()
        }
    }


}