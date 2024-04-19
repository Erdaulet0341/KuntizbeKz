package com.kuntizbe.kz.screens.cities

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kuntizbe.kz.data.PrayerTimes
import com.kuntizbe.kz.retrofit.PrayerTImeRepository
import com.kuntizbe.kz.room.DatabaseBuilder
import com.kuntizbe.kz.room.RoomData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class CitiesViewModel : ViewModel() {

    private val _allTimes = MutableStateFlow<List<RoomData>>(emptyList())
    val allTimes: StateFlow<List<RoomData>> = _allTimes.asStateFlow()
    private val repository = PrayerTImeRepository()
    val prayerTimesLiveData = MutableLiveData<PrayerTimes?>()

     fun observeAllTimes(context: Context) {
         viewModelScope.launch {
             val instance = DatabaseBuilder.getInstance(context)
             instance.prayerDao().getAllTimes().collect { times ->
                 _allTimes.value = times
             }
         }
    }

    fun insertData(id:Int,prayerTimes: PrayerTimes, context: Context){
        viewModelScope.launch {
            val instance = DatabaseBuilder.getInstance(context)
            instance.prayerDao().insertRoomData(RoomData(id = id, prayerTimes = prayerTimes))
            observeAllTimes(context)
        }
    }

    fun checkInsert(id: Int): Boolean{
        return allTimes.value.map { it.id }.contains(id)
    }

    fun deleteById(context: Context, id:Int){
        viewModelScope.launch {
            val instance = DatabaseBuilder.getInstance(context)
            instance.prayerDao().deleteRoomDataById(id)

        }
    }

    fun fetchPrayerTimes(id: Int, context: Context) {
        if(checkInternetConnection(context)){
            repository.fetchPrayerTimes(id) { prayerTimes ->
                prayerTimesLiveData.postValue(prayerTimes)
                insertData(id, prayerTimes!!,  context)
            }
        }
    }

    fun checkInternetConnection(context: Context): Boolean {
        return NetworkUtils.isInternetConnected(context)
    }

}

object NetworkUtils {
    fun isInternetConnected(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val network = connectivityManager.activeNetwork ?: return false
        val networkCapabilities = connectivityManager.getNetworkCapabilities(network) ?: return false

        return networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }
}
