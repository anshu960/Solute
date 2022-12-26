package com.utilitykit.feature.mediaFile.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.utilitykit.feature.mediaFile.model.MediaFile

class  MediaFileRepository {
     val liveData = MutableLiveData<List< MediaFile>>()
     val allLiveData : LiveData<List<MediaFile>>
     get() = liveData

     val selectedLiveData = MutableLiveData< MediaFile>()
     val selected : LiveData< MediaFile>
          get() = selectedLiveData

}