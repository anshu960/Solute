package com.utilitykit.feature.product.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.utilitykit.feature.product.repository.ProductRepository

class ProductViewModalFactory(private val productRepository: ProductRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProductViewModel(productRepository) as T
    }
}