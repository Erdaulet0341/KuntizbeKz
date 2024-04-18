package com.kuntizbe.kz.screens.cities

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.kuntizbe.kz.R
import com.kuntizbe.kz.navigation.NavigationScreens
import com.kuntizbe.kz.screens.playerTime.SaveIdToSharedPreferences
import com.kuntizbe.kz.ui.theme.GrayText
import com.kuntizbe.kz.ui.theme.Main

@Composable
fun SavedCities(navController: NavController, context:Context, viewModel: CitiesViewModel) {

    val id = 8424
    val allTimes by viewModel.allTimes.collectAsState()


    LaunchedEffect(Unit) {
        viewModel.observeAllTimes(context)
    }

    if(allTimes.isEmpty()){
        Box(modifier = Modifier.fillMaxWidth().fillMaxHeight(0.8f), contentAlignment = Alignment.Center){
            Text(
                text = stringResource(id = R.string.null_cities),
                style = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 17.sp, color = GrayText),
                textAlign = TextAlign.Center
            )
        }
    }
    
    LazyColumn {
        items(allTimes){
            Text(
                text = it.prayerTimes.cityname.uppercase(),
                style = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 20.sp, color = Main),
                modifier = Modifier
                    .clickable {
                        SaveIdToSharedPreferences(context = context, it.id)
                        navController.navigate(NavigationScreens.PrayerTime.route)
                    }
                    .fillMaxWidth()
                    .padding(vertical = 10.dp))

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