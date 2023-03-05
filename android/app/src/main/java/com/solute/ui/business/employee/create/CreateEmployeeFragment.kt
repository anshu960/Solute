package com.solute.ui.business.employee.create

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.friendly.framework.dataclass.FriendlyProfile
import com.friendly.framework.feature.employee.handler.EmployeeHandler
import com.friendly.framework.feature.employee.model.Employee
import com.google.android.material.textfield.TextInputEditText
import com.solute.R
import com.solute.app.App

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CreateEmployeeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CreateEmployeeFragment : Fragment() {
    var employee : Employee? = null
    var employeeProfile : FriendlyProfile? = null

    var searchField : TextInputEditText? = null
    var employeeCard : CardView? = null
    var searchButton : Button? = null
    var saveButton : Button? = null

    var employeeImage : ImageView? = null
    var employeeName : TextView? = null
    var employeeMobile : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        EmployeeHandler.shared().viewModel?.employee?.observe(this) {
            employee = it
            onChangeEmployee()
        }
        EmployeeHandler.shared().viewModel?.profile?.observe(this) {
            employeeProfile = it
            onChangeUser()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_create_employee, container, false)
        employeeCard = view.findViewById(R.id.add_employee_employee_card)
        searchField = view.findViewById(R.id.add_employee_tiet)
        searchButton = view.findViewById(R.id.add_employee_search_btn)
        saveButton = view.findViewById(R.id.add_employee_save_btn)
        employeeImage = view.findViewById(R.id.add_employee_employee_img)
        employeeName = view.findViewById(R.id.add_employee_employee_name)
        employeeMobile = view.findViewById(R.id.add_employee_employee_mobile)
        employeeCard?.visibility = View.GONE
        saveButton?.visibility = View.GONE
        searchButton?.setOnClickListener { onClickSearch() }
        saveButton?.setOnClickListener { onClickSave() }
        return view
    }
    fun onChangeEmployee(){
        if(employee != null){
            EmployeeHandler.shared().viewModel?.fetchAllEmployee()
            App.shared().mainActivity?.toast("Request Sent, please ask your employee to accept the request")
            App.shared().mainActivity?.onBackPressed()
            EmployeeHandler.shared().repository.employeeLiveData.postValue(null)
        }
    }
    fun onChangeUser(){
        if(employeeProfile != null){
            employeeCard?.visibility = View.VISIBLE
            saveButton?.visibility = View.VISIBLE
            employeeName?.text = employeeProfile!!.Name
            employeeMobile?.text = employeeProfile!!.MobileNumber
        }
    }

    fun onClickSearch(){
        var mob = searchField?.text.toString()
        if(mob != "" && mob.length >= 9){
            EmployeeHandler.shared().viewModel?.findUser(mob)
        }
    }

    fun onClickSave(){
        if(employeeProfile != null) {
            EmployeeHandler.shared().viewModel?.createNew(employeeProfile!!)
        }
    }

}