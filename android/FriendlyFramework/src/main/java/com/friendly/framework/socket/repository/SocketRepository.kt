package com.friendly.framework.socket.repository

import androidx.lifecycle.MutableLiveData

class SocketRepository {
    val socketConnectionStatus = MutableLiveData<Int>()
    val isCrashed = MutableLiveData<Boolean>()
}