package com.utilitykit.feature.business.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.utilitykit.feature.business.model.Business

class BusinessRepository {
    val businessLiveData = MutableLiveData<Business>()
    var business: Business? = null
    val allBusiness: LiveData<Business>
        get() = businessLiveData
}