package com.solute.ui.business.profile.self.address.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.friendly.framework.UtilityActivity
import com.friendly.framework.feature.address.model.Address
import com.solute.ui.business.profile.self.address.viewHolder.AddressViewHolder


class AddressAdapter(val context: Context, private val all: ArrayList<Address>, var onSelect:((address: Address) -> Unit)? = null) :
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
                onSelect?.let { it1 -> it1(item) }
            }
        }
        holder.bind(item,onSelect)
    }
}