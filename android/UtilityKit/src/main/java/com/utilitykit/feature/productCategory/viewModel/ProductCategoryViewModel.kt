package com.utilitykit.feature.productCategory.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.utilitykit.Constants.Key
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

    fun createNewCategory(name:String) {
        val request = JSONObject()
        val user = User()
        val business = BusinessHandler.shared().repository.business
        request.put(Key.userId, user._id)
        request.put(Key.businessID, business!!.Id)
        request.put(Key.name, name)
        SocketService.shared().send(SocketEvent.CREATE_PRODUCT_CATEGORY, request)
    }
}