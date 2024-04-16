package com.kuntizbe.kz.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.kuntizbe.kz.data.PrayerTimes


@Entity(tableName = "prayer_times")
data class RoomData (
    @PrimaryKey val id: Int,
    val prayerTimes: PrayerTimes
)

class PrayerTimesConverter {

    @TypeConverter
    fun fromPrayerTimes(prayerTimes: PrayerTimes): String {
        val gson = Gson()
        return gson.toJson(prayerTimes)
    }

    @TypeConverter
    fun toPrayerTimes(prayerTimesString: String): PrayerTimes {
        val gson = Gson()
        val type = object : TypeToken<PrayerTimes>() {}.type
        return gson.fromJson(prayerTimesString, type)
    }
}