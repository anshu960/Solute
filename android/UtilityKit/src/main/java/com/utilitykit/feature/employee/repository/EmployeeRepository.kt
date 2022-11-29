package com.utilitykit.feature.employee.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.utilitykit.dataclass.User
import com.utilitykit.feature.commonModel.Profile
import com.utilitykit.feature.employee.model.Employee
import com.utilitykit.feature.employee.model.EmployeeAttendance

class EmployeeRepository {
    val allEmployeeLiveData = MutableLiveData<ArrayList<Employee>>()
    val allEmployee : LiveData<ArrayList<Employee>>
        get() = allEmployeeLiveData

    val employeeLiveData = MutableLiveData<Employee>()
    val employee : LiveData<Employee>
        get() = employeeLiveData

    val profileLiveData = MutableLiveData<Profile>()
    val profile : LiveData<Profile>
        get() = profileLiveData

    //Attendance
    val employeeAttendanceLiveData = MutableLiveData<EmployeeAttendance>()
    val employeeAttendanceData : LiveData<EmployeeAttendance>
        get() = employeeAttendanceLiveData

    val allEmployeeAttendanceLiveData = MutableLiveData<ArrayList<EmployeeAttendance>>()
    val allEmployeeAttendance : LiveData<ArrayList<EmployeeAttendance>>
        get() = allEmployeeAttendanceLiveData

}