package com.compose.codearticle.presentaion.screens.splashScreen

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.compose.codearticle.presentaion.navigation.Screen

fun NavGraphBuilder.splashScreenRoute(navController: NavController){
    composable(Screen.SplashScreen.route){
        SplashScreen(navController = navController)
    }
}