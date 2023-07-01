package com.compose.codearticle.presentaion.screens.settingScreen

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.compose.codearticle.presentaion.navigation.Screen

fun NavGraphBuilder.settingScreenRoute(navController: NavController){
    composable(Screen.SettingScreen.route){
        SettingScreen(navController = navController)
    }
}