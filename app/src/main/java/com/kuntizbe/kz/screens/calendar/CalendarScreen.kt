package com.kuntizbe.kz.screens.calendar

import android.annotation.SuppressLint
import android.os.Build
import android.widget.CalendarView
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.kuntizbe.kz.R
import com.kuntizbe.kz.navigation.NavigationScreens
import com.kuntizbe.kz.ui.commonWidgets.CenteredToolbar
import com.kuntizbe.kz.ui.commonWidgets.PointDivider
import com.kuntizbe.kz.ui.theme.BlueBtn
import com.kuntizbe.kz.ui.theme.TextColorMain
import com.kuntizbe.kz.ui.theme.White
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun CalendarScreen(navController: NavController) {
    val viewModel: CalendarViewModel = viewModel()

    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            CenteredToolbar(
                title = stringResource(id = R.string.days),
                onNavigationIconClick = {
                    navController.popBackStack()
                }
            )
            Column(modifier = Modifier.padding(20.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "КҮНДЕР",
                    style = TextStyle(
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.W700,
                        fontSize = 21.sp,
                        color = TextColorMain
                    ),
                    modifier = Modifier.padding(top = 50.dp, bottom = 12.dp)
                )

                PointDivider(pointSize = 100)

                Spacer(modifier = Modifier.height(10.dp))

            }

            val date = CalendarBody()

            Button(
                onClick = {
                    viewModel.saveDateToSharedPreferences(context, date)
                    navController.navigate(NavigationScreens.HomeForCalendarScreen.route)
                },
                modifier = Modifier
                    .height(45.dp)
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth()
                    .background(color = BlueBtn, shape = RoundedCornerShape(5.dp)),
                colors = ButtonColors(
                    containerColor = Color.Transparent,
                    contentColor = White,
                    disabledContainerColor = Color.Transparent,
                    disabledContentColor = Color.Transparent
                )
            ) {
                Text(
                    text = "ОК",
                    style = TextStyle(
                        color = White,
                        fontSize = 21.sp,
                        fontWeight = FontWeight.Bold
                    ),
                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.Q)
@SuppressLint("ResourceAsColor")
@Composable
fun CalendarBody() : String{
    val currentDate = LocalDate.now()
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val date = remember { mutableStateOf(currentDate.format(formatter))  }

    AndroidView(
        factory = {
            CalendarView(it).apply {
                val locale = Locale("kk")
                val config = context.resources.configuration
                config.setLocale(locale)
                context.createConfigurationContext(config)
            }
        },
        modifier = Modifier.fillMaxWidth(),
        update = { calendarView ->
            calendarView.setOnDateChangeListener { f, year, month, dayOfMonth ->

                val m = if(month.toString().length == 1) "0${month+1}" else month+1
                val d = if(dayOfMonth.toString().length == 1) "0$dayOfMonth" else dayOfMonth
                date.value = "$year-$m-$d"
            }
        }
    )
    return date.value
}

