package com.utilitykit.feature.address.handler

import com.google.gson.Gson
import com.utilitykit.Constants.Key
import com.utilitykit.UtilityActivity
import com.utilitykit.feature.mediaFile.model.MediaFile
import com.utilitykit.feature.mediaFile.repository.MediaFileRepository
import com.utilitykit.feature.mediaFile.viewModel.MediaFileViewModel
import io.socket.emitter.Emitter
import org.json.JSONObject

class AddressHandler{


    var viewModel: MediaFileViewModel? = null
    val repository = MediaFileRepository()
    var activity = UtilityActivity()
    var onCreateNew : ((address: MediaFile)->Unit)? = null
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

    fun setup(model: MediaFileViewModel){
        viewModel = model
    }

     val onRetrieve = Emitter.Listener {
        if (it.isNotEmpty())
        {
            val anyData = it.first() as JSONObject
            if (anyData.has(Key.payload)){
                val payload = anyData.getJSONArray(Key.payload)
                for (i in 0 until payload.length())
                {
                    val item = payload.getJSONObject(i)
                    val modelObject = gson.fromJson(item.toString(),MediaFile::class.java)
                    viewModel?.insert(modelObject)
                }
            }
        }
    }

    val onCreate = Emitter.Listener {
        if (it.isNotEmpty())
        {
            val anyData = it.first() as JSONObject
            if (anyData.has(Key.payload)){
                val payload = anyData.getJSONObject(Key.payload)
                val modelObject = gson.fromJson(payload.toString(),MediaFile::class.java)
                viewModel?.insert(modelObject)
                onCreateNew?.let { it1 -> it1(modelObject) }
            }
        }
    }

}
