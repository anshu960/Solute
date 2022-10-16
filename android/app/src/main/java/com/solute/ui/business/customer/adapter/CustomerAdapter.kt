package com.solute.ui.business.customer.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.solute.ui.business.BusinessActivity
import com.solute.ui.business.customer.viewHolder.CustomerViewHolder
import com.utilitykit.feature.customer.model.Customer

class CustomerAdapter (val context: Context, val allCustomer: ArrayList<Customer>) :
    RecyclerView.Adapter<CustomerViewHolder>() {

    override fun getItemCount(): Int {
        return allCustomer.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CustomerViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: CustomerViewHolder, position: Int) {
        val item = allCustomer[position]
        holder.itemView.setOnClickListener {
            if(context is BusinessActivity){
                context.toast("Customer Selected")
            }
        }
        holder.bind(item,position)
    }
}
