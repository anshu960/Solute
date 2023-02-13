package com.solute.utility

import android.content.Intent
import android.net.Uri
import android.util.Log
import com.solute.App
import com.utilitykit.UtilityActivity
import com.utilitykit.dataclass.ContactData
import com.utilitykit.dataclass.User
import com.utilitykit.feature.invoice.model.CustomerInvoice


class SMSManager {
    companion object{
        val inviteSms = "There is a great application 'Friendly' to Chat and Meet Friendly people around the world\n" +
                "https://play.google.com/store/apps/details?id=com.solute" +
                "\nCan't wait to see you there\n"
    }


    fun sendSms(activity : UtilityActivity?,mobile:String,message:String){
        val uri: Uri = Uri.parse("smsto:$mobile")
        val intent = Intent(Intent.ACTION_SENDTO, uri)
        intent.putExtra("sms_body", message)
        activity?.startActivity(intent)
    }

//    fun sendSMS(activity : UtilityActivity?,phoneNo: String, msg: String) {
//        if (ActivityCompat.checkSelfPermission(
//                App.applicationContext(),
//               SEND_SMS
//            ) == PackageManager.PERMISSION_GRANTED
//        ) {
//            try {
//                val smsMgrVar = SmsManager.getDefault()
//                smsMgrVar.sendTextMessage(phoneNo, null, msg, null, null)
//                Toast.makeText(
//                    App.applicationContext(), "Message Sent",
//                    Toast.LENGTH_LONG
//                ).show()
//            } catch (ErrVar: Exception) {
//                Toast.makeText(
//                    activity, ErrVar.message.toString(),
//                    Toast.LENGTH_LONG
//                ).show()
//                ErrVar.printStackTrace()
//            }
//        } else {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                activity?.requestPermissions(arrayOf(SEND_SMS), 10)
//            }else{
//                sendSms(activity,phoneNo,msg)
//            }
//        }
//
//    }


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

    fun sendInviteSms(contact: ContactData){
        val user = User()
        val componsedMessage = "Hi ${contact.name}\n" + inviteSms + "Regards\n${user.name}"
        val uri: Uri = Uri.parse("smsto:${contact.mobileNumber}")
        val intent = Intent(Intent.ACTION_SENDTO, uri)
        intent.putExtra("sms_body", componsedMessage)
        val activity = App.applicationContext().activity
        if(activity != null){
            activity!!.startActivity(intent)
        }else{
            Log.d("SMSManager","Unable to send SMS as the activity is null")
        }
    }
}