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

    @Query("select * from Address order by UpdatedAt DESC LIMIT 1")
    fun getRecentItem(): LiveData<Address>

    @Query("select * from Address where FeatureObjectID = :featureId order by UpdatedAt DESC LIMIT 1")
    fun getRecentItemForBusiness(featureId:String): LiveData<Address>

    @Query("SELECT * FROM Address ORDER by UpdatedAt DESC")
    fun getAllItems(): LiveData<List< Address>>

    @Query("select * from Address where FeatureObjectID = :featureId order by UpdatedAt DESC")
    fun getAllItemsForBusiness(featureId: String): LiveData<List< Address>>

    @Query("select * from Address where FeatureObjectID = :featureId order by UpdatedAt DESC")
    suspend fun getAllItemsFor(featureId: String):List<Address>
}
