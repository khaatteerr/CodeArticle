package com.compose.codearticle.presentaion.screens.settingScreen.updateData

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.navigation.NavController

@Composable
fun UpdateDataScreen(navController: NavController) {
    UpdateDataContent(navController)
}

@Composable
fun UpdateDataContent(navController:NavController) {
     Box(Modifier.fillMaxSize().background(Green)) {

     }
}
