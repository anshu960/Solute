package com.solute.utility

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.utilitykit.Constants.Key.Companion.invoiceNumber
import com.utilitykit.UtilityActivity

class WhatsappManager {
     fun sendInvoice(activity:UtilityActivity?,mobileNumber:String,invoiceNumber:Long,amount :Float) {
        val message = "Solute\n" +
                "Invoice Generate\n" +
                "Invoice Number : $invoiceNumber \n" +
                "Invoice Amount : $amount\n" +
                "You can see your invoice anytime at link below\n" +
                " https://solute.app/#/receipt?id=$invoiceNumber"
        try {
            activity?.packageManager?.getPackageInfo("com.whatsapp", PackageManager.GET_ACTIVITIES)
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://wa.me/$mobileNumber?text=$message")
            )
            intent.setPackage("com.whatsapp")
            activity?.startActivity(intent)
        } catch (e: PackageManager.NameNotFoundException) {
            activity?.toast("Whatsapp app not installed in your phone")
            e.printStackTrace()
        }
    }
}