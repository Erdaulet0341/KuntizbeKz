package com.kuntizbe.kz.screens.menu.menuItemScreens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.kuntizbe.kz.R
import com.kuntizbe.kz.ui.commonWidgets.CenteredToolbar
import com.kuntizbe.kz.ui.commonWidgets.CommonButton
import com.kuntizbe.kz.ui.theme.BlueBtn
import com.kuntizbe.kz.ui.theme.GrayDivider
import com.kuntizbe.kz.ui.theme.GrayText
import com.kuntizbe.kz.ui.theme.OrangeBtn
import com.kuntizbe.kz.ui.theme.TypographiesCostom
import com.kuntizbe.kz.ui.theme.White

@Composable
fun MessageScreen(navController: NavController) {
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            CenteredToolbar(
                title = stringResource(id = R.string.message),
                onNavigationIconClick = {
                    navController.popBackStack()
                }
            )

            Text(
                text = "Тапсырыс беру номері:",
                modifier = Modifier.padding(vertical = 12.dp),
                style = TypographiesCostom.secondHeaderText,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center
            )

            CommonButton(
                modifier = Modifier
                    .height(45.dp)
                    .fillMaxWidth()
                    .background(color = BlueBtn, shape = RoundedCornerShape(5.dp)),
                title = "+7 707 479 19 75",
                icon = painterResource(id = R.drawable.call_24),
                onClick = {
                    val intent = Intent(Intent.ACTION_DIAL).apply {
                        data = Uri.parse("tel:+7 707 479 19 75\"")
                    }
                    context.startActivity(intent)
                }
            )

            Divider(color = GrayDivider, modifier = Modifier
                .padding(vertical = 24.dp)
                .height(1.dp)
                .fillMaxWidth())

            CommonButton(
                modifier = Modifier
                    .height(45.dp)
                    .fillMaxWidth()
                    .background(color = OrangeBtn, shape = RoundedCornerShape(5.dp)),
                title = "Почта",
                icon = painterResource(id = R.drawable.message_24),
                onClick = {
                    val intent = Intent(Intent.ACTION_SENDTO).apply {
                        data = Uri.parse("mailto:erdaulet0341@gmail.com")
                    }
                    context.startActivity(intent)
                }
            )

            ImageSwiperScreen()
        }
    }
}



@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImageSwiperScreen() {

    val images = listOf(
        painterResource(id = R.drawable.message_001),
        painterResource(id = R.drawable.message_002),
        painterResource(id = R.drawable.message_003),
        painterResource(id = R.drawable.message_004),
        painterResource(id = R.drawable.message_005)
    )

    val pagerState = rememberPagerState(pageCount = { images.size })

    Column(modifier = Modifier
        .padding(top = 24.dp)
        .fillMaxSize(0.7f)){
        HorizontalPager(
            state = pagerState,
        ) { index ->

            Image(
                modifier = Modifier.fillMaxSize(),
                painter = images[index],
                contentDescription = "null",
                alignment = Alignment.Center
            )
        }

    }
    SwiperPagination(
        pageCount = pagerState.pageCount,
        currentPage = pagerState.currentPage,
    )
}

@Composable
fun SwiperPagination(
    pageCount: Int,
    currentPage: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(pageCount) { index ->
            Box(
                modifier = Modifier
                    .padding(end = 10.dp)
                    .width(10.dp)
                    .height(10.dp)
                    .background(
                        color = if (index == currentPage) GrayText else GrayText.copy(
                            alpha = 0.4f
                        ),
                        shape = RoundedCornerShape(50)
                    )
            )
        }
    }
}