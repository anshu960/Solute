package com.solute.utility

import android.content.Context
import android.telephony.TelephonyManager
import android.util.Log
import com.utilitykit.feature.business.handler.BusinessHandler

class Country {
    val TAG = "COUNTRY"
    fun getCountryCode():String{
        var countryCode = ""
        if(BusinessHandler.shared().context != null){
            countryCode = BusinessHandler.shared().context!!.getResources().getConfiguration().locale.getCountry()
            detectSIMCountry(BusinessHandler.shared().context!!)?.let {
                return it
            }

            detectNetworkCountry(BusinessHandler.shared().context!!)?.let {
                return it
            }

            detectLocaleCountry(BusinessHandler.shared().context!!)?.let {
                return it
            }
        }
        return countryCode
    }

    private fun detectSIMCountry(context: Context): String? {
        try {
            val telephonyManager = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            Log.d(TAG, "detectSIMCountry: ${telephonyManager.simCountryIso}")
            return telephonyManager.simCountryIso
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    private fun detectNetworkCountry(context: Context): String? {
        try {
            val telephonyManager = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            Log.d(TAG, "detectNetworkCountry: ${telephonyManager.simCountryIso}")
            return telephonyManager.networkCountryIso
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    private fun detectLocaleCountry(context: Context): String? {
        try {
            val localeCountryISO = context.getResources().getConfiguration().locale.getCountry()
            Log.d(TAG, "detectNetworkCountry: $localeCountryISO")
            return localeCountryISO
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

      fun countryFlag(countryCode:String):String{
        val flagOffset = 0x1F1E6
        val asciiOffset = 0x41
        val firstChar = Character.codePointAt(countryCode, 0) - asciiOffset + flagOffset
        val secondChar = Character.codePointAt(countryCode, 1) - asciiOffset + flagOffset

        return (String(Character.toChars(firstChar))
                + String(Character.toChars(secondChar)))
    }
}