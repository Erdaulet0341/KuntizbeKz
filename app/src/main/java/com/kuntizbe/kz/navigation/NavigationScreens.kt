package com.kuntizbe.kz.navigation

sealed class NavigationScreens(val route: String) {
    object Splash : NavigationScreens("splash_screen")
    object MainNavigation : NavigationScreens("main_navigation")
    object Home : NavigationScreens("home_screen")
    object PrayerTime : NavigationScreens("player_time")
    object Info : NavigationScreens("info_screen")
    object Menu : NavigationScreens("menu_screen")
    object Calendar : NavigationScreens("calendar")
    object Faq : NavigationScreens("faq_screen")
    object Books : NavigationScreens("books_screen")
    object Links : NavigationScreens("links_screen")
    object Cities : NavigationScreens("cities_screen")
    object Message : NavigationScreens("message_screen")
    object AboutUs : NavigationScreens("about_screen")
    object Qibla : NavigationScreens("qibla_screen")
    object Settings : NavigationScreens("settings_screen")
    object HomeForCalendarScreen: NavigationScreens("HomeFor_Calendar_Screen")
    object WelcomePage: NavigationScreens("Welcome_Page")

}