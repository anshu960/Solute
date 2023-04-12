package com.friendly.framework.feature.productInventory.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.friendly.framework.feature.productInventory.model.ProductInventory

class ProductInventoryRepository {

    private val liveData = MutableLiveData<List<ProductInventory>>()
    val allLiveData : LiveData<List<ProductInventory>>
        get() = liveData

    private val selectedLiveData = MutableLiveData< ProductInventory>()

    val selected : LiveData<ProductInventory>
        get() = selectedLiveData
}