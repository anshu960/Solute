package com.solute.ui.business.profile.self.address.viewHolder

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.solute.R
import com.utilitykit.feature.address.model.Address

class AddressViewHolder (inflater: LayoutInflater, parent: ViewGroup) : RecyclerView.ViewHolder(
    inflater.inflate(
        R.layout.recycler_item_address, parent, false
    )
) {
    private var addressText: TextView? = null


    init {
        addressText = itemView.findViewById(R.id.recycler_item_address_address)
    }

    fun bind(address: Address, onSelect: ((address: Address) -> Unit)? = null) {
        addressText?.text = "${address.LandMark}, ${address.Area}, ${address.City}, ${address.State}, ${address.State}, ${address.ZipCode}, ${address.Country}"
    }
}