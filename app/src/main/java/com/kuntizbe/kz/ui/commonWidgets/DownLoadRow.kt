package com.kuntizbe.kz.ui.commonWidgets

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.kuntizbe.kz.ui.theme.Main
import com.kuntizbe.kz.ui.theme.White

@Composable
fun DownloadRow(
    modifier: Modifier,
    title: String,
    number: String,
    btnText:String,
    link:String
) {
    val ctx = LocalContext.current

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = number, fontWeight = FontWeight.SemiBold)

        Text(text = title)

        Button(
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(1.dp, Main),
            colors = ButtonColors(
                containerColor = White,
                contentColor = Main,
                disabledContainerColor = Main,
                disabledContentColor = Main
            ),
            contentPadding = ButtonDefaults.ContentPadding,
            onClick = {
                val urlIntent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(link)
                )
                ctx.startActivity(urlIntent)
            }) {


            Text(text = btnText, color = Main)
        }
    }
}