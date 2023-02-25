package com.solute.ui.business.inventory.analytics

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.friendly.framework.feature.sync.BusinessAnalytics
import com.solute.R


class AnalyticsViewHolder(inflater: LayoutInflater, parent: ViewGroup) : RecyclerView.ViewHolder(
    inflater.inflate(
        R.layout.recycler_item_analytics, parent, false
    )
) {
    private var title: TextView? = null
    private var value: TextView? = null
    private var footer: TextView? = null
    init {
        title = itemView.findViewById(R.id.recycler_item_analytics_title)
        value = itemView.findViewById(R.id.recycler_item_analytics_value)
        footer = itemView.findViewById(R.id.recycler_item_analytics_footer)
    }

    fun bind(fragment: Fragment?, analytics: BusinessAnalytics) {
        title?.text = analytics.title
        value?.text = analytics.value.toString()
        footer?.text = analytics.footer
    }
}