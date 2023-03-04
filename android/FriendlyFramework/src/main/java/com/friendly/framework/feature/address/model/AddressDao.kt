package com.friendly.framework.feature.address.model

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

    @Query("select * from Address where IsDeleted = false order by UpdatedAt DESC LIMIT 1")
    fun getRecentItem(): LiveData<Address>

    @Query("select * from Address where FeatureObjectID = :featureId AND IsDeleted = false order by UpdatedAt DESC LIMIT 1")
    fun getRecentItemForBusiness(featureId:String): Address

    @Query("SELECT * FROM Address where IsDeleted = false ORDER by UpdatedAt DESC")
    fun getAllItems(): List< Address>

    @Query("select * from Address where FeatureObjectID = :featureId AND IsDeleted = false order by UpdatedAt DESC")
    fun getAllItemsForBusiness(featureId: String): List< Address>

    @Query("select * from Address where FeatureObjectID = :featureId AND IsDeleted = false order by UpdatedAt DESC")
    fun getAllItemsFor(featureId: String):List<Address>
}
