package com.kuntizbe.kz.ui.commonWidgets

import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.kuntizbe.kz.ui.theme.Main

@Composable
fun CommonDivider(
    modifier: Modifier = Modifier,
    thickness: Dp = 0.5.dp,
    color: Color = Main
) {
    Divider(
        modifier = modifier,
        thickness = thickness,
        color = color
    )
}
