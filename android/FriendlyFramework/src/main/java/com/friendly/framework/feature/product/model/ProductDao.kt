package com.friendly.framework.feature.product.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ProductDao {
    @Insert( onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: Product)

    @Update
    fun update(item: Product)

    @Delete
    fun delete(item: Product)

    @Query("select * from Product order by UpdatedAt DESC LIMIT 1")
    fun getRecentItem(): LiveData<Product>

    @Query("select * from Product WHERE _id=:prodId order by UpdatedAt DESC")
    fun getForProduct( prodId: String?): List<Product>

    @Query("select * from Product WHERE BusinessID=:businessId and IsDeleted=:IsDeleted order by UpdatedAt DESC")
    fun getProductsFor( businessId: String,IsDeleted: Boolean =false): List<Product>

    @Query("select * from Product WHERE _id=:prodId order by UpdatedAt DESC LIMIT 1")
    suspend fun findById( prodId: String?): Product?

    @Query("select * from Product WHERE IsDeleted=:IsDeleted order by UpdatedAt DESC")
    fun getAllItems(IsDeleted: Boolean =false): List<Product>
}