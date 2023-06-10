package com.friendly.framework.feature.cart.handler

import com.friendly.framework.UtilityActivity
import com.friendly.framework.feature.cart.repository.CartRepository
import com.friendly.framework.feature.cart.viewModel.CartViewModel
import com.friendly.framework.feature.product.handler.ProductHandler
import com.friendly.framework.feature.product.model.Product
import com.google.gson.Gson
import org.json.JSONObject

class CartHandler {

    var viewModel: CartViewModel? = null
    val repository = CartRepository()
    var activity = UtilityActivity()
    val gson = Gson()
    init {
        instance = this
    }
    companion object{
        private var instance: CartHandler? = null
        fun shared() : CartHandler {
            if(instance != null){
                return instance as CartHandler
            }else{
                return CartHandler()
            }
        }
    }

    fun setup(model:CartViewModel){
        viewModel = model
    }

    fun addToCart(product: Product){
        var allCartItem = repository.cartLiveData.value
        if (allCartItem != null){
            if(allCartItem.has(product.Id)){
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

    fun addToCart(product: Product,quantity:Int){
        var allCartItem = repository.cartLiveData.value
        if (allCartItem != null){
            if(allCartItem.has(product.Id)){
                var existingQuantity = allCartItem.getInt(product.Id)
                existingQuantity+=quantity
                allCartItem.put(product.Id,quantity)
            }else{
                allCartItem.put(product.Id,quantity)
            }
            repository.cartLiveData.postValue(allCartItem)
        }else{
            val newCart = JSONObject()
            newCart.put(product.Id,quantity)
            repository.cartLiveData.postValue(newCart)
        }
        viewModel?.updateCount()
        viewModel?.updateProductsInCart()
    }

    fun addToCart(barCode: String,callBack:(message:String)->Unit){
        ProductHandler.shared().viewModel?.findProductByBarCode(barCode){ prd : Product?->
            prd?.let {
                addToCart(it)
                callBack("Added to cart")
            }
        }
    }

    fun removeFromCart(product: Product){
        var allCartItem = repository.cartLiveData.value
        if (allCartItem != null){
            if(allCartItem.has(product.Id)){
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