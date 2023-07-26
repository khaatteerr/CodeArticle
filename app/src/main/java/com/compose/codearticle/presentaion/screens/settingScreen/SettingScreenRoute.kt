package com.compose.codearticle.presentaion.screens.settingScreen

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.compose.codearticle.presentaion.navigation.Screen

fun NavGraphBuilder.settingScreenRoute(navController: NavController) {
    composable(Screen.SettingScreen.route,
        exitTransition = {
            slideOutHorizontally { it }
        },
        enterTransition = {
            slideInHorizontally { it }
        }) {
        SettingScreen(navController = navController)
    }
}