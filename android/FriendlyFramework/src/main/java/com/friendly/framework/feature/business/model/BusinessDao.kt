package com.friendly.framework.feature.business.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface BusinessDao {
    @Insert( onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: Business)

    @Update
    suspend fun update(item: Business)

    @Delete
    suspend fun delete(item: Business)

    @Query("delete from Business where _id=:id")
    fun delete(id:String)

    @Query("delete from Business")
    fun clearAll()

    @Query("select * from Business order by UpdatedAt DESC")
    fun getAllItems(): LiveData<List<Business>>

    @Query("select * from Business order by UpdatedAt DESC")
    fun getAllItemsForUser(): List<Business>
}