package com.utilitykit.feature.business.handler

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import com.utilitykit.Constants.Key
import com.utilitykit.Constants.Key.Companion.model
import com.utilitykit.Constants.Key.Companion.payload
import com.utilitykit.Constants.TableNames
import com.utilitykit.SocketUtill.SocketEvent
import com.utilitykit.SocketUtill.SocketManager
import com.utilitykit.database.SQLite
import com.utilitykit.dataclass.Message
import com.utilitykit.dataclass.User
import com.utilitykit.feature.business.model.Business
import com.utilitykit.feature.business.repository.BusinessRepository
import com.utilitykit.feature.business.viewModel.BusinessViewModalFactory
import com.utilitykit.feature.business.viewModel.BusinessViewModel
import io.socket.emitter.Emitter
import org.json.JSONObject

class BusinessHandler {

    private lateinit var businessViewModel: BusinessViewModel
    val repository = BusinessRepository()
    val gson = Gson()

    init {
        instance = this
    }
    companion object{
        private var instance: BusinessHandler? = null
        fun shared() : BusinessHandler {
            if(instance != null){
                return instance as BusinessHandler
            }else{
                return BusinessHandler()
            }
        }
    }

    fun setup(model:BusinessViewModel){
        businessViewModel = model

    }

    fun fetchAllBusiness(){
        val request = JSONObject()
        val user = User()
        request.put(Key.userId,user._id)
        SocketManager.send(SocketEvent.RETRIVE_BUSINESS,request)
    }

     val retriveBusiness = Emitter.Listener {
        if (it.isNotEmpty())
        {
            val anyData = it.first() as JSONObject
            if (anyData.has(Key.payload)){
                val payload = anyData.getJSONArray(Key.payload)
                for (i in 0 until payload.length())
                {
                    val item = payload.getJSONObject(i)
                    val business = gson.fromJson(item.toString(),Business::class.java)
                    repository.businessLiveData.postValue(business)
                }
            }
        }
    }

}