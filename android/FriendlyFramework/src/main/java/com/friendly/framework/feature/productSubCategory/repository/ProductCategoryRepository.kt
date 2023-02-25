package com.friendly.framework.feature.productSubCategory.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.friendly.framework.feature.productSubCategory.model.ProductSubCategory

class ProductSubCategoryRepository {
     val subCategoryLiveData = MutableLiveData<List<ProductSubCategory>>()
     val allSubCategory : LiveData<List<ProductSubCategory>>
     get() = subCategoryLiveData

     val selectedSubCategoryLiveData = MutableLiveData<ProductSubCategory>()
     val selectedSubCategory : LiveData<ProductSubCategory>
          get() = selectedSubCategoryLiveData

}