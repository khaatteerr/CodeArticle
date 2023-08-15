package com.compose.codearticle.presentaion.screens.authorization

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.compose.codearticle.presentaion.navigation.Screen

fun NavGraphBuilder.authorizationScreenRoute(navController: NavController){
    composable(Screen.AuthorizationScreen.route){
        AuthorizationScreen(navController = navController)
    }
}