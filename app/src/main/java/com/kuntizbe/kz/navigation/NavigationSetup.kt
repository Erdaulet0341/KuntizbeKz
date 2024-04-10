package com.kuntizbe.kz.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.kuntizbe.kz.screens.splash.SplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.kuntizbe.kz.screens.home.HomeScreen
import com.kuntizbe.kz.screens.info.InfoScreen
import com.kuntizbe.kz.screens.menu.MenuScreen
import com.kuntizbe.kz.screens.menu.menuItemScreens.AboutUsScreen
import com.kuntizbe.kz.screens.menu.menuItemScreens.BookScreen
import com.kuntizbe.kz.screens.menu.menuItemScreens.CalendarScreen
import com.kuntizbe.kz.screens.menu.menuItemScreens.CitiesScreen
import com.kuntizbe.kz.screens.menu.menuItemScreens.FaqScreen
import com.kuntizbe.kz.screens.menu.menuItemScreens.LinksScreen
import com.kuntizbe.kz.screens.menu.menuItemScreens.MessageScreen
import com.kuntizbe.kz.screens.menu.menuItemScreens.QiblaScreen
import com.kuntizbe.kz.screens.menu.menuItemScreens.SettingsScreen
import com.kuntizbe.kz.screens.playerTime.PlayerTimeScreen


@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun NavigationSetup(navController: NavHostController) {

    NavHost(navController = navController, startDestination = NavigationScreens.Splash.route) {
        composable(NavigationScreens.Splash.route) {
            SplashScreen(navController)
        }
        composable(NavigationScreens.MainNavigation.route){
            MainNavigationScreen(navController1 = navController)
        }
        composable(NavigationScreens.Home.route) {
            HomeScreen(navController)
        }
        composable(NavigationScreens.PlayerTime.route) {
            PlayerTimeScreen(navController)
        }
        composable(NavigationScreens.Info.route) {
            InfoScreen(navController)
        }
        composable(NavigationScreens.Menu.route) {
            MenuScreen(navController)
        }
        composable(NavigationScreens.Calendar.route) {
            CalendarScreen(navController)
        }
        composable(NavigationScreens.Faq.route) {
            FaqScreen(navController)
        }
        composable(NavigationScreens.Books.route) {
            BookScreen(navController)
        }
        composable(NavigationScreens.Links.route) {
            LinksScreen(navController)
        }
        composable(NavigationScreens.Cities.route) {
            CitiesScreen(navController)
        }
        composable(NavigationScreens.Message.route) {
            MessageScreen(navController)
        }
        composable(NavigationScreens.AboutUs.route) {
            AboutUsScreen(navController)
        }
        composable(NavigationScreens.Qibla.route) {
            QiblaScreen(navController)
        }
        composable(NavigationScreens.Settings.route) {
            SettingsScreen(navController)
        }
    }
}