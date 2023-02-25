package com.friendly.framework.feature.employee.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.friendly.framework.dataclass.FriendlyProfile
import com.friendly.framework.feature.employee.model.Employee
import com.friendly.framework.feature.employee.model.EmployeeAttendance

class EmployeeRepository {
    val allEmployeeLiveData = MutableLiveData<ArrayList<Employee>>()
    val allEmployee : LiveData<ArrayList<Employee>>
        get() = allEmployeeLiveData

    val employeeLiveData = MutableLiveData<Employee>()
    val employee : LiveData<Employee>
        get() = employeeLiveData

    val profileLiveData = MutableLiveData<FriendlyProfile>()
    val profile : LiveData<FriendlyProfile>
        get() = profileLiveData

    //Attendance
    val employeeAttendanceLiveData = MutableLiveData<EmployeeAttendance>()
    val employeeAttendanceData : LiveData<EmployeeAttendance>
        get() = employeeAttendanceLiveData

    val allEmployeeAttendanceLiveData = MutableLiveData<ArrayList<EmployeeAttendance>>()
    val allEmployeeAttendance : LiveData<ArrayList<EmployeeAttendance>>
        get() = allEmployeeAttendanceLiveData

}