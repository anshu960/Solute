package com.friendly.framework.feature.businessType.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.friendly.framework.feature.businessType.model.BusinessType

class BusinessTypeRepository {
    val businessTypeLiveData = MutableLiveData<ArrayList<BusinessType>>()
    var businessType: BusinessType? = null
    val allBusinessType: LiveData<ArrayList<BusinessType>>
        get() = businessTypeLiveData
}