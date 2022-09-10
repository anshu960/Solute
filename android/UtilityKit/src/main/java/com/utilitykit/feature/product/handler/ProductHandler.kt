package com.utilitykit.feature.product.handler

import com.google.gson.Gson
import com.utilitykit.Constants.Key
import com.utilitykit.SocketUtill.SocketEvent
import com.utilitykit.SocketUtill.SocketManager
import com.utilitykit.dataclass.User
import com.utilitykit.feature.business.handler.BusinessHandler
import com.utilitykit.feature.product.model.Product
import com.utilitykit.feature.product.repository.ProductRepository
import com.utilitykit.feature.product.viewModel.ProductViewModel
import io.socket.emitter.Emitter
import org.json.JSONObject

class ProductHandler {

    private lateinit var productViewModel: ProductViewModel
    val repository = ProductRepository()
    val gson = Gson()
    var allProduct : ArrayList<Product> = arrayListOf()
    init {
        instance = this
    }
    companion object{
        private var instance: ProductHandler? = null
        fun shared() : ProductHandler {
            if(instance != null){
                return instance as ProductHandler
            }else{
                return ProductHandler()
            }
        }
    }

    fun setup(model:ProductViewModel){
        productViewModel = model
    }

    fun addToCart(product: Product){
        var allCartItem = repository.cartLiveData.value
        if (allCartItem != null){
            if(allCartItem!!.has(product.Id)){
                var quantity = allCartItem.getInt(product.Id)
                quantity+=1
                allCartItem.put(product.Id,quantity)
            }else{
                allCartItem.put(product.Id,1)
            }
            repository.cartLiveData.postValue(allCartItem)
        }else{
            val newCart = JSONObject()
            newCart.put(product.Id,1)
            repository.cartLiveData.postValue(newCart)
        }
    }

    fun removeFromCart(product: Product){
        var allCartItem = repository.cartLiveData.value
        if (allCartItem != null){
            if(allCartItem!!.has(product.Id)){
                var quantity = allCartItem.getInt(product.Id)
                quantity-=1
                if(quantity<1){
                 allCartItem.remove(product.Id)
                }else{
                    allCartItem.put(product.Id,quantity)
                }
            }
            repository.cartLiveData.postValue(allCartItem)
        }
        repository.updateCount()
    }

    fun fetchAllProduct(){
        val request = JSONObject()
        val user = User()
        val business = BusinessHandler.shared().repository.business
        request.put(Key.userId,user._id)

        request.put(Key.businessID,business!!.Id)
        SocketManager.send(SocketEvent.RETRIVE_PRODUCT,request)
    }

     val retriveProduct = Emitter.Listener {
        if (it.isNotEmpty())
        {
            val anyData = it.first() as JSONObject
            if (anyData.has(Key.payload)){
                val payload = anyData.getJSONArray(Key.payload)
                allProduct = arrayListOf()
                for (i in 0 until payload.length())
                {
                    val item = payload.getJSONObject(i)
                    val product = gson.fromJson(item.toString(),Product::class.java)
                    allProduct.add(product)
                    repository.productLiveData.postValue(product)
                }
            }
        }
    }

}