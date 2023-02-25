package com.friendly.framework.feature.business.handler

import androidx.appcompat.app.AppCompatActivity
import com.friendly.framework.UtilityActivity
import com.friendly.framework.constants.KeyConstant
import com.friendly.framework.dataclass.FriendlyUser
import com.friendly.framework.feature.business.model.Business
import com.friendly.framework.feature.business.repository.BusinessRepository
import com.friendly.framework.feature.business.viewModel.BusinessViewModel
import com.friendly.framework.socket.SocketEvent
import com.friendly.framework.socket.SocketService
import com.google.gson.Gson
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
        val user = FriendlyUser()
        request.put(KeyConstant.userId,user._id)
        SocketService.shared().send(SocketEvent.RETRIVE_BUSINESS,request)
    }

     val retriveBusiness = Emitter.Listener {
        if (it.isNotEmpty())
        {
            val anyData = it.first() as JSONObject
            if (anyData.has(KeyConstant.payload)){
                val payload = anyData.getJSONArray(KeyConstant.payload)
                for (i in 0 until payload.length())
                {
                    val item = payload.getJSONObject(i)
                    val business = gson.fromJson(item.toString(), Business::class.java)
                    businessViewModel?.insertDatabase(business)
                }
            }
        }
    }

    val onCreateNewBusiness = Emitter.Listener {
        if (it.isNotEmpty())
        {
            val anyData = it.first() as JSONObject
            if (anyData.has(KeyConstant.payload)){
                val payload = anyData.getJSONObject(KeyConstant.payload)
                val business = gson.fromJson(payload.toString(),Business::class.java)
                businessViewModel?.insertDatabase(business)
            }
        }
    }
    val onDeleteBusiness = Emitter.Listener {
        if (it.isNotEmpty())
        {
            val anyData = it.first() as JSONObject
            if (anyData.has(KeyConstant.payload)){
                val payload = anyData.getJSONObject(KeyConstant.payload)
                onDeleteBusinessResponse?.let { it1 -> it1(payload) }
                if(payload.has(KeyConstant._id)){
                    val businessId = payload.getString(KeyConstant._id)
                    businessViewModel?.removeBusinessFromDatabase(businessId)
                    onDeleteBusinessResponse?.let { it1 -> it1(payload) }
                }
            }
        }
    }

}