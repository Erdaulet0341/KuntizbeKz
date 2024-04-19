package com.kuntizbe.kz.screens.home

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Build
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
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
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.kuntizbe.kz.R
import com.kuntizbe.kz.screens.playerTime.gregorianToHijriFormatted
import com.kuntizbe.kz.ui.commonWidgets.PointDivider
import com.kuntizbe.kz.ui.theme.MainLight
import com.kuntizbe.kz.ui.theme.TypographiesCostom
import com.kuntizbe.kz.ui.theme.White
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    val context = LocalContext.current
    val backDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher!!
    val todayDate = LocalDate.now()
    val formatter = DateTimeFormatter.ofPattern("MMMM", Locale("kk", "KZ"))
    val monthName = todayDate.format(formatter)
    val formatterWeek = DateTimeFormatter.ofPattern("EEEE", Locale("kk", "KZ"))
    val weekName = todayDate.format(formatterWeek)

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(White),
        topBar = {
            HomeToolBar(
                scrollBehavior = scrollBehavior,
            )
        }
    ) {
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = gregorianToHijriFormatted().uppercase(), style = TypographiesCostom.secondHeaderText,
                    modifier = Modifier.padding(vertical = 5.dp)
                )
                PointDivider(pointSize = 100)
                Spacer(modifier = Modifier.height(5.dp))
                Text(text = todayDate.dayOfMonth.toString(), style = TypographiesCostom.mainDate)
                Text(text = "${monthName.uppercase()} / ${todayDate.year} ЖЫЛ", style = TypographiesCostom.firstHeaderText)
                Text(
                    text = weekName.uppercase(),
                    style = TypographiesCostom.firstHeaderTextMain,
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

    HandleBackButton(backDispatcher = backDispatcher) {
        (context as? Activity)?.finish()
    }
}


@Composable
fun HandleBackButton(backDispatcher: OnBackPressedDispatcher, onBackPressed: () -> Unit) {
    val lifecycleOwner = LocalLifecycleOwner.current

    val backCallback = remember {
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                onBackPressed()
            }
        }
    }

    BackHandler(onBack = onBackPressed)

    DisposableEffect(lifecycleOwner) {
        backDispatcher.addCallback(lifecycleOwner, backCallback)
        onDispose {
            backCallback.remove()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeToolBar(
    scrollBehavior: TopAppBarScrollBehavior,
) {
    TopAppBar(
        title = {
            Image(
                painterResource(R.drawable.logo_home),
                contentDescription = "logo"
            )
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = White ,
            scrolledContainerColor = MainLight.copy(alpha = 0.7f),
        ),
        scrollBehavior = scrollBehavior
    )
}
