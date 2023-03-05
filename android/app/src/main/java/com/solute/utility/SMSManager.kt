package com.solute.utility

import android.content.Intent
import android.net.Uri
import android.util.Log
import com.friendly.framework.UtilityActivity
import com.friendly.framework.dataclass.FriendlyUser
import com.friendly.framework.feature.invoice.model.CustomerInvoice
import com.solute.app.App



class SMSManager {
    companion object{
        val inviteSms = "There is a great application 'Friendly' to Chat and Meet Friendly people around the world\n" +
                "https://play.google.com/store/apps/details?id=com.solute" +
                "\nCan't wait to see you there\n"
    }


    fun sendSms(activity : UtilityActivity?, mobile:String, message:String){
        val uri: Uri = Uri.parse("smsto:$mobile")
        val intent = Intent(Intent.ACTION_SENDTO, uri)
        intent.putExtra("sms_body", message)
        activity?.startActivity(intent)
    }


    fun sendInvoiceReceipt(invoiceNumber:Int,amount :Float,customerInvoice: CustomerInvoice){
        var message = "Solute\n"
        message += "Invoice Generate\n"
        message += "Invoice Number : $invoiceNumber\n"
        message += "Invoice Amount : $amount\n"
        message += "You can see your invoice anytime at link below\n"
        message += customerInvoice.ShareLink
            sendSms(null,"+919031570222",message)
    }
    fun sendInvoiceReceipt(activity:UtilityActivity?,mobileNumber:String,invoiceNumber:Long,amount :Float,customerInvoice: CustomerInvoice){
        var message = "Solute\n"
        message += "Invoice Generate\n"
        message += "Invoice Number : $invoiceNumber\n"
        message += "Invoice Amount : $amount\n"
        message += "You can see your invoice anytime at link below\n"
        message += customerInvoice.ShareLink
        sendSms(activity,mobileNumber,message)
    }

    fun shareInvoice(activity:UtilityActivity?,invoiceNumber:Long,amount :Float,customerInvoice: CustomerInvoice){
        val shareText = Intent(Intent.ACTION_SEND)
        shareText.type = "text/plain"
        var message = "Solute\n"
        message += "Invoice Generate\n"
        message += "Invoice Number : $invoiceNumber\n"
        message += "Invoice Amount : $amount\n"
        message += "You can see your invoice anytime at link below\n"
        message += customerInvoice.ShareLink
        shareText.putExtra(Intent.EXTRA_SUBJECT, "Subject from my application")
        shareText.putExtra(Intent.EXTRA_TEXT, message)
        activity?.startActivity(Intent.createChooser(shareText, "Share Via"))
    }


}