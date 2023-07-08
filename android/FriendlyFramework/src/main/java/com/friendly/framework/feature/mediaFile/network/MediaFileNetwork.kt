package com.friendly.framework.feature.mediaFile.network

import com.friendly.framework.constants.KeyConstant
import com.friendly.framework.feature.customer.network.CustomerNetwork
import com.friendly.framework.feature.mediaFile.event.MediaFileEvent
import com.friendly.framework.feature.mediaFile.handler.MediaFileHandler
import com.friendly.framework.feature.product.network.ProductNetworkInterface
import com.friendly.framework.network.ApiResponseCallBack
import com.friendly.framework.socket.SocketService
import okhttp3.MediaType
import okhttp3.RequestBody
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MediaFileNetwork {
    init {
        instance = this
    }
    companion object{
        private var instance: MediaFileNetwork? = null
        fun shared() : MediaFileNetwork {
            if(instance != null){
                return instance as MediaFileNetwork
            }else{
                return MediaFileNetwork()
            }
        }
    }
    fun connectScoket(){
        SocketService.shared().mSocket?.on(MediaFileEvent.CREATE, MediaFileHandler.shared().onCreate)
        SocketService.shared().mSocket?.on(MediaFileEvent.RETRIEVE, MediaFileHandler.shared().onRetrieve)
    }

    fun disconnectSocket(){
        SocketService.shared().mSocket?.off(MediaFileEvent.CREATE)
        SocketService.shared().mSocket?.off(MediaFileEvent.RETRIEVE)
    }

    fun retrieve(request: JSONObject, onResult: (JSONObject?) -> Unit){
        val retrofit = CustomerNetwork.shared().buildService(MediaFileNetworkInterface::class.java)
        val bodyRequest: RequestBody =
            RequestBody.create(MediaType.parse("application/json"), request.toString())
        val callBack = ApiResponseCallBack()
        callBack.onResult={payload->
            onResult(payload as JSONObject)
        }
        callBack.onError={msg->
            onResult(null)
        }
        retrofit.retrieve(bodyRequest).enqueue(callBack.callback)
    }
}
