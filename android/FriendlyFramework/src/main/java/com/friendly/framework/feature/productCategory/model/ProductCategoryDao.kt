package com.friendly.framework.feature.productCategory.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ProductCategoryDao {
    @Insert( onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: ProductCategory)

    @Update
    fun update(item: ProductCategory)

    @Delete
    fun delete(item: ProductCategory)

    @Query("select * from ProductCategory order by UpdatedAt DESC LIMIT 1")
    fun getRecentItem(): ProductCategory?

    @Query("select * from ProductCategory where BusinessID = :businessId order by UpdatedAt DESC LIMIT 1")
    fun getRecentItemForBusiness(businessId:String): ProductCategory?

    @Query("SELECT * FROM ProductCategory ORDER by UpdatedAt DESC")
    fun getAllItems(): List<ProductCategory>

    @Query("select * from ProductCategory where BusinessID = :BusinessID order by UpdatedAt DESC")
    fun getAllItemsForBusiness(BusinessID: String): List<ProductCategory>
}