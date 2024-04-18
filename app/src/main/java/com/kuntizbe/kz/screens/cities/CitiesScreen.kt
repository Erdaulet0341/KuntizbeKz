package com.kuntizbe.kz.screens.cities

import CustomTabs
import android.content.Context
import android.util.Log
import android.widget.Toast
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
import com.kuntizbe.kz.data.REGIONS
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
            .fillMaxSize()
            .background(White)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            CenteredToolbar(title = stringResource(id = R.string.cities), onNavigationIconClick = {
                navController.popBackStack()
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
        SearchDialog(cities) {
            isDialogOpen = !isDialogOpen
        }
    }

    Spacer(modifier = Modifier.height(20.dp))
    LazyColumn {
        items(REGIONS) {
            FaqItem(title = it.nameKaz, content = {
                Column(
                    modifier = Modifier.padding(horizontal = 10.dp)
                ) {
                    ListOfCities(it.cities, viewModel, context, navController)
                }
            })
        }
    }
}

@Composable
fun ListOfCities(
    cities: List<City>, viewModel: CitiesViewModel, context: Context, navController: NavController
) {
    val coroutineScope = rememberCoroutineScope()

    cities.forEach { city ->
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
            Text(

                text = city.cityName.uppercase(),
                style = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 16.sp, color = Main),
                modifier = Modifier.clickable {
                    if (isInserted) {
                        SaveIdToSharedPreferences(context = context, city.cityId)
                        navController.navigate(NavigationScreens.PrayerTime.route)
                    } else {
                        Toast.makeText(
                            context, "First download city then click", Toast.LENGTH_SHORT
                        ).show()
                    }
                })
            Icon(
                modifier = Modifier.clickable {
                    if (isInserted) {
                        Toast.makeText(
                            context, "Already", Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        viewModel.fetchPrayerTimes(city.cityId, context)
                        coroutineScope.launch {
                            icon = R.drawable.access_time_24
                            delay(1000)
                            if (viewModel.checkInsert(city.cityId)) {
                                Toast.makeText(
                                    context, "Success", Toast.LENGTH_SHORT
                                ).show()
                                icon = R.drawable.download_done_24
                            } else {
                                Toast.makeText(context, "Failture", Toast.LENGTH_SHORT).show()
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


val cities = listOf(
    "New York",
    "Los Angeles",
    "Chicago",
    "Houston",
    "Phoenix",
    "Philadelphia",
    "San Afdntonio",
    "San Diego",
    "Dallas",
    "San Jose",
    "New York",
    "Los Angeles",
    "Chicago",
    "Houston",
    "Phoenix",
    "Philadelphia",
    "San Afdntonio",
    "San Diego",
    "Dallas",
    "San Jose",
    "New York",
    "Los Angeles",
    "Chicago",
    "Houston",
    "Phoenix",
    "Philadelphia",
    "San Afdntonio",
    "San Diego",
    "Dallas",
    "San Jose"
)

@Composable
fun SearchDialog(
    cities: List<String>, onDismiss: () -> Unit
) {
    var searchText by remember { mutableStateOf("") }

    Dialog(
        onDismissRequest = onDismiss
    ) {
        Surface {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
            ) {
                Icon(imageVector = Icons.Default.Close,
                    tint = Main,
                    contentDescription = "close",
                    modifier = Modifier
                        .size(30.dp)
                        .clickable { onDismiss() }
                        .align(Alignment.End))

                OutlinedTextField(value = searchText,
                    onValueChange = { searchText = it },
                    label = {
                        Text(
                            stringResource(id = R.string.search_hidden), color = GraySettings
                        )
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(onDone = { /* Perform search action if needed */ }),
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Main, backgroundColor = White, cursorColor = Main
                    ))

                Spacer(modifier = Modifier.height(16.dp))

                FilteredCitiesList(cities = cities, query = searchText)
            }
        }
    }
}

@Composable
fun FilteredCitiesList(
    cities: List<String>, query: String
) {
    val filteredCities = cities.filter {
        it.contains(query, ignoreCase = true)
    }

    LazyColumn {

        items(filteredCities) { city ->
            Text(
                text = city,
                style = MaterialTheme.typography.body1,
                modifier = Modifier
                    .height(40.dp)
                    .fillMaxWidth()
                    .padding(top = 8.dp)
                    .clickable {
                        Log.d("API", city)
                    },
            )
        }
    }

    if (filteredCities.isEmpty()) {
        Text(
            text = "no city",
            style = MaterialTheme.typography.body1,
        )
    }
}