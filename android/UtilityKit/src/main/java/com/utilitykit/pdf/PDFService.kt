package com.utilitykit.pdf

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.content.res.Resources
import android.graphics.*
import android.graphics.pdf.PdfDocument
import android.graphics.pdf.PdfDocument.PageInfo
import android.os.Build
import androidx.core.app.ActivityCompat
import com.itextpdf.text.pdf.PdfReader
import com.itextpdf.text.pdf.parser.PdfTextExtractor
import com.utilitykit.UtilityActivity
import com.utilitykit.feature.cart.model.Sale
import com.utilitykit.feature.invoice.model.CustomerInvoice
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


class PDFService {
    // declaring width and height
    // for our PDF file.
    var PERMISSIONS: Array<String> = arrayOf<String>(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )
    var file_name_path = ""
    var PERMISSION_ALL = 1

    var pageHeight = 1120
    var pageWidth = 792
    fun createInvoice(context: Context?,invoice: CustomerInvoice,sales:ArrayList<Sale>) :PdfDocument{

        if (!hasPermissions(context, *PERMISSIONS)) {
            ActivityCompat.requestPermissions(context as UtilityActivity, PERMISSIONS, PERMISSION_ALL)
        }

        var file: File =
            File(context!!.getExternalFilesDir(null)!!.getAbsolutePath(), "pdfsdcard_location")
        if (!file.exists()) {
            file.mkdir()
        }
        val perm = hasPermissions(context, *PERMISSIONS)
        val bounds = Rect()
        val pageWidth = 300
        val pageheight = 470
        val pathHeight = 2
        val fileName = "mypdf"
        file_name_path = "/pdfsdcard_location/$fileName.pdf"
        val myPdfDocument = PdfDocument()
        val paint = Paint()
        val paint2 = Paint()
        val path = Path()
        val myPageInfo = PageInfo.Builder(pageWidth, pageheight, 1).create()
        val documentPage = myPdfDocument.startPage(myPageInfo)
        val canvas: Canvas = documentPage.canvas
        var y = 25 // x = 10,

        //int x = (canvas.getWidth() / 2);
        //int x = (canvas.getWidth() / 2);
        var x = 10

        paint.getTextBounds(
            "My Invoice Title",
            0,
            "My Invoice Title".length,
            bounds
        )
        x = canvas.getWidth() / 2 - bounds.width() / 2
        canvas.drawText("My Invoice Title", x.toFloat(), y.toFloat(), paint)

        paint.getTextBounds(
            "My Invoice Sub Title",
            0,
            "My Invoice Sub Title".length,
            bounds
        )
        x = canvas.getWidth() / 2 - bounds.width() / 2
        y += (paint.descent() - paint.ascent()).toInt()
        canvas.drawText("My Invoice Sub Title", x.toFloat(), y.toFloat(), paint)

        y += (paint.descent() - paint.ascent()).toInt()
        canvas.drawText("", x.toFloat(), y.toFloat(), paint)

        //horizontal line

        //horizontal line
        path.lineTo(pageWidth.toFloat(), pathHeight.toFloat())
        paint2.color = Color.GRAY
        paint2.style = Paint.Style.STROKE
        path.moveTo(x.toFloat(), y.toFloat())

        canvas.drawLine(0F, y.toFloat(), pageWidth.toFloat(), y.toFloat(), paint2)

        //blank space

        //blank space
        y += (paint.descent() - paint.ascent()).toInt()
        canvas.drawText("", x.toFloat(), y.toFloat(), paint)

        y += (paint.descent() - paint.ascent()).toInt()
        x = 10
        canvas.drawText("My Invoice Sub Title", x.toFloat(), y.toFloat(), paint)

        y += (paint.descent() - paint.ascent()).toInt()
        x = 10
        canvas.drawText("My Invoice Sub Title", x.toFloat(), y.toFloat(), paint)

        //blank space

        //blank space
        y += (paint.descent() - paint.ascent()).toInt()
        canvas.drawText("", x.toFloat(), y.toFloat(), paint)

        //horizontal line

        //horizontal line
        path.lineTo(pageWidth.toFloat(), pathHeight.toFloat())
        paint2.color = Color.GRAY
        paint2.style = Paint.Style.STROKE
        path.moveTo(x.toFloat(), y.toFloat())
        canvas.drawLine(0F, y.toFloat(), pageWidth.toFloat(), y.toFloat(), paint2)

        //blank space

        //blank space
        y += (paint.descent() - paint.ascent()).toInt()
        canvas.drawText("", x.toFloat(), y.toFloat(), paint)

        val res: Resources = context!!.getResources()
//        val bitmap = BitmapFactory.decodeResource(res, R.drawable.logo)
//        val b = Bitmap.createScaledBitmap(bitmap, 100, 50, false)
//        canvas.drawBitmap(b, x, y, paint)
        y += 25
        canvas.drawText("", 120F, y.toFloat(), paint)



        myPdfDocument.finishPage(documentPage)

         file =
            File(context.getExternalFilesDir(null)?.getAbsolutePath().toString() + file_name_path)
        try {
            myPdfDocument.writeTo(FileOutputStream(file))
        } catch (e: IOException) {
            e.printStackTrace()
        }

        myPdfDocument.close()

//        viewPdfFile(context)
        return myPdfDocument
    }
    fun viewPdfFile(context: Context) {
//        file_name_path = file_name_path.replace("file","content")
        val file =
            File(context.getExternalFilesDir(null)?.getAbsolutePath().toString() + file_name_path)
        try {
            val pdfReader = PdfReader(file.path)
            val stringParse = PdfTextExtractor.getTextFromPage(pdfReader, 1).trim { it <= ' ' }
            pdfReader.close()
//            textView.setText(stringParse)
        } catch (e: Exception) {
            e.printStackTrace()
//            textView.setText("Error in Reading")
        }
    }
    fun hasPermissions(context: Context?, vararg permissions: String?): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (permission in permissions) {
                if (ActivityCompat.checkSelfPermission(
                        context,
                        permission!!
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    return false
                }
            }
        }
        return true
    }

}