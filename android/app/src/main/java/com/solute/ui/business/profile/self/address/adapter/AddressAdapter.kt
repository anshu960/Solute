package com.solute.ui.business.profile.self.address.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.solute.ui.business.profile.self.address.viewHolder.AddressViewHolder
import com.utilitykit.UtilityActivity
import com.utilitykit.feature.address.model.Address

class AddressAdapter(val context: Context, val all: ArrayList<Address>, var onSelect:((address: Address) -> Unit)? = null) :
    RecyclerView.Adapter<AddressViewHolder>() {

    override fun getItemCount(): Int {
        return all.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return AddressViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: AddressViewHolder, position: Int) {
        val item = all[position]
        holder.itemView.setOnClickListener {
            if(context is UtilityActivity){
//                EmployeeHandler.shared().repository.employeeLiveData.postValue(item)
//                val activity = BusinessHandler.shared().activity as? BusinessActivity
//                activity?.navController?.navigate(R.id.business_details_details_container)
            }
        }
        holder.bind(item,onSelect)
    }
}