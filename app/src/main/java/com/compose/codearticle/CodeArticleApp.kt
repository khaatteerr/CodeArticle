package com.compose.codearticle

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.compose.codearticle.presentaion.navigation.CodeArticleNavGraph
import com.compose.codearticle.presentaion.navigation.NavigationBarItems
import com.compose.codearticle.presentaion.navigation.Screen
import com.compose.codearticle.presentaion.screens.homeScreen.HomeViewModel
import com.compose.codearticle.presentaion.screens.homeScreen.SearchSection
import com.compose.codearticle.presentaion.theme.CodeArticleTheme
import com.exyte.animatednavbar.AnimatedNavigationBar
import com.exyte.animatednavbar.animation.balltrajectory.Straight
import com.exyte.animatednavbar.animation.indendshape.Height
import com.exyte.animatednavbar.utils.noRippleClickable

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "AutoboxingStateValueProperty")
@Composable
fun CodeArticleApp() {

    val navigationBarItems = remember {
        NavigationBarItems.values()
    }
    var selectedIndex by remember { mutableStateOf(0) }

    val navController = rememberNavController()
    val backStackEntry = navController.currentBackStackEntryAsState()


    CodeArticleTheme {
        Scaffold(modifier = Modifier
            .background(MaterialTheme.colorScheme.primary),
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.primary,
         //   topBar = { SearchSection()},
            bottomBar = {
                AnimatedVisibility(visible = isBottomBarVisible(backStackEntry),
                    enter = slideInVertically { it },
                    exit = slideOutVertically { it }) {

                    AnimatedNavigationBar(
                        Modifier.height(55.dp),
                        selectedIndex = selectedIndex,
                        ballAnimation = Straight(tween(300)),
                        indentAnimation = Height(tween(300)),
                        ballColor = Color.White,
                        barColor = MaterialTheme.colorScheme.primary
                    ) {

                        navigationBarItems.forEach { item ->


                            val isSelected = item.route == backStackEntry.value?.destination?.route

                            if (isSelected) {
                                selectedIndex = item.ordinal
                            }
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .noRippleClickable {
                                        selectedIndex = item.ordinal
                                        if (!isSelected) {
                                            navController.navigate(item.route) {
                                                launchSingleTop = true
                                                popUpTo(Screen.HomeScreen.route) {
                                                    inclusive = false
                                                }
                                            }

                                        }

                                    },
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    modifier = Modifier.size(25.dp),
                                    painter = if (isSelected) painterResource(id = item.fillIcon) else painterResource(id = item.icon),
                                    contentDescription = "Bottom bar icon",
                                    tint = if (selectedIndex == item.ordinal) Color.White
                                    else MaterialTheme.colorScheme.inversePrimary
                                )

                            }

                        }
                    }
                }
            }


        ) {
            CodeArticleNavGraph(navController = navController)
        }

    }


}

fun isBottomBarVisible(backStackEntry: State<NavBackStackEntry?>): Boolean {
    return listOf(

        //Add Any screen that you need to hide bottom Navigation on it

//        Screen.PostScreen.route,
//        Screen.UpdateDataScreen.route,
//        Screen.ChangePasswordScreen.route,
//        Screen.RateUsScreen.route,
//        Screen.TermsScreen.route,
//        Screen.PrivacyScreen.route,
//        Screen.LoginScreen.route,
//
        //Screen that bottom nav is visible

        Screen.HomeScreen.route,
        Screen.ProfileScreen.route

    ).any { it == backStackEntry.value?.destination?.route }
}

