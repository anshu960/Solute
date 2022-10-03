package com.solute.ui.business.create

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText
import com.solute.R
import com.utilitykit.UtilityActivity
import com.utilitykit.feature.business.handler.BusinessHandler
import com.utilitykit.feature.business.viewModel.BusinessViewModalFactory
import com.utilitykit.feature.business.viewModel.BusinessViewModel

class CreateBusinessActivity : UtilityActivity() {
    var backButton : ImageButton? = null
    var businessNameText : TextInputEditText? = null
    var businessGSTText : TextInputEditText? = null
    var businessPANText : TextInputEditText? = null
    var businessAddressText : TextInputEditText? = null
    var businessEmailText : TextInputEditText? = null
    var businessMobileText : TextInputEditText? = null
    var saveButton : Button? = null

    private lateinit var businessViewModel: BusinessViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_business)
        this.backButton = findViewById(R.id.create_business_header_back)
        backButton?.setOnClickListener { onBackPressed() }
        businessNameText = findViewById(R.id.create_business_name_tiet)
        businessGSTText = findViewById(R.id.create_business_gst_tiet)
        businessPANText = findViewById(R.id.create_business_pan_tiet)
        businessAddressText = findViewById(R.id.create_business_address_tiet)
        businessEmailText = findViewById(R.id.create_business_email_tiet)
        businessMobileText = findViewById(R.id.create_business_mobile_tiet)
        saveButton = findViewById(R.id.create_business_save_number)
        saveButton?.setOnClickListener { onClickSave() }
        businessViewModel = ViewModelProvider(
            this,
            BusinessViewModalFactory(BusinessHandler.shared().repository)
        ).get(
            BusinessViewModel::class.java
        )
        BusinessHandler.shared().repository.businessLiveData.observe(this){
            if(it != null && it.Id != null){
                BusinessHandler.shared().fetchAllBusiness()
                alert("Congrats","Business create successfully, please go back to home to see your new Business")
            }else{
                toast("Oops!, Couldn't create at the moment")
            }
        }
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
            toast("Please enter name of the business")
            return
        }
        if(businessGSTText?.text != null && businessGSTText!!.text.toString() != ""){
            gst = businessGSTText!!.text.toString()
        }
        if(businessPANText?.text != null && businessPANText!!.text.toString() != ""){
            pan = businessPANText!!.text.toString()
        }
        if(gst == "" && pan == ""){
            toast("Please enter GST or PAN of the business")
            return
        }
        if(businessAddressText?.text != null && businessAddressText!!.text.toString() != ""){
            address = businessAddressText!!.text.toString()
        }else{
            toast("Please enter address of the business")
            return
        }
        if(businessEmailText?.text != null && businessEmailText!!.text.toString() != ""){
            email = businessEmailText!!.text.toString()
        }
        if(businessMobileText?.text != null && businessMobileText!!.text.toString() != ""){
            mobile = businessMobileText!!.text.toString()
        }else{
            toast("Please enter Mobile Number of the business")
            return
        }
        businessViewModel?.createNewBusiness(name,gst,pan,address,email,mobile)
    }
}