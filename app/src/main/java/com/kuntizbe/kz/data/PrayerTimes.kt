package com.kuntizbe.kz.data

data class PrayerTimes(
    val cityname: String,
    val prayerDays: Map<String, PrayerDay>
)