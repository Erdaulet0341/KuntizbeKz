package com.kuntizbe.kz.screens.menu.menuItemScreens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.kuntizbe.kz.R
import com.kuntizbe.kz.ui.commonWidgets.CenteredToolbar
import com.kuntizbe.kz.ui.commonWidgets.DownloadRow
import com.kuntizbe.kz.ui.ext.clickableWithoutRipple
import com.kuntizbe.kz.ui.theme.GrayBg
import com.kuntizbe.kz.ui.theme.Main
import com.kuntizbe.kz.ui.theme.TextColorMain
import com.kuntizbe.kz.ui.theme.TypographiesCostom
import com.kuntizbe.kz.ui.theme.White

@Composable
fun FaqScreen(navController: NavController) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            CenteredToolbar(
                title = stringResource(id = R.string.faq),
                onNavigationIconClick = {
                    navController.popBackStack()
                }
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState())
                ) {
                    FaqItem(title = stringResource(id = R.string.about_us), content = { About() })

                    FaqItem(title = stringResource(id = R.string.faq_dear_users), content = { Dear() })

                    FaqItem(title = stringResource(id = R.string.important_title), content = { Important() })

                    FaqItem(title = stringResource(id = R.string.how_time), content = { HowTime() })

                    FaqItem(title = stringResource(id = R.string.qibla_nav), content = { QiblaNav() })

                    FaqItem(title = stringResource(id = R.string.namaz_time), content = { NamazTime() })

                }
            }
        }
    }
}

@Composable
fun NamazTime(){

    Column(modifier = Modifier.fillMaxWidth()) {
        TextStyleForItemsMain(stringResource(id = R.string.ismak))
        TextStyleForItems(stringResource(id = R.string.ismak_text))

        TextStyleForItemsMain(stringResource(id = R.string.bamdat))
        TextStyleForItems(stringResource(id = R.string.bamdat_text))

        TextStyleForItemsMain(stringResource(id = R.string.kun))
        TextStyleForItems(stringResource(id = R.string.kun_text))

        TextStyleForItemsMain(stringResource(id = R.string.ishraq))
        TextStyleForItems(stringResource(id = R.string.ishraq_text))

        TextStyleForItemsMain(stringResource(id = R.string.daxua))
        TextStyleForItems(stringResource(id = R.string.daxua_text))

        TextStyleForItemsMain(stringResource(id = R.string.zaual))
        TextStyleForItems(stringResource(id = R.string.zaual_text))

        TextStyleForItemsMain(stringResource(id = R.string.besin))
        TextStyleForItems(stringResource(id = R.string.besin_text))

        TextStyleForItemsMain(stringResource(id = R.string.asr))
        TextStyleForItems(stringResource(id = R.string.asr_text))

        TextStyleForItemsMain(stringResource(id = R.string.asr_sani))
        TextStyleForItems(stringResource(id = R.string.asr_sani_text))

        TextStyleForItemsMain(stringResource(id = R.string.ekinti))
        TextStyleForItems(stringResource(id = R.string.ekinti_texxt))

        TextStyleForItemsMain(stringResource(id = R.string.sham))
        TextStyleForItems(stringResource(id = R.string.sham_text))

        TextStyleForItemsMain(stringResource(id = R.string.sham_ker))
        TextStyleForItems(stringResource(id = R.string.sham_ker_text))

        TextStyleForItemsMain(stringResource(id = R.string.quptan))
        TextStyleForItems(stringResource(id = R.string.quptan_text))

        TextStyleForItemsMain(stringResource(id = R.string.ishai))
        TextStyleForItems(stringResource(id = R.string.ishai_text))

        TextStyleForItemsMain(stringResource(id = R.string.tun))
        TextStyleForItems(stringResource(id = R.string.tun_text))

        TextStyleForItemsMain(stringResource(id = R.string.tahajit))
        TextStyleForItems(stringResource(id = R.string.tahajit_text))

        TextStyleForItemsMain(stringResource(id = R.string.sare))
        TextStyleForItems(stringResource(id = R.string.sare_text))
    }
}

@Composable
fun QiblaNav(){
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp)) {
        Text(
            text = stringResource(id = R.string.qibla_nav),
            style = TextStyle(
                fontFamily = FontFamily.SansSerif,
                fontSize = 17.sp,
                color = TextColorMain
            )
        )
        Spacer(modifier = Modifier.height(4.dp))
        DownloadRow(
            modifier = Modifier
                .fillMaxWidth()
                .background(GrayBg)
                .padding(horizontal = 3.dp),
            title = stringResource(id = R.string.how_time_arab),
            number = "1",
            btnText = stringResource(id =R.string.how_time_pdf),
            link ="https://drive.google.com/file/d/1NNqpsX8ZyBLnWL_Y6u3OBA9O0usS9bHf/view?usp=drive_link"
        )
        Spacer(modifier = Modifier.height(4.dp))
        DownloadRow(
            modifier = Modifier
                .fillMaxWidth()
                .background(GrayBg)
                .padding(horizontal = 4.dp),
            title = stringResource(id = R.string.how_time_engl),
            number = "2",
            btnText = stringResource(id =R.string.how_time_pdf),
            link ="https://drive.google.com/file/d/1yyZku-UfhxsLWvgUS50zCpapkCQCZHfu/view?usp=drive_link"
        )
    }
}

