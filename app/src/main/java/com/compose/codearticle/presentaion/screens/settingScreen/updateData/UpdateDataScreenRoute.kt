package com.compose.codearticle.presentaion.screens.settingScreen.updateData

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.compose.codearticle.presentaion.navigation.Screen

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.updateDataScreenRoute(navController: NavController){
    composable(Screen.UpdateDataScreen.route){
        UpdateDataScreen(navController = navController)
    }
}