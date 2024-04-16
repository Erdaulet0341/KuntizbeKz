package com.kuntizbe.kz.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [RoomData::class], version = 1)
@TypeConverters(PrayerTimesConverter::class)
abstract class PrayerDatabase : RoomDatabase() {
    abstract fun prayerDao(): RoomDataDao
}

object DatabaseBuilder {
    private var INSTANCE: PrayerDatabase? = null
    fun getInstance(context: Context): PrayerDatabase {
        synchronized(this) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    PrayerDatabase::class.java,
                    "prayer_times1"
                ).build()
            }
            return INSTANCE!!
        }
    }
}