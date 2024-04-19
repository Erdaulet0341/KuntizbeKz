package com.kuntizbe.kz.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalView
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40,

)

@Composable
fun KuntizbeKzTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val systemUiController = rememberSystemUiController()
    val colorScheme = LightColorScheme
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            if(darkTheme){
                systemUiController.setSystemBarsColor(White)
            }
            else{
                systemUiController.setSystemBarsColor(Gray)
                systemUiController.setNavigationBarColor(White)
            }
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}