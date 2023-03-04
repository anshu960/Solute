package com.friendly.framework.feature.business.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface AuthDao {
    @Insert( onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: Business)

    @Update
    suspend fun update(item: Business)

    @Delete
    suspend fun delete(item: Business)

    @Query("delete from Business")
    fun clearAll()

    @Query("select * from Business order by UpdatedAt DESC")
    fun getAllItems(): List<Business>
}