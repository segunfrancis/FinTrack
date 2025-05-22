package com.segunfrancis.fintrack.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val LightColorScheme = lightColorScheme(
    primary = Teal,
    onPrimary = White,
    primaryContainer = Teal,
    onPrimaryContainer = White,
    secondary = Orange,
    onSecondary = White,
    background = PageBackground,
    onBackground = Black,
    surface = White,
    onSurface = Black,
    outline = Divider,
    surfaceVariant = Divider
)

private val DarkColorScheme = darkColorScheme(
    primary = Teal,
    onPrimary = Black,
    primaryContainer = Teal,
    onPrimaryContainer = Black,
    secondary = Orange,
    onSecondary = Black,
    background = Black,
    onBackground = White,
    surface = Black,
    onSurface = White,
    outline = Divider,
    surfaceVariant = Divider
)

@Composable
fun FinTrackTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}