package com.friendly.framework.feature.business.network


import com.friendly.framework.constants.KeyConstant
import com.friendly.framework.feature.business.event.Event
import com.friendly.framework.feature.business.handler.BusinessHandler
import com.friendly.framework.feature.customer.network.CustomerNetwork
import com.friendly.framework.feature.mediaFile.network.MediaFileNetworkInterface
import com.friendly.framework.network.ApiResponseCallBack
import com.friendly.framework.network.FriendlyNetwork
import com.friendly.framework.socket.SocketService
import okhttp3.MediaType
import okhttp3.RequestBody
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Network {
    init {
        instance = this
    }
    companion object{
        private var instance: Network? = null
        fun shared() : Network {
            if(instance != null){
                return instance as Network
            }else{
                return Network()
            }
        }
    }
    private val retrofit = FriendlyNetwork.shared().buildService(NetworkInterface::class.java)

    fun connectSocket(){
        SocketService.shared().mSocket?.on(Event.CREATE.value, BusinessHandler.shared().onCreateNewBusiness)
        SocketService.shared().mSocket?.on(Event.RETRIEVE.value, BusinessHandler.shared().retriveBusiness)
    }

    fun disconnectSocket(){
        SocketService.shared().mSocket?.off(Event.CREATE.value)
        SocketService.shared().mSocket?.off(Event.RETRIEVE.value)
    }


    fun updateAddress(request: JSONObject, onResult: (JSONObject?) -> Unit,onError: (String?) -> Unit){
            val retrofit = CustomerNetwork.shared().buildService(NetworkInterface::class.java)
            val bodyRequest: RequestBody =
                RequestBody.create(MediaType.parse("application/json"), request.toString())
            val callBack = ApiResponseCallBack()
            callBack.onResult={payload->
                onResult(payload as JSONObject)
            }
            callBack.onError={msg->
                onError(msg)
            }
            retrofit.updateAddress(bodyRequest).enqueue(callBack.callback)
    }
    fun update(request: JSONObject, onResult: (JSONObject?) -> Unit,onError: (String?) -> Unit){
        val retrofit = CustomerNetwork.shared().buildService(NetworkInterface::class.java)
        val bodyRequest: RequestBody =
            RequestBody.create(MediaType.parse("application/json"), request.toString())
        val callBack = ApiResponseCallBack()
        callBack.onResult={payload->
            onResult(payload as JSONObject)
        }
        callBack.onError={msg->
            onError(msg)
        }
        retrofit.updateBusiness(bodyRequest).enqueue(callBack.callback)
    }
}