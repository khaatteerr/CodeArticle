package com.compose.codearticle.presentaion.screens.profileScreen

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.compose.codearticle.presentaion.navigation.Screen

fun NavGraphBuilder.profileScreenRoute(navController: NavController){
    composable(Screen.ProfileScreen.route,
        exitTransition = {
            slideOutHorizontally  {   it  }
        },
        enterTransition  = {
            slideInHorizontally {    it   }
        },
        popEnterTransition = { slideInHorizontally { -it } },
        popExitTransition = { slideOutHorizontally { -it } }){
        ProfileScreen(navController = navController)
    }
}