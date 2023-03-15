package com.friendly.framework.feature.invocie.model

import androidx.lifecycle.LiveData
import androidx.room.*
import com.friendly.framework.feature.cart.model.Sale

@Dao
interface SaleDao {
    @Insert( onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: Sale)

    @Update
    fun update(item: Sale)

    @Delete
    fun delete(item: Sale)

    @Query("select * from Sale order by SaleDate")
    fun getRecentItem(): Sale?

    @Query("SELECT * FROM Sale ORDER by SaleDate")
    fun getAllItems(): List<Sale>

    @Query("select * from Sale where BusinessID = :BusinessID order by SaleDate")
    fun getAllItemsForBusiness(BusinessID: String): List<Sale>
}