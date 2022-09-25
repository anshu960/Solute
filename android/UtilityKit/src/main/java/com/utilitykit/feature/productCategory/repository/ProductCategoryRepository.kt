package com.utilitykit.feature.productCategory.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.utilitykit.feature.product.model.Product
import com.utilitykit.feature.productCategory.model.ProductCategory

class ProductCategoryRepository {
     val categoryLiveData = MutableLiveData<List<ProductCategory>>()
     val allCategory : LiveData<List<ProductCategory>>
     get() = categoryLiveData

     val selectedCategoryLiveData = MutableLiveData<ProductCategory>()
     val selectedCategory : LiveData<ProductCategory>
          get() = selectedCategoryLiveData

}