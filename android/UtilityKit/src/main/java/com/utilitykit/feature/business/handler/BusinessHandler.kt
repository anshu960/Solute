package com.utilitykit.feature.business.handler

import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.utilitykit.Constants.Key
import com.utilitykit.Constants.Key.Companion.business
import com.utilitykit.UtilityActivity
import com.utilitykit.socket.SocketEvent
import com.utilitykit.dataclass.User
import com.utilitykit.feature.business.model.Business
import com.utilitykit.feature.business.repository.BusinessRepository
import com.utilitykit.feature.business.viewModel.BusinessViewModel
import com.utilitykit.socket.SocketService
import io.socket.emitter.Emitter
import org.json.JSONObject

class BusinessHandler {

    var businessViewModel: BusinessViewModel? = null
    val repository = BusinessRepository()
    val gson = Gson()
    var activity : UtilityActivity = UtilityActivity()
    var mainActivity : AppCompatActivity = UtilityActivity()
    var onDeleteBusinessResponse : ((JSONObject)->Unit)? = null

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
        SocketService.shared().send(SocketEvent.RETRIVE_BUSINESS,request)
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
                    businessViewModel?.insertDatabase(business)
                }
            }
        }
    }

    val onCreateNewBusiness = Emitter.Listener {
        if (it.isNotEmpty())
        {
            val anyData = it.first() as JSONObject
            if (anyData.has(Key.payload)){
                val payload = anyData.getJSONObject(Key.payload)
                val business = gson.fromJson(payload.toString(),Business::class.java)
                businessViewModel?.insertDatabase(business)
            }
        }
    }
    val onDeleteBusiness = Emitter.Listener {
        if (it.isNotEmpty())
        {
            val anyData = it.first() as JSONObject
            if (anyData.has(Key.payload)){
                val payload = anyData.getJSONObject(Key.payload)
                onDeleteBusinessResponse?.let { it1 -> it1(payload) }
                if(payload.has(Key._id)){
                    val businessId = payload.getString(Key._id)
                    businessViewModel?.removeBusinessFromDatabase(businessId)
                    onDeleteBusinessResponse?.let { it1 -> it1(payload) }
                }
            }
        }
    }

}