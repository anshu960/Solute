package com.bharat.bandhu

import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException


class MainActivity : AppCompatActivity() {

    var generateButton : Button?  = null
    var textInput : TextInputEditText?  = null
    var imageView : ImageView?  = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        generateButton = findViewById(R.id.generate_button)
        textInput = findViewById(R.id.mobile_number_text)
        imageView = findViewById(R.id.imageView)
        generateButton?.setOnClickListener { generateBarcode() }
    }

    fun generateBarcode(){
        val multiFormatWriter = MultiFormatWriter()
        val mobileNumber = textInput!!.text.toString()
        val text = mobileNumber
        try {
            val bitMatrix = multiFormatWriter.encode(
                text,
                BarcodeFormat.CODE_128,
                imageView!!.width,
                imageView!!.height
            )
            val bitmap =
                Bitmap.createBitmap(imageView!!.width, imageView!!.height, Bitmap.Config.RGB_565)
            for (i in 0 until imageView!!.width) {
                for (j in 0 until imageView!!.height) {
                    bitmap.setPixel(i, j, if (bitMatrix[i, j]) Color.BLACK else Color.WHITE)
                }
            }
            imageView!!.setImageBitmap(bitmap)
        } catch (e: WriterException) {
            e.printStackTrace()
        }
    }
}