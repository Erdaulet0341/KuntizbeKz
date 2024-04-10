package com.kuntizbe.kz.ui.commonWidgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.kuntizbe.kz.ui.theme.GrayDivider

@Composable
fun PointDivider(pointSize:Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        repeat(pointSize) {
            Divider(modifier = Modifier
                .width(4.dp)
                .background(GrayDivider))
            Spacer(modifier = Modifier.width(2.dp))
        }
    }
}

@Composable
fun PointDividerVertical(pointSize: Int) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        repeat(pointSize) {
            Divider(
                modifier = Modifier
                    .height(3.dp)
                    .width(1.dp)
                    .background(Color.Black)
            )
            Spacer(modifier = Modifier.height(2.dp))
        }
    }
}
