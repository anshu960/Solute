package com.friendly.framework.feature.customer.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.friendly.framework.constants.KeyConstant
import com.friendly.framework.database.DatabaseHandler
import com.friendly.framework.dataclass.FriendlyProfile
import com.friendly.framework.dataclass.FriendlyUser
import com.friendly.framework.feature.business.handler.BusinessHandler
import com.friendly.framework.feature.employee.model.Employee
import com.friendly.framework.feature.employee.model.EmployeeAttendance
import com.friendly.framework.feature.employee.repository.EmployeeRepository
import com.friendly.framework.socket.SocketEvent
import com.friendly.framework.socket.SocketService
import com.friendly.frameworkt.feature.business.handler.AuthHandler
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject

class EmployeeViewModel(private val employeeRepository: EmployeeRepository) : ViewModel() {
    val gson = Gson()

    init {
        viewModelScope.launch(Dispatchers.IO) {
//            fetchAllCustomer()
        }
    }

    val allEmployee: LiveData<ArrayList<Employee>>
        get() = employeeRepository.allEmployee

    val employee: LiveData<Employee>
        get() = employeeRepository.employee

    val profile: LiveData<FriendlyProfile>
        get() = employeeRepository.profile

    //Attendance
    val allEmployeeAttendance: LiveData<ArrayList<EmployeeAttendance>>
        get() = employeeRepository.allEmployeeAttendance

    val employeeAttendanceData: LiveData<EmployeeAttendance>
        get() = employeeRepository.employeeAttendanceData

    fun loadAttendanceFor(employee: Employee){
        employeeRepository.allEmployeeAttendanceLiveData.postValue(arrayListOf())
        DatabaseHandler.shared().database.employeeAttendanceDao().getAllItemsFor(employee.Id).observe(
            BusinessHandler.shared().activity){
                if(!it.isNullOrEmpty()){
                    employeeRepository.allEmployeeAttendanceLiveData.postValue(it as ArrayList<EmployeeAttendance>?)
                }
            }

    }

    fun fetchAllEmployee() {
        val user = FriendlyUser()
        var request = JSONObject()
        request.put(KeyConstant.userId, user._id)
        request.put(KeyConstant.businessID, BusinessHandler.shared().repository.business.value?.Id)
        request.put(KeyConstant.deviceId, AuthHandler.shared().deviceId)
        SocketService.shared().send(SocketEvent.RETRIVE_EMPLOYEE, request)
    }

    fun createNew(employeeProfile: FriendlyProfile) {
        val user = FriendlyUser()
        var request = JSONObject()
        request.put(KeyConstant.userId, user._id)
        request.put(KeyConstant.businessID, BusinessHandler.shared().repository.business.value?.Id)
        request.put(KeyConstant.name, employeeProfile.name)
        request.put(KeyConstant.mobileNumber, employeeProfile.mobileNumber)
        request.put(KeyConstant.employeeUserID, employeeProfile._id)
        request.put(KeyConstant.emailId, employeeProfile.emailID)
        request.put(KeyConstant.deviceId, AuthHandler.shared().deviceId)
        SocketService.shared().send(SocketEvent.CREATE_EMPLOYEE, request)
    }

    fun insert(employee: Employee) {
        viewModelScope.launch {
            DatabaseHandler.shared().database.employeeDao()
                .insert(employee)
        }
    }

    fun findUser(mobile: String) {
        val user = FriendlyUser()
        var request = JSONObject()
        request.put(KeyConstant.userId, user._id)
        request.put(KeyConstant.businessID, BusinessHandler.shared().repository.business.value?.Id)
        request.put(KeyConstant.mobileNumber, mobile)
        request.put(KeyConstant.deviceId, AuthHandler.shared().deviceId)
        SocketService.shared().send(SocketEvent.FIND_USER, request)
    }

    fun createNewAttendance(employee: Employee,isPresent : Boolean ,date: String, hours: Float, comment: String) {
        val user = FriendlyUser()
        val request = JSONObject()
        val business = BusinessHandler.shared().repository.business.value
        request.put(KeyConstant.userId, user._id)
        request.put(KeyConstant.businessID, business?.Id)
        request.put(KeyConstant.employeeUserID, employee.EmployeeUserID)
        request.put(KeyConstant.employeeID, employee.Id)
        request.put(KeyConstant.attendanceDate, date)
        request.put(KeyConstant.isPresent, isPresent)
        request.put(KeyConstant.hours, hours)
        request.put(KeyConstant.comment, comment)
        request.put(KeyConstant.deviceId, AuthHandler.shared().deviceId)
        SocketService.shared().send(SocketEvent.ADD_EMPLOYEE_ATTENDACE, request)
    }

    fun insertAttendance(attendance: EmployeeAttendance) {
        viewModelScope.launch {
            DatabaseHandler.shared().database.employeeAttendanceDao()
                .insert(attendance)
        }
    }

    fun isPresent(dateString: String, employee: Employee):Boolean? {
        allEmployeeAttendance.value?.forEach {
            if(it.AttendanceDate?.contains(dateString) == true){
                return it.IsPresent
            }
        }
        return null
    }
}