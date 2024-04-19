package com.kuntizbe.kz.screens.menu.menuItemScreens

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
import androidx.compose.material.Divider
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.kuntizbe.kz.R
import com.kuntizbe.kz.ui.commonWidgets.CenteredToolbar
import com.kuntizbe.kz.ui.theme.GrayDivider
import com.kuntizbe.kz.ui.theme.GraySettings
import com.kuntizbe.kz.ui.theme.Main
import com.kuntizbe.kz.ui.theme.MainSecond
import com.kuntizbe.kz.ui.theme.White


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SettingsScreen(navController: NavController) {
    val viewModel:SettingsViewModel = viewModel()
    val context = LocalContext.current

    var checkedIshrak by remember { mutableStateOf(viewModel.getSettingsFromSharedPreferences(context, "ishrak")) }
    var checkedKerakat by remember { mutableStateOf(viewModel.getSettingsFromSharedPreferences(context, "kerakat")) }
    var checkedAsri by remember { mutableStateOf(viewModel.getSettingsFromSharedPreferences(context, "asri")) }
    var checkedIsfifar by remember { mutableStateOf(viewModel.getSettingsFromSharedPreferences(context, "isfifar")) }
    var checkedIshtibak by remember { mutableStateOf(viewModel.getSettingsFromSharedPreferences(context, "ishtibak")) }
    var checkedIshai by remember { mutableStateOf(viewModel.getSettingsFromSharedPreferences(context, "ishai")) }

//    var isDialogOpen by remember { mutableStateOf(false) }

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
                    .padding(horizontal = 20.dp)
                    .verticalScroll(rememberScrollState())
            ) {

//                Spacer(modifier = Modifier.height(25.dp))
//
//                Divider(
//                    color = GrayDivider, modifier = Modifier
//                        .padding(vertical = 14.dp)
//                        .height(1.dp)
//                        .fillMaxWidth()
//                )
//
//                Row(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(45.dp)
//                        .border(width = 1.dp, color = Main, shape = RectangleShape)
//                        .clickable { isDialogOpen = !isDialogOpen }
//                        .padding(horizontal = 20.dp),
//                    verticalAlignment = Alignment.CenterVertically,
//                    horizontalArrangement = Arrangement.SpaceBetween
//                ) {
//                    Text(
//                        text = stringResource(id = R.string.search_hidden_city),
//                        modifier = Modifier,
//                        color = GraySettings
//                    )
//                    Icon(imageVector = Icons.Default.Search, contentDescription = "search", tint = Main)
//                }
//
//                if (isDialogOpen) {
//                    SearchDialog(cities) {
//                        isDialogOpen = !isDialogOpen
//                    }
//                }
//
//                Spacer(modifier = Modifier.height(16.dp))
//
//                PointDivider(pointSize = 100)

                Spacer(modifier = Modifier.height(40.dp))

                SwitchSettings(
                    text = stringResource(id = R.string.israk),
                    checked = checkedIshrak,
                    onCheckedChange = { newCheckedState -> checkedIshrak = newCheckedState
                    viewModel.saveSettingsToSharedPreferences(context, "ishrak", newCheckedState)
                    } )
                Divider(
                    color = GrayDivider, modifier = Modifier
                        .padding(top = 4.dp, bottom = 10.dp)
                        .height(1.dp)
                        .fillMaxWidth()
                )

                SwitchSettings(
                    text = stringResource(id = R.string.keraxat),
                    checked = checkedKerakat,
                    onCheckedChange = { newCheckedState -> checkedKerakat = newCheckedState
                        viewModel.saveSettingsToSharedPreferences(context, "kerakat", newCheckedState)
                    })
                Divider(
                    color = GrayDivider, modifier = Modifier
                        .padding(top = 4.dp, bottom = 10.dp)
                        .height(1.dp)
                        .fillMaxWidth()
                )

                SwitchSettings(
                    text = stringResource(id = R.string.asriaual),
                    checked = checkedAsri,
                    onCheckedChange = { newCheckedState -> checkedAsri = newCheckedState
                        viewModel.saveSettingsToSharedPreferences(context, "asri", newCheckedState)

                    })
                Divider(
                    color = GrayDivider, modifier = Modifier
                        .padding(top = 4.dp, bottom = 10.dp)
                        .height(1.dp)
                        .fillMaxWidth()
                )

                SwitchSettings(
                    text = stringResource(id = R.string.isfifar),
                    checked = checkedIsfifar,
                    onCheckedChange = { newCheckedState -> checkedIsfifar = newCheckedState
                        viewModel.saveSettingsToSharedPreferences(context, "isfifar", newCheckedState)
                    })
                Divider(
                    color = GrayDivider, modifier = Modifier
                        .padding(top = 4.dp, bottom = 10.dp)
                        .height(1.dp)
                        .fillMaxWidth()
                )

                SwitchSettings(
                    text = stringResource(id = R.string.ishtibak),
                    checked = checkedIshtibak,
                    onCheckedChange = { newCheckedState -> checkedIshtibak = newCheckedState
                        viewModel.saveSettingsToSharedPreferences(context, "ishtibak", newCheckedState)
                    })
                Divider(
                    color = GrayDivider, modifier = Modifier
                        .padding(top = 4.dp, bottom = 10.dp)
                        .height(1.dp)
                        .fillMaxWidth()
                )

                SwitchSettings(
                    text = stringResource(id = R.string.ishaisani),
                    checked = checkedIshai,
                    onCheckedChange = { newCheckedState -> checkedIshai = newCheckedState
                        viewModel.saveSettingsToSharedPreferences(context, "ishai", newCheckedState)
                    })
                Divider(
                    color = GrayDivider, modifier = Modifier
                        .padding(top = 4.dp, bottom = 10.dp)
                        .height(1.dp)
                        .fillMaxWidth()
                )

                Text(
                    modifier = Modifier.fillMaxWidth().padding(top = 30.dp),
                    text = stringResource(id = R.string.settings_text),
                    style = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 16.sp, color = Main),
                    textAlign = TextAlign.Justify
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
                fontSize = 20.sp,
                color = if (checked) Main else GraySettings
            ),
            modifier = Modifier.padding(vertical = 5.dp)
        )

        Switch(
            modifier = Modifier.height(20.dp),
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