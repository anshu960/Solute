package com.utilitykit.feature.business.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.utilitykit.feature.business.model.Business
import com.utilitykit.feature.business.repository.BusinessRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BusinessViewModel (private val bussinessRepository: BusinessRepository):ViewModel(){
    init {
        viewModelScope.launch (Dispatchers.IO){

        }
    }
    val allBusiness : LiveData<Business>
    get() = bussinessRepository.allBusiness
}