package com.friendly.framework.feature.customer.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CustomerDao {
    @Insert( onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: Customer)

    @Update
    fun update(item: Customer)

    @Delete
    fun delete(item: Customer)

    @Query("select * from Customer order by UpdatedAt LIMIT 1")
    fun getRecentItem(): LiveData<Customer>

    @Query("SELECT * FROM Customer ORDER by UpdatedAt")
    fun getAllItems(): LiveData<List<Customer>>

    @Query("select * from Customer where BusinessID = :BusinessID order by UpdatedAt DESC")
    fun getAllItemsForBusiness(BusinessID: String): List<Customer>

    @Query("select * from Customer where _id = :id order by UpdatedAt DESC LIMIT 1")
    fun findCustomerById(id: String):Customer?

    @Query("select COUNT(*) from CustomerInvoice where CustomerID = :id")
    fun getInvoiceCount(id: String): Int?

    @Query("select SUM(FinalPrice) from CustomerInvoice where CustomerID = :id")
    fun getTotalInvoiceValue(id: String): Float?
}