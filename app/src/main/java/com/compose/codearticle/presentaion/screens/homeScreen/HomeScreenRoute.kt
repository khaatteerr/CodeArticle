package com.compose.codearticle.presentaion.screens.homeScreen

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.compose.codearticle.presentaion.navigation.Screen

 fun NavGraphBuilder.homeScreenRoute(navController: NavController) {

    composable(Screen.HomeScreen.route,
        exitTransition = {
            slideOutHorizontally(){
                -it * 2
            } + fadeOut()
        },
        enterTransition = {
            slideInHorizontally(){
                -it
            }
        }
    ) {
        StoriesHomeScreen(navController = navController)
    }
}