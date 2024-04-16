package com.kuntizbe.kz.screens.playerTime

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import com.kuntizbe.kz.data.PrayerTimes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.GsonBuilder
import com.kuntizbe.kz.data.CityResponse
import com.kuntizbe.kz.data.PrayerDay
import com.kuntizbe.kz.retrofit.PrayerTImeRepository
import com.kuntizbe.kz.retrofit.PrayerTimesDeserializer
import com.kuntizbe.kz.retrofit.RetrofitClient
import kotlinx.coroutines.launch


class PrayerViewModel : ViewModel() {

    private val repository = PrayerTImeRepository()
    val prayerTimesLiveData = MutableLiveData<PrayerTimes?>()

//    fun fetchPrayerTimes() {
//        repository.fetchPrayerTimes { prayerTimes ->
//            prayerTimesLiveData.postValue(prayerTimes)
//        }
//    }

}
