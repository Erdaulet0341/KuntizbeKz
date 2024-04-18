package com.kuntizbe.kz.screens.menu.menuItemScreens
import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

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