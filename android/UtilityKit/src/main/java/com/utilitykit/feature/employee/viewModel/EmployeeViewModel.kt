package com.utilitykit.feature.customer.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.utilitykit.Constants.Key
import com.utilitykit.socket.SocketEvent
import com.utilitykit.dataclass.User
import com.utilitykit.feature.business.handler.BusinessHandler
import com.utilitykit.feature.employee.model.Employee
import com.utilitykit.feature.employee.repository.EmployeeRepository
import com.utilitykit.socket.SocketService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject

class EmployeeViewModel (private val employeeRepository: EmployeeRepository):ViewModel(){
    val gson = Gson()

    init {
        viewModelScope.launch (Dispatchers.IO){
//            fetchAllCustomer()
        }
    }

    val allEmployee : LiveData<ArrayList<Employee>>
        get() = employeeRepository.allEmployee

    val employee : LiveData<Employee>
        get() = employeeRepository.employee

    val user : LiveData<User>
        get() = employeeRepository.user



    fun fetchAllEmployee(){
        val user = User()
        if(BusinessHandler.shared().repository.business != null){
            var request = JSONObject()
            request.put(Key.userId,user._id)
            request.put(Key.businessID,BusinessHandler.shared().repository.business!!.Id)
            SocketService.shared().send(SocketEvent.RETRIVE_EMPLOYEE,request)
        }
    }

    fun createNew(employeeUser:User){
        val user = User()
        if(BusinessHandler.shared().repository.business != null){
            var request = JSONObject()
            request.put(Key.userId,user._id)
            request.put(Key.businessID,BusinessHandler.shared().repository.business!!.Id)
            request.put(Key.name,employeeUser.name)
            request.put(Key.mobileNumber,employeeUser.mobile)
            request.put(Key.employeeUserID, employeeUser._id)
            request.put(Key.emailId,employeeUser.email)
            SocketService.shared().send(SocketEvent.CREATE_EMPLOYEE,request)
        }
    }

    fun findUser(mobile:String){
        val user = User()
        if(BusinessHandler.shared().repository.business != null){
            var request = JSONObject()
            request.put(Key.userId,user._id)
            request.put(Key.businessID,BusinessHandler.shared().repository.business!!.Id)
            request.put(Key.mobileNumber, mobile)
            SocketService.shared().send(SocketEvent.FIND_USER,request)
        }
    }
}