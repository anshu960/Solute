package com.bharat.bandhu.ui

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bharat.bandhu.R
import com.friendly.framework.Defaults
import com.friendly.framework.UtilityActivity
import com.friendly.framework.constants.KeyConstant
import com.friendly.framework.dataclass.FriendlyUser
import com.friendly.framework.socket.SocketEvent
import com.friendly.framework.socket.SocketService
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException
import org.json.JSONObject


class MainActivity : UtilityActivity() {

    val user = FriendlyUser()
    var generateButton : Button?  = null
    var barcodeText : TextView?  = null
    var barcodeImageView : ImageView?  = null
    var logoImageView : ImageView?  = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        barcodeImageView = findViewById(R.id.home_barcode_image)
        logoImageView = findViewById(R.id.home_logo_image)
        barcodeText = findViewById(R.id.home_barcode_number)
        getCustomerDetails()
    }

    override fun onResume() {
        super.onResume()
        getCustomerDetails()
    }

    fun getCustomerDetails(){
        val request = JSONObject()
        request.put(KeyConstant.userId,user._id)
        request.put(KeyConstant.businessID,"6364c00fb25244fcf46425f0")
        request.put(KeyConstant.mobileNumber,user.mobile)
        SocketService.shared().onEvent={ event, data->
            Log.d("Received",data.toString())
            if(data.has(KeyConstant.payload)){
                val payload = data.getJSONArray(KeyConstant.payload)
                if(payload.length() > 0){
                    val membership = payload.getJSONObject(0)
                    if(membership.has(KeyConstant._id)){
                        Defaults.shared().store(KeyConstant.membershipDetails,membership.toString())
                        this.runOnUiThread { this.loadMembershipInUI() }
                    }
                }
            }
        }
        SocketService.shared().send(SocketEvent.findCustomerByMobile,request)
        loadMembershipInUI()
    }

    fun loadMembershipInUI(){
        val membership = Defaults.shared().json(KeyConstant.membershipDetails)
        val hint = "Membership Details Not Found\nPlease Visit our Store to get your Membership"
        if(membership.has(KeyConstant.barcode)){
            val barcodeNumber = membership.getString(KeyConstant.barcode)
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
            barcodeImageView!!.setImageBitmap(bitmap)
        } catch (e: WriterException) {
            e.printStackTrace()
        }
    }


}