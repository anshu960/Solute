package com.utilitykit.feature.mediaFile.viewModel

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.utilitykit.Constants.Key
import com.utilitykit.Constants.Key.Companion.category
import com.utilitykit.Constants.Key.Companion.featureObjectID
import com.utilitykit.Constants.Key.Companion.product
import com.utilitykit.UtilityActivity
import com.utilitykit.database.DatabaseHandler
import com.utilitykit.dataclass.User
import com.utilitykit.feature.business.handler.AuthHandler
import com.utilitykit.feature.business.handler.BusinessHandler
import com.utilitykit.feature.mediaFile.event.MediaFileEvent
import com.utilitykit.feature.mediaFile.handler.MediaFileHandler
import com.utilitykit.feature.mediaFile.model.MediaFile
import com.utilitykit.feature.mediaFile.repository.MediaFileRepository
import com.utilitykit.feature.product.handler.ProductHandler
import com.utilitykit.feature.product.model.Product
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
    var activity : UtilityActivity? = null

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

    fun uploadImage(fileUri: Uri?,featureObjectID:String){
        if (fileUri != null) {
            val unixTime = System.currentTimeMillis()
            val fileName = "$unixTime.png"
            val imageRef = repository.storageBucketReferece?.child(fileName)
            imageRef?.putFile(fileUri!!)?.addOnSuccessListener { taskSnapshot ->
                taskSnapshot.storage.downloadUrl.addOnSuccessListener {
                    MediaFileHandler.shared().onCreateNew={
                        this.activity?.runOnUiThread  {
                            this.activity?.toast("Image Updated Successfully")
                        }
                    }
                    val imageUrl = it.toString()
                    updateMediaFileInServer(featureObjectID,imageUrl)
                }
            }?.addOnFailureListener { e ->
                print(e.message)
                this.activity?.runOnUiThread {
                    activity?.toast("Oops! Failed to upload image at the moment")
                }
            }
        }else{
            activity?.onBackPressed()
        }
    }


    fun updateMediaFileInServer(featureObjectID: String, image: String) {
        val user = User()
        val request = JSONObject()
        val business = BusinessHandler.shared().repository.business
        request.put(Key.businessID, business.value?.Id)
        request.put(Key.fileURL, image)
        request.put(Key.userId, user._id)
        request.put(Key.deviceId, AuthHandler.shared().deviceId)
        request.put(Key.featureObjectID, featureObjectID)
        MediaFileHandler.shared().viewModel?.createNew(request)
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