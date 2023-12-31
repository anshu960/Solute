package com.friendly.framework.feature.productCategory.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.friendly.framework.feature.productCategory.repository.ProductCategoryRepository

class ProductCategoryViewModalFactory(private val productRepository: ProductCategoryRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProductCategoryViewModel(productRepository) as T
    }
}