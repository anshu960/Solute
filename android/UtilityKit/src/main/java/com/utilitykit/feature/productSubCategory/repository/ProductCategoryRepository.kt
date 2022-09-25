package com.utilitykit.feature.productSubCategory.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.utilitykit.feature.productSubCategory.model.ProductSubCategory

class ProductSubCategoryRepository {
     val subCategoryLiveData = MutableLiveData<List<ProductSubCategory>>()
     val allSubCategory : LiveData<List<ProductSubCategory>>
     get() = subCategoryLiveData

}