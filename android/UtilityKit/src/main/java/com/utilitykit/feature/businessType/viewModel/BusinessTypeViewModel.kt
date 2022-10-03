package com.utilitykit.feature.businessType.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.utilitykit.feature.businessType.model.BusinessType
import com.utilitykit.feature.businessType.repository.BusinessTypeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BusinessTypeViewModel (private val bussinessTypeRepository: BusinessTypeRepository):ViewModel(){
    init {
        viewModelScope.launch (Dispatchers.IO){

        }
    }


    val allBusinessType : LiveData<ArrayList<BusinessType>>
    get() = bussinessTypeRepository.allBusinessType
}