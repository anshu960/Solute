package com.utilitykit.feature.employee.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.utilitykit.dataclass.User
import com.utilitykit.feature.employee.model.Employee

class EmployeeRepository {
    val allEmployeeLiveData = MutableLiveData<ArrayList<Employee>>()
    val allEmployee : LiveData<ArrayList<Employee>>
        get() = allEmployeeLiveData

    val employeeLiveData = MutableLiveData<Employee>()
    val employee : LiveData<Employee>
        get() = employeeLiveData

    val userLiveData = MutableLiveData<User>()
    val user : LiveData<User>
        get() = userLiveData

}