package com.solute.ui.onBoardBusiness

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.friendly.framework.feature.business.handler.BusinessHandler
import com.friendly.framework.feature.business.viewModel.BusinessViewModalFactory
import com.friendly.framework.feature.business.viewModel.BusinessViewModel
import com.google.android.material.textfield.TextInputEditText
import com.solute.MainActivity
import com.solute.R
import com.solute.app.App
import com.solute.navigation.AppNavigator


class CreateBusinessFragment : Fragment() {
    var backButton : ImageButton? = null
    var businessNameText : TextInputEditText? = null
    var businessGSTText : TextInputEditText? = null
    var businessPANText : TextInputEditText? = null
    var businessAddressText : TextInputEditText? = null
    var businessEmailText : TextInputEditText? = null
    var businessMobileText : TextInputEditText? = null
    var saveButton : Button? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_create_business, container, false)
        businessNameText = view.findViewById(R.id.create_business_name_tiet)
        businessGSTText = view.findViewById(R.id.create_business_gst_tiet)
        businessPANText = view.findViewById(R.id.create_business_pan_tiet)
        businessAddressText = view.findViewById(R.id.create_business_address_tiet)
        businessEmailText = view.findViewById(R.id.create_business_email_tiet)
        businessMobileText = view.findViewById(R.id.create_business_mobile_tiet)
        saveButton = view.findViewById(R.id.create_business_save_number)
        saveButton?.setOnClickListener { onClickSave() }
        BusinessHandler.shared().onCreateBusinessResponse={
            AppNavigator.shared().navigateToHome()
        }
        return view
    }

    fun onClickSave(){
        var name = ""
        var gst = ""
        var pan = ""
        var address = ""
        var email = ""
        var mobile = ""
        if(businessNameText?.text != null && businessNameText!!.text.toString() != ""){
            name = businessNameText!!.text.toString()
        }else{
            (this.context as MainActivity).toast("Please enter name of the business")
            return
        }
        if(businessGSTText?.text != null && businessGSTText!!.text.toString() != ""){
            gst = businessGSTText!!.text.toString()
        }
        if(businessPANText?.text != null && businessPANText!!.text.toString() != ""){
            pan = businessPANText!!.text.toString()
        }
        if(gst == "" && pan == ""){
            (this.context as MainActivity).toast("Please enter GST or PAN of the business")
            return
        }
        if(businessAddressText?.text != null && businessAddressText!!.text.toString() != ""){
            address = businessAddressText!!.text.toString()
        }else{
            (this.context as MainActivity).toast("Please enter address of the business")
            return
        }
        if(businessEmailText?.text != null && businessEmailText!!.text.toString() != ""){
            email = businessEmailText!!.text.toString()
        }
        if(businessMobileText?.text != null && businessMobileText!!.text.toString() != ""){
            mobile = businessMobileText!!.text.toString()
        }else{
            (this.context as MainActivity).toast("Please enter Mobile Number of the business")
            return
        }
        BusinessHandler.shared().viewModal?.createNewBusiness(name,gst,pan,address,email,mobile)
    }

}