package com.solute.utility

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import com.friendly.framework.UtilityActivity
import com.friendly.framework.feature.customer.model.Customer
import com.friendly.framework.feature.invoice.model.CustomerInvoice


class WhatsappManager {
     fun sendInvoice(activity: UtilityActivity?, customer: Customer?, invoiceNumber:Long, amount :Float, customerInvoice: CustomerInvoice) {
         var message = "Solute\n"
         message += "Invoice Generate\n"
         message += "Invoice Number : $invoiceNumber\n"
         message += "Invoice Amount : $amount\n"
         message += "You can see your invoice anytime at link below\n"
         message += customerInvoice.ShareLink

        try {
            activity?.packageManager?.getPackageInfo("com.whatsapp", PackageManager.GET_ACTIVITIES)
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://wa.me/${customer?.DialCode?.trim()}${customer?.MobileNumber?.trim()}?text=$message")
            )
            intent.setPackage("com.whatsapp")
            activity?.startActivity(intent)
        } catch (e: PackageManager.NameNotFoundException) {
            activity?.toast("Whatsapp app not installed in your phone")
            e.printStackTrace()
        }
    }
}