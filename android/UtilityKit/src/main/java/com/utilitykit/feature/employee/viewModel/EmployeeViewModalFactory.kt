package com.utilitykit.feature.employee.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.utilitykit.feature.customer.repository.CustomerRepository
import com.utilitykit.feature.customer.viewModel.CustomerViewModel
import com.utilitykit.feature.customer.viewModel.EmployeeViewModel
import com.utilitykit.feature.employee.repository.EmployeeRepository

class EmployeeViewModalFactory(private val employeeRepository: EmployeeRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return EmployeeViewModel(employeeRepository) as T
    }
}