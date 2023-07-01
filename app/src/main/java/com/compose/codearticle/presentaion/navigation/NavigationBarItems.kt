package com.compose.codearticle.presentaion.navigation

import com.compose.codearticle.R

enum class NavigationBarItems(
    val icon: Int, val route: String
) {
    Home(icon = R.drawable.ic_home, route = Screen.HomeScreen.route),
    Post(icon = R.drawable.baseline_add_24, route = Screen.PostScreen.route),
    Setting(icon = R.drawable.ic_profile, route = Screen.SettingScreen.route)
}