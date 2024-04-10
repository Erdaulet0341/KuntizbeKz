package com.kuntizbe.kz.navigation

import android.annotation.SuppressLint
import android.os.Parcelable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.navigation.NavController
import com.kuntizbe.kz.screens.home.HomeScreen
import com.kuntizbe.kz.screens.info.InfoScreen
import com.kuntizbe.kz.screens.playerTime.PlayerTimeScreen
import com.kuntizbe.kz.screens.menu.MenuScreen
import kotlinx.parcelize.Parcelize

sealed class MainNavigationDestination: Screen {

    @Composable
    abstract fun Content(navController: NavController)

    @Immutable @Parcelize
    object Home: MainNavigationDestination() {
        @SuppressLint("StateFlowValueCalledInComposition")
        @Composable
        override fun Content(navController: NavController) {
            HomeScreen(navController)
        }
    }


    @Immutable @Parcelize
    object Info: MainNavigationDestination() {
        @Composable
        override fun Content(navController: NavController) {
            InfoScreen(navController)
        }
    }

    @Immutable @Parcelize
    object Menu: MainNavigationDestination() {
        @Composable
        override fun Content(navController: NavController) {
            MenuScreen(navController)
        }
    }

    @Immutable @Parcelize
    object PlayerTime: MainNavigationDestination() {
        @Composable
        override fun Content(navController: NavController) {
            PlayerTimeScreen(navController)
        }
    }

    companion object {
        fun values() = arrayOf(
            PlayerTime,
            Home,
            Info,
            Menu
        )
    }
}

interface Screen : Parcelable