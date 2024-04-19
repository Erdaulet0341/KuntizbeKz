package com.kuntizbe.kz.ui.ext

import android.annotation.SuppressLint
import android.graphics.Rect
import android.view.View
import android.view.ViewTreeObserver
import androidx.compose.foundation.Indication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.semantics.Role

@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.clickableWithoutRipple(
    enabled: Boolean = true,
    onClickLabel: String? = null,
    role: Role? = null,
    onClick: () -> Unit,
): Modifier = composed {
    clickable(
        enabled = enabled,
        indication = null,
        onClickLabel = onClickLabel,
        role = role,
        interactionSource = remember { MutableInteractionSource() },
        onClick = onClick,
    )
}

@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.clickableWithIndication(
    enabled: Boolean = true,
    onClickLabel: String? = null,
    role: Role? = null,
    bounded: Boolean = true,
    debounceInterval: Long = 300,
    rippleColor: Color = Color.Unspecified,
    indication: @Composable () -> Indication = {
        rememberRipple(bounded = bounded, color = rippleColor)
    },
    onClick: () -> Unit,
): Modifier = composed {
    var lastClickTime by remember { mutableStateOf(0L) }
    clickable(
        enabled = enabled,
        indication = indication(),
        onClickLabel = onClickLabel,
        role = role,
        interactionSource = remember { MutableInteractionSource() },
        onClick = {
            val currentTime = System.currentTimeMillis()
            if ((currentTime - lastClickTime) < debounceInterval) return@clickable
            lastClickTime = currentTime
            onClick()
        },
    )
}


@Composable
fun rememberIsKeyboardOpen(): State<Boolean> {
    val view = LocalView.current

    return produceState(initialValue = view.isKeyboardOpen()) {
        val viewTreeObserver = view.viewTreeObserver
        val listener = ViewTreeObserver.OnGlobalLayoutListener { value = view.isKeyboardOpen() }
        viewTreeObserver.addOnGlobalLayoutListener(listener)

        awaitDispose { viewTreeObserver.removeOnGlobalLayoutListener(listener) }
    }
}

@Composable
fun getSafeNavigationPadding(): PaddingValues {
    return WindowInsets.systemBars
        .only(WindowInsetsSides.Horizontal + WindowInsetsSides.Bottom)
        .asPaddingValues()
}

@Composable
fun getSafeStatusBarPadding(): PaddingValues {
    return WindowInsets.systemBars
        .only(WindowInsetsSides.Horizontal + WindowInsetsSides.Top)
        .asPaddingValues()
}

fun Modifier.safeNavigationPadding() = composed {
    padding(getSafeNavigationPadding())
}

fun Modifier.safeStatusBarPadding() = composed {
    padding(getSafeStatusBarPadding())
}

fun View.isKeyboardOpen(): Boolean {
    val rect = Rect()
    val keyboardPercent = 0.15
    getWindowVisibleDisplayFrame(rect)
    val screenHeight = rootView.height
    val keypadHeight = screenHeight - rect.bottom
    return keypadHeight > screenHeight * keyboardPercent
}