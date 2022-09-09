package com.utilitykit.feature.business.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.utilitykit.feature.business.model.Business

class BusinessRepository {
    private val businessLiveData = MutableLiveData<Business>()
    val allBusiness : LiveData<Business>
    get() = businessLiveData

    fun retriveAllBusiness(){

    }
}