package com.kuntizbe.kz.screens.playerTime

import android.util.Log
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
import androidx.compose.material.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.kuntizbe.kz.R
import com.kuntizbe.kz.navigation.NavigationScreens
import com.kuntizbe.kz.ui.commonWidgets.CenteredToolbar
import com.kuntizbe.kz.ui.commonWidgets.PointDivider
import com.kuntizbe.kz.ui.theme.Black
import com.kuntizbe.kz.ui.theme.GrayDivider
import com.kuntizbe.kz.ui.theme.Main
import com.kuntizbe.kz.ui.theme.White
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun PlayerTimeScreen(navController: NavController) {

    val viewModel: PrayerViewModel = viewModel()
    val prayerTimesState by viewModel.prayerTimesLiveData.observeAsState()


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            CenteredToolbar(
                title = stringResource(id = R.string.player_time_title),
                onNavigationIconClick = {
                    navController.navigate(
                        NavigationScreens.MainNavigation.route
                    )
                }
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier.padding(bottom = 12.dp),
                    text = "Алматы".uppercase(), style = TextStyle(
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.W700,
                        fontSize = 36.sp,
                        color = Main
                    )
                )
                PointDivider(pointSize = 100)
                Text(
                    text = "3 - Шәууал 1445",
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.W700,
                        fontSize = 21.sp,
                        color = Main
                    ),
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                Text(
                    text = "12 - Сәуір 2024",
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.W700,
                        fontSize = 23.sp,
                        color = Main
                    ),
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                PointDivider(pointSize = 100)
                Spacer(modifier = Modifier.height(20.dp))

                PlayerTimeItem(stringResource(id = R.string.isfifar), "12:00")
                PlayerTimeItem(stringResource(id = R.string.isfifar), "12:00")
                PlayerTimeItem(stringResource(id = R.string.isfifar), "12:00")
                PlayerTimeItem(stringResource(id = R.string.isfifar), "12:00")
                PlayerTimeItem(stringResource(id = R.string.isfifar), "12:00")
                PlayerTimeItem(stringResource(id = R.string.isfifar), "12:00")
                PlayerTimeItem(stringResource(id = R.string.isfifar), "12:00")
                PlayerTimeItem(stringResource(id = R.string.isfifar), "12:00")
                PlayerTimeItem(stringResource(id = R.string.isfifar), "12:00")
                PlayerTimeItem(stringResource(id = R.string.isfifar), "12:00")
            }
        }
    }
}

@Composable
fun PlayerTimeItem(text: String, time:String){
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = text,
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.W700,
                    fontSize = 25.sp,
                    color = Black
                ),
                modifier = Modifier
            )
            Text(
                text = time,
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.W700,
                    fontSize = 25.sp,
                    color = Black
                ),
                modifier = Modifier
            )
        }
        Divider(
            color = GrayDivider, modifier = Modifier
                .padding(vertical = 8.dp)
                .height(1.dp)
                .fillMaxWidth()
        )
    }
}
