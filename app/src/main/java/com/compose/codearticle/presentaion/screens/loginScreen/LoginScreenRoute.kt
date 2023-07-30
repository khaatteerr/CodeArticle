package com.compose.codearticle.presentaion.screens.loginScreen

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.compose.codearticle.presentaion.navigation.Screen

fun NavGraphBuilder.loginScreenRoute(navController: NavController){
    composable(Screen.LoginScreen.route){
        LoginInScreen(navController = navController)
    }
}