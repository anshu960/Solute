package com.utilitykit.feature.customer.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.utilitykit.Constants.Key
import com.utilitykit.Constants.Key.Companion.customer
import com.utilitykit.UtilityKitApp
import com.utilitykit.socket.SocketEvent
import com.utilitykit.dataclass.User
import com.utilitykit.feature.business.handler.BusinessHandler
import com.utilitykit.feature.commonModel.Profile
import com.utilitykit.feature.customer.model.Customer
import com.utilitykit.feature.employee.model.Employee
import com.utilitykit.feature.employee.model.EmployeeAttendance
import com.utilitykit.feature.employee.repository.EmployeeRepository
import com.utilitykit.feature.product.model.Product
import com.utilitykit.socket.SocketService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
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

    val profile: LiveData<Profile>
        get() = employeeRepository.profile

    //Attendance
    val allEmployeeAttendance: LiveData<ArrayList<EmployeeAttendance>>
        get() = employeeRepository.allEmployeeAttendance

    val employeeAttendanceData: LiveData<EmployeeAttendance>
        get() = employeeRepository.employeeAttendanceData

    fun loadAttendanceFor(employee: Employee){
        employeeRepository.allEmployeeAttendanceLiveData.postValue(arrayListOf())
            UtilityKitApp.applicationContext().database.employeeAttendanceDao().getAllItemsFor(employee.Id).observe(BusinessHandler.shared().activity){
                if(!it.isNullOrEmpty()){
                    employeeRepository.allEmployeeAttendanceLiveData.postValue(it as ArrayList<EmployeeAttendance>?)
                }
            }

    }

    fun fetchAllEmployee() {
        val user = User()
        var request = JSONObject()
        request.put(Key.userId, user._id)
        request.put(Key.businessID, BusinessHandler.shared().repository.business.value?.Id)
        SocketService.shared().send(SocketEvent.RETRIVE_EMPLOYEE, request)
    }

    fun createNew(employeeProfile: Profile) {
        val user = User()
        var request = JSONObject()
        request.put(Key.userId, user._id)
        request.put(Key.businessID, BusinessHandler.shared().repository.business.value?.Id)
        request.put(Key.name, employeeProfile.Name)
        request.put(Key.mobileNumber, employeeProfile.MobileNumber)
        request.put(Key.employeeUserID, employeeProfile.Id)
        request.put(Key.emailId, employeeProfile.EmailID)
        SocketService.shared().send(SocketEvent.CREATE_EMPLOYEE, request)
    }

    fun insert(employee: Employee) {
        viewModelScope.launch {
            UtilityKitApp.applicationContext().database.employeeDao()
                .insert(employee)
        }
    }

    fun findUser(mobile: String) {
        val user = User()
        var request = JSONObject()
        request.put(Key.userId, user._id)
        request.put(Key.businessID, BusinessHandler.shared().repository.business.value?.Id)
        request.put(Key.mobileNumber, mobile)
        SocketService.shared().send(SocketEvent.FIND_USER, request)
    }

    fun createNewAttendance(employee: Employee,isPresent : Boolean ,date: String, hours: Float, comment: String) {
        val user = User()
        val request = JSONObject()
        val business = BusinessHandler.shared().repository.business.value
        request.put(Key.userId, user._id)
        request.put(Key.businessID, business?.Id)
        request.put(Key.employeeUserID, employee.EmployeeUserID)
        request.put(Key.employeeID, employee.Id)
        request.put(Key.attendanceDate, date)
        request.put(Key.isPresent, isPresent)
        request.put(Key.hours, hours)
        request.put(Key.comment, comment)
        SocketService.shared().send(SocketEvent.ADD_EMPLOYEE_ATTENDACE, request)
    }

    fun insertAttendance(attendance: EmployeeAttendance) {
        viewModelScope.launch {
            UtilityKitApp.applicationContext().database.employeeAttendanceDao()
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