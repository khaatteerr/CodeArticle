package com.compose.codearticle.presentaion.navigation

sealed class Screen(val route: String) {
    object HomeScreen : Screen("HomeScreen")
    object PostScreen: Screen("PostScreen")
    object SettingScreen: Screen("SettingScreen")
    object UpdateDataScreen: Screen("UpdateDataScreen")
    object ChangePasswordScreen: Screen("ChangePasswordScreen")
    object RateUsScreen: Screen("RateUsScreen")
    object TermsScreen: Screen("TermsScreen")
    object PrivacyScreen: Screen("PrivacyScreen")
}
