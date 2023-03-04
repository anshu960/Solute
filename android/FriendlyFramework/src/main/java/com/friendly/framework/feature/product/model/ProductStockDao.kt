package com.friendly.framework.feature.product.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ProductStockDao {
    @Insert( onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: ProductStock)

    @Update
    fun update(item: ProductStock)

    @Delete
    fun delete(item: ProductStock)

    @Query("select * from ProductStock order by UpdatedAt DESC LIMIT 1")
    fun getRecentItem(): LiveData<ProductStock>

    @Query("select * from ProductStock WHERE ProductID=:prodId order by UpdatedAt DESC")
    fun getForProduct( prodId: String?): List<ProductStock>

    @Query("select * from ProductStock WHERE ProductID=:prodId order by UpdatedAt DESC LIMIT 1")
    fun getRecentForProduct( prodId: String?): ProductStock?

    @Query("select * from ProductStock order by UpdatedAt DESC")
    fun getAllItems(): List<ProductStock>
}