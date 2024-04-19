package com.kuntizbe.kz.retrofit

import android.util.Log
import com.kuntizbe.kz.data.PrayerTimes
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PrayerTImeRepository {
    fun fetchPrayerTimes( id: Int, callback: (PrayerTimes?) -> Unit) {
        val call = RetrofitClient.instance.getPrayerTimes(id)
        call.enqueue(object : Callback<PrayerTimes> {
            override fun onResponse(
                call: Call<PrayerTimes>,
                response: Response<PrayerTimes>
            ) {
                if (response.isSuccessful) {
                    response.message()
                    val prayerTimes = response.body()
                    callback(prayerTimes)
                } else {
                    callback(null)
                }
            }

            override fun onFailure(call: Call<PrayerTimes>, t: Throwable) {
                callback(null)
            }
        })
    }
}