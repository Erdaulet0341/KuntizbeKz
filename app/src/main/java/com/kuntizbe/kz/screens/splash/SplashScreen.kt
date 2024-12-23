package com.kuntizbe.kz.screens.splash

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.kuntizbe.kz.R
import com.kuntizbe.kz.navigation.NavigationScreens
import com.kuntizbe.kz.ui.theme.White
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController){

    val scale = remember {
        Animatable(0f)
    }
    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 2f,
            animationSpec = tween(
                durationMillis = 1000,
                easing = {
                    OvershootInterpolator(1.0f).getInterpolation(it)
                }
            )
        )
        delay(300L)
        navController.navigate(route = NavigationScreens.MainNavigation.route)
    }

    Box(
        modifier = Modifier.background(White).fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.kuntizbe_logo),
            contentDescription = "logo",
            modifier = Modifier.scale(scale.value)
        )
    }
}