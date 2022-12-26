package com.utilitykit.feature.invocie.model

import androidx.lifecycle.LiveData
import androidx.room.*
import com.utilitykit.feature.cart.model.Sale
import com.utilitykit.feature.invoice.model.CustomerInvoice

@Dao
interface CustomerInvoiceDao {
    @Insert( onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: CustomerInvoice)

    @Update
    suspend fun update(item: CustomerInvoice)

    @Delete
    suspend fun delete(item: CustomerInvoice)

    @Query("select * from CustomerInvoice order by UpdatedAt DESC LIMIT 1")
    fun getRecentItem(): LiveData<CustomerInvoice>

    @Query("select * from CustomerInvoice where BusinessID = :businessId order by UpdatedAt DESC LIMIT 1")
    suspend fun getRecentItemForBusiness(businessId:String): CustomerInvoice?

    @Query("SELECT * FROM CustomerInvoice ORDER by UpdatedAt DESC")
    fun getAllItems(): LiveData<List<CustomerInvoice>>

    @Query("select * from CustomerInvoice where BusinessID = :BusinessID order by UpdatedAt DESC")
    fun getAllItemsForBusiness(BusinessID: String): LiveData<List<CustomerInvoice>>
}