package com.utilitykit.feature.product.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.utilitykit.Constants.Key
import com.utilitykit.UtilityKitApp
import com.utilitykit.socket.SocketEvent
import com.utilitykit.dataclass.User
import com.utilitykit.feature.business.handler.BusinessHandler
import com.utilitykit.feature.product.model.Product
import com.utilitykit.feature.product.model.ProductStock
import com.utilitykit.feature.product.repository.ProductRepository
import com.utilitykit.socket.SocketService
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

    fun loadProduct(){
        productRepository.productLiveData.postValue(arrayListOf())
        if(!BusinessHandler.shared().repository.business.value?.Id.isNullOrEmpty()){
            val businessId = BusinessHandler.shared().repository.business.value?.Id
            UtilityKitApp.applicationContext().database.productDao().getProductsFor(businessId!!).observe(BusinessHandler.shared().activity){
                if(!it.isNullOrEmpty()){
                    productRepository.productLiveData.postValue(it as ArrayList<Product>)
                }
            }
        }
    }

    fun createNewProduct(request:JSONObject){
        SocketService.shared().send(SocketEvent.CREATE_PRODUCT,request)
    }
    fun updateProduct(request:JSONObject){
        SocketService.shared().send(SocketEvent.UPDATE_PRODUCT,request)
    }

    fun updateProductImage(product:Product,image:String){
        val user = User()
        val request = JSONObject()
        if(BusinessHandler.shared().repository.business != null) {
            val business = BusinessHandler.shared().repository.business
            request.put(Key.businessID,business.value?.Id)
        }
        request.put(Key.image,image)
        request.put(Key._id,product.Id)
        request.put(Key.userId,user._id)
        SocketService.shared().send(SocketEvent.UPDATE_PRODUCT_IMAGE,request)
    }

    fun deleteProduct(product:Product){
        val user = User()
        val request = JSONObject()
        if(BusinessHandler.shared().repository.business != null) {
            val business = BusinessHandler.shared().repository.business
            request.put(Key.businessID,business.value?.Id)
        }
        request.put(Key.userId,user._id)
        request.put(Key._id,product.Id)
        SocketService.shared().send(SocketEvent.DELETE_PRODUCT,request)
    }

    fun removeStockQuantity(product:Product,quantity:Int,message:String){
        val user = User()
        val request = JSONObject()
        if(BusinessHandler.shared().repository.business != null) {
            val business = BusinessHandler.shared().repository.business
            request.put(Key.businessID,business.value?.Id)
        }
        request.put(Key.productID,product.Id)
        request.put(Key.userId,user._id)
        request.put(Key.quantity,quantity)
        request.put(Key.comment,message)
        SocketService.shared().send(SocketEvent.REMOVE_STOCK_QUANTITY,request)
    }


    fun addStockQuantity(product:Product,quantity:Int,message:String){
        val user = User()
        val request = JSONObject()
        val business = BusinessHandler.shared().repository.business
        request.put(Key.businessID,business.value?.Id)
        request.put(Key.productID,product.Id)
        request.put(Key.userId,user._id)
        request.put(Key.quantity,quantity)
        request.put(Key.comment,message)
        SocketService.shared().send(SocketEvent.ADD_STOCK_QUANTITY,request)
    }
    fun resetStockQuantity(product:Product,quantity:Int,message:String){
        val user = User()
        val request = JSONObject()
        val business = BusinessHandler.shared().repository.business
        request.put(Key.businessID,business.value?.Id)
        request.put(Key.productID,product.Id)
        request.put(Key.userId,user._id)
        request.put(Key.quantity,quantity)
        request.put(Key.comment,message)
        SocketService.shared().send(SocketEvent.RESET_STOCK_QUANTITY,request)
    }

    fun insertStock(stockEntry : ProductStock){
        viewModelScope.launch{
            UtilityKitApp.applicationContext().database.productStockDao()
                .insert(stockEntry)
        }
    }
    fun insertProduct(product : Product){
        viewModelScope.launch{
            UtilityKitApp.applicationContext().database.productDao()
                .insert(product)
        }
    }


}