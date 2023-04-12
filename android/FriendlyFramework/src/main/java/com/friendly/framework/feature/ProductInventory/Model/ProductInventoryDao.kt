package com.friendly.framework.feature.productInventory.model

import androidx.lifecycle.LiveData
import androidx.room.*
@Dao
interface ProductInventoryDao {
    @Insert( onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: ProductInventory)

    @Update
    fun update(item: ProductInventory)

    @Delete
    fun delete(item: ProductInventory)

    @Query("select * from ProductInventory order by UpdatedAt DESC LIMIT 1")
    fun getRecentItem(): LiveData<ProductInventory>

    @Query("select * from ProductInventory WHERE ProductID=:prodId order by UpdatedAt DESC")
    fun getForProduct( prodId: String?): List<ProductInventory>

    @Query("select * from ProductInventory WHERE ProductID=:prodId order by UpdatedAt DESC LIMIT 1")
    fun getRecentForProduct( prodId: String?): ProductInventory?

    @Query("select * from ProductInventory order by UpdatedAt DESC")
    fun getAllItems(): List<ProductInventory>
}