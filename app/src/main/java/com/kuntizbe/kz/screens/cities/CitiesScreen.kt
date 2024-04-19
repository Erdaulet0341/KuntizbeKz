package com.kuntizbe.kz.screens.cities

import CustomTabs
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.kuntizbe.kz.R
import com.kuntizbe.kz.data.City
import com.kuntizbe.kz.data.Regions
import com.kuntizbe.kz.navigation.NavigationScreens
import com.kuntizbe.kz.screens.menu.menuItemScreens.FaqItem
import com.kuntizbe.kz.screens.playerTime.SaveIdToSharedPreferences
import com.kuntizbe.kz.ui.commonWidgets.CenteredToolbar
import com.kuntizbe.kz.ui.theme.GraySettings
import com.kuntizbe.kz.ui.theme.Main
import com.kuntizbe.kz.ui.theme.White
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun CitiesScreen(navController: NavController) {
    val context = LocalContext.current
    val viewModel: CitiesViewModel = viewModel()

    LaunchedEffect(Unit) {
        viewModel.observeAllTimes(context)
    }


    Box(
        modifier = Modifier
            .background(White)
            .fillMaxSize()
            .background(White)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            CenteredToolbar(title = stringResource(id = R.string.cities), onNavigationIconClick = {
                navController.navigate(NavigationScreens.Home.route)
            })
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                CustomTabs(viewModel, context, navController)
            }
        }
    }
}

