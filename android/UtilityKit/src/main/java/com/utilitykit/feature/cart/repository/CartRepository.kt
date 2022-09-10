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

    val discountLiveData = MutableLiveData<Int>()
    val discount : LiveData<Int>
    get() = discountLiveData

    val subtotalLiveData = MutableLiveData<Int>()
    val subtotal : LiveData<Int>
        get() = subtotalLiveData

    val taxlLiveData = MutableLiveData<Int>()
    val tax : LiveData<Int>
        get() = taxlLiveData

    val instantDiscountLiveData = MutableLiveData<Int>()
    val instantDiscount : LiveData<Int>
        get() = instantDiscountLiveData

    val totalAmountLiveData = MutableLiveData<Int>()
    val totalAmount : LiveData<Int>
        get() = totalAmountLiveData




}