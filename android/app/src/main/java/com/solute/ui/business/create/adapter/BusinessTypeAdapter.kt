package com.solute.ui.business.create.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.friendly.framework.feature.businessType.handler.BusinessTypeHandler
import com.friendly.framework.feature.businessType.model.BusinessType
import com.solute.R
import com.solute.ui.business.create.CreateBusinessActivity
import com.solute.ui.business.create.SelectBusinessTypeActivity
import com.squareup.picasso.Picasso


class BusinessTypeAdapter(val context: Context, val allBusinessType: ArrayList<BusinessType>) :
    RecyclerView.Adapter<BusinessTypeViewHolder>() {

    override fun getItemCount(): Int {
        return allBusinessType.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusinessTypeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return BusinessTypeViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: BusinessTypeViewHolder, position: Int) {
        val item = allBusinessType[position]
        holder.itemView.setOnClickListener {
            if(context is SelectBusinessTypeActivity){
                val activity = context
                val intent = Intent(activity, CreateBusinessActivity::class.java)
                BusinessTypeHandler.shared().repository.businessType = item
                activity.startActivity(intent)
            }
        }
        holder.bind(item)
    }
}

class BusinessTypeViewHolder(inflater: LayoutInflater, parent: ViewGroup) : RecyclerView.ViewHolder(
    inflater.inflate(
        R.layout.recycler_item_business_type, parent, false
    )
) {
    private var businessTypeImage: ImageView? = null
    private var businessTypeName: TextView? = null


    init {
        businessTypeName = itemView.findViewById(R.id.recycler_business_type_title)
        businessTypeImage = itemView.findViewById(R.id.recycler_business_type_image)
    }

    fun bind(businessType: BusinessType) {
        businessTypeName?.text = businessType.Name
        val picasso = Picasso.get()
        if(businessType.Image.count() > 0){
            picasso.load(businessType.Image[0]).into(businessTypeImage)
        }
    }
}