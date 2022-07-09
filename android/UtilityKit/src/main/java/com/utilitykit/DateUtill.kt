package com.utilitykit

import java.text.SimpleDateFormat
import java.util.*

object DateUtill {

    fun getTimestamp():Long{
        val cal = Calendar.getInstance()
        val timestamp = cal.timeInMillis
//        val formatted = formatedDate(timestamp)
//        print(formatted)
        return timestamp
    }

    fun formatDate(dateString: String):String{
        //"yyyy-MM-dd'T'hh:mm:ss.SSS'Z'"
//        val sdf = SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS'Z'")
//        sdf.timeZone = TimeZone.getTimeZone("UTC")
//        try {
//            val newDate =  sdf.parse(dateString)
//            val year = newDate.year
//            val month = newDate.month
//            val day = newDate.day
//            val date = newDate.date
//            var dateStr = newDate.toString()
//            dateStr = dateString.split("T").first()
//            return dateStr
////            return "$day $month $year $date"
//        } catch (e: java.lang.Exception) {
//            e.printStackTrace()
//            return ""
//        }


    return ""

    }

    fun formatedDate(timestamp: Long):String{
        val formatter = SimpleDateFormat("DD-MM-yyyy", Locale.ENGLISH)
        formatter.timeZone = TimeZone.getTimeZone("IST")
        return formatter.format(Date(timestamp))
    }


    private const val SECOND_MILLIS = 1000
    private const val MINUTE_MILLIS = 60 * SECOND_MILLIS
    private const val HOUR_MILLIS = 60 * MINUTE_MILLIS
    private const val DAY_MILLIS = 24 * HOUR_MILLIS


    fun String.getDateWithServerTimeStamp(): Date? {
        val dateFormat = SimpleDateFormat(
            "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
            Locale.getDefault()
        )
        dateFormat.timeZone = TimeZone.getTimeZone("GMT")  // IMP !!!
        try {
            return dateFormat.parse(this)
        } catch (e: Exception) {
            return null
        }
    }

    fun convertTimeStampToDurationTime(var0: Long): String {
        val var2 = var0 / 1000L
        val var4 = var2 / 60L % 60L
        val var6 = var2 / 60L / 60L % 24L
        return if (var6 == 0L) String.format(Locale.getDefault(), "%02d:%02d", var4, var2 % 60L) else String.format(
            Locale.getDefault(), "%02d:%02d:%02d", var6, var4, var2 % 60L)
    }

    fun getMessageTime(time: String, dateFormat: String): String {
        val format = SimpleDateFormat(dateFormat)
        val timestamp = java.sql.Timestamp.valueOf(time)
        return format.format(timestamp)
    }
}
