package com.compose.codearticle.presentaion.screens.settingScreen.privacy

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController

@Composable
fun PrivacyScreen(navController: NavController) {
    PrivacyContent(navController)
}

@Composable
fun PrivacyContent(navController: NavController) {
     Box(Modifier.fillMaxSize().background(Color.Magenta)) {

     }
}
