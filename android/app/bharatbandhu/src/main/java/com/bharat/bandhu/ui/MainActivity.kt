package com.bharat.bandhu.ui

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bharat.bandhu.R
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException
import com.utilitykit.Constants.Key
import com.utilitykit.Defaults
import com.utilitykit.socket.SocketEvent
import com.utilitykit.dataclass.User
import com.utilitykit.socket.SocketService
import org.json.JSONObject


class MainActivity : AppCompatActivity() {

    val user = User()
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
        request.put(Key.userId,user._id)
        request.put(Key.businessID,"6364c00fb25244fcf46425f0")
        request.put(Key.mobileNumber,user.mobile)
        SocketService.shared().onEvent={event,data->
            Log.d("Received",data.toString())
            if(data.has(Key.payload)){
                val payload = data.getJSONArray(Key.payload)
                if(payload.length() > 0){
                    val membership = payload.getJSONObject(0)
                    if(membership.has(Key._id)){
                        Defaults.store(Key.membershipDetails,membership.toString())
                        this.runOnUiThread { this.loadMembershipInUI() }
                    }
                }
            }
        }
        SocketService.shared().send(SocketEvent.findCustomerByMobile,request)
        loadMembershipInUI()
    }

    fun loadMembershipInUI(){
        val membership = Defaults.json(Key.membershipDetails)
        val hint = "Membership Details Not Found\nPlease Visit our Store to get your Membership"
        if(membership.has(Key.barcode)){
            val barcodeNumber = membership.getString(Key.barcode)
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