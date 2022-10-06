package com.utilitykit.feature.product.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.utilitykit.Constants.Key
import com.utilitykit.Constants.Key.Companion.business
import com.utilitykit.SocketUtill.SocketEvent
import com.utilitykit.SocketUtill.SocketManager
import com.utilitykit.feature.business.handler.BusinessHandler
import com.utilitykit.feature.product.model.Product
import com.utilitykit.feature.product.repository.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject

class ProductViewModel (private val productRepository: ProductRepository):ViewModel(){
    init {
        viewModelScope.launch (Dispatchers.IO){

        }
    }
    val allProduct : LiveData<ArrayList<Product>>
    get() = productRepository.allProduct

    val newProduct : LiveData<Product>
        get() = productRepository.newProduct

    val selectedProduct : LiveData<Product>
        get() = productRepository.selectedProduct

    fun createNewProduct(request:JSONObject){
            SocketManager.send(SocketEvent.CREATE_PRODUCT,request)
    }

    fun updateProductImage(product:Product,image:String){
        val request = JSONObject()
        if(BusinessHandler.shared().repository.business != null) {
            val business = BusinessHandler.shared().repository.business
            request.put(Key.businessID,business!!.Id)
        }
        request.put(Key.image,image)
        request.put(Key._id,product.Id)
        SocketManager.send(SocketEvent.UPDATE_PRODUCT_IMAGE,request)
    }



}