@Composable
fun AllCities(viewModel: CitiesViewModel, context: Context, navController: NavController) {
    var isDialogOpen by remember { mutableStateOf(false) }
    val regions = Regions()

    Row(modifier = Modifier
        .fillMaxWidth()
        .height(45.dp)
        .border(width = 1.dp, color = Main, shape = RectangleShape)
        .clickable { isDialogOpen = !isDialogOpen }
        .padding(horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween) {
        Text(
            text = stringResource(id = R.string.search_hidden_city),
            modifier = Modifier,
            color = GraySettings
        )
        Icon(
            imageVector = Icons.Default.Search, contentDescription = "search", tint = Main
        )
    }

    if (isDialogOpen) {
        SearchDialog(cities = regions.AllCitiesOfRegions,
            viewModel = viewModel,
            context = context,
            navController = navController,
            onDismiss = {
                isDialogOpen = !isDialogOpen
            })
    }

    Spacer(modifier = Modifier.height(20.dp))
    LazyColumn {
        items(regions.REGIONS) {
            FaqItem(title = it.nameKaz, content = {
                Column(
                    modifier = Modifier.padding(horizontal = 10.dp)
                ) {
                    ListOfCities(it.cities, viewModel, context, navController, "null", false)
                }
            })
        }
    }
}

@Composable
fun ListOfCities(
    cities: List<City>,
    viewModel: CitiesViewModel,
    context: Context,
    navController: NavController,
    query: String,
    isFiltered: Boolean
) {
    val coroutineScope = rememberCoroutineScope()
    val toastSuccess = stringResource(id = R.string.toast_success)
    val toastFail = stringResource(id = R.string.toast_failture)
    val toastAlready = stringResource(id = R.string.toast_already)
    val toastFirst = stringResource(id = R.string.toast_first_download)

    var finalList = cities
    val filteredCities = cities.filter {
        it.cityName.contains(query, ignoreCase = true)
    }

    if (isFiltered) {
        if (filteredCities.isEmpty()) {
            Text(
                text = stringResource(id = R.string.dont_find_city),
                style = MaterialTheme.typography.body1,
            )
        }
        finalList = filteredCities.sortedBy { it.cityName }
    }

    finalList.forEach { city ->
        val isInserted = viewModel.checkInsert(city.cityId)
        var icon by remember {
            mutableStateOf(if (isInserted) R.drawable.download_done_24 else R.drawable.download_24)
        }
        if (isInserted) painterResource(id = R.drawable.download_done_24) else painterResource(
            id = R.drawable.download_24
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = city.cityName.uppercase(),
                style = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 16.sp, color = Main),
                modifier = Modifier.clickable {
                    if (isInserted) {
                        SaveIdToSharedPreferences(context = context, city.cityId)
                        navController.navigate(NavigationScreens.PrayerTime.route)
                    } else {
                        Toast.makeText(
                            context, toastFirst, Toast.LENGTH_SHORT
                        ).show()
                    }
                })
            Icon(
                modifier = Modifier.clickable {
                    if (isInserted) {
                        Toast.makeText(
                            context, toastAlready, Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        viewModel.fetchPrayerTimes(city.cityId, context)
                        coroutineScope.launch {
                            icon = R.drawable.access_time_24
                            delay(1500)
                            if (viewModel.checkInsert(city.cityId)) {
                                Toast.makeText(
                                    context, toastSuccess, Toast.LENGTH_SHORT
                                ).show()
                                SaveIdToSharedPreferences(context = context, city.cityId)
                                icon = R.drawable.download_done_24
                            } else {
                                Toast.makeText(context, toastFail, Toast.LENGTH_SHORT).show()
                                icon = R.drawable.download_24
                            }
                        }

                    }
                }, painter = painterResource(id = icon), contentDescription = "null", tint = Main
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()
        ) {
            repeat(100) {
                Divider(
                    modifier = Modifier
                        .width(4.dp)
                        .background(Main)
                )
                Spacer(modifier = Modifier.width(2.dp))
            }
        }
    }
}

@Composable
fun ListOfCities2(
    cities: List<City>,
    viewModel: CitiesViewModel,
    context: Context,
    navController: NavController,
    query: String,
) {
    val coroutineScope = rememberCoroutineScope()
    val toastSuccess = stringResource(id = R.string.toast_success)
    val toastFail = stringResource(id = R.string.toast_failture)
    val toastAlready = stringResource(id = R.string.toast_already)
    val toastFirst = stringResource(id = R.string.toast_first_download)

    var filteredCities = cities.filter {
        it.cityName.contains(query, ignoreCase = true)
    }

    if (filteredCities.isEmpty()) {
        Text(
            text = stringResource(id = R.string.dont_find_city),
            style = MaterialTheme.typography.body1,
        )
    }
    filteredCities = filteredCities.sortedBy { it.cityName }

    LazyColumn {
        items(filteredCities){city ->
            val isInserted = viewModel.checkInsert(city.cityId)
            var icon by remember {
                mutableStateOf(if (isInserted) R.drawable.download_done_24 else R.drawable.download_24)
            }
            if (isInserted) painterResource(id = R.drawable.download_done_24) else painterResource(
                id = R.drawable.download_24
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = city.cityName.uppercase(),
                    style = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 16.sp, color = Main),
                    modifier = Modifier.clickable {
                        if (isInserted) {
                            SaveIdToSharedPreferences(context = context, city.cityId)
                            navController.navigate(NavigationScreens.PrayerTime.route)
                        } else {
                            Toast.makeText(
                                context, toastFirst, Toast.LENGTH_SHORT
                            ).show()
                        }
                    })
                Icon(
                    modifier = Modifier.clickable {
                        if (isInserted) {
                            Toast.makeText(
                                context, toastAlready, Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            viewModel.fetchPrayerTimes(city.cityId, context)
                            coroutineScope.launch {
                                icon = R.drawable.access_time_24
                                delay(1500)
                                if (viewModel.checkInsert(city.cityId)) {
                                    Toast.makeText(
                                        context, toastSuccess, Toast.LENGTH_SHORT
                                    ).show()
                                    SaveIdToSharedPreferences(context = context, city.cityId)
                                    icon = R.drawable.download_done_24
                                } else {
                                    Toast.makeText(context, toastFail, Toast.LENGTH_SHORT).show()
                                    icon = R.drawable.download_24
                                }
                            }

                        }
                    }, painter = painterResource(id = icon), contentDescription = "null", tint = Main
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()
            ) {
                repeat(100) {
                    Divider(
                        modifier = Modifier
                            .width(4.dp)
                            .background(Main)
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                }
            }
        }
    }
}

@Composable
fun SearchDialog(
    cities: List<City>,
    onDismiss: () -> Unit,
    viewModel: CitiesViewModel,
    navController: NavController,
    context: Context
) {
    var searchText by remember { mutableStateOf("") }

    Dialog(
        onDismissRequest = onDismiss
    ) {
        Surface {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.9f)
                    .padding(12.dp)
            ) {
                Icon(imageVector = Icons.Default.Close,
                    tint = Main,
                    contentDescription = "close",
                    modifier = Modifier
                        .size(30.dp)
                        .clickable { onDismiss() }
                        .align(Alignment.End))

                OutlinedTextField(
                    value = searchText,
                    onValueChange = { searchText = it },
                    label = {
                        Text(stringResource(id = R.string.search_hidden), color = GraySettings)
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(onDone = {  }),
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Main, backgroundColor = White, cursorColor = Main
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                ListOfCities2(cities, viewModel, context, navController, searchText)
            }
        }
    }
}