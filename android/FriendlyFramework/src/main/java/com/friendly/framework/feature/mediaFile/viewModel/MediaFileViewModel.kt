package com.friendly.framework.feature.mediaFile.viewModel

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.friendly.framework.UtilityActivity
import com.friendly.framework.constants.KeyConstant
import com.friendly.framework.database.DatabaseHandler
import com.friendly.framework.dataclass.FriendlyUser
import com.friendly.framework.feature.business.handler.BusinessHandler
import com.friendly.framework.feature.mediaFile.event.MediaFileEvent
import com.friendly.framework.feature.mediaFile.handler.MediaFileHandler
import com.friendly.framework.feature.mediaFile.model.MediaFile
import com.friendly.framework.feature.mediaFile.network.MediaFileNetwork
import com.friendly.framework.feature.mediaFile.repository.MediaFileRepository
import com.friendly.framework.socket.SocketService
import com.friendly.frameworkt.feature.business.handler.AuthHandler
import com.google.gson.Gson
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

    val allData: LiveData<List<MediaFile>>
        get() = repository.allLiveData

    val selectedData : LiveData< MediaFile>
        get() = repository.selected



    fun loadFor(featureObjectID:String,completion:(ArrayList<MediaFile>)->Unit){
        scope.launch {
            val mediaFiles = DatabaseHandler.shared().database.mediaFileDao().itemFor(featureObjectID)
            if(mediaFiles.isNotEmpty()){
                completion(mediaFiles as ArrayList<MediaFile>)
            }else{
                retrieveFor(featureObjectID)
            }
        }
    }
    fun find(featureObjectID:String):String{
        var stringToReturn = ""
        allData.value?.forEach {item->
            if(item.FeatureObjectID == featureObjectID && !item.FileURL.isNullOrEmpty()){
                stringToReturn = item.FileURL!!
            }
        }
        return stringToReturn
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
        val user = FriendlyUser()
        val request = JSONObject()
        val business = BusinessHandler.shared().repository.business
        request.put(KeyConstant.businessID, business.value?.Id)
        request.put(KeyConstant.fileURL, image)
        request.put(KeyConstant.userId, user._id)
        request.put(KeyConstant.deviceId, AuthHandler.shared().deviceId)
        request.put(KeyConstant.featureObjectID, featureObjectID)
        MediaFileHandler.shared().viewModel?.createNew(request)
    }

    fun retrieveFor(featureObjectID:String){
        val request = JSONObject()
        val user = FriendlyUser()
        val business = BusinessHandler.shared().repository.business
        request.put(KeyConstant.userId,user._id)
        if(!business.value?.Id.isNullOrEmpty()){
            request.put(KeyConstant.businessID, business.value?.Id)
        }
        request.put(KeyConstant.deviceId, AuthHandler.shared().deviceId)
        request.put(KeyConstant.featureObjectID,featureObjectID)
        SocketService.shared().send(MediaFileEvent.RETRIEVE,request)
    }

    fun retrieve(){
        val request = JSONObject()
        val user = FriendlyUser()
        val business = BusinessHandler.shared().repository.business
        request.put(KeyConstant.userId,user._id)
        request.put(KeyConstant.businessID, business.value?.Id)
        request.put(KeyConstant.deviceId, AuthHandler.shared().deviceId)
        MediaFileNetwork.shared().retrieve(request){responseJson->
            val payload = responseJson?.getJSONArray(KeyConstant.payload)
            var allObj : ArrayList<MediaFile> = arrayListOf()
            if(payload != null){
                for (i in 0 until payload!!.length())
                {
                    val item = payload?.getJSONObject(i)
                    val modelObject = Gson().fromJson(item.toString(),MediaFile::class.java)
                    allObj.add(modelObject)
                    insert(modelObject)
                }
            }
            repository.liveData.postValue(allObj)
        }
    }

    fun createNew(request:JSONObject) {
        val unixTime = System.currentTimeMillis()
        request.put(KeyConstant.uniqueId,unixTime)
        SocketService.shared().send(MediaFileEvent.CREATE, request)
    }

    fun insert(newData :  MediaFile){
        CoroutineScope(Job() + Dispatchers.IO).launch {
            DatabaseHandler.shared().database.mediaFileDao().insert(newData)
        }
    }
}