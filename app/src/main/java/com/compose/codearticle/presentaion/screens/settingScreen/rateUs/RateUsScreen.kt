package com.compose.codearticle.presentaion.screens.settingScreen.rateUs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController

@Composable
fun RateUsScreen(navController: NavController) {
    RateUsContent(navController)
}

@Composable
fun RateUsContent(navController: NavController) {
     Box(Modifier.fillMaxSize().background(Color.Blue)) {

     }
}
