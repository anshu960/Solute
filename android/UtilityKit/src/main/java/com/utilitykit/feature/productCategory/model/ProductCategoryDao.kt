package com.utilitykit.feature.productCategory.model

import androidx.lifecycle.LiveData
import androidx.room.*
import com.utilitykit.feature.invoice.model.CustomerInvoice

@Dao
interface ProductCategoryDao {
    @Insert( onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: ProductCategory)

    @Update
    suspend fun update(item: ProductCategory)

    @Delete
    suspend fun delete(item: ProductCategory)

    @Query("select * from ProductCategory order by UpdatedAt DESC LIMIT 1")
    fun getRecentItem(): LiveData<ProductCategory>

    @Query("select * from ProductCategory where BusinessID = :businessId order by UpdatedAt DESC LIMIT 1")
    fun getRecentItemForBusiness(businessId:String): LiveData<ProductCategory>

    @Query("SELECT * FROM ProductCategory ORDER by UpdatedAt DESC")
    fun getAllItems(): LiveData<List<ProductCategory>>

    @Query("select * from ProductCategory where BusinessID = :BusinessID order by UpdatedAt DESC")
    fun getAllItemsForBusiness(BusinessID: String): LiveData<List<ProductCategory>>
}