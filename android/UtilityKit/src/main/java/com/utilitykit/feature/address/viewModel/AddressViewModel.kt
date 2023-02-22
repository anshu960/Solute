package com.utilitykit.feature.address.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.utilitykit.Constants.Key
import com.utilitykit.UtilityActivity
import com.utilitykit.database.DatabaseHandler
import com.utilitykit.dataclass.User
import com.utilitykit.feature.address.event.AddressEvent
import com.utilitykit.feature.address.model.Address
import com.utilitykit.feature.address.repository.AddressRepository
import com.utilitykit.feature.business.handler.AuthHandler
import com.utilitykit.feature.business.handler.BusinessHandler
import com.utilitykit.socket.SocketService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.json.JSONObject

class  AddressViewModel(private val repository: AddressRepository) :
    ViewModel() {

    private var job: Job = Job()
    private val scope = CoroutineScope(job + Dispatchers.IO)
    var activity : UtilityActivity? = null

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
            val mediaFiles = DatabaseHandler.shared().database.mediaFileDao().getAllItemsFor(featureObjectID)
            if(mediaFiles.isNotEmpty()){
                completion(mediaFiles as ArrayList<Address>)
            }else{
                retrieveFor(featureObjectID)
            }
        }
    }

    fun retrieveFor(featureObjectID:String){
        val request = JSONObject()
        val user = User()
        val business = BusinessHandler.shared().repository.business
        request.put(Key.userId,user._id)
        request.put(Key.businessID, business.value?.Id)
        request.put(Key.deviceId, AuthHandler.shared().deviceId)
        request.put(Key.featureObjectID,featureObjectID)
        SocketService.shared().send(AddressEvent.RETRIEVE.value,request)
    }

    fun retrieve(){
        val request = JSONObject()
        val user = User()
        val business = BusinessHandler.shared().repository.business
        request.put(Key.userId,user._id)
        request.put(Key.featureObjectID, business.value?.Id)
        request.put(Key.deviceId, AuthHandler.shared().deviceId)
        SocketService.shared().send(AddressEvent.RETRIEVE.value,request)
    }

    fun createNew(request:JSONObject) {
        val unixTime = System.currentTimeMillis()
        request.put(Key.uniqueId,unixTime)
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
