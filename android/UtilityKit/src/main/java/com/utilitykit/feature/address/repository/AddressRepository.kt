package com.utilitykit.feature.address.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.storage.StorageReference
import com.utilitykit.feature.address.model.Address

class  AddressRepository {

     var storageBucketReferece : StorageReference? = null

     val liveData = MutableLiveData<List<Address>>()
     val allLiveData : LiveData<List<Address>>
     get() = liveData

     val selectedLiveData = MutableLiveData< Address>()
     val selected : LiveData< Address>
          get() = selectedLiveData

}
