package com.friendly.framework.feature.sale.model

import androidx.room.*
import java.util.Date

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

    @Query("select Max(Quantity)-Min(Quantity) as total_change_per_hour " +
            "from Sale \n" +
            "GROUP BY strftime('%H', CreatedAt),strftime('%j', CreatedAt)\n" +
            "ORDER by CreatedAt DESC")
    fun getTodaySaleCount():List<Int>?
}