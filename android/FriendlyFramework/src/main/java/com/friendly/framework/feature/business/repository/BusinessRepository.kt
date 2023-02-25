package com.friendly.framework.feature.business.repository

import androidx.lifecycle.MutableLiveData
import com.friendly.framework.feature.business.model.Business
import com.friendly.framework.feature.sync.BusinessAnalytics

class BusinessRepository {
    val analyticsLiveData = MutableLiveData<ArrayList<BusinessAnalytics>>()
    val analytics : MutableLiveData<ArrayList<BusinessAnalytics>>
    get() = analyticsLiveData

    val businessLiveData = MutableLiveData<Business>()
    val business: MutableLiveData<Business>
        get() = businessLiveData

    val allBusinessLiveData = MutableLiveData<ArrayList<Business>>()
    val allBusiness : MutableLiveData<ArrayList<Business>>
        get() = allBusinessLiveData
}