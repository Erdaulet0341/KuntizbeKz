package com.kuntizbe.kz.screens.menu.menuItemScreens

import android.annotation.SuppressLint
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.util.Log
import android.view.ContextThemeWrapper
import android.view.ViewGroup
import android.widget.CalendarView
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
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CalendarLocale
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.kuntizbe.kz.R
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.os.ConfigurationCompat
import androidx.core.view.size
import com.kuntizbe.kz.ui.commonWidgets.CenteredToolbar
import com.kuntizbe.kz.ui.commonWidgets.PointDivider
import com.kuntizbe.kz.ui.theme.BlueBtn
import com.kuntizbe.kz.ui.theme.Main
import com.kuntizbe.kz.ui.theme.TextColorMain
import com.kuntizbe.kz.ui.theme.TypographiesCostom
import com.kuntizbe.kz.ui.theme.White
import java.time.LocalDate
import java.time.chrono.HijrahChronology
import java.time.chrono.HijrahDate
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun CalendarScreen(navController: NavController) {
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

            CalendarBody()

            Button(
                onClick = {

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
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.Q)
@SuppressLint("ResourceAsColor")
@Composable
fun CalendarBody() {

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
            calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
                Log.d("ttt", "$year - $month - $dayOfMonth")
            }
        }
    )
}

