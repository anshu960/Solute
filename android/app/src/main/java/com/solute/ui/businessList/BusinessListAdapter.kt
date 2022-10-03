package com.solute.ui.businessList

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.solute.MainActivity
import com.solute.R
import com.solute.ui.business.BusinessMainActivity
import com.utilitykit.feature.business.handler.BusinessHandler
import com.utilitykit.feature.business.model.Business
import com.utilitykit.feature.sync.SyncHandler

class BusinessListAdapter(val context: Context, val allBusiness: List<Business>) :
    RecyclerView.Adapter<BusinessViewHolder>() {

    override fun getItemCount(): Int {
        return allBusiness.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusinessViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return BusinessViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: BusinessViewHolder, position: Int) {
        val item = allBusiness[position]
        holder.itemView.setOnClickListener {
            if(context is MainActivity){
                val mainActivty = context as MainActivity
                val intent = Intent(mainActivty,BusinessMainActivity::class.java)
                BusinessHandler.shared().repository.business = item
                SyncHandler.shared().clearBusinessAnalytics()
                SyncHandler.shared().syncAllBusinessData()
                mainActivty.startActivity(intent)
            }
        }
        holder.bind(item)
    }
}

class BusinessViewHolder(inflater: LayoutInflater, parent: ViewGroup) : RecyclerView.ViewHolder(
    inflater.inflate(
        R.layout.recycler_item_business, parent, false
    )
) {
    private var businessName: TextView? = null
    private var businessMobile: TextView? = null
    private var businessAddress: TextView? = null

    init {
        businessName = itemView.findViewById(R.id.recycler_item_business_name_txt)
        businessMobile = itemView.findViewById(R.id.recycler_item_business_mobile_txt)
        businessAddress = itemView.findViewById(R.id.recycler_item_business_location_txt)
    }

    fun bind(business: Business) {
        businessName?.text = business.Name
        businessMobile?.text = business.MobileNumber
        businessAddress?.text = business.Address

    }
}