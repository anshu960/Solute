package com.friendly.framework.feature.product.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ProductBarCodeDao {
    @Insert( onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: ProductBarCode)

    @Update
    fun update(item: ProductBarCode)

    @Delete
    fun delete(item: ProductBarCode)

    @Query("select * from ProductBarCode order by UpdatedAt DESC LIMIT 1")
    fun getRecentItem(): ProductBarCode?

    @Query("select * from ProductBarCode WHERE ProductID=:prodId order by UpdatedAt DESC")
    fun getForProduct( prodId: String?): List<ProductBarCode>

    @Query("select * from ProductBarCode WHERE BarCode=:barcode order by UpdatedAt DESC LIMIT 1")
    fun findById( barcode: String): ProductBarCode?

    @Query("select * from ProductBarCode WHERE ProductID=:prodId order by UpdatedAt DESC LIMIT 1")
    fun getRecentForProduct( prodId: String?): ProductBarCode?

    @Query("select * from ProductBarCode order by UpdatedAt DESC")
    fun getAllItems(): List<ProductBarCode>
}