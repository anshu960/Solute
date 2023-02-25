package com.friendly.framework.feature.mediaFile.network

import com.friendly.framework.feature.mediaFile.event.MediaFileEvent
import com.friendly.framework.feature.mediaFile.handler.MediaFileHandler
import com.friendly.framework.socket.SocketService

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