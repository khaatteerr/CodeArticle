package com.compose.codearticle.presentaion.screens.settingScreen.terms

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.compose.codearticle.presentaion.theme.Orange

@Composable
fun TermsScreen(navController: NavController) {
    TermsContent(navController)
}

@Composable
fun TermsContent(navController: NavController) {
     Box(Modifier.fillMaxSize().background(Orange)) {

     }
}
