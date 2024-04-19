package com.kuntizbe.kz.screens.info

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.kuntizbe.kz.R
import com.kuntizbe.kz.navigation.NavigationScreens
import com.kuntizbe.kz.screens.playerTime.gregorianDate
import com.kuntizbe.kz.screens.playerTime.gregorianToHijriFormatted
import com.kuntizbe.kz.ui.commonWidgets.CenteredToolbar
import com.kuntizbe.kz.ui.commonWidgets.PointDivider
import com.kuntizbe.kz.ui.commonWidgets.PointDividerVertical
import com.kuntizbe.kz.ui.theme.Main
import com.kuntizbe.kz.ui.theme.TypographiesCostom
import com.kuntizbe.kz.ui.theme.White

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun InfoScreen(navController: NavController) {


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()) {
            CenteredToolbar(
                title = stringResource(id = R.string.info_title),
                onNavigationIconClick = {
                    navController.navigate(
                        NavigationScreens.MainNavigation.route
                    )
                })
            Column(modifier = Modifier
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState()), horizontalAlignment = Alignment.CenterHorizontally) {

                Spacer(modifier = Modifier.height(8.dp))

                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){
                    Text(
                        text = gregorianDate(),
                        modifier = Modifier.padding(start = 3.dp,end = 4.dp).weight(0.5f),
                        style = TypographiesCostom.smallGrayText
                    )
                    PointDividerVertical(pointSize = 5)
                    Text(
                        text = gregorianToHijriFormatted(),
                        modifier = Modifier.padding(start = 8.dp).weight(0.5f),
                        style = TypographiesCostom.smallGrayText
                    )
                }

                PointDivider(pointSize = 100)

                Text(
                    text = "МАҚАЛА",
                    style = TypographiesCostom.contentTextColorBold,
                    modifier = Modifier.padding(vertical = 5.dp)
                )

                PointDivider(pointSize = 100)

                Text(
                    text = "Ресей мен Украина арасындағы соғыс (1)".uppercase(),
                    style = TypographiesCostom.contentTextColorBold,
                    color = Main,
                    modifier = Modifier.padding(vertical = 5.dp),
                    textAlign = TextAlign.Center,
                )

                Text(
                    text = "1917 жылы 4 желтоқсанда Ресей Кеңестік Федерациялық Социалистік Республикасының (РКФСР) Халық Комиссарлар Кеңесі Украина Республикасын мойындағанын жариялады. Ал 1918 жылы Украинада Гетман Скоропадскийдің басқаруымен РКФСР елшілігі пайда болды. Екі ел арасындағы дипломатиялық қарым-қатынас 1922 жылы КСРО құрылғанға дейін жалғасты. 1992 жылы 14 ақпанда КСРО ыдырағаннан кейін де тағы Ресей Федерациясы мен Украина арасында дипломатиялық қарым-қатынас орнатылды. Тығыз байланыста болған «бауырлас» екі ел арасынан бүгінде қара мысық кесіп өтті. Оған не себеп?",
                    modifier = Modifier.padding(top = 8.dp).fillMaxWidth(),
                    style = TypographiesCostom.contentTextColor,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Justify
                )

                Text(
                    text = "Мәскеу мен Киев арасындағы қақтығыстың тарихы тереңде. Қазіргі соғыс – соңғы 30 жылдағы саясаттың кесірі. Оны шартты түрде үш кезеңге бөлуге болады.",
                    modifier = Modifier.padding(vertical = 6.dp).fillMaxWidth(),
                    style = TypographiesCostom.contentTextColor,
                    textAlign = TextAlign.Justify
                )

                Text(
                    text = "1990-2003 жыл: Украинаның Ресейден кетуі.",
                    modifier = Modifier.padding(vertical = 6.dp),
                    style = TypographiesCostom.contentTextColor,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Justify
                )

                Text(
                    text = "1990 жылдың бірінші жартысында Ресей мен Украина арасындағы Қара теңіз флоты меншігіне және инфрақұрылымына қатысты мәселе үлкен дауға айналды. Мәскеу Киевтің құзырында болған Қырымдағы базаларынан айырылғысы келмеді. Ал Украина Қара теңіз флотының барлық инфрақұрылымы мен мүлкін қайта тағайындауға әрекет етті. 1992 жылы 5 сәуірде Украина президенті Леонид Кравчук КСРО-ның Қара теңіз флотын Украинаның меншігі деп жариялады. Ал 7 сәуір күні Ресей президенті Борис Ельцин де осындай қадамға баруға мәжбүр болды.",
                    modifier = Modifier.padding(vertical = 6.dp),
                    style = TypographiesCostom.contentTextColor,
                    textAlign = TextAlign.Justify
                )
            }
        }
    }
}

