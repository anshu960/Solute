package com.friendly.framework.feature.mediaFile.handler

import com.friendly.framework.UtilityActivity
import com.friendly.framework.constants.KeyConstant
import com.friendly.framework.feature.mediaFile.model.MediaFile
import com.friendly.framework.feature.mediaFile.repository.MediaFileRepository
import com.friendly.framework.feature.mediaFile.viewModel.MediaFileViewModel
import com.google.gson.Gson
import io.socket.emitter.Emitter
import org.json.JSONObject

class MediaFileHandler{


    var viewModel: MediaFileViewModel? = null
    val repository = MediaFileRepository()
    var activity = UtilityActivity()
    var onCreateNew : ((category: MediaFile)->Unit)? = null
    val gson = Gson()
    init {
        instance = this
    }
    companion object{
        private var instance: MediaFileHandler? = null
        fun shared() : MediaFileHandler {
            if(instance != null){
                return instance as MediaFileHandler
            }else{
                return MediaFileHandler()
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
            if (anyData.has(KeyConstant.payload)){
                val payload = anyData.getJSONArray(KeyConstant.payload)
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
            if (anyData.has(KeyConstant.payload)){
                val payload = anyData.getJSONObject(KeyConstant.payload)
                val modelObject = gson.fromJson(payload.toString(),MediaFile::class.java)
                viewModel?.insert(modelObject)
                onCreateNew?.let { it1 -> it1(modelObject) }
            }
        }
    }

}