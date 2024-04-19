package com.kuntizbe.kz.screens.menu.menuItemScreens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.kuntizbe.kz.R
import com.kuntizbe.kz.ui.commonWidgets.CenteredToolbar
import com.kuntizbe.kz.ui.commonWidgets.PointDivider
import com.kuntizbe.kz.ui.theme.White

@Composable
fun LinksScreen(navController: NavController) {

    val list = getLInkList()
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            CenteredToolbar(
                title = stringResource(id = R.string.links),
                onNavigationIconClick = {
                    navController.popBackStack()
                }
            )
            
            LazyColumn(modifier = Modifier.padding(16.dp)){
                items(list){
                    Image(
                        modifier = Modifier
                            .height(80.dp)
                            .fillMaxSize()
                            .padding(vertical = 20.dp)
                            .clickable {
                                val intent = Intent(Intent.ACTION_VIEW).apply {
                                    data = Uri.parse(it.url)
                                }
                                context.startActivity(intent)
                            },
                        painter = it.image,
                        contentDescription = it.url
                    )

                    PointDivider(pointSize = 100)
                }
            }
        }
    }
}

data class Link(
    val image: Painter,
    val url: String
)

@Composable
fun getLInkList(): List<Link>{
    return  listOf(
        Link(painterResource(id = R.drawable.islamdini), "https://www.islamdini.kz"),
        Link(painterResource(id = R.drawable.veraislam), "https://veraislam.ru"),
        Link(painterResource(id = R.drawable.turkiye_takvimi), "https://www.turktakvim.com"),
        Link(painterResource(id = R.drawable.dinimizislam), "https://dinimizislam.com"),
        Link(painterResource(id = R.drawable.namaztimes), "https://namaztimes.kz"),
        Link(painterResource(id = R.drawable.balalaralemi), "https://balalaralemi.kz"),

        )
}