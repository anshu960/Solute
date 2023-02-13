package com.solute.ui.business.profile.self.address

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.solute.R

class SelfBusinessProfileAddressFragment : Fragment() {


    var addBtn : FloatingActionButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_self_business_profile_address, container, false)
        addBtn = view.findViewById(R.id.address_list_create_fab_btn)
        addBtn?.setOnClickListener { onClickAdd() }
        return view
    }

    fun onClickAdd(){
        findNavController().navigate(R.id.navigate_to_create_address)
    }
}
