package com.utilitykit.feature.product.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ProductStockDao {
    @Insert( onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: ProductStock)

    @Update
    suspend fun update(item: ProductStock)

    @Delete
    suspend fun delete(item: ProductStock)

    @Query("select * from ProductStock order by UpdatedAt DESC LIMIT 1")
    fun getRecentItem(): LiveData<ProductStock>

    @Query("select * from ProductStock WHERE ProductID=:prodId order by UpdatedAt DESC")
    fun getForProduct( prodId: String?): LiveData<List<ProductStock>>

    @Query("select * from ProductStock WHERE ProductID=:prodId order by UpdatedAt DESC LIMIT 1")
    fun getRecentForProduct( prodId: String?): LiveData<ProductStock>

    @Query("select * from ProductStock order by UpdatedAt DESC")
    fun getAllItems(): LiveData<List<ProductStock>>
}