package com.friendly.framework.feature.cart.model

import com.friendly.framework.feature.product.model.Product
import org.json.JSONObject

data class Cart(
   var products : ArrayList<Product> = arrayListOf(),
   var productQuantity : JSONObject = JSONObject(),
   var count : Int = 0
)