package com.solute.deepLink

import android.net.Uri
import com.friendly.framework.feature.invoice.handler.InvoiceHandler
import com.solute.navigation.AppNavigator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Long

class DeepLinkHandler {
    fun processPendingLink(link:Uri?){
        if(link != null){
            val parts = link.toString().split("/#/")
            if (parts.count() == 2){
                val featureAndId = parts[1].split("?id=")
                if(featureAndId.count() == 2){
                    processFeature(featureAndId[0],featureAndId[1])
                }
            }
        }
    }
    fun processFeature(name:String,id:String){
        when (name) {
            "receipt" -> {
                CoroutineScope(Job() + Dispatchers.IO).launch {
                    val invoiceNumber = id.toLong()
                    InvoiceHandler.shared().invoiceNumber = invoiceNumber
                    AppNavigator.shared().goToReceiptDetails()
                }
            }
            "" -> print("x == 2")
            else -> { // Note the block
                print("x is neither 1 nor 2")
            }
        }
    }
}