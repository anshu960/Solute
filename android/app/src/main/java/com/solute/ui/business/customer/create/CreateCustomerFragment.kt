package com.solute.ui.business.customer.create

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText
import com.solute.R
import com.utilitykit.feature.customer.handler.CustomerHandler
import com.utilitykit.feature.customer.model.Customer


/**
 * A simple [Fragment] subclass.
 * Use the [CreateCustomerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CreateCustomerFragment : Fragment() {

    var name : TextInputEditText? = null
    var mobile : TextInputEditText? = null
    var email : TextInputEditText? = null
    var barcode : TextInputEditText? = null
    var saveButton : Button? = null
    var customer : Customer? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_create_customer, container, false)
        name = view.findViewById(R.id.create_customer_name_tiet)
        mobile = view.findViewById(R.id.create_customer_mobile_tiet)
        email = view.findViewById(R.id.create_customer_email_tiet)
        barcode = view.findViewById(R.id.create_customer_barcode_tiet)
        saveButton = view.findViewById(R.id.create_customer_save_btn)
        saveButton?.setOnClickListener { onClickSave() }
        loadSelectedCustomer()
        return view
    }

    fun loadSelectedCustomer(){
        if(CustomerHandler.shared().repository.customer.value != null){
            customer = CustomerHandler.shared().repository.customer.value
            name?.setText(customer?.Name)
            mobile?.setText(customer?.MobileNumber)
            email?.setText(customer?.EmailID)
            barcode?.setText(customer?.Barcode)
        }
    }

    fun onClickSave(){
        if(customer != null){

        }
    }

}