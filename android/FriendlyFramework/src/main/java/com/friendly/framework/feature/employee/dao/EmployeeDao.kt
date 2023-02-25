package com.friendly.framework.feature.employee.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.friendly.framework.feature.employee.model.Employee

@Dao
interface EmployeeDao {
    @Insert( onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: Employee)

    @Update
    suspend fun update(item: Employee)

    @Delete
    suspend fun delete(item: Employee)

    @Query("delete from Employee")
    fun clearAll()

    @Query("select * from Employee order by UpdatedAt DESC")
    fun getAllItems(): LiveData<List<Employee>>
}