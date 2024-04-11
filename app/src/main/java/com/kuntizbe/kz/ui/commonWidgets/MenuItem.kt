package com.kuntizbe.kz.ui.commonWidgets

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kuntizbe.kz.R
import com.kuntizbe.kz.ui.theme.Gray
import com.kuntizbe.kz.ui.theme.Main
import com.kuntizbe.kz.ui.theme.White

@Composable
fun MenuItem(text:String, icon :ImageVector, onClick: () -> Unit){

    Box(
        modifier = Modifier
            .height(130.dp)
            .fillMaxWidth()
            .padding(horizontal = 5.dp)
            .border(width = 3.dp, color = Main)
            .background(White)
            .clickable {
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {
        Column( verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                modifier = Modifier.size(28.dp),
                imageVector = icon,
                contentDescription = text,
                tint = Main
            )
            Text(
                modifier = Modifier
                    .padding(horizontal = 3.dp),
                text = text.uppercase(),
                style = TextStyle(color = Main, fontWeight = FontWeight.SemiBold, fontSize = 14.sp),
                textAlign = TextAlign.Center
            )
        }
    }
}