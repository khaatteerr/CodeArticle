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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.compose.codearticle.presentaion.screens.settingScreen.SettingScreenViewModel
import com.compose.codearticle.presentaion.screens.settingScreen.darkMode.ThemeViewModel

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF101010),
    secondary = SecondaryColor,
    tertiary = ThirdColor,
    onPrimary = Orange,
    onBackground = White0_7,
    background = Color(0xFF101010),
    surface = Color(0xFF101010),
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
    surfaceVariant = Color(0xFFBDC3C7),
    inversePrimary = Color.Black,
    outline = DarkGray0_2,

    )

@Composable
fun CodeArticleTheme(
   // themeViewModel: ThemeViewModel = hiltViewModel(),
    content: @Composable () -> Unit
) {

  //  val themeState by themeViewModel.themeState.collectAsState()

    val colorScheme = DarkColorScheme
//    when {
//        themeState.isDarkMode -> DarkColorScheme
//        else -> LightColorScheme
//    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window

            window.statusBarColor = Color.Transparent.toArgb()
         }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}