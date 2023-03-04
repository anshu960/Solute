package com.friendly.framework.feature.employee.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.friendly.framework.feature.employee.model.Employee

@Dao
interface EmployeeDao {
    @Insert( onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: Employee)

    @Update
    fun update(item: Employee)

    @Delete
    fun delete(item: Employee)

    @Query("delete from Employee")
    fun clearAll()

    @Query("select * from Employee order by UpdatedAt DESC")
    fun getAllItems(): List<Employee>
}