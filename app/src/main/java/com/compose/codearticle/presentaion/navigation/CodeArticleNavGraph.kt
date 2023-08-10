package com.compose.codearticle.presentaion.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.compose.codearticle.presentaion.screens.homeScreen.homeScreenRoute
import com.compose.codearticle.presentaion.screens.loginScreen.loginScreenRoute
import com.compose.codearticle.presentaion.screens.myArticles.myArticlesRoute
import com.compose.codearticle.presentaion.screens.profileScreen.profileScreenRoute
import com.compose.codearticle.presentaion.screens.publishScreen.postScreenRoute
import com.compose.codearticle.presentaion.screens.registerScreen.registerScreenRoute
import com.compose.codearticle.presentaion.screens.settingScreen.changePassword.changePasswordScreenRoute
import com.compose.codearticle.presentaion.screens.settingScreen.privacy.privacyScreenRoute
import com.compose.codearticle.presentaion.screens.settingScreen.rateUs.rateUsScreenRoute
import com.compose.codearticle.presentaion.screens.settingScreen.settingScreenRoute
import com.compose.codearticle.presentaion.screens.settingScreen.terms.termsScreenRoute
import com.compose.codearticle.presentaion.screens.settingScreen.updateData.updateDataScreenRoute
import com.compose.codearticle.presentaion.screens.splashScreen.splashScreenRoute

@Composable
fun CodeArticleNavGraph(navController: NavHostController) {

    NavHost(navController = navController, startDestination = Screen.HomeScreen.route) {

        //Splash screen
        splashScreenRoute(navController = navController)
        // Main Screens - Bottom bar Screens
        homeScreenRoute(navController = navController)
        postScreenRoute(navController = navController)
        settingScreenRoute(navController = navController)

        // Setting Screen Navigation
        updateDataScreenRoute(navController = navController)
        changePasswordScreenRoute(navController = navController)
        rateUsScreenRoute(navController = navController)
        termsScreenRoute(navController = navController)
        privacyScreenRoute(navController = navController)

        //profile Screen
        profileScreenRoute(navController = navController)

        //Login Screen
        loginScreenRoute(navController = navController)

        //Register Screen
        registerScreenRoute(navController = navController)

        //My Articles
        myArticlesRoute(navController = navController)
    }
}