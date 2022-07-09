package com.solute.utility

import android.content.Intent
import android.net.Uri
import android.util.Log
import com.solute.App
import com.solute.dataclass.ContactData
import com.solute.dataclass.User

class SMSManager {
    companion object{
        val inviteSms = "There is a great application 'Friendly' to Chat and Meet Friendly people around the world\n" +
                "https://play.google.com/store/apps/details?id=com.friendly" +
                "\nCan't wait to see you there\n"
    }


    fun sendSms(mobile:String,message:String){
        val uri: Uri = Uri.parse("smsto:$mobile")
        val intent = Intent(Intent.ACTION_SENDTO, uri)
        intent.putExtra("sms_body", message)
        App.applicationContext().activity?.startActivity(intent)
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