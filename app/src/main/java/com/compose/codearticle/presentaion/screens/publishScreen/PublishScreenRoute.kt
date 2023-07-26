package com.compose.codearticle.presentaion.screens.publishScreen

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.compose.codearticle.presentaion.navigation.Screen

fun NavGraphBuilder.postScreenRoute(navController: NavController){
    composable(Screen.PostScreen.route,
        exitTransition = {
            slideOutVertically () {  it  }
        },
        enterTransition  = {
            slideInVertically(
            ) {   it   }
        }){
        PostScreen(navController = navController)
    }
}