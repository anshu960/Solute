package com.solute.ui.business.NavigationHeader

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.solute.R

class NavigationHeader  : Fragment() {

    var businessIconImage : ImageView? = null
    var businessName : TextView? = null
    var businessDescription : TextView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.nav_header_business, container, false)
        businessIconImage = view.findViewById(R.id.nav_header_business_img)
        businessName = view.findViewById(R.id.nav_header_business_name)
        businessDescription = view.findViewById(R.id.nav_header_business_description)
        businessName?.text = ""
        businessDescription?.text = ""
        return view
    }
}