package com.kuntizbe.kz.screens.home

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.kuntizbe.kz.screens.calendar.getDateFromSharedPreferences
import com.kuntizbe.kz.screens.calendar.gregorianToHijriFormattedForHome
import com.kuntizbe.kz.ui.commonWidgets.CenteredToolbar
import com.kuntizbe.kz.ui.commonWidgets.PointDivider
import com.kuntizbe.kz.ui.theme.GraySettings
import com.kuntizbe.kz.ui.theme.GrayText
import com.kuntizbe.kz.ui.theme.TypographiesCostom
import com.kuntizbe.kz.ui.theme.White
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeForCalendarScreen(navController: NavController) {

    val context = LocalContext.current
    val date = getDateFromSharedPreferences(context)
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val dateParse = LocalDate.parse(date, formatter)
    val kazakhLocale = Locale("kk", "KZ")
    val weekName = dateParse.dayOfWeek.getDisplayName(java.time.format.TextStyle.FULL, kazakhLocale)
    val monthName = dateParse.month.getDisplayName(java.time.format.TextStyle.FULL, kazakhLocale)
    val islamicDate = gregorianToHijriFormattedForHome(date)

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(White),
        topBar = {
            CenteredToolbar(
                title = "",
                onNavigationIconClick = {
                    navController.popBackStack()
                }
            )
        }
    ) {
        Box(modifier = Modifier
            .background(White)
            .fillMaxSize()
            .padding(16.dp)) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = islamicDate.uppercase(), style = TypographiesCostom.secondHeaderText,
                    modifier = Modifier.padding(vertical = 5.dp)
                )
                PointDivider(pointSize = 100)
                Spacer(modifier = Modifier.height(5.dp))
                Text(text = dateParse.dayOfMonth.toString(), style = TextStyle(
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Bold,
                    fontSize = 180.sp,
                    color = GrayText
                )
                )
                Text(text = "${monthName.uppercase()} / ${dateParse.year} ЖЫЛ", style = TypographiesCostom.firstHeaderText)
                Text(
                    text = weekName.uppercase(),
                    style = TextStyle(
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.W700,
                        fontSize = 24.sp,
                        color = GraySettings),
                    modifier = Modifier.padding(vertical = 5.dp)
                )
                PointDivider(pointSize = 100)
                Text(
                    text = "Ы.Алтынсарин дүниеге келді (1841 ж). ",
                    textAlign = TextAlign.Center,
                    style = TypographiesCostom.contentTextColor,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                PointDivider(pointSize = 100)
                Text(
                    text = "Фатиха мен Аятүл-күрсиді оқыған адамға сол күні көз тимейді.",
                    textAlign = TextAlign.Center,
                    style = TypographiesCostom.contentTextColor,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                Text(
                    text = "Хадис шәриф",
                    style = TypographiesCostom.contentTextColorBold
                )
            }
        }
    }

}
