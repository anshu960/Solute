package com.utilitykit.feature.cart.model

import com.google.gson.annotations.SerializedName
import com.utilitykit.feature.product.model.Product
import org.json.JSONObject

data class Cart(
   var products : ArrayList<Product> = arrayListOf(),
   var productQuantity : JSONObject = JSONObject(),
   var count : Int = 0
)