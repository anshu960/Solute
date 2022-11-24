package com.solute.ui.business.customer.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.solute.R
import com.solute.ui.business.BusinessActivity
import com.solute.ui.business.customer.viewHolder.CustomerViewHolder
import com.utilitykit.feature.customer.handler.CustomerHandler
import com.utilitykit.feature.customer.model.Customer

class CustomerAdapter (val context: Context ,val allCustomer: ArrayList<Customer>,var onSelect:((customer: Customer) -> Unit)? = null) :
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
            if(onSelect != null){
                onSelect?.let { it1 -> it1(item) }
            }else if(context is BusinessActivity){
                CustomerHandler.shared().repository.customerLiveData.postValue(item)
                context.navController.navigate(R.id.business_customer_create)
            }
        }
        holder.bind(item,onSelect)
    }
}
