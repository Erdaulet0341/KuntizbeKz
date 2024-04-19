package com.kuntizbe.kz.screens.menu

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.kuntizbe.kz.R
import com.kuntizbe.kz.navigation.NavigationScreens
import com.kuntizbe.kz.ui.commonWidgets.CenteredToolbar
import com.kuntizbe.kz.ui.commonWidgets.MenuItem
import com.kuntizbe.kz.ui.theme.White

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun MenuScreen(navController: NavController) {

    Box(
        modifier = Modifier
            .background(White)
            .fillMaxSize()
            .background(White)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            CenteredToolbar(
                title = stringResource(id = R.string.menu_title),
                onNavigationIconClick = {
                    navController.navigate(
                        NavigationScreens.MainNavigation.route
                    )
                }
            )

            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .verticalScroll(rememberScrollState())
                    .fillMaxWidth()
            ) {

                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Box(Modifier.weight(1f)) {
                        MenuItem(
                            text = stringResource(id = R.string.player_time_title),
                            icon = ImageVector.vectorResource(R.drawable.access_time_24),
                            onClick = {
                                navController.navigate(
                                    NavigationScreens.PrayerTime.route
                                )
                            }
                        )
                    }
                    Box(Modifier.weight(1f)) {
                        MenuItem(
                            text = stringResource(id = R.string.home_title),
                            icon = ImageVector.vectorResource(R.drawable.home_24),
                            onClick = {
                                navController.navigate(
                                    NavigationScreens.Home.route
                                )
                            }
                        )
                    }
                    Box(Modifier.weight(1f)) {
                        MenuItem(
                            text = stringResource(id = R.string.info_title),
                            icon = ImageVector.vectorResource(R.drawable.info_24),
                            onClick = {
                                navController.navigate(
                                    NavigationScreens.Info.route
                                )
                            }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Box(Modifier.weight(1f)) {
                        MenuItem(
                            text = stringResource(id = R.string.days),
                            icon = ImageVector.vectorResource(R.drawable.calendar_month_24),
                            onClick = {
                                navController.navigate(
                                    NavigationScreens.Calendar.route
                                )
                            }
                        )
                    }
                    Box(Modifier.weight(1f)) {
                        MenuItem(
                            text = stringResource(id = R.string.faq),
                            icon = ImageVector.vectorResource(R.drawable.faq_high_24),
                            onClick = {
                                navController.navigate(
                                    NavigationScreens.Faq.route
                                )
                            }
                        )
                    }
                    Box(Modifier.weight(1f)) {
                        MenuItem(
                            text = stringResource(id = R.string.books),
                            icon = ImageVector.vectorResource(R.drawable.book_24),
                            onClick = {
                                navController.navigate(
                                    NavigationScreens.Books.route
                                )
                            }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Box(Modifier.weight(1f)) {
                        MenuItem(
                            text = stringResource(id = R.string.links),
                            icon = ImageVector.vectorResource(R.drawable.links_24),
                            onClick = {
                                navController.navigate(
                                    NavigationScreens.Links.route
                                )
                            }
                        )
                    }
                    Box(Modifier.weight(1f)) {
                        MenuItem(
                            text = stringResource(id = R.string.cities),
                            icon = ImageVector.vectorResource(R.drawable.city_24),
                            onClick = {
                                navController.navigate(
                                    NavigationScreens.Cities.route
                                )
                            }
                        )
                    }
                    Box(Modifier.weight(1f)) {
                        MenuItem(
                            text = stringResource(id = R.string.message),
                            icon = ImageVector.vectorResource(R.drawable.message_24),
                            onClick = {
                                navController.navigate(
                                    NavigationScreens.Message.route
                                )
                            }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Box(Modifier.weight(1f)) {
                        MenuItem(
                            text = stringResource(id = R.string.about_us),
                            icon = ImageVector.vectorResource(R.drawable.people_24),
                            onClick = {
                                navController.navigate(
                                    NavigationScreens.AboutUs.route
                                )
                            }
                        )
                    }
                    Box(Modifier.weight(1f)) {
                        MenuItem(
                            text = stringResource(id = R.string.location),
                            icon = ImageVector.vectorResource(R.drawable.location_24),
                            onClick = {
                                navController.navigate(
                                    NavigationScreens.Qibla.route
                                )
                            }
                        )
                    }
                    Box(Modifier.weight(1f)) {
                        MenuItem(
                            text = stringResource(id = R.string.settings),
                            icon = ImageVector.vectorResource(R.drawable.settings_24),
                            onClick = {
                                navController.navigate(
                                    NavigationScreens.Settings.route
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}
