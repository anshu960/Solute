package com.solute.ui.business.profile.self.finance

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.solute.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SelfBusinessProfileFinanceFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SelfBusinessProfileFinanceFragment : Fragment() {

    var addButton : FloatingActionButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_self_business_profile_finance, container, false)
        addButton = view.findViewById(R.id.self_business_profile_finance_fab)
        addButton?.setOnClickListener { onClickAdd() }
        return view
    }

    fun onClickAdd(){

    }
}