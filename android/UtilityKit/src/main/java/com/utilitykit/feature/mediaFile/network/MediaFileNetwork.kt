package com.utilitykit.feature.mediaFile.network

import com.utilitykit.feature.mediaFile.event.MediaFileEvent
import com.utilitykit.feature.mediaFile.handler.MediaFileHandler
import com.utilitykit.socket.SocketService

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
        SocketService.shared().mSocket?.on(MediaFileEvent.CREATE.value, MediaFileHandler.shared().onCreate)
        SocketService.shared().mSocket?.on(MediaFileEvent.RETRIEVE.value, MediaFileHandler.shared().onRetrieve)
    }

    fun disconnectSocket(){
        SocketService.shared().mSocket?.off(MediaFileEvent.CREATE.value)
        SocketService.shared().mSocket?.off(MediaFileEvent.RETRIEVE.value)
    }
}