package com.friendly.framework.feature.employee.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.friendly.framework.feature.customer.viewModel.EmployeeViewModel
import com.friendly.framework.feature.employee.repository.EmployeeRepository


class EmployeeViewModalFactory(private val employeeRepository: EmployeeRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return EmployeeViewModel(employeeRepository) as T
    }
}