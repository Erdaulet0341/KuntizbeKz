package com.kuntizbe.kz.ui.commonWidgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kuntizbe.kz.ui.theme.White

@Composable
fun CommonButton(
    modifier: Modifier,
    onClick: () -> Unit,
    title: String,
    icon: Painter
){
    Button(
        onClick = onClick,
        modifier = modifier,
        colors = ButtonColors(
            containerColor = Color.Transparent,
            contentColor = White,
            disabledContainerColor = Color.Transparent,
            disabledContentColor = Color.Transparent
        )
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Icon(
                painter = icon,
                contentDescription = "null",
                modifier = Modifier.padding(end = 16.dp)
            )
            Text(
                text = title, style = TextStyle(
                    color = White,
                    fontSize = 17.sp,
                )
            )
        }
    }
}