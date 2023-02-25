package com.friendly.framework.feature.productSubCategory.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.friendly.framework.constants.KeyConstant
import com.friendly.framework.database.DatabaseHandler
import com.friendly.framework.dataclass.FriendlyUser
import com.friendly.framework.feature.business.handler.BusinessHandler
import com.friendly.framework.feature.productCategory.model.ProductCategory
import com.friendly.framework.feature.productSubCategory.model.ProductSubCategory
import com.friendly.framework.feature.productSubCategory.repository.ProductSubCategoryRepository
import com.friendly.framework.socket.SocketEvent
import com.friendly.framework.socket.SocketService
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
        if(!BusinessHandler.shared().repository.business.value?.Id.isNullOrEmpty()){
            val businessId = BusinessHandler.shared().repository.business.value!!.Id
            DatabaseHandler.shared().database.productSubCategoryDao().getAllItemsForBusiness(businessId).observe(BusinessHandler.shared().activity){
                productSubCategoryRepository.subCategoryLiveData.postValue(it as ArrayList<ProductSubCategory>?)
            }
        }
    }

    fun loadSubCategory(category: ProductCategory){
        if(!BusinessHandler.shared().repository.business.value?.Id.isNullOrEmpty()){
            DatabaseHandler.shared().database.productSubCategoryDao().getAllItemsForCategory(category.Id).observe(BusinessHandler.shared().activity){
                productSubCategoryRepository.subCategoryLiveData.postValue(it as ArrayList<ProductSubCategory>?)
            }
        }
    }

    fun createNewSubCategory(name:String,category:ProductCategory) {
        val request = JSONObject()
        val user = FriendlyUser()
        val business = BusinessHandler.shared().repository.business
        request.put(KeyConstant.userId, user._id)
        request.put(KeyConstant.businessID, business.value?.Id)
        request.put(KeyConstant.categoryId,category.Id)
        request.put(KeyConstant.name, name)
        SocketService.shared().send(SocketEvent.CREATE_PRODUCT_SUB_CATEGORY, request)
    }
    fun insert(category : ProductSubCategory){
        viewModelScope.launch{
            DatabaseHandler.shared().database.productSubCategoryDao()
                .insert(category)
        }
    }
}