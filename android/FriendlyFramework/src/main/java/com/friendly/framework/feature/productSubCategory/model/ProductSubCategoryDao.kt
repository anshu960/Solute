package com.friendly.framework.feature.productSubCategory.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ProductSubCategoryDao {
    @Insert( onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: ProductSubCategory)

    @Update
    fun update(item: ProductSubCategory)

    @Delete
    fun delete(item: ProductSubCategory)

    @Query("select * from ProductSubCategory order by UpdatedAt DESC LIMIT 1")
    fun getRecentItem(): ProductSubCategory?

    @Query("select * from ProductSubCategory where BusinessID = :businessId order by UpdatedAt DESC LIMIT 1")
    fun getRecentItemForBusiness(businessId:String): ProductSubCategory?

    @Query("SELECT * FROM ProductSubCategory ORDER by UpdatedAt DESC")
    fun getAllItems(): List<ProductSubCategory>

    @Query("select * from ProductSubCategory where BusinessID = :BusinessID order by UpdatedAt DESC")
    fun getAllItemsForBusiness(BusinessID: String): List<ProductSubCategory>

    @Query("select * from ProductSubCategory where CategoryID = :categoryId order by UpdatedAt DESC")
    fun getAllItemsForCategory(categoryId: String): List<ProductSubCategory>
}