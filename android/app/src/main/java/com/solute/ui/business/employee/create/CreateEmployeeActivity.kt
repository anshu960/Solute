package com.solute.ui.business.employee.create

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.lifecycle.VIEW_MODEL_STORE_OWNER_KEY
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText
import com.solute.R
import com.utilitykit.UtilityActivity
import com.utilitykit.dataclass.User
import com.utilitykit.feature.commonModel.Profile
import com.utilitykit.feature.customer.viewModel.EmployeeViewModel
import com.utilitykit.feature.employee.handler.EmployeeHandler
import com.utilitykit.feature.employee.model.Employee
import com.utilitykit.feature.employee.viewModel.EmployeeViewModalFactory

class CreateEmployeeActivity : UtilityActivity() {

    var viewModal : EmployeeViewModel? = null
    var employee : Employee? = null
    var employeeProfile : Profile? = null

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
            viewModal?.findUser(mob)
        }
    }

    fun onClickSave(){
        if(employeeProfile != null) {
            viewModal?.createNew(employeeProfile!!)
        }
    }

}
