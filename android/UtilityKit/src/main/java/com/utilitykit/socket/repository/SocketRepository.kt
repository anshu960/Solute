package com.utilitykit.socket.repository

import androidx.lifecycle.MutableLiveData
import com.utilitykit.feature.business.model.Business

class SocketRepository {
    val socketConnectionStatus = MutableLiveData<Int>()
    val isCrashed = MutableLiveData<Boolean>()
}