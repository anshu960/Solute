package com.utilitykit.feature.cart.helper

import com.google.gson.Gson
import com.utilitykit.Constants.Key
import com.utilitykit.SocketUtill.SocketEvent
import com.utilitykit.SocketUtill.SocketManager
import com.utilitykit.dataclass.User
import com.utilitykit.feature.business.handler.BusinessHandler
import com.utilitykit.feature.cart.viewModel.CartViewModel
import com.utilitykit.feature.product.handler.ProductHandler
import com.utilitykit.feature.product.model.Product
import com.utilitykit.feature.product.repository.CartRepository
import com.utilitykit.feature.product.repository.ProductRepository
import com.utilitykit.feature.product.viewModel.ProductViewModel
import io.socket.emitter.Emitter
import org.json.JSONObject

class CartHelper {

    var viewModel: CartViewModel? = null
    val repository = CartRepository()
    val gson = Gson()
    init {
        instance = this
    }
    companion object{
        private var instance: CartHelper? = null
        fun shared() : CartHelper {
            if(instance != null){
                return instance as CartHelper
            }else{
                return CartHelper()
            }
        }
    }

    fun setup(model:CartViewModel){
        viewModel = model
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
        viewModel?.updateCount()
        viewModel?.updateProductsInCart()
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
        viewModel?.updateCount()
        viewModel?.updateProductsInCart()
    }
}