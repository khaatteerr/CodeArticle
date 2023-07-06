package com.compose.codearticle.presentaion.screens.settingScreen.rateUs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.compose.codearticle.presentaion.navigation.Screen

fun NavGraphBuilder.rateUsScreenRoute(navController: NavController){
    composable(Screen.RateUsScreen.route){
        RateUsScreen(navController = navController)
    }
}