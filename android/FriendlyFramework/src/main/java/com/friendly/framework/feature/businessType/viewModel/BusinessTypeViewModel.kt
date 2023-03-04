package com.friendly.framework.feature.businessType.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.friendly.framework.feature.businessType.model.BusinessType
import com.friendly.framework.feature.businessType.repository.BusinessTypeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BusinessTypeViewModel (private val bussinessTypeRepository: BusinessTypeRepository):ViewModel(){

    val allBusinessType : LiveData<ArrayList<BusinessType>>
    get() = bussinessTypeRepository.allBusinessType
}