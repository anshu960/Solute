package com.solute.ui.business.inventory.analytics

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.friendly.framework.feature.sync.BusinessAnalytics
import com.solute.MainActivity
import com.solute.ui.business.BusinessActivity

class AnalyticsAdapter(val context: Context, val fragment: Fragment?, val allAnalytics: List<BusinessAnalytics>) :
    RecyclerView.Adapter<AnalyticsViewHolder>() {

    override fun getItemCount(): Int {
        return allAnalytics.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnalyticsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return AnalyticsViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: AnalyticsViewHolder, position: Int) {

        val item = allAnalytics[position]
        holder.itemView.setOnClickListener {
            if(context is MainActivity){
                val mainActivty = context as MainActivity
                val intent = Intent(mainActivty, BusinessActivity::class.java)
                mainActivty.startActivity(intent)
            }
        }
        holder.bind(fragment,item)
    }
}