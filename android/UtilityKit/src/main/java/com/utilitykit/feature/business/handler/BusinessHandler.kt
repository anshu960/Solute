package com.utilitykit.feature.business.handler

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.utilitykit.Constants.Key
import com.utilitykit.UtilityActivity
import com.utilitykit.UtilityKitApp
import com.utilitykit.database.UtilityKitDatabase
import com.utilitykit.socket.SocketEvent
import com.utilitykit.dataclass.User
import com.utilitykit.feature.business.model.Business
import com.utilitykit.feature.business.repository.BusinessRepository
import com.utilitykit.feature.business.viewModel.BusinessViewModel
import com.utilitykit.socket.SocketService
import io.socket.emitter.Emitter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONObject

class BusinessHandler {

    private lateinit var businessViewModel: BusinessViewModel
    val repository = BusinessRepository()
    val gson = Gson()
    var allBusiness : ArrayList<Business> = arrayListOf()
    var activity : AppCompatActivity = UtilityActivity()
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
                    businessViewModel.insertDatabase(business)
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
                repository.businessLiveData.postValue(business)
                businessViewModel.insertDatabase(business)
            }
        }
    }

}