package com.kuntizbe.kz.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

data class Typographies(
    val mainDate:TextStyle,
    val secondHeaderText:TextStyle,
    val firstHeaderText:TextStyle,
    val firstHeaderTextMain: TextStyle,
    val contentTextColor:TextStyle,
    val contentTextColorBold: TextStyle,
    val smallGrayText:TextStyle,
    val contentTextColorMain:TextStyle,
    val firstHeaderTextMain32sp:TextStyle
    )

val TypographiesCostom = Typographies(
    mainDate = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Bold,
        fontSize = 180.sp,
        color = Main
    ),
    secondHeaderText = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.W700,
        fontSize = 20.sp,
        color = TextColorMain
    ),
    firstHeaderText = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.W700,
        fontSize = 24.sp,
        color = TextColorMain
    ),
    firstHeaderTextMain = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.W700,
        fontSize = 24.sp,
        color = Main
    ),
    firstHeaderTextMain32sp = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp,
        color = Main
    ),
    contentTextColor = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        color = TextColorMain
    ),
    contentTextColorMain = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        color = Main
    ),
    contentTextColorBold = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        color = TextColorMain
    ),
    smallGrayText = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Normal,
        fontSize = 13.sp,
        color = GrayText
    )
)