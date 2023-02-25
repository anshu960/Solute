package com.friendly.framework.constants

import android.content.Context
import android.telephony.TelephonyManager
import android.util.Log
import com.friendly.framework.UtilityActivity

class Country {
    val TAG = "COUNTRY"
    fun getCountryCode(activity: UtilityActivity):String{
        var countryCode = ""
        countryCode = activity.resources.configuration.locale.country
        if(!countryCode.isNullOrEmpty()){
            return countryCode
        }
        detectNetworkCountry(activity)?.let {
            return it
        }

        detectLocaleCountry(activity)?.let {
            return it
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