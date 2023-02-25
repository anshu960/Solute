package com.solute.ui.business.employee.create

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.lifecycle.ViewModelProvider
import com.friendly.framework.UtilityActivity
import com.friendly.framework.dataclass.FriendlyProfile
import com.friendly.framework.feature.customer.viewModel.EmployeeViewModel
import com.friendly.framework.feature.employee.handler.EmployeeHandler
import com.friendly.framework.feature.employee.model.Employee
import com.friendly.framework.feature.employee.viewModel.EmployeeViewModalFactory
import com.google.android.material.textfield.TextInputEditText
import com.solute.R


class CreateEmployeeActivity : UtilityActivity() {

    var viewModal : EmployeeViewModel? = null
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
        setContentView(R.layout.activity_create_employee)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        employeeCard = findViewById(R.id.add_employee_employee_card)
        searchField = findViewById(R.id.add_employee_tiet)
        searchButton = findViewById(R.id.add_employee_search_btn)
        saveButton = findViewById(R.id.add_employee_save_btn)
        employeeImage = findViewById(R.id.add_employee_employee_img)
        employeeName = findViewById(R.id.add_employee_employee_name)
        employeeMobile = findViewById(R.id.add_employee_employee_mobile)
        title = "Add Employee"
        employeeCard?.visibility = View.GONE
        saveButton?.visibility = View.GONE
        searchButton?.setOnClickListener { onClickSearch() }
        saveButton?.setOnClickListener { onClickSave() }
        createViewModels()
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId === android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    fun createViewModels(){
        viewModal = ViewModelProvider(
            this,
            EmployeeViewModalFactory(EmployeeHandler.shared().repository)
        ).get(
            EmployeeViewModel::class.java
        )
        viewModal?.employee?.observe(this) {
            employee = it
            onChangeEmployee()
        }
        viewModal?.profile?.observe(this) {
            employeeProfile = it
            onChangeUser()
        }
        EmployeeHandler.shared().setup(viewModal!!)
    }

    fun onChangeEmployee(){
        if(employee != null){
            viewModal?.fetchAllEmployee()
            toastLong("Request Sent, please ask your employee to accept the request")
            this.onBackPressed()
            EmployeeHandler.shared().repository.employeeLiveData.postValue(null)
        }
    }
    fun onChangeUser(){
        if(employeeProfile != null){
            employeeCard?.visibility = View.VISIBLE
            saveButton?.visibility = View.VISIBLE
            employeeName?.text = employeeProfile!!.name
            employeeMobile?.text = employeeProfile!!.mobileNumber
        }
    }

    fun onClickSearch(){
        var mob = searchField?.text.toString()
        if(mob != "" && mob.length >= 9){
            viewModal?.findUser(mob)
        }
    }

    fun onClickSave(){
        if(employeeProfile != null) {
            viewModal?.createNew(employeeProfile!!)
        }
    }

}
