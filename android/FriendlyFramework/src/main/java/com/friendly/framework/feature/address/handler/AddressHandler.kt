package com.friendly.framework.feature.address.handler

import com.friendly.framework.UtilityActivity
import com.friendly.framework.constants.KeyConstant
import com.friendly.framework.feature.address.model.Address
import com.friendly.framework.feature.address.repository.AddressRepository
import com.friendly.framework.feature.address.viewModel.AddressViewModel
import com.google.gson.Gson
import io.socket.emitter.Emitter
import org.json.JSONObject

class AddressHandler{


    var viewModel: AddressViewModel? = null
    val repository = AddressRepository()
    var activity = UtilityActivity()
    var onCreateResponse : ((address: Address)->Unit)? = null
    var onUpdateResponse : ((address: Address)->Unit)? = null
    var onDeleteResponse : ((address: Address)->Unit)? = null
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
            if (anyData.has(KeyConstant.payload)){
                val payload = anyData.getJSONArray(KeyConstant.payload)
                val allModalObject : ArrayList<Address> = arrayListOf()
                for (i in 0 until payload.length())
                {
                    val item = payload.getJSONObject(i)
                    val modelObject = gson.fromJson(item.toString(), Address::class.java)
                    allModalObject.add(modelObject)
                    viewModel?.insert(modelObject)
                }
            }
        }
    }

    val onCreate = Emitter.Listener {
        if (it.isNotEmpty())
        {
            val anyData = it.first() as JSONObject
            if (anyData.has(KeyConstant.payload)){
                val payload = anyData.getJSONObject(KeyConstant.payload)
                val modelObject = gson.fromJson(payload.toString(),Address::class.java)
                viewModel?.insert(modelObject)
                onCreateResponse?.let { it1 -> it1(modelObject) }
            }
        }
    }

    val onUpdate = Emitter.Listener {
        if (it.isNotEmpty())
        {
            val anyData = it.first() as JSONObject
            if (anyData.has(KeyConstant.payload)){
                val payload = anyData.getJSONObject(KeyConstant.payload)
                val modelObject = gson.fromJson(payload.toString(),Address::class.java)
                viewModel?.insert(modelObject)
                onUpdateResponse?.let { it1 -> it1(modelObject) }
            }
        }
    }

    val onDelete = Emitter.Listener {
        if (it.isNotEmpty())
        {
            val anyData = it.first() as JSONObject
            if (anyData.has(KeyConstant.payload)){
                val payload = anyData.getJSONObject(KeyConstant.payload)
                val modelObject = gson.fromJson(payload.toString(),Address::class.java)
                viewModel?.delete(modelObject)
                onDeleteResponse?.let { it1 -> it1(modelObject) }
            }
        }
    }

}
