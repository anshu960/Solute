package com.utilitykit.feature.mediaFile.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.storage.StorageReference
import com.utilitykit.feature.mediaFile.model.MediaFile

class  MediaFileRepository {

     var storageBucketReferece : StorageReference? = null

     val liveData = MutableLiveData<List< MediaFile>>()
     val allLiveData : LiveData<List<MediaFile>>
     get() = liveData

     val selectedLiveData = MutableLiveData< MediaFile>()
     val selected : LiveData< MediaFile>
          get() = selectedLiveData

}