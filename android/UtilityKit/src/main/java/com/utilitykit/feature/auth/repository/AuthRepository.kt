package com.utilitykit.feature.business.repository

import androidx.lifecycle.MutableLiveData
import com.utilitykit.feature.business.model.Auth
import com.utilitykit.feature.business.model.Business
import com.utilitykit.feature.sync.BusinessAnalytics

class AuthRepository {
    val authLiveData = MutableLiveData<Auth>()
    val auth: MutableLiveData<Auth>
        get() = authLiveData

    val allAuthLiveData = MutableLiveData<ArrayList<Auth>>()
    val allAuth: MutableLiveData<ArrayList<Auth>>
        get() = allAuthLiveData
}