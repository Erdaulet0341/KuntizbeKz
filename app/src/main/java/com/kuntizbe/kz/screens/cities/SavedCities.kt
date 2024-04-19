package com.kuntizbe.kz.screens.cities

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
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
import com.kuntizbe.kz.ui.theme.White

@SuppressLint("SuspiciousIndentation")
@Composable
fun SavedCities(navController: NavController, context: Context, viewModel: CitiesViewModel) {

    val allTimes by viewModel.allTimes.collectAsState()
    val openDialog = remember { mutableStateOf(false)  }
    val deletedItemId = remember { mutableStateOf(0)  }

    LaunchedEffect(Unit) {
        viewModel.observeAllTimes(context)
    }

    if (allTimes.isEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.8f), contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(id = R.string.null_cities),
                style = TextStyle(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 17.sp,
                    color = GrayText
                ),
                textAlign = TextAlign.Center
            )
        }
    }

    LazyColumn {
        items(allTimes) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = it.prayerTimes.cityname.uppercase(),
                    style = TextStyle(
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 20.sp,
                        color = Main
                    ),
                    modifier = Modifier
                        .clickable {
                            SaveIdToSharedPreferences(context = context, it.id)
                            navController.navigate(NavigationScreens.PrayerTime.route)
                        }
                        .fillMaxWidth(0.8f)
                        .padding(vertical = 10.dp)
                )

                Icon(
                    modifier = Modifier.clickable {
                        deletedItemId.value = it.id
                        openDialog.value = true
                    }, painter = painterResource(id = R.drawable.delete_24), contentDescription = "null", tint = Main
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

    val deleteSuccess = stringResource(id = R.string.dialog_delete_success)
    if(openDialog.value)
    AlertDialog(
        modifier = Modifier.fillMaxWidth(),
        onDismissRequest = {
            openDialog.value = false
        },
        title = {
            Text(text = stringResource(id = R.string.dialog_title), fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
        },
        text = {
            Text(text = stringResource(id = R.string.dialog_boby))
        },
        confirmButton = {
            Button(
                modifier = Modifier.fillMaxWidth(0.45f),
                colors = ButtonDefaults.buttonColors( backgroundColor = Color.Red, contentColor = White),
                onClick = {
                    viewModel.deleteById(context, deletedItemId.value)
                    viewModel.observeAllTimes(context)
                    Toast.makeText(
                        context, deleteSuccess, Toast.LENGTH_SHORT
                    ).show()
                    openDialog.value = false
                }) {
                Text(text = stringResource(id = R.string.confirm), color = White)
            }
        },
        dismissButton = {
            Button(
                modifier = Modifier.fillMaxWidth(0.45f),
                colors = ButtonDefaults.buttonColors( backgroundColor = Main, contentColor = White),
                onClick = {
                    openDialog.value = false
                }) {
                Text(text = stringResource(id = R.string.dissmis), color = White)
            }
        }
    )

}