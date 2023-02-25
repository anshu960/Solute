package com.friendly.framework.feature.invocie.model

import androidx.lifecycle.LiveData
import androidx.room.*
import com.friendly.framework.feature.cart.model.Sale

@Dao
interface SaleDao {
    @Insert( onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: Sale)

    @Update
    suspend fun update(item: Sale)

    @Delete
    suspend fun delete(item: Sale)

    @Query("select * from Sale order by SaleDate")
    fun getRecentItem(): LiveData<Sale>

    @Query("SELECT * FROM Sale ORDER by SaleDate")
    fun getAllItems(): LiveData<List<Sale>>

    @Query("select * from Sale where BusinessID = :BusinessID order by SaleDate")
    fun getAllItemsForBusiness(BusinessID: String): LiveData<List<Sale>>
}