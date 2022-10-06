package com.utilitykit.feature.business.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.utilitykit.Constants.Key
import com.utilitykit.SocketUtill.SocketEvent
import com.utilitykit.SocketUtill.SocketManager
import com.utilitykit.dataclass.User
import com.utilitykit.feature.business.handler.BusinessHandler
import com.utilitykit.feature.business.model.Business
import com.utilitykit.feature.business.repository.BusinessRepository
import com.utilitykit.feature.businessType.handler.BusinessTypeHandler
import com.utilitykit.feature.sync.BusinessAnalytics
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject

class BusinessViewModel (private val bussinessRepository: BusinessRepository):ViewModel(){
    init {
        viewModelScope.launch (Dispatchers.IO){

        }
    }
    val analytics : LiveData<ArrayList<BusinessAnalytics>>
        get() = bussinessRepository.analytics

    val allBusiness : LiveData<ArrayList<Business>>
    get() = bussinessRepository.allBusinessLiveData

    fun createNewBusiness(name:String,gst:String,pan:String,address:String,email:String,mobile:String){
        val request = JSONObject()
        val user = User()
        request.put(Key.userId,user._id)
        request.put(Key.name,name)
        request.put(Key.gstNumber,gst)
        request.put(Key.panNumber,pan)
        request.put(Key.address,address)
        request.put(Key.emailId,email)
        request.put(Key.mobileNumber,mobile)
        if(BusinessTypeHandler.shared().repository.businessType != null){
            request.put(Key.businessTypeID,BusinessTypeHandler.shared().repository.businessType!!.Id)
        }
        SocketManager.send(SocketEvent.CREATE_BUSINESS,request)
    }
}