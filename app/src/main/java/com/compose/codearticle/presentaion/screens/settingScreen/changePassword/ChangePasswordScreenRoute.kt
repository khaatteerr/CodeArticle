package com.compose.codearticle.presentaion.screens.settingScreen.changePassword

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.compose.codearticle.presentaion.navigation.Screen

 fun NavGraphBuilder.changePasswordScreenRoute(navController: NavController){
    composable(Screen.ChangePasswordScreen.route){
        ChangePasswordScreen(navController = navController)
    }
}