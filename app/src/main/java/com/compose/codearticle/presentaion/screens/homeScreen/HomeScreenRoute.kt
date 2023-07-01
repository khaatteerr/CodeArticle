package com.compose.codearticle.presentaion.screens.homeScreen

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.compose.codearticle.presentaion.navigation.Screen

fun NavGraphBuilder.homeScreenRoute(navController: NavController) {

    composable(Screen.HomeScreen.route) {
        HomeScreen(navController = navController)
    }
}