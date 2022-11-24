package com.utilitykit.feature.business.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.utilitykit.Constants.Key
import com.utilitykit.UtilityKitApp
import com.utilitykit.socket.SocketEvent
import com.utilitykit.dataclass.User
import com.utilitykit.feature.business.model.Business
import com.utilitykit.feature.business.repository.BusinessRepository
import com.utilitykit.feature.businessType.handler.BusinessTypeHandler
import com.utilitykit.feature.sync.BusinessAnalytics
import com.utilitykit.socket.SocketService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.onEmpty
import kotlinx.coroutines.launch
import org.json.JSONObject

class BusinessViewModel (private val bussinessRepository: BusinessRepository):ViewModel(){
    init {
        viewModelScope.launch{

        }
    }
    val analytics : LiveData<ArrayList<BusinessAnalytics>>
        get() = bussinessRepository.analytics

    val allBusiness : LiveData<List<Business>>
    get() = bussinessRepository.allBusiness



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
        SocketService.shared().send(SocketEvent.CREATE_BUSINESS,request)
    }
    fun insertDatabase(business: Business){
        viewModelScope.launch{
            UtilityKitApp.applicationContext().database.businessDao().insert(business)
        }
    }

    fun getSalesData(){
        viewModelScope.launch {

        }

    }

}