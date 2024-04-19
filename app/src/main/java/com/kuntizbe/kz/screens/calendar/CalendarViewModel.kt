package com.kuntizbe.kz.screens.calendar

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.kuntizbe.kz.screens.playerTime.islamicToKazakhMonth
import java.time.LocalDate
import java.time.chrono.HijrahDate
import java.time.format.DateTimeFormatter
import java.util.Locale

class CalendarViewModel : ViewModel() {

    fun saveDateToSharedPreferences(context: Context, date: String) {
        val sharedPref: SharedPreferences = context.getSharedPreferences(
            "CALENDAR_DATE", Context.MODE_PRIVATE
        )
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putString("DATE", date).apply()
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun getDateFromSharedPreferences(context: Context): String {
    val sharedPref: SharedPreferences = context.getSharedPreferences(
        "CALENDAR_DATE", Context.MODE_PRIVATE
    )
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val date = LocalDate.parse(LocalDate.now().toString(), formatter)
    return sharedPref.getString("DATE", date.toString())!!
}

@RequiresApi(Build.VERSION_CODES.O)
fun gregorianToHijriFormattedForHome(date:String): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val localDate = LocalDate.parse(date, formatter)
    val islamicDate = HijrahDate.from(localDate)
    val islamicDateFormatter = islamicDate.format(DateTimeFormatter.ofPattern("dd-yyyy MMMM", Locale.ENGLISH))
    val month = islamicDateFormatter.substring(8)
    val kazakhMonth = islamicToKazakhMonth(month)
    val day = islamicDateFormatter.substring(0, 2)
    val year = islamicDateFormatter.substring(3, 8)

    if(kazakhMonth != "null") return "$day - $kazakhMonth $year"
    return islamicDate.format(DateTimeFormatter.ofPattern("dd - MMMM yyyy", Locale.ENGLISH))
}