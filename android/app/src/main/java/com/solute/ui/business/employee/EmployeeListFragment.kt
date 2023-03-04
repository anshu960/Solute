package com.solute.ui.business.employee

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.friendly.framework.feature.employee.handler.EmployeeHandler
import com.friendly.framework.feature.employee.model.Employee
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.solute.R
import com.solute.app.App
import com.solute.ui.business.employee.adapter.EmployeeAdapter


class EmployeeListFragment : Fragment() {

    var recycler : RecyclerView? = null
    var allEmployee : ArrayList<Employee> = arrayListOf()
    var adapter : EmployeeAdapter? = null
    var fabButton : FloatingActionButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        EmployeeHandler.shared().viewModel?.allEmployee?.observe(this) {
            if (it.isNotEmpty()) {
                allEmployee = it
                this.reload()
            }
        }
        EmployeeHandler.shared().viewModel?.fetchAllEmployee()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_employee_list, container, false)
        recycler = view.findViewById(R.id.employee_list_fragment_recycler)
        fabButton = view.findViewById(R.id.employee_list_fragment_fab_btn)
        fabButton?.setOnClickListener {
            App.shared().mainActivity?.navController?.navigate(R.id.fragment_create_employee)
        }
        reload()
        return view
    }
    fun reload(){
        this.recycler!!.layoutManager = LinearLayoutManager(this.context)
        adapter = this.context?.let { EmployeeAdapter(it, allEmployee) }
        this.recycler!!.adapter = this.adapter
    }
}