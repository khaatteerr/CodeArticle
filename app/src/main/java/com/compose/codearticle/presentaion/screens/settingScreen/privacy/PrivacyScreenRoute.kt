package com.compose.codearticle.presentaion.screens.settingScreen.privacy

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.compose.codearticle.presentaion.navigation.Screen

fun NavGraphBuilder.privacyScreenRoute(navController: NavController){
    composable(Screen.PrivacyScreen.route){
        PrivacyScreen(navController = navController)
    }
}