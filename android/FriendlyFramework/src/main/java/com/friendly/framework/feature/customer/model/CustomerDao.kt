package com.friendly.framework.feature.customer.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CustomerDao {
    @Insert( onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: Customer)

    @Update
    suspend fun update(item: Customer)

    @Delete
    suspend fun delete(item: Customer)

    @Query("select * from Customer order by UpdatedAt LIMIT 1")
    fun getRecentItem(): LiveData<Customer>

    @Query("SELECT * FROM Customer ORDER by UpdatedAt")
    fun getAllItems(): LiveData<List<Customer>>

    @Query("select * from Customer where BusinessID = :BusinessID order by UpdatedAt DESC")
    fun getAllItemsForBusiness(BusinessID: String): LiveData<List<Customer>>

    @Query("select * from Customer where _id = :id order by UpdatedAt DESC LIMIT 1")
    fun findCustomerById(id: String): LiveData<Customer>

    @Query("select COUNT(*) from CustomerInvoice where CustomerID = :id")
    suspend fun getInvoiceCount(id: String): Int?

    @Query("select SUM(FinalPrice) from CustomerInvoice where CustomerID = :id")
    suspend fun getTotalInvoiceValue(id: String): Float?
}