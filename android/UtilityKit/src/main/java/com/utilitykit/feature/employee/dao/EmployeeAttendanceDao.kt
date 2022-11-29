package com.utilitykit.feature.employee.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.utilitykit.feature.employee.model.EmployeeAttendance

@Dao
interface EmployeeAttendanceDao {
    @Insert( onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: EmployeeAttendance)

    @Update
    suspend fun update(item: EmployeeAttendance)

    @Delete
    suspend fun delete(item: EmployeeAttendance)

    @Query("delete from EmployeeAttendance")
    fun clearAll()

    @Query("select * from EmployeeAttendance order by UpdatedAt DESC")
    fun getAllItems(): LiveData<List<EmployeeAttendance>>
}