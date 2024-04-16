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
                    Log.d("API1", response.body().toString() + " name of")
                    callback(prayerTimes)
                } else {
                    Log.d("API1", response.message() + "dont success")
                    callback(null)
                }
            }

            override fun onFailure(call: Call<PrayerTimes>, t: Throwable) {
                Log.d("API1", t.message.toString() + "onFailure")
                callback(null)
            }
        })
    }
}