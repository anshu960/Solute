package com.utilitykit.feature.product.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.utilitykit.feature.product.model.Product
import org.json.JSONObject

class ProductRepository {
     val productLiveData = MutableLiveData<Product>()
     val allProduct : LiveData<Product>
     get() = productLiveData

    val cartLiveData = MutableLiveData<JSONObject>()
    val cart : LiveData<JSONObject>
        get() = cartLiveData

    val cartCountLiveData = MutableLiveData<Int>()
    val cartCount : LiveData<Int>
        get()=cartCountLiveData

    fun updateCount(){
        var count = 0
        cart.value?.keys()?.forEach {
            count+=1
        }
        cartCountLiveData.postValue(count)
    }

    fun getProductQuantity(product: Product):Int{
        var quanity = 0
        if(cart.value != null && cart.value!!.has(product.Id)){
            quanity = cart.value!!.getInt(product.Id!!)
        }
        updateCount()
        return quanity
    }

}