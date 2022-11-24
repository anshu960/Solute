package com.utilitykit.feature.business.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.utilitykit.UtilityKitApp
import com.utilitykit.feature.business.model.Business
import com.utilitykit.feature.sync.BusinessAnalytics

class BusinessRepository {
    val analyticsLiveData = MutableLiveData<ArrayList<BusinessAnalytics>>()
    val analytics : MutableLiveData<ArrayList<BusinessAnalytics>>
    get() = analyticsLiveData

    val businessLiveData = MutableLiveData<Business>()
    var business: Business? = null

    val allBusiness: LiveData<List<Business>>
        get() = UtilityKitApp.applicationContext().database.businessDao().getAllItems()
}