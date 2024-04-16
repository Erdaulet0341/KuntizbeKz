package com.kuntizbe.kz.screens.menu.menuItemScreens

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.kuntizbe.kz.R
import com.kuntizbe.kz.screens.cities.SearchDialog
import com.kuntizbe.kz.screens.cities.cities
import com.kuntizbe.kz.ui.commonWidgets.CenteredToolbar
import com.kuntizbe.kz.ui.commonWidgets.PointDivider
import com.kuntizbe.kz.ui.theme.GrayDivider
import com.kuntizbe.kz.ui.theme.GraySettings
import com.kuntizbe.kz.ui.theme.Main
import com.kuntizbe.kz.ui.theme.MainSecond
import com.kuntizbe.kz.ui.theme.White
import java.time.LocalDate
import java.time.chrono.HijrahDate
import java.time.format.DateTimeFormatter
import java.util.Locale


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SettingsScreen(navController: NavController) {
    var checkedIshrak by remember { mutableStateOf(false) }
    var checkedKerakat by remember { mutableStateOf(false) }
    var checkedAsri by remember { mutableStateOf(false) }
    var checkedIsfifar by remember { mutableStateOf(false) }
    var checkedIshtibak by remember { mutableStateOf(false) }
    var checkedIshai by remember { mutableStateOf(false) }

    var isDialogOpen by remember { mutableStateOf(false) }

//    gregorianToHijriFormatted(2024, 4, 3)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            CenteredToolbar(
                title = stringResource(id = R.string.settings),
                onNavigationIconClick = {
                    navController.popBackStack()
                }
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
                    .verticalScroll(rememberScrollState())
            ) {

                Spacer(modifier = Modifier.height(25.dp))

                Divider(
                    color = GrayDivider, modifier = Modifier
                        .padding(vertical = 14.dp)
                        .height(1.dp)
                        .fillMaxWidth()
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(45.dp)
                        .border(width = 1.dp, color = Main, shape = RectangleShape)
                        .clickable { isDialogOpen = !isDialogOpen }
                        .padding(horizontal = 20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = stringResource(id = R.string.search_hidden_city),
                        modifier = Modifier,
                        color = GraySettings
                    )
                    Icon(imageVector = Icons.Default.Search, contentDescription = "search", tint = Main)
                }

                if (isDialogOpen) {
                    SearchDialog(cities) {
                        isDialogOpen = !isDialogOpen
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                PointDivider(pointSize = 100)

                Spacer(modifier = Modifier.height(60.dp))

                SwitchSettings(
                    text = stringResource(id = R.string.israk),
                    checked = checkedIshrak,
                    onCheckedChange = { newCheckedState -> checkedIshrak = newCheckedState })
                Divider(
                    color = GrayDivider, modifier = Modifier
                        .padding(top = 4.dp, bottom = 10.dp)
                        .height(1.dp)
                        .fillMaxWidth()
                )

                SwitchSettings(
                    text = stringResource(id = R.string.keraxat),
                    checked = checkedKerakat,
                    onCheckedChange = { newCheckedState -> checkedKerakat = newCheckedState })
                Divider(
                    color = GrayDivider, modifier = Modifier
                        .padding(top = 4.dp, bottom = 10.dp)
                        .height(1.dp)
                        .fillMaxWidth()
                )

                SwitchSettings(
                    text = stringResource(id = R.string.asriaual),
                    checked = checkedAsri,
                    onCheckedChange = { newCheckedState -> checkedAsri = newCheckedState })
                Divider(
                    color = GrayDivider, modifier = Modifier
                        .padding(top = 4.dp, bottom = 10.dp)
                        .height(1.dp)
                        .fillMaxWidth()
                )

                SwitchSettings(
                    text = stringResource(id = R.string.isfifar),
                    checked = checkedIsfifar,
                    onCheckedChange = { newCheckedState -> checkedIsfifar = newCheckedState })
                Divider(
                    color = GrayDivider, modifier = Modifier
                        .padding(top = 4.dp, bottom = 10.dp)
                        .height(1.dp)
                        .fillMaxWidth()
                )

                SwitchSettings(
                    text = stringResource(id = R.string.ishtibak),
                    checked = checkedIshtibak,
                    onCheckedChange = { newCheckedState -> checkedIshtibak = newCheckedState })
                Divider(
                    color = GrayDivider, modifier = Modifier
                        .padding(top = 4.dp, bottom = 10.dp)
                        .height(1.dp)
                        .fillMaxWidth()
                )

                SwitchSettings(
                    text = stringResource(id = R.string.ishaisani),
                    checked = checkedIshai,
                    onCheckedChange = { newCheckedState -> checkedIshai = newCheckedState })
                Divider(
                    color = GrayDivider, modifier = Modifier
                        .padding(top = 4.dp, bottom = 10.dp)
                        .height(1.dp)
                        .fillMaxWidth()
                )
            }
        }
    }
}


@Composable
fun SwitchSettings(text: String, checked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = text.uppercase(),
            style = TextStyle(
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.W700,
                fontSize = 24.sp,
                color = if (checked) Main else GraySettings
            ),
            modifier = Modifier.padding(vertical = 5.dp)
        )

        Switch(
            colors = SwitchDefaults.colors(
                checkedBorderColor = GraySettings,
                uncheckedBorderColor = GraySettings,
                checkedThumbColor = Main,
                checkedTrackColor = MainSecond,
                uncheckedThumbColor = GraySettings,
                uncheckedTrackColor = White,
            ),
            checked = checked,
            onCheckedChange = onCheckedChange
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun gregorianToHijriFormatted(gregorianYear: Int, gregorianMonth: Int, gregorianDayOfMonth: Int) {
    val gregorianDate = LocalDate.of(gregorianYear, gregorianMonth, gregorianDayOfMonth)
    val hijriDate = HijrahDate.from(gregorianDate)

    val formatter = DateTimeFormatter.ofPattern("d-MMMM yyyy", Locale("ru", "KZ"))
    val formatted = formatter.format(hijriDate)

    Log.d("API", formatted)

}