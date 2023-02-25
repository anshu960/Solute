package com.friendly.framework.feature.address.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.friendly.framework.constants.KeyConstant
import com.friendly.framework.database.DatabaseHandler
import com.friendly.framework.dataclass.FriendlyUser
import com.friendly.framework.feature.address.event.AddressEvent
import com.friendly.framework.feature.address.model.Address
import com.friendly.framework.feature.address.repository.AddressRepository
import com.friendly.framework.feature.business.handler.BusinessHandler
import com.friendly.framework.socket.SocketService
import com.friendly.frameworkt.feature.business.handler.AuthHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.json.JSONObject

class  AddressViewModel(private val repository: AddressRepository) :
    ViewModel() {

    private var job: Job = Job()
    private val scope = CoroutineScope(job + Dispatchers.IO)
    init {
        viewModelScope.launch(Dispatchers.IO) {

        }
    }

    val allData: LiveData<List<Address>>
        get() = repository.allLiveData

    val selectedData : LiveData< Address>
        get() = repository.selected



    fun loadFor(featureObjectID:String,completion:(ArrayList<Address>)->Unit){
        scope.launch {
            val allAddress = DatabaseHandler.shared().database.addressDao().getAllItemsFor(featureObjectID)
            if(allAddress.isNotEmpty()){
                completion(allAddress as ArrayList<Address>)
            }else{
                retrieveFor(featureObjectID)
            }
        }
    }

    fun retrieveFor(featureObjectID:String){
        val request = JSONObject()
        val user = FriendlyUser()
        val business = BusinessHandler.shared().repository.business
        request.put(KeyConstant.userId,user._id)
        request.put(KeyConstant.businessID, business.value?.Id)
        request.put(KeyConstant.deviceId, AuthHandler.shared().deviceId)
        request.put(KeyConstant.featureObjectID,featureObjectID)
        SocketService.shared().send(AddressEvent.RETRIEVE.value,request)
    }

    fun retrieve(){
        val request = JSONObject()
        val user = FriendlyUser()
        val business = BusinessHandler.shared().repository.business
        request.put(KeyConstant.userId,user._id)
        request.put(KeyConstant.featureObjectID, business.value?.Id)
        request.put(KeyConstant.deviceId, AuthHandler.shared().deviceId)
        SocketService.shared().send(AddressEvent.RETRIEVE.value,request)
    }

    fun createNew(request:JSONObject) {
        val unixTime = System.currentTimeMillis()
        request.put(KeyConstant.uniqueId,unixTime)
        SocketService.shared().send(AddressEvent.CREATE.value, request)
    }
    fun updateExistingAddress(request:JSONObject) {
        SocketService.shared().send(AddressEvent.UPDATE.value, request)
    }

    fun deleteExistingAddress(request:JSONObject) {
        SocketService.shared().send(AddressEvent.DELETE.value, request)
    }

    fun insert(newData : Address){
        viewModelScope.launch{
            DatabaseHandler.shared().database.addressDao().insert(newData)
        }
    }
    fun delete(data : Address){
        viewModelScope.launch{
            DatabaseHandler.shared().database.addressDao().delete(data)
        }
    }
}
