package com.kuntizbe.kz.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.kuntizbe.kz.R
import com.kuntizbe.kz.ui.commonWidgets.SafeBottomNavigation
import com.kuntizbe.kz.ui.theme.Gray
import com.kuntizbe.kz.ui.theme.Main
import dev.olshevski.navigation.reimagined.NavAction
import dev.olshevski.navigation.reimagined.NavController
import dev.olshevski.navigation.reimagined.NavHost
import dev.olshevski.navigation.reimagined.moveToTop
import dev.olshevski.navigation.reimagined.navigate
import dev.olshevski.navigation.reimagined.popUpTo
import dev.olshevski.navigation.reimagined.rememberNavController

@Composable
fun MainNavigationScreen(
    startDestination: MainNavigationDestination = MainNavigationDestination.Home,
    navController1: androidx.navigation.NavController
) {
    val navController = rememberNavController(
        startDestination = startDestination
    )


    BottomNavigationBackHandler(navController)

    Column {
        Box(Modifier.weight(1f)) {
            NavHost(controller = navController
            ) {destination ->
                    destination.Content(navController1)
            }
        }

        val lastDestination = navController.backstack.entries.last().destination
        val values = remember {
            MainNavigationDestination.values()
        }

        SafeBottomNavigation {
            values.forEach { destination ->
                val isSelected = destination == lastDestination
                val (icon, title) = destination.getIconAndText()
                BottomNavigationItem(
                    icon = {
                        Icon(
                            modifier = Modifier.size(28.dp),
                            imageVector = ImageVector.vectorResource(id = icon),
                            contentDescription = stringResource(id = title),
                            tint = if (isSelected) Main else Gray
                        )
                    },
                    selected = isSelected,
                    onClick = {
                        if (!navController.moveToTop { it == destination }) {
                            navController.navigate(destination)
                        }
                    },
                    selectedContentColor = Main,
                    unselectedContentColor = Gray
                )
            }
        }
    }
}

@Composable
private fun BottomNavigationBackHandler(
    navController: NavController<MainNavigationDestination>
) {
    BackHandler(enabled = navController.backstack.entries.size > 1) {
        val lastEntry = navController.backstack.entries.last()
        if (lastEntry.destination == MainNavigationDestination.Home) {
            navController.moveLastEntryToStart()
        } else {
            navController.popUpTo(inclusive = false) {
                it == MainNavigationDestination.Home
            }
        }
    }
}

private fun NavController<MainNavigationDestination>.moveLastEntryToStart() {
    setNewBackstack(
        entries = backstack.entries.toMutableList().also {
            val entry = it.removeLast()
            it.add(0, entry)
        },
        action = NavAction.Pop
    )
}

private fun MainNavigationDestination.getIconAndText() = when (this) {
    is MainNavigationDestination.Home -> R.drawable.home_24 to R.string.home_title
    is MainNavigationDestination.Menu -> R.drawable.menu_24 to R.string.menu_title
    is MainNavigationDestination.Info -> R.drawable.info_24 to R.string.info_title
    is MainNavigationDestination.PlayerTime -> R.drawable.access_time_24 to R.string.player_time_title
}