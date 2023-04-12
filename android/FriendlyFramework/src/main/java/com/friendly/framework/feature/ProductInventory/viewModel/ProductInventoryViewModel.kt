package com.friendly.framework.feature.productInventory.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.friendly.framework.feature.productInventory.model.ProductInventory
import com.friendly.framework.feature.productInventory.repository.ProductInventoryRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class ProductInventoryViewModel(private val repository: ProductInventoryRepository) :
    ViewModel() {
    private val scope = CoroutineScope(Job() + Dispatchers.IO)

    val allData: LiveData<List<ProductInventory>>
        get() = repository.allLiveData

    val selectedData : LiveData<ProductInventory>
        get() = repository.selected
}