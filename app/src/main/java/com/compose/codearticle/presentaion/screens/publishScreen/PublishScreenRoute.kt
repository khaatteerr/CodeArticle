package com.compose.codearticle.presentaion.screens.publishScreen

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.compose.codearticle.presentaion.navigation.Screen

fun NavGraphBuilder.postScreenRoute(navController: NavController){
    composable(Screen.PostScreen.route){
        PostScreen(navController = navController)
    }
}