package com.friendly.framework.feature.invocie.model

import androidx.lifecycle.LiveData
import androidx.room.*
import com.friendly.framework.feature.invoice.model.CustomerInvoice

@Dao
interface CustomerInvoiceDao {
    @Insert( onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: CustomerInvoice)

    @Update
    fun update(item: CustomerInvoice)

    @Delete
    fun delete(item: CustomerInvoice)

    @Query("select * from CustomerInvoice order by UpdatedAt DESC LIMIT 1")
    fun getRecentItem(): LiveData<CustomerInvoice>

    @Query("select * from CustomerInvoice where BusinessID = :businessId order by UpdatedAt DESC LIMIT 1")
    fun getRecentItemForBusiness(businessId:String): CustomerInvoice?

    @Query("SELECT * FROM CustomerInvoice ORDER by UpdatedAt DESC")
    fun getAllItems(): List<CustomerInvoice>

    @Query("select * from CustomerInvoice where BusinessID = :BusinessID order by UpdatedAt DESC")
    fun getAllItemsForBusiness(BusinessID: String): List<CustomerInvoice>
}