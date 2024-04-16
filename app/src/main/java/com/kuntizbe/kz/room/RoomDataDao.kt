package com.kuntizbe.kz.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface RoomDataDao {

    @Query("SELECT * FROM  prayer_times")
    fun getAllTimes(): Flow<List<RoomData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRoomData(roomData: RoomData)

    @Query("SELECT * FROM prayer_times WHERE id = :id")
    suspend fun getTimeById(id: Int): RoomData

    @Query("DELETE FROM prayer_times")
    suspend fun deleteAllData()
}