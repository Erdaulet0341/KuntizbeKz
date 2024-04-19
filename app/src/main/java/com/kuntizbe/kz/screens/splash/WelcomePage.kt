package com.kuntizbe.kz.screens.splash

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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
import com.kuntizbe.kz.ui.theme.Black
import com.kuntizbe.kz.ui.theme.Main
import com.kuntizbe.kz.ui.theme.TypographiesCostom
import com.kuntizbe.kz.ui.theme.White

@Composable
fun WelcomePage(navController: NavController) {

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .background(White)
            .fillMaxSize()
            .padding(horizontal = 20.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.9f)
        ) {

            Image(
                painterResource(R.drawable.logo_home),
                contentDescription = "logo",
                modifier = Modifier
                    .padding(top = 10.dp)
                    .height(60.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
            )

            Spacer(modifier = Modifier.height(60.dp))
            Text(
                text = stringResource(id = R.string.welcome).uppercase(),
                style = TypographiesCostom.firstHeaderTextMain,
                modifier = Modifier.padding(vertical = 5.dp)
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                text = stringResource(id = R.string.welcome_text),
                style = TextStyle(color = Black, fontSize = 17.sp),
                textAlign = TextAlign.Justify
            )

        }
        Button(
            modifier = Modifier
                .align(Alignment.End),
            colors = ButtonDefaults.buttonColors(backgroundColor = Main, contentColor = White),
            onClick = {
                saveFirstToSharedPreferences(context, true)
                navController.navigate(NavigationScreens.Cities.route)
            }) {
            Text(
                text = stringResource(id = R.string.continue_text).uppercase(),
                color = White,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

fun saveFirstToSharedPreferences(context: Context, isOpen: Boolean) {
    val sharedPref: SharedPreferences = context.getSharedPreferences(
        "FIRST_OPEN", Context.MODE_PRIVATE
    )
    val editor: SharedPreferences.Editor = sharedPref.edit()
    editor.putString("IS_OPENED", isOpen.toString()).apply()
}

@RequiresApi(Build.VERSION_CODES.O)
fun getFirstFromSharedPreferences(context: Context): Boolean {
    val sharedPref: SharedPreferences = context.getSharedPreferences(
        "FIRST_OPEN", Context.MODE_PRIVATE
    )
    return sharedPref.getString("IS_OPENED", "false")!!.toBoolean()
}