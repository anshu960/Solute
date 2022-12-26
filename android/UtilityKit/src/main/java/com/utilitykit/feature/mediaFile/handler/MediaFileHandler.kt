package com.utilitykit.feature.mediaFile.handler

import android.provider.MediaStore.Audio.Media
import com.google.gson.Gson
import com.utilitykit.Constants.Key
import com.utilitykit.Constants.Key.Companion.category
import com.utilitykit.socket.SocketEvent
import com.utilitykit.UtilityActivity
import com.utilitykit.dataclass.User
import com.utilitykit.feature.business.handler.AuthHandler
import com.utilitykit.feature.business.handler.BusinessHandler
import com.utilitykit.feature.mediaFile.model.MediaFile
import com.utilitykit.feature.mediaFile.repository.MediaFileRepository
import com.utilitykit.feature.mediaFile.viewModel.MediaFileViewModel
import com.utilitykit.feature.productCategory.model.ProductCategory
import com.utilitykit.socket.SocketService
import io.socket.emitter.Emitter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
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