package com.utilitykit.feature.cart.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.utilitykit.feature.product.handler.ProductHandler
import com.utilitykit.feature.product.model.Product
import com.utilitykit.feature.product.repository.CartRepository
import com.utilitykit.feature.product.repository.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject

class CartViewModel (private val cartRepository: CartRepository):ViewModel(){
    init {
        viewModelScope.launch (Dispatchers.IO){

        }
    }

    val cartProducts : LiveData<List<Product>>
        get() = cartRepository.cartProducts

    val cart : LiveData<JSONObject>
        get() = cartRepository.cart

    val cartCount : LiveData<Int>
    get()=cartRepository.cartCount

    fun updateProductsInCart(){
        var productsAddedToCart : ArrayList<Product> = arrayListOf()
        ProductHandler.shared().repository.allProduct.value?.forEach {
            if(cart.value?.has(it.Id) == true){
                productsAddedToCart.add(it)
            }
        }
        cartRepository.cartProductLiveData.postValue(productsAddedToCart)
    }

    fun updateCount(){
        var count = 0
        cart.value?.keys()?.forEach {
            count+=1
        }
        cartRepository.cartCountLiveData.postValue(count)
    }
}