@Composable
fun HowTime(){
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp)) {
        Text(
            text = stringResource(id = R.string.how_time),
            style = TextStyle(
                fontFamily = FontFamily.SansSerif,
                fontSize = 17.sp,
                color = TextColorMain
            )
        )
        Spacer(modifier = Modifier.height(4.dp))
        DownloadRow(
            modifier = Modifier
                .fillMaxWidth()
                .background(GrayBg)
                .padding(horizontal = 3.dp),
            title = stringResource(id = R.string.how_time_arab),
            number = "1",
            btnText = stringResource(id =R.string.how_time_pdf),
            link ="https://drive.google.com/file/d/130CIRVYuKxseHX76ln3Uvxs9SyFTPO4v/view?usp=drive_link"
        )
        Spacer(modifier = Modifier.height(4.dp))
        DownloadRow(
            modifier = Modifier
                .fillMaxWidth()
                .background(GrayBg)
                .padding(horizontal = 4.dp),
            title = stringResource(id = R.string.how_time_engl),
            number = "2",
            btnText = stringResource(id =R.string.how_time_pdf),
            link ="https://drive.google.com/file/d/1jiyGeTTmnlbluMnZ3fx7CiVCYzc65xLy/view"
        )
    }
}

@Composable
fun FaqItem(title: String, content: @Composable () -> Unit) {
    var isShow = remember  { mutableStateOf(false) }
    val rotationAngle = remember { Animatable(0f) }
    LaunchedEffect(isShow.value) {
        rotationAngle.animateTo(if (isShow.value) 360f else 0f, animationSpec = tween(durationMillis = 800))
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .background(Main)
                .padding(horizontal = 8.dp)
                .clickableWithoutRipple {
                    isShow.value = !isShow.value
                },
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(0.9f),
                text = title.uppercase(),
                style = TextStyle(color = White, fontSize = 14.sp)
            )
            Icon(imageVector = if(isShow.value) Icons.Default.Close else Icons.Default.Add , modifier = Modifier.rotate(rotationAngle.value), contentDescription = "", tint = White)
        }
        AnimatedVisibility(isShow.value){
            content()
        }
    }
}

@Composable
fun About(){
    Text(
        text = stringResource(id = R.string.about_us_text),
        modifier = Modifier.padding(10.dp),
        style = TypographiesCostom.contentTextColor,
        textAlign = TextAlign.Justify,
        lineHeight = 25.sp
    )
}

@Composable
fun TextStyleForItems(text: String){
    Text(
        modifier = Modifier.padding(vertical = 4.dp),
        text = text,
        style = TypographiesCostom.contentTextColor,
        textAlign = TextAlign.Justify,
        lineHeight = 23.sp
    )
}

@Composable
fun TextStyleForItemsMain(text: String){
    Text(
        modifier = Modifier.padding(top = 4.dp),
        text = text,
        style = TypographiesCostom.contentTextColorMain,
        textAlign = TextAlign.Justify,
        lineHeight = 23.sp
    )
}

@Composable
fun Dear(){
    Column(modifier = Modifier.padding(10.dp)) {
        TextStyleForItems(stringResource(id = R.string.faq_dear_1))
        TextStyleForItems(stringResource(id = R.string.faq_dear_2))
        TextStyleForItems(stringResource(id = R.string.faq_dear_3))
        TextStyleForItems(stringResource(id = R.string.faq_dear_4))
        TextStyleForItems(stringResource(id = R.string.faq_dear_5))
        TextStyleForItems(stringResource(id = R.string.faq_dear_6))
        TextStyleForItems(stringResource(id = R.string.faq_dear_7))
        TextStyleForItems(stringResource(id = R.string.faq_dear_8))
        TextStyleForItems(stringResource(id = R.string.faq_dear_9))
        TextStyleForItems(stringResource(id = R.string.faq_dear_10))
        TextStyleForItems(stringResource(id = R.string.faq_dear_11))
        TextStyleForItems(stringResource(id = R.string.faq_dear_12))
        TextStyleForItems(stringResource(id = R.string.faq_dear_13))
        TextStyleForItems(stringResource(id = R.string.faq_dear_14))
        TextStyleForItems(stringResource(id = R.string.faq_dear_15))
    }
}

@Composable
fun Important(){
    Column(modifier = Modifier.fillMaxWidth()) {

        val annotatedString = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    fontStyle = FontStyle.Italic
                )
            ) {
                append(stringResource(id = R.string.imp_1_under) + " ")
            }

            withStyle(
                style = SpanStyle(
                    fontWeight = FontWeight.Normal
                )
            ) {
                append(stringResource(id = R.string.imp_1_1) + " ")
            }

            withStyle(
                style = SpanStyle(
                    fontWeight = FontWeight.SemiBold
                )
            ) {
                append(stringResource(id = R.string.imp_1_bold_1) + " ")
            }

            withStyle(
                style = SpanStyle(
                    fontWeight = FontWeight.Normal
                )
            ) {
                append(stringResource(id = R.string.imp_1_2) + " ")
            }
            withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.SemiBold
                    )
                    ) {
                append(stringResource(id = R.string.imp_1_bold_2) + " ")
            }
            withStyle(
                style = SpanStyle(
                    fontWeight = FontWeight.Normal
                )
            ) {
                append(stringResource(id = R.string.imp_1_3) + " ")
            }
            withStyle(
                style = SpanStyle(
                    fontWeight = FontWeight.SemiBold
                )
            ) {
                append(stringResource(id = R.string.imp_1_bold_3) + " ")
            }

            withStyle(
                style = SpanStyle(
                    fontWeight = FontWeight.Normal
                )
            ) {
                append(stringResource(id = R.string.imp_1_4) + " ")
            }
        }

        Text(
            text = annotatedString,
            textAlign = TextAlign.Justify
        )
    }
}

