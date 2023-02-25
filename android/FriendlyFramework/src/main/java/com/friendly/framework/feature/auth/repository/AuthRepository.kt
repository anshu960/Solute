package com.friendly.framework.feature.business.repository

import androidx.lifecycle.MutableLiveData
import com.friendly.framework.feature.business.model.Auth

class AuthRepository {
    val authLiveData = MutableLiveData<Auth>()
    val auth: MutableLiveData<Auth>
        get() = authLiveData

    val allAuthLiveData = MutableLiveData<ArrayList<Auth>>()
    val allAuth: MutableLiveData<ArrayList<Auth>>
        get() = allAuthLiveData
}