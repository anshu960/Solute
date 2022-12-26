package com.utilitykit.feature.mediaFile.model

import androidx.lifecycle.LiveData
import androidx.room.*
@Dao
interface  MediaFileDao {
    @Insert( onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: MediaFile)

    @Update
    suspend fun update(item:  MediaFile)

    @Delete
    suspend fun delete(item:  MediaFile)

    @Query("select * from MediaFile order by UpdatedAt DESC LIMIT 1")
    fun getRecentItem(): LiveData< MediaFile>

    @Query("select * from MediaFile where BusinessID = :businessId order by UpdatedAt DESC LIMIT 1")
    fun getRecentItemForBusiness(businessId:String): LiveData< MediaFile>

    @Query("SELECT * FROM MediaFile ORDER by UpdatedAt DESC")
    fun getAllItems(): LiveData<List< MediaFile>>

    @Query("select * from MediaFile where BusinessID = :BusinessID order by UpdatedAt DESC")
    fun getAllItemsForBusiness(BusinessID: String): LiveData<List< MediaFile>>

    @Query("select * from MediaFile where FeatureObjectID = :FeatureObjectID order by UpdatedAt DESC")
    suspend fun getAllItemsFor(FeatureObjectID: String):List<MediaFile>
}