package com.friendly.framework.feature.productInventory.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.friendly.framework.feature.productInventory.repository.ProductInventoryRepository

class ProductInventoryViewModelFactory (private val repository: ProductInventoryRepository):
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return  ProductInventoryViewModel(repository) as T
    }
}