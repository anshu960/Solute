package com.utilitykit.feature.businessType.handler

import com.google.gson.Gson
import com.utilitykit.Constants.Key
import com.utilitykit.SocketUtill.SocketEvent
import com.utilitykit.SocketUtill.SocketManager
import com.utilitykit.dataclass.User
import com.utilitykit.feature.businessType.model.BusinessType
import com.utilitykit.feature.businessType.repository.BusinessTypeRepository
import com.utilitykit.feature.businessType.viewModel.BusinessTypeViewModel
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
        val user = User()
        request.put(Key.userId,user._id)
        SocketManager.send(SocketEvent.RETRIVE_BUSINESS_TYPE,request)
    }

     val retriveBusinessType = Emitter.Listener {
        if (it.isNotEmpty())
        {
            val anyData = it.first() as JSONObject
            if (anyData.has(Key.payload)){
                val payload = anyData.getJSONArray(Key.payload)
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