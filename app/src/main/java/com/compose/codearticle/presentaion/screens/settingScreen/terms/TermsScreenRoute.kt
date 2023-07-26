package com.compose.codearticle.presentaion.screens.settingScreen.terms

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.compose.codearticle.presentaion.navigation.Screen

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.termsScreenRoute(navController: NavController){
    composable(Screen.TermsScreen.route){
        TermsScreen(navController = navController)
    }
}