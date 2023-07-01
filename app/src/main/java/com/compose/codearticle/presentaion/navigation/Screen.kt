package com.compose.codearticle.presentaion.navigation

sealed class Screen(val route: String) {
    object HomeScreen : Screen("HomeScreen")
    object PostScreen: Screen("PostScreen")
    object SettingScreen: Screen("SettingScreen")
}
