package com.bharat.bandhu.ui.home.account

import android.content.Intent
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.bharat.bandhu.R
import com.bharat.bandhu.ui.launch.LaunchActivity
import com.bharat.bandhu.ui.onboard.LoginActivity
import com.friendly.framework.Defaults
import com.friendly.framework.constants.KeyConstant
import com.friendly.framework.dataclass.FriendlyUser
import com.friendly.framework.feature.customer.handler.CustomerHandler
import com.google.gson.Gson
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.json.JSONObject

class AccountFragment : Fragment() {
    val user = FriendlyUser()
    var barcodeText : TextView?  = null
    var barcodeImageView : ImageView?  = null
    var logoImageView : ImageView?  = null
    var logoutCard : CardView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        CustomerHandler.shared().repository.customer.observe(this){customer->
            CoroutineScope(Job() + Dispatchers.Main).launch {
                if(customer != null){
                    val jsonData = Gson().toJson(customer)
                    Defaults.shared().store(KeyConstant.membershipDetails,jsonData)
                    loadMembershipInUI()
                }else{
                    loadMembershipInUI()
                }
            }
        }
        getCustomerDetails()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_account, container, false)
        barcodeImageView = view.findViewById(R.id.home_barcode_image)
        logoImageView = view.findViewById(R.id.home_logo_image)
        barcodeText = view.findViewById(R.id.home_barcode_number)
        loadMembershipInUI()
        logoutCard = view.findViewById(R.id.home_power_card)
        logoutCard?.setOnClickListener {
            val intent = Intent(requireContext(),LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            Defaults.shared().remove(KeyConstant.membershipDetails)
            Defaults.shared().remove(KeyConstant.loginDetails)
            startActivity(intent)
        }
        return view
    }
    fun getCustomerDetails(){
        val request = JSONObject()
        request.put(KeyConstant.userId,user._id)
        request.put(KeyConstant.businessID,"6364c00fb25244fcf46425f0")
        request.put(KeyConstant.mobileNumber,user.mobile)
//        startActivityIndicator()
        CustomerHandler.shared().viewModel?.findCustomerByMobile(user.mobile )
        loadMembershipInUI()
    }

    fun loadMembershipInUI(){
        val membership = Defaults.shared().json(KeyConstant.membershipDetails)
        val hint = "Membership Details Not Found\nPlease Visit our Store to get your Membership"
        if(membership.has(KeyConstant.customerBarcode)){
            val barcodeNumber = membership.getString(KeyConstant.customerBarcode)
            if(barcodeNumber.isNotEmpty()){
                generateBarcode(barcodeNumber)
                barcodeText?.text = barcodeNumber
            }else{
                barcodeText?.text = hint
            }
        }else{
            barcodeText?.text = hint
        }
    }

    fun generateBarcode(code:String){
        val multiFormatWriter = MultiFormatWriter()
        val text = code
        var width = Resources.getSystem().getDisplayMetrics().widthPixels
        var height = Resources.getSystem().getDisplayMetrics().heightPixels/4
        Log.d("Dimension","Widht : $width height $height")
        try {
            val bitMatrix = multiFormatWriter.encode(
                text,
                BarcodeFormat.CODE_128,
                width,
                height
            )
            val bitmap =
                Bitmap.createBitmap(width,height, Bitmap.Config.RGB_565)
            for (i in 0 until width) {
                for (j in 0 until height) {
                    bitmap.setPixel(i, j, if (bitMatrix[i, j]) Color.BLACK else Color.WHITE)
                }
            }
            barcodeImageView?.setImageBitmap(bitmap)
        } catch (e: WriterException) {
            e.printStackTrace()
        }
    }
}