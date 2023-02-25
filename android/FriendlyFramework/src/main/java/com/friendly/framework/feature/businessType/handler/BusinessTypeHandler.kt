package com.friendly.framework.feature.businessType.handler

import com.friendly.framework.constants.KeyConstant
import com.friendly.framework.dataclass.FriendlyUser
import com.friendly.framework.feature.businessType.model.BusinessType
import com.friendly.framework.feature.businessType.repository.BusinessTypeRepository
import com.friendly.framework.feature.businessType.viewModel.BusinessTypeViewModel
import com.friendly.framework.socket.SocketEvent
import com.friendly.framework.socket.SocketService
import com.friendly.frameworkt.feature.business.handler.AuthHandler
import com.google.gson.Gson
import io.socket.emitter.Emitter
import org.json.JSONObject

class BusinessTypeHandler {

    private lateinit var businessTypeViewModel: BusinessTypeViewModel
    val repository = BusinessTypeRepository()
    val gson = Gson()
    var allBusinessType : ArrayList<BusinessType> = arrayListOf()
    init {
        instance = this
    }
    companion object{
        private var instance: BusinessTypeHandler? = null
        fun shared() : BusinessTypeHandler {
            if(instance != null){
                return instance as BusinessTypeHandler
            }else{
                return BusinessTypeHandler()
            }
        }
    }

    fun setup(model:BusinessTypeViewModel){
        businessTypeViewModel = model

    }

    fun fetchAllBusinessType(){
        val request = JSONObject()
        val user = FriendlyUser()
        request.put(KeyConstant.userId,user._id)
        request.put(KeyConstant.deviceId, AuthHandler.shared().deviceId)
        SocketService.shared().send(SocketEvent.RETRIVE_BUSINESS_TYPE,request)
    }

     val retriveBusinessType = Emitter.Listener {
        if (it.isNotEmpty())
        {
            val anyData = it.first() as JSONObject
            if (anyData.has(KeyConstant.payload)){
                val payload = anyData.getJSONArray(KeyConstant.payload)
                allBusinessType = arrayListOf()
                for (i in 0 until payload.length())
                {
                    val item = payload.getJSONObject(i)
                    val businessType = gson.fromJson(item.toString(),BusinessType::class.java)
                    allBusinessType.add(businessType)
                }
                repository.businessTypeLiveData.postValue(allBusinessType)

            }
        }
    }

}