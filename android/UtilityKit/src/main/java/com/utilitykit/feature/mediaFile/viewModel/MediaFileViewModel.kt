package com.utilitykit.feature.mediaFile.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.utilitykit.Constants.Key
import com.utilitykit.Constants.Key.Companion.category
import com.utilitykit.Constants.Key.Companion.featureObjectID
import com.utilitykit.database.DatabaseHandler
import com.utilitykit.dataclass.User
import com.utilitykit.feature.business.handler.AuthHandler
import com.utilitykit.feature.business.handler.BusinessHandler
import com.utilitykit.feature.mediaFile.event.MediaFileEvent
import com.utilitykit.feature.mediaFile.model.MediaFile
import com.utilitykit.feature.mediaFile.repository.MediaFileRepository
import com.utilitykit.socket.SocketEvent
import com.utilitykit.socket.SocketService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.json.JSONObject

class  MediaFileViewModel(private val repository: MediaFileRepository) :
    ViewModel() {

    private var job: Job = Job()
    private val scope = CoroutineScope(job + Dispatchers.IO)

    init {
        viewModelScope.launch(Dispatchers.IO) {

        }
    }

    val allData: LiveData<List<MediaFile>>
        get() = repository.allLiveData

    val selectedData : LiveData< MediaFile>
        get() = repository.selected



    fun loadFor(featureObjectID:String,completion:(ArrayList<MediaFile>)->Unit){
        scope.launch {
            val mediaFiles = DatabaseHandler.shared().database.mediaFileDao().getAllItemsFor(featureObjectID)
            if(mediaFiles.isNotEmpty()){
                completion(mediaFiles as ArrayList<MediaFile>)
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
        SocketService.shared().send(MediaFileEvent.RETRIEVE.value,request)
    }

    fun retrieve(){
        val request = JSONObject()
        val user = User()
        val business = BusinessHandler.shared().repository.business
        request.put(Key.userId,user._id)
        request.put(Key.businessID, business.value?.Id)
        request.put(Key.deviceId, AuthHandler.shared().deviceId)
        SocketService.shared().send(MediaFileEvent.RETRIEVE.value,request)
    }

    fun createNew(request:JSONObject) {
        val unixTime = System.currentTimeMillis()
        request.put(Key.uniqueId,unixTime)
        SocketService.shared().send(MediaFileEvent.CREATE.value, request)
    }

    fun insert(newData :  MediaFile){
        viewModelScope.launch{
            DatabaseHandler.shared().database.mediaFileDao().insert(newData)
        }
    }
}