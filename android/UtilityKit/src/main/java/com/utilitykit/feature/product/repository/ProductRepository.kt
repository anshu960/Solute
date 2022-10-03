package com.utilitykit.feature.product.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.utilitykit.feature.product.model.Product
import org.json.JSONObject

class ProductRepository {
     val newProductLiveData = MutableLiveData<Product>()
     val newProduct : LiveData<Product>
     get() = newProductLiveData

     val productLiveData = MutableLiveData<ArrayList<Product>>()
     val allProduct : LiveData<ArrayList<Product>>
     get() = productLiveData

     val selectedProductLiveData = MutableLiveData<Product>()
     val selectedProduct : LiveData<Product>
          get() = selectedProductLiveData

}