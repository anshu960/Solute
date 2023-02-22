package com.utilitykit.feature.address.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.utilitykit.feature.address.model.Address

class  AddressRepository {

     val liveData = MutableLiveData<List<Address>>()
     val allLiveData : LiveData<List<Address>>
     get() = liveData

     var selectedAddress : Address? = null

     val selectedLiveData = MutableLiveData< Address>()
     val selected : LiveData< Address>
          get() = selectedLiveData

}
