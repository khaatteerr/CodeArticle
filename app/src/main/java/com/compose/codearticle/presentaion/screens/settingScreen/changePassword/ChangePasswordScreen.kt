package com.compose.codearticle.presentaion.screens.settingScreen.changePassword

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController

@Composable
fun ChangePasswordScreen(navController: NavController) {
    ChangePasswordContent(navController)
}

@Composable
fun ChangePasswordContent(navController: NavController) {
     Column(Modifier.fillMaxSize() ) {
         Text(text = "change",Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
     }
}
