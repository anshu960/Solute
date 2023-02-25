package com.friendly.framework.feature.mediaFile.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.friendly.framework.feature.mediaFile.model.MediaFile
import com.google.firebase.storage.StorageReference

class  MediaFileRepository {

     var storageBucketReferece : StorageReference? = null

     val liveData = MutableLiveData<List<MediaFile>>()
     val allLiveData : LiveData<List<MediaFile>>
     get() = liveData

     val selectedLiveData = MutableLiveData< MediaFile>()
     val selected : LiveData< MediaFile>
          get() = selectedLiveData

}