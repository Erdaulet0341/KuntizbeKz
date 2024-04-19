package com.kuntizbe.kz.screens.menu.menuItemScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.kuntizbe.kz.R
import com.kuntizbe.kz.ui.commonWidgets.CenteredToolbar
import com.kuntizbe.kz.ui.theme.TypographiesCostom
import com.kuntizbe.kz.ui.theme.White

@Composable
fun AboutUsScreen(navController: NavController) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            CenteredToolbar(
                title = stringResource(id = R.string.about_us),
                onNavigationIconClick = {
                    navController.popBackStack()
                }
            )
           
            Text(
                text = stringResource(id = R.string.about_us_text),
                modifier = Modifier
                    .padding(vertical = 6.dp, horizontal = 20.dp)
                    .fillMaxWidth(),
                style = TypographiesCostom.contentTextColor,
                fontSize = 17.sp,
                textAlign = TextAlign.Justify,
                lineHeight = 27.sp
            )
            Spacer(modifier = Modifier.height(36.dp))
            Image(painter = painterResource(R.drawable.biz_turaly), contentDescription = "null")
        }
    }
}
