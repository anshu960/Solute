package com.utilitykit.feature.productSubCategory.model

import androidx.lifecycle.LiveData
import androidx.room.*
import com.utilitykit.Constants.Key.Companion.category
import com.utilitykit.feature.productCategory.model.ProductCategory

@Dao
interface ProductSubCategoryDao {
    @Insert( onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: ProductSubCategory)

    @Update
    suspend fun update(item: ProductSubCategory)

    @Delete
    suspend fun delete(item: ProductSubCategory)

    @Query("select * from ProductSubCategory order by UpdatedAt DESC LIMIT 1")
    fun getRecentItem(): LiveData<ProductSubCategory>

    @Query("select * from ProductSubCategory where BusinessID = :businessId order by UpdatedAt DESC LIMIT 1")
    fun getRecentItemForBusiness(businessId:String): LiveData<ProductSubCategory>

    @Query("SELECT * FROM ProductSubCategory ORDER by UpdatedAt DESC")
    fun getAllItems(): LiveData<List<ProductSubCategory>>

    @Query("select * from ProductSubCategory where BusinessID = :BusinessID order by UpdatedAt DESC")
    fun getAllItemsForBusiness(BusinessID: String): LiveData<List<ProductSubCategory>>

    @Query("select * from ProductSubCategory where CategoryID = :categoryId order by UpdatedAt DESC")
    fun getAllItemsForCategory(categoryId: String): LiveData<List<ProductSubCategory>>
}