package com.friendly.framework.feature.employee.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.friendly.framework.feature.employee.model.EmployeeAttendance

@Dao
interface EmployeeAttendanceDao {
    @Insert( onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: EmployeeAttendance)

    @Update
    fun update(item: EmployeeAttendance)

    @Delete
    fun delete(item: EmployeeAttendance)

    @Query("delete from EmployeeAttendance")
    fun clearAll()

    @Query("select * from EmployeeAttendance where EmployeeID = :id AND AttendanceDate = :date order by UpdatedAt DESC LIMIT 1")
    fun findAttendance(id: String,date:String): EmployeeAttendance

    @Query("select * from EmployeeAttendance where EmployeeID = :id order by UpdatedAt DESC")
    fun getAllItemsFor(id: String): List<EmployeeAttendance>

    @Query("select * from EmployeeAttendance order by UpdatedAt DESC")
    fun getAllItems(): List<EmployeeAttendance>
}