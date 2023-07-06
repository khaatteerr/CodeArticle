package com.compose.codearticle.presentaion.screens.settingScreen.terms

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.compose.codearticle.presentaion.navigation.Screen

fun NavGraphBuilder.termsScreenRoute(navController: NavController){
    composable(Screen.TermsScreen.route){
        TermsScreen(navController = navController)
    }
}