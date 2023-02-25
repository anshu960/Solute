package com.friendly.framework.feature.employee.handler

import com.friendly.framework.UtilityActivity
import com.friendly.framework.constants.KeyConstant
import com.friendly.framework.dataclass.FriendlyProfile
import com.friendly.framework.feature.customer.viewModel.EmployeeViewModel
import com.friendly.framework.feature.employee.model.Employee
import com.friendly.framework.feature.employee.model.EmployeeAttendance
import com.friendly.framework.feature.employee.repository.EmployeeRepository
import com.google.gson.Gson
import io.socket.emitter.Emitter
import org.json.JSONObject

class EmployeeHandler {

    var viewModel: EmployeeViewModel? = null
    val repository = EmployeeRepository()
    var activity = UtilityActivity()
    val gson = Gson()
    init {
        instance = this
    }
    var onCreateNewAttendance : ((attendance: EmployeeAttendance)->Unit)? = null

    companion object{
        private var instance: EmployeeHandler? = null
        fun shared() : EmployeeHandler {
            if(instance != null){
                return instance as EmployeeHandler
            }else{
                return EmployeeHandler()
            }
        }
    }


    fun setup(model:EmployeeViewModel){
        viewModel = model
    }
    val onCreateEmployee = Emitter.Listener {
        if (it.isNotEmpty()) {
            val jsonData = it.first() as JSONObject
            if(jsonData.has(KeyConstant.payload)){
                val employeeData = jsonData.getJSONObject(KeyConstant.payload)
                val newEmployee = gson.fromJson(employeeData.toString(), Employee::class.java)
                repository.employeeLiveData.postValue(newEmployee)
            }
        }
    }

    val onCreateEmployeeAttendance = Emitter.Listener {
        if (it.isNotEmpty()) {
            val jsonData = it.first() as JSONObject
            if(jsonData.has(KeyConstant.payload)){
                val attendanceData = jsonData.getJSONObject(KeyConstant.payload)
                val newEmployeeAttendance = gson.fromJson(attendanceData.toString(),EmployeeAttendance::class.java)
                repository.employeeAttendanceLiveData.postValue(newEmployeeAttendance)
                viewModel?.insertAttendance(newEmployeeAttendance)
                onCreateNewAttendance?.let { it1 -> it1(newEmployeeAttendance) }
            }
        }
    }

    val onFetchAllEmployee = Emitter.Listener {
        if (it.isNotEmpty()) {
            val anyData = it.first() as JSONObject
            if (anyData.has(KeyConstant.payload)){
                val payload = anyData.getJSONArray(KeyConstant.payload)
                var allEmployee : ArrayList<Employee> = arrayListOf()
                if(payload.length() > 0) {
                    for (i in 0 until payload.length()) {
                        val employeeData = payload.get(i)
                        val newEmployee = gson.fromJson(employeeData.toString(),Employee::class.java)
                        allEmployee.add(newEmployee)
                    }
                    repository.allEmployeeLiveData.postValue(allEmployee)
                }
            }
        }
    }

    val onFindUser = Emitter.Listener {
        if (it.isNotEmpty()) {
            val anyData = it.first() as JSONObject
            if (anyData.has(KeyConstant.payload)){
                val payload = anyData.getJSONArray(KeyConstant.payload)
                if(payload.length() > 0) {
                    val userData = payload.get(0)
                    val profile = gson.fromJson(userData.toString(), FriendlyProfile::class.java)
                    repository.profileLiveData.postValue(profile)
                }
            }
        }
    }
}