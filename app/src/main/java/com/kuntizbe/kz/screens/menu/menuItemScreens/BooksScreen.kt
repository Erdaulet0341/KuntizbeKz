package com.kuntizbe.kz.screens.menu.menuItemScreens

import android.content.Intent
import android.net.Uri
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.kuntizbe.kz.R
import com.kuntizbe.kz.ui.commonWidgets.CenteredToolbar
import com.kuntizbe.kz.ui.theme.Main
import com.kuntizbe.kz.ui.theme.TypographiesCostom
import com.kuntizbe.kz.ui.theme.White

@Composable
fun BookScreen(navController: NavController) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            CenteredToolbar(
                title = stringResource(id = R.string.books),
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
                    FaqItem(title = stringResource(id = R.string.ai_ulim), content = {
                        BookItemDetails(
                            text = stringResource(id = R.string.ai_ulim_text),
                            image = painterResource(
                                id = R.drawable.aliuly
                            ),
                            link = "https://drive.google.com/file/d/197Pre7gOG5XS2YkWi4DLVXxDrQiD6BJv/view?usp=sharing"
                        )
                    })

                    FaqItem(title = stringResource(id = R.string.xazret), content = {
                        BookItemDetails(
                            text = stringResource(id = R.string.xazret_text),
                            image = painterResource(
                                id = R.drawable.lifeof
                            ),
                            link = "https://drive.google.com/file/d/1hxGon-OkwClpPsqzP-3i0iFkK4vf6V2a/view?usp=sharing"
                        )
                    })

                    FaqItem(title = stringResource(id = R.string.islam_axlaqy), content = {
                        BookItemDetails(
                            text = stringResource(id = R.string.islam_axlaqy_text),
                            image = painterResource(
                                id = R.drawable.islamakhlah
                            ),
                            link = "https://drive.google.com/file/d/1fP10ZaWFixbfqSHWpbLERL4W8lFa25ax/view?usp=sharing"
                        )
                    })

                    FaqItem(title = stringResource(id = R.string.jannat), content = {
                        BookItemDetails(
                            text = stringResource(id = R.string.jannat_text),
                            image = painterResource(
                                id = R.drawable.zhannatzholy
                            ),
                            link = "https://drive.google.com/file/d/1U5kU6zdp-mIP4gt9x1c4T4ziMbocgHDn/view?usp=sharing"
                        )
                    })

                    FaqItem(title = stringResource(id = R.string.qiyamet), content = {
                        BookItemDetails(
                            text = stringResource(id = R.string.qiyamet_text),
                            image = painterResource(
                                id = R.drawable.qiamet
                            ),
                            link = "https://drive.google.com/file/d/1IMlO21GMuH96y93I2oVAeL8dcbAeW1Wk/view?usp=sharing"
                        )
                    })

                    FaqItem(title = stringResource(id = R.string.jizn), content = {
                        BookItemDetails(
                            text = stringResource(id = R.string.jizn_text),
                            image = painterResource(
                                id = R.drawable.liferus
                            ),
                            link = "https://drive.google.com/file/d/1gH9dvT2Mket6qALCbvvGyQ2f37U9IMlC/view?usp=sharing"
                        )
                    })

                }
            }
        }
    }
}

@Composable
fun BookItemDetails(text: String, image: Painter, link: String) {
    val ctx = LocalContext.current

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .height(180.dp),
            painter = image,
            contentDescription = "null"
        )
        Text(
            text = text,
            modifier = Modifier
                .fillMaxWidth(),
            style = TypographiesCostom.contentTextColor,
            fontSize = 17.sp,
            textAlign = TextAlign.Justify,
            lineHeight = 20.sp
        )
        Button(
            modifier = Modifier
                .align(Alignment.End)
                .padding(top = 4.dp),
            shape = RectangleShape,
            colors = ButtonColors(
                containerColor = Main,
                contentColor = White,
                disabledContainerColor = Main,
                disabledContentColor = Main
            ),
            onClick = {
                val urlIntent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(link)
                )
                ctx.startActivity(urlIntent)
            }) {
            Row(horizontalArrangement = Arrangement.SpaceEvenly) {
                Icon(
                    modifier = Modifier.padding(end = 8.dp),
                    painter = painterResource(id = R.drawable.download_24),
                    contentDescription = "text",
                    tint = White
                )
                Text(text = stringResource(id = R.string.how_time_pdf), color = White)
            }
        }
    }
}