package com.friendly.framework.share

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import java.util.*

class Share {
    fun shareViaWhatsApp(activity: Activity?, message: String) {
        val whatsappIntent = Intent(Intent.ACTION_SEND)
        whatsappIntent.type = "text/plain"
//        whatsappIntent.setPackage(Constants.whatsappBusinessBundleId)
        whatsappIntent.putExtra(
            Intent.EXTRA_TEXT,
            message
        )
        try {
            if (Objects.requireNonNull(activity) != null) {
                Objects.requireNonNull(activity)?.startActivity(whatsappIntent)
            }
        } catch (ex: ActivityNotFoundException) {
            activity?.startActivity(
                Intent(
                    Intent.ACTION_VIEW,
//                    Uri.parse("http://play.google.com/store/apps/details?id=${Constants.whatsappBusinessBundleId}")
                )
            )
        }
    }

}