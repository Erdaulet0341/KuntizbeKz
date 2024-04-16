package com.kuntizbe.kz

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.navigation.compose.rememberNavController
import com.kuntizbe.kz.navigation.NavigationScreens
import com.kuntizbe.kz.navigation.NavigationSetup
import com.kuntizbe.kz.ui.theme.KuntizbeKzTheme

class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KuntizbeKzTheme {
                val navController = rememberNavController()
                NavigationSetup(navController = navController, startDestination =  NavigationScreens.Cities.route)
            }
        }
    }
}
