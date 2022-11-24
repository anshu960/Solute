package com.utilitykit.feature.productSubCategory.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.utilitykit.Constants.Key
import com.utilitykit.UtilityKitApp
import com.utilitykit.socket.SocketEvent
import com.utilitykit.dataclass.User
import com.utilitykit.feature.business.handler.BusinessHandler
import com.utilitykit.feature.productCategory.model.ProductCategory
import com.utilitykit.feature.productSubCategory.model.ProductSubCategory
import com.utilitykit.feature.productSubCategory.repository.ProductSubCategoryRepository
import com.utilitykit.socket.SocketService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject

class ProductSubCategoryViewModel(private val productSubCategoryRepository: ProductSubCategoryRepository) :
    ViewModel() {
    init {
        viewModelScope.launch(Dispatchers.IO) {

        }
    }

    val allSubCategory: LiveData<List<ProductSubCategory>>
        get() = productSubCategoryRepository.allSubCategory
    val selectedSubCategory : LiveData<ProductSubCategory>
        get() = productSubCategoryRepository.selectedSubCategory

    fun loadSubCategory(){
        if(!BusinessHandler.shared().repository.business?.Id.isNullOrEmpty()){
            val businessId = BusinessHandler.shared().repository.business!!.Id
            UtilityKitApp.applicationContext().database.productSubCategoryDao().getAllItemsForBusiness(businessId).observe(BusinessHandler.shared().activity){
                productSubCategoryRepository.subCategoryLiveData.postValue(it as ArrayList<ProductSubCategory>?)
            }
        }
    }

    fun createNewSubCategory(name:String,category:ProductCategory) {
        val request = JSONObject()
        val user = User()
        val business = BusinessHandler.shared().repository.business
        request.put(Key.userId, user._id)
        request.put(Key.businessID, business!!.Id)
        request.put(Key.categoryId,category.Id)
        request.put(Key.name, name)
        SocketService.shared().send(SocketEvent.CREATE_PRODUCT_SUB_CATEGORY, request)
    }
    fun insert(category : ProductSubCategory){
        viewModelScope.launch{
            UtilityKitApp.applicationContext().database.productSubCategoryDao()
                .insert(category)
        }
    }
}