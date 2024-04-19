package com.kuntizbe.kz.screens.menu.menuItemScreens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.kuntizbe.kz.R
import com.kuntizbe.kz.ui.commonWidgets.CenteredToolbar
import com.kuntizbe.kz.ui.theme.GrayText
import com.kuntizbe.kz.ui.theme.White

@Composable
fun QiblaScreen(navController: NavController) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            CenteredToolbar(
                title = stringResource(id = R.string.location),
                onNavigationIconClick = {
                    navController.popBackStack()
                }
            )
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                Text(modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center, text = stringResource(id = R.string.qibla_text), color = GrayText)
            }
        }
    }
}