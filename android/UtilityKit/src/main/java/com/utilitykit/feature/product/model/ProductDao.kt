package com.utilitykit.feature.product.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ProductDao {
    @Insert( onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: Product)

    @Update
    suspend fun update(item: Product)

    @Delete
    suspend fun delete(item: Product)

    @Query("select * from Product order by UpdatedAt DESC LIMIT 1")
    fun getRecentItem(): LiveData<Product>

    @Query("select * from Product WHERE ProductID=:prodId order by UpdatedAt DESC")
    fun getForProduct( prodId: String?): LiveData<List<Product>>

    @Query("select * from Product WHERE BusinessID=:businessId order by UpdatedAt DESC")
    fun getProductsFor( businessId: String): LiveData<List<Product>>

    @Query("select * from Product WHERE ProductID=:prodId order by UpdatedAt DESC LIMIT 1")
    fun getRecentForProduct( prodId: String?): LiveData<Product>

    @Query("select * from Product order by UpdatedAt DESC")
    fun getAllItems(): LiveData<List<Product>>
}