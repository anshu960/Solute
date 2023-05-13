package com.solute.pdf.util

import android.content.Context
import android.graphics.Bitmap
import com.google.zxing.BarcodeFormat
import com.itextpdf.text.Image
import java.io.ByteArrayOutputStream
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


fun Int.asString(context: Context): String {
    return context.getString(this)
}
//
//fun String.asBarCode(format: BarcodeFormat, width: Int, height: Int): Image {
//    val barcodeEncoder = BarcodeEncoder()
//    val bitmap = barcodeEncoder.encodeBitmap(this, format, width, height)
//    val stream = ByteArrayOutputStream()
//    bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
//    val byteArray = stream.toByteArray()
//    return Image.getInstance(byteArray)
//}


fun Long.toDateString(): String {
    val formatter = SimpleDateFormat("dd MMM yyyy", Locale.US)
    return formatter.format(Date(this))
}

