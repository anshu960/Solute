package com.friendly.framework.feature.productCategory.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.friendly.framework.feature.productCategory.model.ProductCategory

class ProductCategoryRepository {
     val categoryLiveData = MutableLiveData<List<ProductCategory>>()
     val allCategory : LiveData<List<ProductCategory>>
     get() = categoryLiveData

     val selectedCategoryLiveData = MutableLiveData<ProductCategory>()
     val selectedCategory : LiveData<ProductCategory>
          get() = selectedCategoryLiveData

}