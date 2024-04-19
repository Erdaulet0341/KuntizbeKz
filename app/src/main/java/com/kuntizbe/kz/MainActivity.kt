package com.kuntizbe.kz

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.navigation.compose.rememberNavController
import com.kuntizbe.kz.navigation.NavigationScreens
import com.kuntizbe.kz.navigation.NavigationSetup
import com.kuntizbe.kz.screens.splash.getFirstFromSharedPreferences
import com.kuntizbe.kz.ui.theme.KuntizbeKzTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val isOpen = getFirstFromSharedPreferences(this)
        setContent {
            KuntizbeKzTheme {
                val navController = rememberNavController()
                if(isOpen) NavigationSetup(navController = navController, startDestination =  NavigationScreens.Splash.route)
                else NavigationSetup(navController = navController, startDestination =  NavigationScreens.WelcomePage.route)
            }
        }
    }
}
