package com.compose.codearticle.presentaion.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.compose.codearticle.presentaion.screens.homeScreen.homeScreenRoute
import com.compose.codearticle.presentaion.screens.publishScreen.postScreenRoute
import com.compose.codearticle.presentaion.screens.settingScreen.settingScreenRoute

@Composable
fun CodeArticleNavGraph(navController: NavHostController) {

    NavHost(navController = navController, startDestination = Screen.HomeScreen.route) {
        homeScreenRoute(navController = navController)
        postScreenRoute(navController = navController)
        settingScreenRoute(navController = navController)
    }
}