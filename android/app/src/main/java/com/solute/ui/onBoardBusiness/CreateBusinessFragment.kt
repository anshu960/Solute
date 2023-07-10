package com.solute.ui.onBoardBusiness

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.friendly.framework.constants.KeyConstant
import com.friendly.framework.dataclass.FriendlyUser
import com.friendly.framework.feature.business.handler.BusinessHandler
import com.friendly.framework.feature.business.viewModel.BusinessViewModalFactory
import com.friendly.framework.feature.business.viewModel.BusinessViewModel
import com.google.android.material.textfield.TextInputEditText
import com.shuhart.stepview.StepView
import com.solute.MainActivity
import com.solute.R
import com.solute.app.App
import com.solute.app.ToastService
import com.solute.navigation.AppNavigator
import org.json.JSONArray
import org.json.JSONObject


class CreateBusinessFragment : Fragment() {
    var currentTab = 0
    var businessNameText : TextInputEditText? = null
    var businessGSTText : TextInputEditText? = null
    var businessPANText : TextInputEditText? = null
    var businessAddressText : TextInputEditText? = null
    var businessEmailText : TextInputEditText? = null
    var businessMobileText : TextInputEditText? = null
    var saveButton : Button? = null
    var addressContainerCv : CardView? = null
    var infoContainerCv :CardView? = null

    var locationText: TextView? = null
    var locateButton: Button? = null
    var location:JSONObject = JSONObject()

    var addressNameText : TextInputEditText? = null
    var addressLocalityText : TextInputEditText? = null
    var addressCityText : TextInputEditText? = null
    var zipCode : TextInputEditText? = null
    var state : TextInputEditText? = null
    var landmark : TextInputEditText? = null

    var stepView : StepView? = null

    var addressObject = JSONObject()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                onBackPressed()
            }
        })
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_create_business, container, false)
        stepView = view.findViewById(R.id.create_product_step_view)
        addressContainerCv = view.findViewById(R.id.create_business_address)
        infoContainerCv = view.findViewById(R.id.create_business_info_cv)
        addressContainerCv?.visibility = View.GONE
        infoContainerCv?.visibility = View.VISIBLE
        businessNameText = view.findViewById(R.id.create_business_name_tiet)
        businessGSTText = view.findViewById(R.id.create_business_gst_tiet)
        businessPANText = view.findViewById(R.id.create_business_pan_tiet)
        businessEmailText = view.findViewById(R.id.create_business_email_tiet)
        businessMobileText = view.findViewById(R.id.create_business_mobile_tiet)
        saveButton = view.findViewById(R.id.create_business_save_number)
        saveButton?.text = resources.getString(R.string.next)
        saveButton?.setOnClickListener { onClickSave() }
        addressNameText = view.findViewById(R.id.create_business_address_house_tiet)
        addressLocalityText = view.findViewById(R.id.create_business_address_locality_tiet)
        addressCityText = view.findViewById(R.id.create_business_address_city_tiet)
        zipCode = view.findViewById(R.id.create_business_address_pincode_tiet)
        state = view.findViewById(R.id.create_business_address_state_tiet)
        landmark = view.findViewById(R.id.create_business_address_landmark_tiet)
        locationText = view.findViewById(R.id.create_business_address_location_tv)
        locateButton = view.findViewById(R.id.create_business_address_location_btn)
        locateButton?.setOnClickListener { onClickLocate() }
        BusinessHandler.shared().onCreateBusinessResponse={
            AppNavigator.shared().navigateToHome()
        }
        return view
    }

    fun onBackPressed(){
        if(currentTab == 1){
//            requireActivity().onBackPressed()
            currentTab = 0
            stepView?.go(currentTab, true)
            infoContainerCv?.visibility = View.VISIBLE
            addressContainerCv?.visibility = View.GONE
        }else{

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
//        if(businessAddressText?.text != null && businessAddressText!!.text.toString() != ""){
//            address = businessAddressText!!.text.toString()
//        }else{
//            (this.context as MainActivity).toast("Please enter address of the business")
//            return
//        }
        if(businessEmailText?.text != null && businessEmailText!!.text.toString() != ""){
            email = businessEmailText!!.text.toString()
        }
        if(businessMobileText?.text != null && businessMobileText!!.text.toString() != ""){
            mobile = businessMobileText!!.text.toString()
        }else{
            (this.context as MainActivity).toast("Please enter Mobile Number of the business")
            return
        }
        if(currentTab == 0){
            currentTab = 1
            stepView?.go(currentTab, true)
            infoContainerCv?.visibility = View.GONE
            addressContainerCv?.visibility = View.VISIBLE
            return
        }
        if(currentTab == 1){

        }
        val house = addressNameText?.text.toString()
        if(house == ""){
            ToastService.shared().toast("Please enter House/Flat Number")
            return
        }
        val locality = addressLocalityText?.text.toString()
        if(locality == ""){
            ToastService.shared().toast("Please enter Street or Locality or area name")
            return
        }
        val city = addressCityText?.text.toString()
        if(city == ""){
            ToastService.shared().toast("Please enter name of the City")
            return
        }
        val zip = zipCode?.text.toString()
        if(zip == ""){
            ToastService.shared().toast("Please enter your Postal Code or Zip Code")
            return
        }
        val stateVal = state?.text.toString()
        if(stateVal == ""){
            ToastService.shared().toast("Please enter your State")
            return
        }
        val landMark = landmark?.text.toString()
        if(landMark == ""){
            ToastService.shared().toast("Please enter nearby landmark")
            return
        }
        val user = FriendlyUser()
        val unixTime = System.currentTimeMillis()
        val addressObject = JSONObject()
        addressObject.put(KeyConstant.userId,user._id)
        addressObject.put(KeyConstant.uniqueId,unixTime)
        addressObject.put(KeyConstant.house,house)
        addressObject.put(KeyConstant.area,locality)
        addressObject.put(KeyConstant.city,city)
        addressObject.put(KeyConstant.zipCode,zip)
        addressObject.put(KeyConstant.state,stateVal)
        addressObject.put(KeyConstant.landMark,landMark)
        val locationarr = JSONArray()
        if(location.has(KeyConstant.latitude) && location.has(KeyConstant.longitude)){
            locationarr.put(location.get(KeyConstant.latitude))
            locationarr.put(location.get(KeyConstant.longitude))
        }
        addressObject.put(KeyConstant.location,locationarr)
        BusinessHandler.shared().viewModal?.createNewBusiness(name,gst,pan,addressObject,email,mobile)
    }

    fun onClickLocate(){
        (context as? MainActivity)?.getLocation { loc->
            location = loc
            locationText?.text = "${loc.getDouble(KeyConstant.latitude)}," + "${loc.getDouble(KeyConstant.longitude)}"
            ToastService.shared().toast(loc.toString())
        }
    }

}