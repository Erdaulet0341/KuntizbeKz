package com.kuntizbe.kz.screens.menu.menuItemScreens
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModel

class SettingsViewModel : ViewModel() {

    fun saveSettingsToSharedPreferences(context: Context, name: String, value:Boolean) {
        val sharedPref: SharedPreferences = context.getSharedPreferences(
            "LAST_CITY_ID", Context.MODE_PRIVATE
        )
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putString(name, value.toString()).apply()
    }

    fun getSettingsFromSharedPreferences(context: Context, name: String): Boolean {
        val sharedPref: SharedPreferences = context.getSharedPreferences(
            "LAST_CITY_ID", Context.MODE_PRIVATE
        )
        return sharedPref.getString(name, "false")!!.toBoolean()
    }

}