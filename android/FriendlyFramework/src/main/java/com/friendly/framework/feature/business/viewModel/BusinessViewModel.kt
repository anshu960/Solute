package com.friendly.framework.feature.business.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.friendly.framework.constants.KeyConstant
import com.friendly.framework.database.DatabaseHandler
import com.friendly.framework.dataclass.FriendlyUser
import com.friendly.framework.feature.business.handler.BusinessHandler
import com.friendly.framework.feature.business.model.Business
import com.friendly.framework.feature.business.repository.BusinessRepository
import com.friendly.framework.feature.businessType.handler.BusinessTypeHandler
import com.friendly.framework.feature.sync.BusinessAnalytics
import com.friendly.framework.socket.SocketEvent
import com.friendly.framework.socket.SocketService
import com.friendly.frameworkt.feature.business.handler.AuthHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.json.JSONObject

class BusinessViewModel(private val bussinessRepository: BusinessRepository) : ViewModel() {
    init {

    }

    val analytics: LiveData<ArrayList<BusinessAnalytics>>
        get() = bussinessRepository.analytics

    val selectedBusiness: LiveData<Business>
        get() = bussinessRepository.businessLiveData

    val allBusiness: LiveData<ArrayList<Business>>
        get() = bussinessRepository.allBusiness

    fun loadBusiness() {
        CoroutineScope(Job() + Dispatchers.IO).launch {
            bussinessRepository.allBusiness.postValue(DatabaseHandler.shared().database.businessDao().getAllItemsForUser() as ArrayList<Business>)
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
        val user = FriendlyUser()
        request.put(KeyConstant.userId, user._id)
        request.put(KeyConstant.name, name)
        request.put(KeyConstant.gstNumber, gst)
        request.put(KeyConstant.panNumber, pan)
        request.put(KeyConstant.address, address)
        request.put(KeyConstant.emailId, email)
        request.put(KeyConstant.mobileNumber, mobile)
        request.put(KeyConstant.deviceId, AuthHandler.shared().deviceId)
        if (BusinessTypeHandler.shared().repository.businessType != null) {
            request.put(
                KeyConstant.businessTypeID,
                BusinessTypeHandler.shared().repository.businessType!!.Id
            )
        }
        SocketService.shared().send(SocketEvent.CREATE_BUSINESS, request)
    }

    fun insertDatabase(business: Business) {
        CoroutineScope(Job() + Dispatchers.IO).launch {
            DatabaseHandler.shared().database.businessDao().insert(business)
        }
    }

    fun removeBusinessFromDatabase(id: String) {
        CoroutineScope(Job() + Dispatchers.IO).launch {
            DatabaseHandler.shared().database.businessDao().delete(id)
        }
    }

    fun clearAll() {
        CoroutineScope(Job() + Dispatchers.IO).launch {
            DatabaseHandler.shared().database.businessDao().clearAll()
        }
    }

    fun deleteBusiness(
       business: Business
    ) {
        val request = JSONObject()
        val user = FriendlyUser()
        request.put(KeyConstant.userId, user._id)
        request.put(KeyConstant._id, business.Id)
        if (BusinessTypeHandler.shared().repository.businessType != null) {
            request.put(
                KeyConstant.businessTypeID,
                BusinessTypeHandler.shared().repository.businessType!!.Id
            )
        }
        SocketService.shared().send(SocketEvent.DELETE_BUSINESS, request)
    }


}