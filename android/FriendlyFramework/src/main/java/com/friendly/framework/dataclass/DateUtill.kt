package com.friendly.framework.dataclass

import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.TimeZone.getTimeZone


object DateUtill {

    fun getTimestamp():Long{
        val cal = Calendar.getInstance()
        val timestamp = cal.timeInMillis
        val formatted = formatedDate(timestamp)
        print(formatted)
        return timestamp
    }

    fun formatedDate(timestamp: Long):String{
        val formatter = SimpleDateFormat("DD-mm-yyyy HH:mm:ss zzzz", Locale.US)
        formatter.setTimeZone(getTimeZone("UTC"))
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
        dateFormat.timeZone = TimeZone.getTimeZone(TimeZone.getDefault().displayName)  // IMP !!!
        try {
            return dateFormat.parse(this)
        } catch (e: Exception) {
            return null
        }
    }



    fun getTime(time: String):String{
        val utc = kotlinx.datetime.TimeZone.UTC
        val local = kotlinx.datetime.TimeZone.currentSystemDefault()
        val utcTime = time.toInstant().toLocalDateTime(utc)
        val localTime = utcTime.toInstant(utc).toLocalDateTime(local)
        val hour = localTime.hour.toString()
        val minutes = localTime.minute.toString()
        return hour + " : " + minutes
    }
    
    fun getTimeToDisplayForConversation(date: Date):String{
        
        var hour = date.hours + 5
        var minute = date.minutes + 35

        if(minute > 60){
            minute = minute - 60
            hour = hour + 1
        }
        var dayVsNight = "AM"
        if(hour < 12){
            dayVsNight = "AM"
        }else{
            hour = hour - 12
            dayVsNight = "PM"
        }

        var time = ""
        var today = Date()
        if(date.year != today.year){
            time = time + date.year
        }else if(date.month != today.month){
            time = time + date.month
        }else if(date.date != today.date){
            time =  date.day.toString() + "/" + date.month + "/" + date.year
        }else{
            time =  hour.toString()+ ":" + minute + " " + dayVsNight
        }
        time =  time
        return time
    }
    
    fun getTimeDates(timeStamp:String){
//        val myFormat = SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS'Z'")
//        myFormat.timeZone = TimeZone.getTimeZone("UTC");
//        val displayformat = "EEE dd MMM, hh:mm"
//        val destFormat = SimpleDateFormat(displayformat)
//        try
//        {
//            val myDate = myFormat.parse(timeStamp)
//            myDate.timezoneOffset  =
//            print(myDate)
//        } catch (e: ParseException)
//        {
//            e.printStackTrace()
//        }
    }
    
    fun getTimeDate(timeStamp:String): String?
    {
        val oldFormatter = SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS'Z'")
        oldFormatter.timeZone = getTimeZone("UTC")
        var value: Date? = null
        var dueDateAsNormal = ""
        try
        {
            value = oldFormatter.parse(timeStamp)
            val newFormatter = SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS'Z'")
            newFormatter.timeZone = TimeZone.getDefault()
            dueDateAsNormal = newFormatter.format(value)
            print(dueDateAsNormal)
        } catch (e: ParseException)
        {
            e.printStackTrace()
        }
        return dueDateAsNormal
    }

    fun convertTimeStampToDurationTime(var0: Long): String {
        val var2 = var0 / 1000L
        val var4 = var2 / 60L % 60L
        val var6 = var2 / 60L / 60L % 24L
        return if (var6 == 0L) String.format(Locale.getDefault(), "%02d:%02d", var4, var2 % 60L) else String.format(
            Locale.getDefault(),
            "%02d:%02d:%02d",
            var6,
            var4,
            var2 % 60L
        )
    }

    fun getMessageTime(time: String, dateFormat: String): String {
        val format = SimpleDateFormat(dateFormat)
        val timestamp = java.sql.Timestamp.valueOf(time)
        return format.format(timestamp)
    }
}
fun String.toDate(
    dateFormat: String = "yyyy-MM-dd'T'HH:mm:ss'Z'", timeZone: TimeZone = TimeZone.getTimeZone(
        "UTC"
    )
): Date {
    val parser = SimpleDateFormat(dateFormat, Locale.getDefault())
    parser.timeZone = timeZone
    return parser.parse(this)
}

fun Date.formatTo(dateFormat: String, timeZone: TimeZone = TimeZone.getTimeZone("GTM")): String {
    val formatter = SimpleDateFormat(dateFormat, Locale.getDefault())
    formatter.timeZone = timeZone
    return formatter.format(this)
}