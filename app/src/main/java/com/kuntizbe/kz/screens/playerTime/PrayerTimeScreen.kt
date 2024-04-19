package com.kuntizbe.kz.screens.playerTime

import android.annotation.SuppressLint
import android.os.Build
import android.widget.Toast
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.kuntizbe.kz.R
import com.kuntizbe.kz.navigation.NavigationScreens
import com.kuntizbe.kz.room.DatabaseBuilder
import com.kuntizbe.kz.ui.commonWidgets.PointDivider
import com.kuntizbe.kz.ui.ext.clickableWithIndication
import com.kuntizbe.kz.ui.theme.Black
import com.kuntizbe.kz.ui.theme.GrayDivider
import com.kuntizbe.kz.ui.theme.Main
import com.kuntizbe.kz.ui.theme.TypographiesCostom
import com.kuntizbe.kz.ui.theme.White
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun PlayerTimeScreen(navController: NavController) {

    val viewModel: PrayerViewModel = viewModel()
    val context = LocalContext.current
    val instanceRoom = DatabaseBuilder.getInstance(context)
    val allTimes by viewModel.allTimes.collectAsState()
    val days by viewModel.days.collectAsState()
    val cityName by viewModel.cityname.collectAsState()
    val textDisplayed = remember { mutableStateOf(false) }
    val toastText = stringResource(id = R.string.toast_install)

    LaunchedEffect(viewModel.allTimes) {
        viewModel.observeAllTimes(instanceRoom)
    }
    val cityId = GetIdFromSharedPreferences(context = context)
    allTimes.size.toString()
    viewModel.todayDate(cityId)

    if (!textDisplayed.value) {
        rememberCoroutineScope().launch {
            delay(300)
            if (days.day == "") {
                navController.navigate(NavigationScreens.Cities.route)
                Toast.makeText(context, toastText, Toast.LENGTH_SHORT).show()
            }
            textDisplayed.value = true
        }
    }

    Box(
        modifier = Modifier
            .background(White)
            .fillMaxSize()
            .background(White)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            PrayerTImeToolBar(
                title = stringResource(id = R.string.player_time_title),
                onNavigationIconClick = {
                    navController.navigate(
                        NavigationScreens.Home.route
                    )
                },
                onNavigationCity = {
                    navController.navigate(NavigationScreens.Cities.route)
                    textDisplayed.value = false
                },
                navController = navController
            )

            Spacer(modifier = Modifier.height(20.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier.padding(bottom = 12.dp),
                    text = cityName.uppercase(), style = TextStyle(
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.W700,
                        fontSize = if(cityName.length>15) 20.sp else 30.sp,
                        color = Main
                    )
                )
                PointDivider(pointSize = 100)
                Text(
                    text = gregorianToHijriFormatted().uppercase(),
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.W700,
                        fontSize = 19.sp,
                        color = Main
                    ),
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                Text(
                    text = gregorianDate().uppercase(),
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.W700,
                        fontSize = 21.sp,
                        color = Main
                    ),
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                PointDivider(pointSize = 100)
                Spacer(modifier = Modifier.height(20.dp))

                PlayerTimeItem(stringResource(id = R.string.imsak1), days.imsak)
                PlayerTimeItem(stringResource(id = R.string.bamdat1), days.bamdat)
                PlayerTimeItem(stringResource(id = R.string.kun1), days.kun)
                if(viewModel.getSettingsFromSharedPreferences(context, "ishrak"))
                    PlayerTimeItem(stringResource(id = R.string.ishraq1), days.ishraq)
                if(viewModel.getSettingsFromSharedPreferences(context, "kerakat"))
                    PlayerTimeItem(stringResource(id = R.string.kerahat1), days.kerahat)
                PlayerTimeItem(stringResource(id = R.string.besin1), days.besin)
                if(viewModel.getSettingsFromSharedPreferences(context, "asri"))
                    PlayerTimeItem(stringResource(id = R.string.asriauual1), days.asriauual)
                PlayerTimeItem(stringResource(id = R.string.ekindi1), days.ekindi)
                if(viewModel.getSettingsFromSharedPreferences(context, "isfifar"))
                    PlayerTimeItem(stringResource(id = R.string.isfirar1), days.isfirar)
                PlayerTimeItem(stringResource(id = R.string.aqsham1), days.aqsham)
                if(viewModel.getSettingsFromSharedPreferences(context, "ishtibak"))
                    PlayerTimeItem(stringResource(id = R.string.ishtibaq1), days.ishtibaq)
                PlayerTimeItem(stringResource(id = R.string.quptan1), days.quptan)
                if(viewModel.getSettingsFromSharedPreferences(context, "ishai"))
                    PlayerTimeItem(stringResource(id = R.string.ishaisani1), days.ishaisani)

            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrayerTImeToolBar(
    title: String,
    isNavigationIconVisible: Boolean = true,
    onNavigationIconClick: (() -> Unit)? = null,
    onNavigationCity: () -> Unit,
    navController: NavController
) {

    TopAppBar(
        title = {
            androidx.compose.material.Text(
                text = title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = TypographiesCostom.firstHeaderTextMain,
                color = Main,
                modifier = Modifier,
            )
        },
        modifier = Modifier
            .fillMaxWidth(),
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = White,
            scrolledContainerColor = White.copy(alpha = 0.8f)
        ),
        windowInsets = TopAppBarDefaults.windowInsets,
        navigationIcon = if (isNavigationIconVisible) {
            {
                IconButton(
                    onClick = { onNavigationIconClick?.invoke() },
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.arrow_left_24),
                        contentDescription = null,
                        tint = Main,
                    )
                }
            }
        } else {
            { }
        },
        actions = {
            Icon(
                modifier = Modifier
                    .clip(CircleShape)
                    .clickableWithIndication {
                        navController.navigate(NavigationScreens.Settings.route)
                    }
                    .padding(8.dp),
                imageVector = ImageVector.vectorResource(id = R.drawable.settings_24),
                contentDescription = null,
                tint = Main)
            Spacer(modifier = Modifier.width(6.dp))
            Icon(
                modifier = Modifier
                    .clip(CircleShape)
                    .clickableWithIndication {
                        onNavigationCity.invoke()
                    }
                    .padding(8.dp),
                imageVector = ImageVector.vectorResource(id = R.drawable.city_24),
                contentDescription = null,
                tint = Main)
        }
    )
}

@Composable
fun PlayerTimeItem(text: String, time: String) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = text.uppercase(),
                textAlign = TextAlign.Start,
                style = TextStyle(
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.W700,
                    fontSize = 20.sp,
                    color = Black
                ),
                modifier = Modifier.weight(0.7f)
            )
            Text(
                text = time,
                textAlign = TextAlign.End,
                style = TextStyle(
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.W700,
                    fontSize = 20.sp,
                    color = Black
                ),
                modifier = Modifier.weight(0.3f)
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