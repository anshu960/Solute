package com.utilitykit.feature.address.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface  AddressDao {
    @Insert( onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: Address)

    @Update
    suspend fun update(item:  Address)

    @Delete
    suspend fun delete(item:  Address)

    @Query("select * from MediaFile order by UpdatedAt DESC LIMIT 1")
    fun getRecentItem(): LiveData<Address>

    @Query("select * from MediaFile where BusinessID = :businessId order by UpdatedAt DESC LIMIT 1")
    fun getRecentItemForBusiness(businessId:String): LiveData<Address>

    @Query("SELECT * FROM MediaFile ORDER by UpdatedAt DESC")
    fun getAllItems(): LiveData<List< Address>>

    @Query("select * from MediaFile where BusinessID = :BusinessID order by UpdatedAt DESC")
    fun getAllItemsForBusiness(BusinessID: String): LiveData<List< Address>>

    @Query("select * from MediaFile where FeatureObjectID = :FeatureObjectID order by UpdatedAt DESC")
    suspend fun getAllItemsFor(FeatureObjectID: String):List<Address>
}
