package com.solute.ui.business.product.detail.inventory.SelectUnit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.solute.R
class SelectMeasurementUnit : Fragment() {

    var recycler : RecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_select_measurement_unit, container, false)
        recycler = view.findViewById(R.id.measurement_recycler)
        return  view
    }

}