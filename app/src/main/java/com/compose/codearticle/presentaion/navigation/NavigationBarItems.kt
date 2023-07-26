package com.compose.codearticle.presentaion.navigation

import androidx.annotation.DrawableRes
import com.compose.codearticle.R

enum class NavigationBarItems(
    @DrawableRes val icon: Int,@DrawableRes val fillIcon :Int, val route: String
) {
    Home(icon = R.drawable.home, fillIcon = R.drawable.fill_home ,route = Screen.HomeScreen.route),
    Post(icon = R.drawable.add_post, fillIcon = R.drawable.add_post, route = Screen.PostScreen.route),
    Setting(icon = R.drawable.normal_person, fillIcon = R.drawable.fill_person, route = Screen.ProfileScreen.route)
}