package com.kuntizbe.kz.screens.playerTime

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kuntizbe.kz.data.PrayerDay
import com.kuntizbe.kz.room.PrayerDatabase
import com.kuntizbe.kz.room.RoomData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.chrono.HijrahDate
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale

class PrayerViewModel : ViewModel() {

    private val _allTimes = MutableStateFlow<List<RoomData>>(emptyList())
    val allTimes: StateFlow<List<RoomData>> = _allTimes.asStateFlow()
    private val _days = MutableStateFlow(emptyPrayerDay)
    val days: StateFlow<PrayerDay> = _days.asStateFlow()
    private val _cityname = MutableStateFlow("")
    val cityname: StateFlow<String> = _cityname.asStateFlow()


    fun observeAllTimes(instanceRoom: PrayerDatabase) {
        viewModelScope.launch {
            instanceRoom.prayerDao().getAllTimes().collect { times ->
                _allTimes.value = times
            }
        }
    }

    fun todayDate(id: Int) {
        val currentDate = Calendar.getInstance().time

        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date = dateFormat.format(currentDate)

        allTimes.value
            .forEach { roomData ->
                if (roomData.id == id) {
                    _cityname.value = roomData.prayerTimes.cityname
                    val data = roomData.prayerTimes.prayerDays.get(date)
                    data.let {
                        _days.value = it!!
                    }
                }
            }
    }

    fun checkCityIsDownloaded(id: Int): Boolean {

        allTimes.value
            .forEach { roomData ->
                if (roomData.id == id) return true
            }

        return false
    }

    fun getSettingsFromSharedPreferences(context: Context, name: String): Boolean {
        val sharedPref: SharedPreferences = context.getSharedPreferences(
            "LAST_CITY_ID", Context.MODE_PRIVATE
        )
        return sharedPref.getString(name, "false")!!.toBoolean()
    }

}

@RequiresApi(Build.VERSION_CODES.O)
fun gregorianDate(): String {
    val todayDate = LocalDate.now()
    return todayDate.format(DateTimeFormatter.ofPattern("dd - MMMM yyyy", Locale("kk", "KZ")))
}

@RequiresApi(Build.VERSION_CODES.O)
fun gregorianToHijriFormatted(): String {
    val todayDate = LocalDate.now()
    val islamicDate = HijrahDate.from(todayDate)
    val islamicDateFormatter = islamicDate.format(DateTimeFormatter.ofPattern("dd-yyyy MMMM", Locale.ENGLISH))
    val month = islamicDateFormatter.substring(8)
    val kazakhMonth = islamicToKazakhMonth(month)
    val day = islamicDateFormatter.substring(0, 2)
    val year = islamicDateFormatter.substring(3, 8)

    if(kazakhMonth != "null") return "$day - $kazakhMonth $year"
    return islamicDate.format(DateTimeFormatter.ofPattern("dd - MMMM yyyy", Locale.ENGLISH))
}

fun islamicToKazakhMonth(month: String):String{

    when(month){
        "Rajab" ->{
            return "Ережеп"
        }
        "Shaʻban" ->{
            return "Шағбан"
        }
        "Ramadan" ->{
            return "Рамазан"
        }
        "Shawwal" ->{
            return "Шәууәл"
        }
        "Dhuʻl-Qiʻdah" ->{
            return "Зұлқағда"
        }
        "Dhuʻl-Hijjah" ->{
            return "Зұлхижжа"
        }
        "Muharram" ->{
            return "Мұхаррам"
        }
        "Safar" ->{
            return "Сафар"
        }
        "Rabiʻ I" ->{
            return "Рабиғул әууәл"
        }
        "Rabiʻ II" ->{
            return "Рабиғул ахир"
        }
        "Jumada I" ->{
        return "Жұмада әууәл"
        }
        "Jumada II" ->{
            return "Жұмада ахир"
        }
    }

    return "null"

}

fun SaveIdToSharedPreferences(context: Context, id: Int) {
    val sharedPref: SharedPreferences = context.getSharedPreferences(
        "LAST_CITY_ID", Context.MODE_PRIVATE
    )
    val editor: SharedPreferences.Editor = sharedPref.edit()
    editor.putString("CITY_ID", id.toString()).apply()
}

fun GetIdFromSharedPreferences(context: Context): Int {
    val sharedPref: SharedPreferences = context.getSharedPreferences(
        "LAST_CITY_ID", Context.MODE_PRIVATE
    )
    return sharedPref.getString("CITY_ID", "0")!!.toInt()
}

val emptyPrayerDay = PrayerDay(
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
)
