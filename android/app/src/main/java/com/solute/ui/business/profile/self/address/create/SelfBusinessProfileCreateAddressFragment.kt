package com.solute.ui.business.profile.self.address.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
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
import com.hbb20.CountryCodePicker
import com.solute.R
import com.solute.utility.location.Location
import com.solute.utility.location.locationListener

class SelfBusinessProfileCreateAddressFragment : Fragment(), OnMapReadyCallback {
    var location: Location?=null
    private var mMap: GoogleMap? = null
    private  var mapView : MapView? = null
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    var latitudeField : TextInputEditText? = null
    var longitudeField : TextInputEditText? = null
    var saveButton : Button? = null
    var countryCode : CountryCodePicker? = null
    var stateField : TextInputEditText? = null
    var cityField : TextInputEditText? = null
    var areaField : TextInputEditText? = null
    var landmarkField : TextInputEditText? = null
    var zipCodeField : TextInputEditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
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

        stateField = view.findViewById(R.id.create_address_state_tiet)
        cityField = view.findViewById(R.id.create_address_city_tiet)
        areaField = view.findViewById(R.id.create_address_area_tiet)
        landmarkField = view.findViewById(R.id.create_address_landmark_tiet)
        zipCodeField = view.findViewById(R.id.create_address_zipcode_tiet)

        mapView = view.findViewById(R.id.business_address_map_view) as MapView
        mapView?.getMapAsync(this)
        mapView?.visibility = View.GONE

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
                latitudeField?.setText(sydney!!.latitude.toString())
                longitudeField?.setText(sydney!!.longitude.toString())
                mMap?.addMarker(MarkerOptions().position(sydney!!).title("Hi").snippet("Let's go!"))
                mMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney!!, 14f))
            }
        })
        return view
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


}
