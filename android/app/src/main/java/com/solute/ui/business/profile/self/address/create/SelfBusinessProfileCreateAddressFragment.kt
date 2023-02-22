package com.solute.ui.business.profile.self.address.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.textfield.TextInputEditText
import com.google.gson.Gson
import com.hbb20.CountryCodePicker
import com.solute.R
import com.solute.utility.location.Location
import com.solute.utility.location.locationListener
import com.utilitykit.Constants.Key
import com.utilitykit.dataclass.User
import com.utilitykit.feature.address.handler.AddressHandler
import com.utilitykit.feature.address.model.Address
import com.utilitykit.feature.address.viewModel.AddressViewModalFactory
import com.utilitykit.feature.address.viewModel.AddressViewModel
import com.utilitykit.feature.business.handler.BusinessHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONObject


class SelfBusinessProfileCreateAddressFragment : Fragment(), OnMapReadyCallback {
    var location: Location?=null
    var latitude: Double?=null
    var longitude: Double?=null
    private var mMap: GoogleMap? = null
    private  var mapView : MapView? = null
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var viewModal:AddressViewModel? = null

    var latitudeField : TextInputEditText? = null
    var longitudeField : TextInputEditText? = null
    var saveButton : Button? = null
    var updateButton : Button? = null
    var deleteButton : Button? = null
    var countryCode : CountryCodePicker? = null
    var stateField : TextInputEditText? = null
    var cityField : TextInputEditText? = null
    var areaField : TextInputEditText? = null
    var landmarkField : TextInputEditText? = null
    var zipCodeField : TextInputEditText? = null

    var existingAddress : Address? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        viewModal = ViewModelProvider(
            this,
            AddressViewModalFactory(AddressHandler.shared().repository)
        )[AddressViewModel::class.java]
        AddressHandler.shared().onCreateResponse={
            CoroutineScope(Job() + Dispatchers.Main).launch {
                BusinessHandler.shared().activity.toast("Address Created Successfully")
                findNavController().popBackStack()
            }
        }
        AddressHandler.shared().onUpdateResponse={
            CoroutineScope(Job() + Dispatchers.Main).launch {
                BusinessHandler.shared().activity.toast("Address Updated Successfully")
                findNavController().popBackStack()
            }
        }
        AddressHandler.shared().onDeleteResponse={
            CoroutineScope(Job() + Dispatchers.Main).launch {
                BusinessHandler.shared().activity.toast("Address Deleted Successfully")
                findNavController().popBackStack()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(
            R.layout.fragment_self_business_profile_create_address,
            container,
            false
        )
        countryCode = view.findViewById(R.id.country_code_picker)
        stateField = view.findViewById(R.id.create_address_state_tiet)
        cityField = view.findViewById(R.id.create_address_city_tiet)
        areaField = view.findViewById(R.id.create_address_area_tiet)
        landmarkField = view.findViewById(R.id.create_address_landmark_tiet)
        zipCodeField = view.findViewById(R.id.create_address_zipcode_tiet)

        mapView = view.findViewById(R.id.business_address_map_view) as MapView
        mapView?.getMapAsync(this)
        mapView?.visibility = View.GONE

        saveButton = view.findViewById(R.id.save_btn)
        saveButton?.setOnClickListener { this.onClickSave() }
        updateButton = view.findViewById(R.id.create_address_update_btn)
        updateButton?.setOnClickListener { this.onClickUpdate() }
        deleteButton = view.findViewById(R.id.create_address_delete_btn)
        deleteButton?.setOnClickListener { this.onClickDelete() }

        latitudeField = view.findViewById(R.id.create_address_latitude_tiet)
        longitudeField = view.findViewById(R.id.create_address_longitude_tiet)
        location= Location(requireActivity() as AppCompatActivity, object : locationListener {
            override fun locationResponse(locationResult: LocationResult) {
                mMap?.clear()
                val sydney = locationResult.lastLocation?.let {
                    LatLng(
                        it.latitude,
                        locationResult.lastLocation!!.longitude
                    )
                }
                latitude = sydney!!.latitude
                longitude = sydney!!.longitude
                latitudeField?.setText(sydney!!.latitude.toString())
                longitudeField?.setText(sydney!!.longitude.toString())
                mMap?.addMarker(MarkerOptions().position(sydney!!).title("Hi").snippet("Let's go!"))
                mMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney!!, 14f))
            }
        })
        loadExistingAddress()
        return view
    }

    fun loadExistingAddress(){
        existingAddress = AddressHandler.shared().repository.selected.value
        if(existingAddress != null){
            saveButton?.visibility = View.GONE
            updateButton?.visibility = View.VISIBLE
            deleteButton?.visibility = View.VISIBLE
            stateField?.setText(existingAddress?.State)
            cityField?.setText(existingAddress?.City)
            areaField?.setText(existingAddress?.Area)
            landmarkField?.setText(existingAddress?.LandMark)
            zipCodeField?.setText(existingAddress?.ZipCode)
        }else{
            saveButton?.visibility = View.VISIBLE
            updateButton?.visibility = View.GONE
            deleteButton?.visibility = View.GONE
        }
    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        location?.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
    override fun onStart() {
        super.onStart()
        location?.inicializeLocation()
    }
    override fun onPause() {
        super.onPause()
        location?.stopUpdateLocation()
    }

    fun onClickSave(){
        val addressObject = JSONObject()
        addressObject.put(Key.uniqueId,System.currentTimeMillis())
        addressObject.put(Key.countryCode,countryCode!!.selectedCountryCode)
        addressObject.put(Key.featureObjectID,BusinessHandler.shared().repository.business.value?.Id)
        addressObject.put(Key.userId, User()._id)
        addressObject.put(Key.name,"")
        addressObject.put(Key.state,stateField?.text.toString())
        addressObject.put(Key.city,cityField?.text.toString())
        addressObject.put(Key.area,areaField?.text.toString())
        addressObject.put(Key.landMark,landmarkField?.text.toString())
        addressObject.put(Key.zipCode,zipCodeField?.text.toString())
        val location = JSONArray()
        location.put(latitude)
        location.put(longitude)
        addressObject.put(Key.location, location)
//        addressObject.put(Key.longitude,longitude)
        viewModal?.createNew(addressObject)
    }

    fun onClickUpdate(){
        val addressObject = JSONObject()
        addressObject.put(Key.uniqueId,System.currentTimeMillis())
        addressObject.put(Key.countryCode,countryCode!!.selectedCountryCode)
        addressObject.put(Key.featureObjectID,BusinessHandler.shared().repository.business.value?.Id)
        addressObject.put(Key.userId, User()._id)
        addressObject.put(Key.name,"")
        addressObject.put(Key.state,stateField?.text.toString())
        addressObject.put(Key.city,cityField?.text.toString())
        addressObject.put(Key.area,areaField?.text.toString())
        addressObject.put(Key.landMark,landmarkField?.text.toString())
        addressObject.put(Key.zipCode,zipCodeField?.text.toString())
        val location = JSONArray()
        location.put(latitude)
        location.put(longitude)
        addressObject.put(Key.location, location)
//        addressObject.put(Key.longitude,longitude)
        viewModal?.updateExistingAddress(addressObject)
    }
    fun onClickDelete(){
        val request = JSONObject(Gson().toJson(existingAddress,Address::class.java))
        viewModal?.deleteExistingAddress(request)
    }

}
