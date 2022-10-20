package com.utilitykit.feature.employee.handler

import com.google.gson.Gson
import com.utilitykit.Constants.Key
import com.utilitykit.UtilityActivity
import com.utilitykit.dataclass.User
import com.utilitykit.feature.customer.handler.CustomerHandler
import com.utilitykit.feature.customer.model.Customer
import com.utilitykit.feature.customer.viewModel.CustomerViewModel
import com.utilitykit.feature.customer.viewModel.EmployeeViewModel
import com.utilitykit.feature.employee.model.Employee
import com.utilitykit.feature.employee.repository.EmployeeRepository
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
            if(jsonData.has(Key.payload)){
                val employeeData = jsonData.getJSONObject(Key.payload)
                val newEmployee = gson.fromJson(employeeData.toString(),Employee::class.java)
                repository.employeeLiveData.postValue(newEmployee)
            }
        }
    }

    val onFetchAllEmployee = Emitter.Listener {
        if (it.isNotEmpty()) {
            val anyData = it.first() as JSONObject
            if (anyData.has(Key.payload)){
                val payload = anyData.getJSONArray(Key.payload)
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
            if (anyData.has(Key.payload)){
                val payload = anyData.getJSONArray(Key.payload)
                if(payload.length() > 0) {
                    val userData = payload.get(0)
                    val user = gson.fromJson(userData.toString(), User::class.java)
                    repository.userLiveData.postValue(user)
                }
            }
        }
    }
}