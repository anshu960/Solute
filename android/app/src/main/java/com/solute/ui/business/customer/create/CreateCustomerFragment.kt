package com.solute.ui.business.customer.create

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.solute.R
import com.solute.ui.business.BusinessActivity
import com.solute.ui.business.customer.adapter.CustomerAdapter
import com.utilitykit.feature.business.handler.BusinessHandler
import com.utilitykit.feature.customer.handler.CustomerHandler
import com.utilitykit.feature.customer.model.Customer


/**
 * A simple [Fragment] subclass.
 * Use the [CreateCustomerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CreateCustomerFragment : Fragment() {
    val activity = BusinessHandler.shared().activity as? BusinessActivity
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
            (activity as AppCompatActivity).supportActionBar?.title = "Update Customer"
            customer = CustomerHandler.shared().repository.customer.value
            name?.setText(customer?.Name)
            mobile?.setText(customer?.MobileNumber)
            email?.setText(customer?.EmailID)
            barcode?.setText(customer?.Barcode)
        }else{
            (activity as AppCompatActivity).supportActionBar?.title = "Create Customer"
        }
    }

    fun onClickSave(){
        val nameVal = name?.text.toString()
        val mobileVal = mobile?.text.toString()
        val emailVal = email?.text.toString()
        val barcodeVal = barcode?.text.toString()
        if(nameVal.count() <=3){
            activity?.toast("Please enter customer name with more than 3 characters")
            return
        }
        if(mobileVal.count() <=8){
            activity?.toast("Please enter valid customer mobile")
            return
        }
        if(customer != null){
            CustomerHandler.shared().onCreateNewCustomer={
                activity?.runOnUiThread {
                    activity?.toast("Updated")
                }
            }
            customer?.Barcode = barcodeVal
            customer?.Name = nameVal
            customer?.MobileNumber = mobileVal
            customer?.EmailID = emailVal
            CustomerHandler.shared().viewModel?.updateCustomer(customer!!)
        }else{
            CustomerHandler.shared().onCreateNewCustomer={
                activity?.runOnUiThread {
                    activity?.toast("Created")
                }
            }
            CustomerHandler.shared().viewModel?.createNewCustomer(nameVal,mobileVal,emailVal,barcodeVal)
        }
    }

}