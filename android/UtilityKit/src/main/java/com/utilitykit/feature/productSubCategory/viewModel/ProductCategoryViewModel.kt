package com.utilitykit.feature.productSubCategory.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.utilitykit.Constants.Key
import com.utilitykit.SocketUtill.SocketEvent
import com.utilitykit.SocketUtill.SocketManager
import com.utilitykit.dataclass.User
import com.utilitykit.feature.business.handler.BusinessHandler
import com.utilitykit.feature.productCategory.model.ProductCategory
import com.utilitykit.feature.productSubCategory.model.ProductSubCategory
import com.utilitykit.feature.productSubCategory.repository.ProductSubCategoryRepository
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

    fun createNewSubCategory(name:String,category:ProductCategory) {
        val request = JSONObject()
        val user = User()
        val business = BusinessHandler.shared().repository.business
        request.put(Key.userId, user._id)
        request.put(Key.businessID, business!!.Id)
        request.put(Key.categoryId,category.Id)
        request.put(Key.name, name)
        SocketManager.send(SocketEvent.CREATE_PRODUCT_SUB_CATEGORY, request)
    }
}