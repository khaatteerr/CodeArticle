package com.compose.codearticle.presentaion.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.DividerDefaults.color
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = Color.Black,
    secondary = SecondaryColor,
    tertiary = ThirdColor,
    onPrimary = Orange,
    onBackground = White0_7,
    background = Color.Black,
    surface = MainColor,
    onSurface = Color.White,
    outline = LightGray0_2,
    inversePrimary = LightGray,
    error = Color.Red.copy(0.7f),
    surfaceVariant = CardsColors
)

private val LightColorScheme = lightColorScheme(
    primary = Color.White,
    secondary = Color(0xFFBDC3C7),
    tertiary = ThirdColor,
    onBackground = Color.Black,
    background = Color.White,
    surface = Color(0xFFBDC3C7),
    onSurface = Color.Black,
    error = Color.Red.copy(0.7f),
    surfaceVariant = BottomBarIcon,
    inversePrimary = Color.Black,
    outline = DarkGray0_2,

)

@Composable
fun CodeArticleTheme(
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
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()

        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}