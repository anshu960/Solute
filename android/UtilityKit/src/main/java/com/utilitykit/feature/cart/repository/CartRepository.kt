package com.utilitykit.feature.product.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.utilitykit.feature.product.model.Product
import org.json.JSONObject

class CartRepository {
    val cartProductLiveData = MutableLiveData<List<Product>>()
    val cartProducts : LiveData<List<Product>>
        get() = cartProductLiveData

    val cartLiveData = MutableLiveData<JSONObject>()
    val cart : LiveData<JSONObject>
        get() = cartLiveData

    val cartCountLiveData = MutableLiveData<Int>()
    val cartCount : LiveData<Int>
        get()=cartCountLiveData



    fun getProductQuantity(product: Product):Int{
        var quanity = 0
        if(cart.value != null && cart.value!!.has(product.Id)){
            quanity = cart.value!!.getInt(product.Id!!)
        }
        return quanity
    }

}