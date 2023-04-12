package com.friendly.framework.feature.productInventory.handler

import com.friendly.framework.feature.productInventory.model.ProductInventory
import com.friendly.framework.feature.productInventory.repository.ProductInventoryRepository
import com.friendly.framework.feature.productInventory.viewModel.ProductInventoryViewModel
import com.google.gson.Gson

class ProductInventoryHandler {
    var viewModel: ProductInventoryViewModel? = null
    val repository = ProductInventoryRepository()
    var onCreateNew : ((category: ProductInventory)->Unit)? = null
    val gson = Gson()
    init {
        instance = this
    }
    companion object{
        private var instance: ProductInventoryHandler? = null
        fun shared() : ProductInventoryHandler {
            if(instance != null){
                return instance as ProductInventoryHandler
            }else{
                return ProductInventoryHandler()
            }
        }
    }

    fun setup(model: ProductInventoryViewModel){
        viewModel = model
    }
}