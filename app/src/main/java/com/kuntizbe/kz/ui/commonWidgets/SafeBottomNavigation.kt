package com.kuntizbe.kz.ui.commonWidgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.BottomNavigation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.kuntizbe.kz.ui.ext.safeNavigationPadding

@Composable
fun SafeBottomNavigation(
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.White,
    elevation: Dp = 0.dp,
    content: @Composable RowScope.() -> Unit
) {
    Column(
        modifier = Modifier
            .background(backgroundColor)
            .safeNavigationPadding()
    ) {
        CommonDivider(thickness = 5.dp)
        BottomNavigation(
            modifier = modifier,
            backgroundColor = backgroundColor,
            elevation = elevation,
            content = content,
        )
    }
}