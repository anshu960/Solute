package com.utilitykit.feature.productCategory.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.utilitykit.Constants.Key
import com.utilitykit.database.DatabaseHandler
import com.utilitykit.socket.SocketEvent
import com.utilitykit.dataclass.User
import com.utilitykit.feature.business.handler.BusinessHandler
import com.utilitykit.feature.productCategory.model.ProductCategory
import com.utilitykit.feature.productCategory.repository.ProductCategoryRepository
import com.utilitykit.socket.SocketService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject

class ProductCategoryViewModel(private val productCategoryRepository: ProductCategoryRepository) :
    ViewModel() {
    init {
        viewModelScope.launch(Dispatchers.IO) {

        }
    }

    val allCategory: LiveData<List<ProductCategory>>
        get() = productCategoryRepository.allCategory

    val selectedCategory : LiveData<ProductCategory>
        get() = productCategoryRepository.selectedCategory

    fun loadCategory(){
        if(!BusinessHandler.shared().repository.business.value?.Id.isNullOrEmpty()){
            val businessId = BusinessHandler.shared().repository.business.value!!.Id
            DatabaseHandler.shared().database.productCategoryDao().getAllItemsForBusiness(businessId).observe(BusinessHandler.shared().activity){
                productCategoryRepository.categoryLiveData.postValue(it as ArrayList<ProductCategory>?)
            }
        }
    }

    fun createNewCategory(name:String) {
        val request = JSONObject()
        val user = User()
        val business = BusinessHandler.shared().repository.business
        request.put(Key.userId, user._id)
        request.put(Key.businessID, business.value?.Id)
        request.put(Key.name, name)
        SocketService.shared().send(SocketEvent.CREATE_PRODUCT_CATEGORY, request)
    }

    fun insert(category : ProductCategory){
        viewModelScope.launch{
            DatabaseHandler.shared().database.productCategoryDao()
                .insert(category)
        }
    }
}