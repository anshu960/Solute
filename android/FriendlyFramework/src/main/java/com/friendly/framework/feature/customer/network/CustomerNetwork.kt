package com.friendly.framework.feature.customer.network

import com.friendly.framework.constants.Server
import com.friendly.framework.feature.customer.event.CustomerEvent
import com.friendly.framework.feature.customer.handler.CustomerHandler
import com.friendly.framework.network.FriendlyNetwork
import com.friendly.framework.socket.SocketService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class CustomerNetwork {
    init {
        instance = this
    }


    companion object{
        private var instance: CustomerNetwork? = null
        fun shared() : CustomerNetwork {
            if(instance != null){
                return instance as CustomerNetwork
            }else{
                return CustomerNetwork()
            }
        }
    }

    fun<T> buildService(service: Class<T>): T{
        return FriendlyNetwork.shared().retrofit.create(service)
    }
    fun connectScoket(){
        SocketService.shared().mSocket?.on(CustomerEvent.CREATE.value, CustomerHandler.shared().onCreateCustomer)
        SocketService.shared().mSocket?.on(CustomerEvent.RETRIEVE.value, CustomerHandler.shared().onFetchAllCustomer)
    }

    fun disconnectSocket(){
        SocketService.shared().mSocket?.off(CustomerEvent.CREATE.value)
        SocketService.shared().mSocket?.off(CustomerEvent.RETRIEVE.value)
    }

}