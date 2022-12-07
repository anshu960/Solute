package com.solute.ui.business.employee.details.employeeInfo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.solute.R
import com.utilitykit.feature.employee.handler.EmployeeHandler

class EmployeeInfoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_employee_info, container, false)
        val name : TextView = view.findViewById(R.id.employee_info_fragment_name_txt)
        val mobile : TextView = view.findViewById(R.id.employee_info_fragment_mobile_txt)
        if(EmployeeHandler.shared().repository.employee.value != null){
            name.text = EmployeeHandler.shared().repository.employee.value?.Name
            mobile.text = EmployeeHandler.shared().repository.employee.value?.MobileNumber
        }
        return view
    }


}