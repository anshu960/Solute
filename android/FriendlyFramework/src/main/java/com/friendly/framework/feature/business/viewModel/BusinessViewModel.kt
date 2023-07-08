package com.friendly.framework.feature.business.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.friendly.framework.constants.KeyConstant
import com.friendly.framework.database.DatabaseHandler
import com.friendly.framework.dataclass.FriendlyUser
import com.friendly.framework.feature.address.model.Address
import com.friendly.framework.feature.business.event.Event
import com.friendly.framework.feature.business.handler.BusinessHandler
import com.friendly.framework.feature.business.model.Business
import com.friendly.framework.feature.business.network.Network
import com.friendly.framework.feature.business.repository.BusinessRepository
import com.friendly.framework.feature.businessType.handler.BusinessTypeHandler
import com.friendly.framework.feature.sync.BusinessAnalytics
import com.friendly.framework.socket.SocketEvent
import com.friendly.framework.socket.SocketService
import com.friendly.frameworkt.feature.business.handler.AuthHandler
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.json.JSONObject

class BusinessViewModel(private val repository: BusinessRepository) : ViewModel() {
    init {

    }

    val analytics: LiveData<ArrayList<BusinessAnalytics>>
        get() = repository.analytics

    val selectedBusiness: LiveData<Business>
        get() = repository.businessLiveData

    val allBusiness: LiveData<ArrayList<Business>>
        get() = repository.allBusiness

    fun loadBusiness() {
        CoroutineScope(Job() + Dispatchers.IO).launch {
            repository.allBusiness.postValue(
                DatabaseHandler.shared().database.businessDao()
                    .getAllItemsFromDB() as ArrayList<Business>
            )
        }
    }

    fun setUpDefaultBusiness() {
        if (repository.business.value != null && repository.business.value?.Id != null) {
            return
        } else {
            CoroutineScope(Job() + Dispatchers.IO).launch {
                val allBusinessFromDatabase =
                    DatabaseHandler.shared().database.businessDao().getAllItemsFromDB()
                if (allBusinessFromDatabase.isNotEmpty()) {
                    repository.businessLiveData.postValue(allBusinessFromDatabase.first())
                }
            }
        }
    }

    fun createNewBusiness(
        name: String,
        gst: String,
        pan: String,
        address: JSONObject,
        email: String,
        mobile: String
    ) {
        val request = JSONObject()
        val user = FriendlyUser()
        val unixTime = System.currentTimeMillis()
        request.put(KeyConstant.userId, user._id)
        request.put(KeyConstant.name, name)
        request.put(KeyConstant.gstNumber, gst)
        request.put(KeyConstant.panNumber, pan)
        request.put(KeyConstant.address, address)
        request.put(KeyConstant.emailId, email)
        request.put(KeyConstant.mobileNumber, mobile)
        request.put(KeyConstant.deviceId, AuthHandler.shared().deviceId)
        request.put(KeyConstant.uniqueId, unixTime)
        if (BusinessTypeHandler.shared().repository.businessType != null) {
            request.put(
                KeyConstant.businessTypeID,
                BusinessTypeHandler.shared().repository.businessType!!.Id
            )
        }
        SocketService.shared().send(SocketEvent.CREATE_BUSINESS, request)
    }

    fun updateAddress(
        address: Address, onSuccess: (String) -> Unit, onError: (String) -> Unit
    ) {
        val request = JSONObject()
        val user = FriendlyUser()
        val unixTime = System.currentTimeMillis()
        request.put(KeyConstant.userId, user._id)
        request.put(KeyConstant.address, Gson().toJsonTree(address))
        request.put(KeyConstant.deviceId, AuthHandler.shared().deviceId)
        request.put(KeyConstant.uniqueId, unixTime)
        if (selectedBusiness.value != null) {
            request.put(
                KeyConstant.businessID,
                selectedBusiness.value!!.Id
            )
        }
        Network.shared().updateAddress(request, { payload ->
            val businessToUpdate = selectedBusiness.value
            businessToUpdate?.Address = Gson().fromJson(payload.toString(), Address::class.java)
            businessToUpdate?.let { insertDatabase(it) }
            CoroutineScope(Job() + Dispatchers.Main).launch {
                payload?.let { onSuccess("Updated") }
            }
        }, {
            CoroutineScope(Job() + Dispatchers.Main).launch {
                onError("Updated")
            }
        }
        )
    }

    fun updateInfo(
        business: Business, onSuccess: (String) -> Unit, onError: (String) -> Unit
    ) {
        val request = JSONObject(Gson().toJson(business))
        Network.shared().update(request, { payload ->
            payload?.let { insertDatabase(Gson().fromJson(payload.toString(),Business::class.java)) }
            CoroutineScope(Job() + Dispatchers.Main).launch {
                payload?.let { onSuccess("Updated") }
            }
        }, {msg->
            CoroutineScope(Job() + Dispatchers.Main).launch {
                msg?.let { onError(it) }
            }
        }
        )
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