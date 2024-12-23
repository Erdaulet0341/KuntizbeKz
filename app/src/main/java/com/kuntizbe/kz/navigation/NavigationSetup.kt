package com.kuntizbe.kz.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.kuntizbe.kz.screens.calendar.CalendarScreen
import com.kuntizbe.kz.screens.cities.CitiesScreen
import com.kuntizbe.kz.screens.home.HomeForCalendarScreen
import com.kuntizbe.kz.screens.menu.MenuScreen
import com.kuntizbe.kz.screens.menu.menuItemScreens.AboutUsScreen
import com.kuntizbe.kz.screens.menu.menuItemScreens.BookScreen
import com.kuntizbe.kz.screens.menu.menuItemScreens.FaqScreen
import com.kuntizbe.kz.screens.menu.menuItemScreens.LinksScreen
import com.kuntizbe.kz.screens.menu.menuItemScreens.MessageScreen
import com.kuntizbe.kz.screens.menu.menuItemScreens.QiblaScreen
import com.kuntizbe.kz.screens.menu.menuItemScreens.SettingsScreen
import com.kuntizbe.kz.screens.splash.SplashScreen
import com.kuntizbe.kz.screens.splash.WelcomePage


@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun NavigationSetup(navController: NavHostController, startDestination: String) {

    NavHost(navController = navController, startDestination = startDestination) {
        composable(NavigationScreens.Splash.route) {
            SplashScreen(navController)
        }
        composable(NavigationScreens.MainNavigation.route) {
            MainNavigationScreen(navController1 = navController, startDestination = MainNavigationDestination.Home)
        }
        composable(NavigationScreens.Home.route) {
            MainNavigationScreen(navController1 = navController, startDestination = MainNavigationDestination.Home)
        }
        composable(NavigationScreens.PrayerTime.route) {
            MainNavigationScreen(navController1 = navController, startDestination = MainNavigationDestination.PrayerTime)
        }
        composable(NavigationScreens.Info.route) {
            MainNavigationScreen(navController1 = navController, startDestination = MainNavigationDestination.Info)
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
        composable(NavigationScreens.HomeForCalendarScreen.route) {
            HomeForCalendarScreen(navController)
        }
        composable(NavigationScreens.WelcomePage.route) {
            WelcomePage(navController)
        }
    }
}