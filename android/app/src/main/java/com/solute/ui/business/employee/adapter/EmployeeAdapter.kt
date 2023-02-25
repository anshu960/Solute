package com.solute.ui.business.employee.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.friendly.framework.UtilityActivity
import com.friendly.framework.feature.business.handler.BusinessHandler
import com.friendly.framework.feature.employee.handler.EmployeeHandler
import com.friendly.framework.feature.employee.model.Employee
import com.solute.R
import com.solute.ui.business.BusinessActivity
import com.solute.ui.business.employee.viewHolder.EmployeeViewHolder


class EmployeeAdapter(val context: Context, val allEmployee: ArrayList<Employee>, var onSelect:((employee: Employee) -> Unit)? = null) :
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
                val activity = BusinessHandler.shared().activity as? BusinessActivity
                activity?.navController?.navigate(R.id.business_details_details_container)
            }
        }
        holder.bind(item,onSelect)
    }
}
