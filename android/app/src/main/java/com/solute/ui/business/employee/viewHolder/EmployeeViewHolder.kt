package com.solute.ui.business.employee.viewHolder

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.solute.R
import com.utilitykit.Constants.Key.Companion.customer
import com.utilitykit.feature.customer.model.Customer
import com.utilitykit.feature.employee.model.Employee

class EmployeeViewHolder (inflater: LayoutInflater, parent: ViewGroup) : RecyclerView.ViewHolder(
    inflater.inflate(
        R.layout.recycler_item_customer, parent, false
    )
) {
    private var name: TextView? = null
    private var mobile: TextView? = null

    init {
        name = itemView.findViewById(R.id.recycler_item_customer_name_txt)
        mobile = itemView.findViewById(R.id.recycler_item_customer_mobile_txt)
    }

    fun bind(employee: Employee, index:Int) {
        name?.text = employee.Name
        mobile?.text = employee.MobileNumber
    }
}