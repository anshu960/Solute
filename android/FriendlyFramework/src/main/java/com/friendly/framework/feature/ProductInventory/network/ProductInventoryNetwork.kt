package com.friendly.framework.feature.productInventory.network

import com.friendly.framework.feature.mediaFile.event.MediaFileEvent
import com.friendly.framework.feature.mediaFile.handler.MediaFileHandler
import com.friendly.framework.feature.mediaFile.network.MediaFileNetwork
import com.friendly.framework.socket.SocketService

class ProductInventoryNetwork {
    init {
        instance = this
    }
    companion object{
        private var instance: ProductInventoryNetwork? = null
        fun shared() : ProductInventoryNetwork {
            if(instance != null){
                return instance as ProductInventoryNetwork
            }else{
                return ProductInventoryNetwork()
            }
        }
    }

    fun connectSocket(){
        SocketService.shared().mSocket?.on(ProductInventoryEvent.CREATE.value, MediaFileHandler.shared().onCreate)
        SocketService.shared().mSocket?.on(ProductInventoryEvent.RETRIEVE.value, MediaFileHandler.shared().onRetrieve)
    }

    fun disconnectSocket(){
        SocketService.shared().mSocket?.off(ProductInventoryEvent.CREATE.value)
        SocketService.shared().mSocket?.off(ProductInventoryEvent.RETRIEVE.value)
    }
}