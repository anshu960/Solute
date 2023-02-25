package com.friendly.framework.feature.cart.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.friendly.framework.feature.product.model.Product
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

    val mrpLiveData = MutableLiveData<Float>()
    val mrp : LiveData<Float>
        get() = mrpLiveData

    val discountLiveData = MutableLiveData<Float>()
    val discount : LiveData<Float>
    get() = discountLiveData

    val subtotalLiveData = MutableLiveData<Float>()
    val subtotal : LiveData<Float>
        get() = subtotalLiveData

    val taxLiveData = MutableLiveData<Float>()
    val tax : LiveData<Float>
        get() = taxLiveData

    val instantDiscountLiveData = MutableLiveData<Float>()
    val instantDiscount : LiveData<Float>
        get() = instantDiscountLiveData

    val totalAmountLiveData = MutableLiveData<Float>()
    val totalAmount : LiveData<Float>
        get() = totalAmountLiveData




}