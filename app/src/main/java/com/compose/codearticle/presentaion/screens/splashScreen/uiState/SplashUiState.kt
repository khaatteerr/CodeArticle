package com.compose.codearticle.presentaion.screens.splashScreen.uiState

import com.compose.codearticle.presentaion.navigation.Screen

sealed class SplashUiEvent(var screen: Screen){
    object HomeScreen:SplashUiEvent(Screen.HomeScreen)
    object LoginScreen:SplashUiEvent(Screen.LoginScreen)
}