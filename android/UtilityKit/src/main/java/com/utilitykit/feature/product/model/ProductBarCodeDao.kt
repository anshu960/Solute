package com.utilitykit.feature.product.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ProductBarCodeDao {
    @Insert( onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: ProductBarCode)

    @Update
    suspend fun update(item: ProductBarCode)

    @Delete
    suspend fun delete(item: ProductBarCode)

    @Query("select * from ProductBarCode order by UpdatedAt DESC LIMIT 1")
    fun getRecentItem(): LiveData<ProductBarCode>

    @Query("select * from ProductBarCode WHERE ProductID=:prodId order by UpdatedAt DESC")
    suspend fun getForProduct( prodId: String?): List<ProductBarCode>

    @Query("select * from ProductBarCode WHERE BarCode=:barcode order by UpdatedAt DESC LIMIT 1")
    suspend fun findById( barcode: String): ProductBarCode?

    @Query("select * from ProductBarCode WHERE ProductID=:prodId order by UpdatedAt DESC LIMIT 1")
    fun getRecentForProduct( prodId: String?): LiveData<ProductBarCode>

    @Query("select * from ProductBarCode order by UpdatedAt DESC")
    fun getAllItems(): LiveData<List<ProductBarCode>>
}