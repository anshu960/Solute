package com.friendly.framework.socket.repository

import androidx.lifecycle.MutableLiveData
enum class CONNECTION_STATUS{
    CONNECTING,CONNECTED,OFFLINE,FAILED,DISCONNECTED
}
class SocketRepository {
    val socketConnectionStatus = MutableLiveData<CONNECTION_STATUS>()
    val isCrashed = MutableLiveData<Boolean>()
}