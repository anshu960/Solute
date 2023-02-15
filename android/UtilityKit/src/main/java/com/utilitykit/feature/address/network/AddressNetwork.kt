package com.utilitykit.feature.address.network

import com.utilitykit.feature.address.event.AddressEvent
import com.utilitykit.feature.address.handler.AddressHandler
import com.utilitykit.socket.SocketService

class AddressNetwork {
    init {
        instance = this
    }
    companion object{
        private var instance:AddressNetwork ? = null
        fun shared() : AddressNetwork  {
            if(instance != null){
                return instance as AddressNetwork
            }else{
                return AddressNetwork()
            }
        }
    }
    fun connectScoket(){
        SocketService.shared().mSocket?.on(AddressEvent.CREATE.value, AddressHandler.shared().onCreate)
        SocketService.shared().mSocket?.on(AddressEvent.RETRIEVE.value,AddressHandler.shared().onRetrieve)
    }

    fun disconnectSocket(){
        SocketService.shared().mSocket?.off(AddressEvent.CREATE.value)
        SocketService.shared().mSocket?.off(AddressEvent.RETRIEVE.value)
    }
}
