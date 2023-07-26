package com.compose.codearticle.presentaion.screens.settingScreen.privacy

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.compose.codearticle.presentaion.navigation.Screen

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.privacyScreenRoute(navController: NavController){
    composable(Screen.PrivacyScreen.route){
        PrivacyScreen(navController = navController)
    }
}