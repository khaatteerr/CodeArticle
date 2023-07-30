package com.compose.codearticle.presentaion.screens.registerScreen

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.compose.codearticle.presentaion.navigation.Screen

fun NavGraphBuilder.registerScreenRoute(navController: NavController){
    composable(Screen.RegisterScreen.route){
        RegisterScreen(navController = navController)
    }
}