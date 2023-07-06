package com.compose.codearticle

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.compose.codearticle.presentaion.navigation.CodeArticleNavGraph
import com.compose.codearticle.presentaion.navigation.NavigationBarItems
import com.compose.codearticle.presentaion.navigation.Screen
import com.compose.codearticle.presentaion.theme.CodeArticleTheme
import com.compose.codearticle.presentaion.theme.Orange
import com.exyte.animatednavbar.AnimatedNavigationBar
import com.exyte.animatednavbar.animation.balltrajectory.Parabolic
import com.exyte.animatednavbar.animation.indendshape.Height
import com.exyte.animatednavbar.animation.indendshape.shapeCornerRadius
import com.exyte.animatednavbar.utils.noRippleClickable
import kotlin.math.roundToInt

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "AutoboxingStateValueProperty")
@Composable
fun CodeArticleApp() {

    val navigationBarItems = remember {
        NavigationBarItems.values()
    }
    var selectedIndex by remember { mutableIntStateOf(0) }

    val navController = rememberNavController()
    val backStackEntry = navController.currentBackStackEntryAsState()

    val bottomBarHeight = 55.dp
    val bottomBarHeightPx = with(LocalDensity.current) { bottomBarHeight.roundToPx().toFloat() }
    val bottomBarOffsetHeightPx = remember { mutableFloatStateOf(0f) }
    val ballBottomBarOffsetHeightPx = 10


// connection to the nested scroll system and listen to the scroll
// happening inside child LazyColumn
    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {

                val delta = available.y
                val newOffset = bottomBarOffsetHeightPx.value + delta
                bottomBarOffsetHeightPx.value = newOffset.coerceIn(-bottomBarHeightPx, 0f)

                return Offset.Zero
            }
        }
    }

    CodeArticleTheme {
        Scaffold(modifier = Modifier
            .nestedScroll(nestedScrollConnection),

            bottomBar = {

                AnimatedVisibility(visible = isBottomBarVisible(backStackEntry),
                    enter = slideInVertically { it },
                    exit = slideOutVertically { it }) {

                    AnimatedNavigationBar(
                        modifier = Modifier
                            .offset {
                                IntOffset(
                                    x = 0,
                                    y = -bottomBarOffsetHeightPx.value.roundToInt() + ballBottomBarOffsetHeightPx
                                )
                            }
                            .height(bottomBarHeight),
                        selectedIndex = selectedIndex,
                        cornerRadius = shapeCornerRadius(
                            topLeft = 34.dp,
                            topRight = 34.dp,
                            bottomLeft = 0.dp,
                            bottomRight = 0.dp
                        ),
                        ballAnimation = Parabolic(tween(300)),
                        indentAnimation = Height(tween(300)),
                        ballColor = Orange,
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
                                    modifier = Modifier.size(26.dp),
                                    painter = painterResource(id = item.icon),
                                    contentDescription = "Bottom bar icon",
                                    tint = if (selectedIndex == item.ordinal) Orange
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

        Screen.PostScreen.route,
        Screen.UpdateDataScreen.route,
        Screen.ChangePasswordScreen.route,
        Screen.RateUsScreen.route,
        Screen.TermsScreen.route,
        Screen.PrivacyScreen.route

    ).any { it == backStackEntry.value?.destination?.route }.not()
}

