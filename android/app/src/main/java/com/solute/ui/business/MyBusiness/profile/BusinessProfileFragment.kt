package com.solute.ui.business.MyBusiness.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.solute.R
import com.utilitykit.feature.business.handler.BusinessHandler
import com.utilitykit.feature.business.model.Business
import com.utilitykit.feature.business.viewModel.BusinessViewModalFactory
import com.utilitykit.feature.business.viewModel.BusinessViewModel


/**
 * A simple [Fragment] subclass.
 * Use the [BusinessProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BusinessProfileFragment : Fragment() {

    private lateinit var businessViewModel: BusinessViewModel
    var business : Business? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        businessViewModel = ViewModelProvider(
            this,
            BusinessViewModalFactory(BusinessHandler.shared().repository)
        )[BusinessViewModel::class.java]
        businessViewModel.allBusiness
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_business_profile, container, false)

        return view
    }


}