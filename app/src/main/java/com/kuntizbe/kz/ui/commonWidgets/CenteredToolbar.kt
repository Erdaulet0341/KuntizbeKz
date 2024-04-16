package com.kuntizbe.kz.ui.commonWidgets

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.kuntizbe.kz.R
import com.kuntizbe.kz.ui.theme.Black
import com.kuntizbe.kz.ui.theme.Main
import com.kuntizbe.kz.ui.theme.TypographiesCostom
import com.kuntizbe.kz.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CenteredToolbar(
    title: String,
    modifier: Modifier = Modifier,
    onNavigationIconClick: (() -> Unit)? = null,
    navigationIcon: ImageVector = ImageVector.vectorResource(id = R.drawable.arrow_left_24),
    isNavigationIconVisible: Boolean = true,
    actions: @Composable RowScope.() -> Unit = {},
    backgroundColor: Color = White,
    titleColor: Color = Main,
    navigationIconTint: Color = Main,
    titleStyle: TextStyle = TypographiesCostom.firstHeaderTextMain,
    titleModifier: Modifier = Modifier,
    windowInsets: WindowInsets = TopAppBarDefaults.windowInsets
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = titleStyle,
                color = titleColor,
                modifier = titleModifier,
            )
        },
        modifier = modifier
            .fillMaxWidth(),
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = backgroundColor,
            scrolledContainerColor = backgroundColor.copy(alpha = 0.8f)
        ),
        windowInsets = windowInsets,
        navigationIcon = if (isNavigationIconVisible) {
            {
                IconButton(
                    onClick = { onNavigationIconClick?.invoke() },
                ) {
                    Icon(
                        imageVector = navigationIcon,
                        contentDescription = null,
                        tint = navigationIconTint,
                    )
                }
            }
        } else {
            { }
        },
        actions = actions,
    )
}


