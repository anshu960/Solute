package com.utilitykit.feature.business.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.utilitykit.feature.business.model.Business
import com.utilitykit.feature.business.repository.BusinessRepository
import com.utilitykit.feature.sync.BusinessAnalytics
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BusinessViewModel (private val bussinessRepository: BusinessRepository):ViewModel(){
    init {
        viewModelScope.launch (Dispatchers.IO){

        }
    }
    val analytics : LiveData<ArrayList<BusinessAnalytics>>
        get() = bussinessRepository.analytics

    val allBusiness : LiveData<Business>
    get() = bussinessRepository.allBusiness
}