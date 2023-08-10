package com.compose.codearticle.presentaion.screens.myArticles

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.compose.codearticle.presentaion.navigation.Screen

fun NavGraphBuilder.myArticlesRoute(navController: NavController){
    composable(Screen.MyArticles.route){
        MyArticles(navController = navController)
    }
}