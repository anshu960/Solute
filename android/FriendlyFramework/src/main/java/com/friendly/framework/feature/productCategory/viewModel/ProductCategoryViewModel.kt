package com.friendly.framework.feature.productCategory.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.friendly.framework.constants.KeyConstant
import com.friendly.framework.database.DatabaseHandler
import com.friendly.framework.dataclass.FriendlyUser
import com.friendly.framework.feature.business.handler.BusinessHandler
import com.friendly.framework.feature.productCategory.model.ProductCategory
import com.friendly.framework.feature.productCategory.repository.ProductCategoryRepository
import com.friendly.framework.socket.SocketEvent
import com.friendly.framework.socket.SocketService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.json.JSONObject

class ProductCategoryViewModel(private val productCategoryRepository: ProductCategoryRepository) :
    ViewModel() {

    val allCategory: LiveData<List<ProductCategory>>
        get() = productCategoryRepository.allCategory

    val selectedCategory : LiveData<ProductCategory>
        get() = productCategoryRepository.selectedCategory

    fun loadCategory(){
        if(!BusinessHandler.shared().repository.business.value?.Id.isNullOrEmpty()){
            CoroutineScope(Job() + Dispatchers.IO).launch {
                val businessId = BusinessHandler.shared().repository.business.value!!.Id
                productCategoryRepository.categoryLiveData.postValue(DatabaseHandler.shared().database.productCategoryDao().getAllItemsForBusiness(businessId) as ArrayList<ProductCategory>?)
            }
        }
    }

    fun createNewCategory(name:String) {
        val request = JSONObject()
        val user = FriendlyUser()
        val business = BusinessHandler.shared().repository.business
        request.put(KeyConstant.userId, user._id)
        request.put(KeyConstant.businessID, business.value?.Id)
        request.put(KeyConstant.name, name)
        SocketService.shared().send(SocketEvent.CREATE_PRODUCT_CATEGORY, request)
    }

    fun insert(category : ProductCategory){
        CoroutineScope(Job() + Dispatchers.IO).launch {
            DatabaseHandler.shared().database.productCategoryDao()
                .insert(category)
        }
    }
}