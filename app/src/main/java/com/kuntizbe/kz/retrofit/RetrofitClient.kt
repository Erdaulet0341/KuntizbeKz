package com.kuntizbe.kz.retrofit

import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.reflect.TypeToken
import com.kuntizbe.kz.data.CityResponse
import com.kuntizbe.kz.data.PrayerDay
import com.kuntizbe.kz.data.PrayerTimes
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Path
import retrofit2.http.Query
import java.lang.reflect.Type

interface PrayerService {
    @GET("year")
    fun getPrayerTimes(@Query("id") id: Int): Call<PrayerTimes>

    @GET("cities?id=almaty")
    suspend fun getCities(): CityResponse
}

object RetrofitClient {
    private const val BASE_URL = "https://namaztimes.kz/api/"


    private val gson = GsonBuilder()
        .registerTypeAdapter(PrayerTimes::class.java, PrayerTimesDeserializer)
        .create()

    val instance: PrayerService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        retrofit.create(PrayerService::class.java)
    }
}

object PrayerTimesDeserializer : JsonDeserializer<PrayerTimes> {
    private val classesType = object: TypeToken<Map<String, PrayerDay>>() {}.type
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): PrayerTimes {
        val jsonObject = json.asJsonObject
        val cityname = jsonObject.remove("cityname").asJsonPrimitive.asString
        val prayerDaysMap : Map<String, PrayerDay> = context!!.deserialize(jsonObject, classesType)
        return PrayerTimes(cityname, prayerDaysMap)
    }
}