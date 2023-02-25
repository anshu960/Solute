package com.friendly.framework.feature.productSubCategory.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.friendly.framework.feature.productSubCategory.repository.ProductSubCategoryRepository

class ProductSubCategoryViewModalFactory(private val subCategoryRepository: ProductSubCategoryRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProductSubCategoryViewModel(subCategoryRepository) as T
    }
}