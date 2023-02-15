package com.utilitykit.feature.address.handler

import com.google.gson.Gson
import com.utilitykit.Constants.Key
import com.utilitykit.UtilityActivity
import com.utilitykit.feature.address.model.Address
import com.utilitykit.feature.address.repository.AddressRepository
import com.utilitykit.feature.address.viewModel.AddressViewModel
import io.socket.emitter.Emitter
import org.json.JSONObject

class AddressHandler{


    var viewModel: AddressViewModel? = null
    val repository = AddressRepository()
    var activity = UtilityActivity()
    var onCreateNewAddress : ((address: Address)->Unit)? = null
    val gson = Gson()
    init {
        instance = this
    }
    companion object{
        private var instance: AddressHandler? = null
        fun shared() : AddressHandler {
            if(instance != null){
                return instance as AddressHandler
            }else{
                return AddressHandler()
            }
        }
    }

    fun setup(model: AddressViewModel){
        viewModel = model
    }

     val onRetrieve = Emitter.Listener {
        if (it.isNotEmpty())
        {
            val anyData = it.first() as JSONObject
            if (anyData.has(Key.payload)){
                val payload = anyData.getJSONArray(Key.payload)
                val allModalObject : ArrayList<Address> = arrayListOf()
                for (i in 0 until payload.length())
                {
                    val item = payload.getJSONObject(i)
                    val modelObject = gson.fromJson(item.toString(), Address::class.java)
                    allModalObject.add(modelObject)
                    viewModel?.insert(modelObject)
                }
                repository.liveData?.postValue(allModalObject)
            }
        }
    }

    val onCreate = Emitter.Listener {
        if (it.isNotEmpty())
        {
            val anyData = it.first() as JSONObject
            if (anyData.has(Key.payload)){
                val payload = anyData.getJSONObject(Key.payload)
                val modelObject = gson.fromJson(payload.toString(),Address::class.java)
                viewModel?.insert(modelObject)
                onCreateNewAddress?.let { it1 -> it1(modelObject) }
            }
        }
    }

}
