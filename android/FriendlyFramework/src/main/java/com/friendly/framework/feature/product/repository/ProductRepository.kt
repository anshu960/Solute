package com.friendly.framework.feature.product.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.friendly.framework.feature.product.model.Product
import com.friendly.framework.feature.product.model.ProductBarCode
import com.friendly.framework.feature.product.model.ProductStock

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

     private val productStockLiveData = MutableLiveData<ArrayList<ProductStock>>()
     val productStock : LiveData<ArrayList<ProductStock>>
          get() = productStockLiveData

     val productBarCodeLiveData = MutableLiveData<ArrayList<ProductBarCode>>()
     val productBarCode : LiveData<ArrayList<ProductBarCode>>
          get() = productBarCodeLiveData


}