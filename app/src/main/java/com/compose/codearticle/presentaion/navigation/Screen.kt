package com.compose.codearticle.presentaion.navigation

sealed class Screen(val route: String) {

    object SplashScreen:Screen("SplashScreen")
    object AuthorizationScreen:Screen("AuthorizationScreen")
    object LoginScreen : Screen("LoginScreen")
    object RegisterScreen : Screen("RegisterScreen")

    object HomeScreen : Screen("HomeScreen")
    object PostScreen: Screen("PostScreen")
    object SettingScreen: Screen("SettingScreen")
    object ProfileScreen: Screen("ProfileScreen")
    object UpdateDataScreen: Screen("UpdateDataScreen")
    object ChangePasswordScreen: Screen("ChangePasswordScreen")
    object RateUsScreen: Screen("RateUsScreen")
    object TermsScreen: Screen("TermsScreen")
    object PrivacyScreen: Screen("PrivacyScreen")
    object MyArticles:Screen("MyArticles")
}
