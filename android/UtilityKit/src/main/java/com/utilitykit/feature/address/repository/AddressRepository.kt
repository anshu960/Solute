package com.utilitykit.feature.address.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.storage.StorageReference
import com.utilitykit.feature.mediaFile.model.MediaFile

class  AddressRepository {

     var storageBucketReferece : StorageReference? = null

     val liveData = MutableLiveData<List< MediaFile>>()
     val allLiveData : LiveData<List<MediaFile>>
     get() = liveData

     val selectedLiveData = MutableLiveData< MediaFile>()
     val selected : LiveData< MediaFile>
          get() = selectedLiveData

}
