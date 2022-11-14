package com.solute.ui.business.employee.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.solute.ui.business.BusinessActivity
import com.solute.ui.business.employee.details.EmployeeDetailsActivity
import com.solute.ui.business.employee.viewHolder.EmployeeViewHolder
import com.utilitykit.UtilityActivity
import com.utilitykit.feature.employee.handler.EmployeeHandler
import com.utilitykit.feature.employee.model.Employee

class EmployeeAdapter(val context: Context, val allEmployee: ArrayList<Employee>) :
    RecyclerView.Adapter<EmployeeViewHolder>() {

    override fun getItemCount(): Int {
        return allEmployee.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return EmployeeViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        val item = allEmployee[position]
        holder.itemView.setOnClickListener {
            if(context is UtilityActivity){
                EmployeeHandler.shared().repository.employeeLiveData.postValue(item)
                val intent = Intent(context,EmployeeDetailsActivity::class.java)
                context.startActivity(intent)
            }
        }
        holder.bind(item,position)
    }
}
