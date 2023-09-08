@file:OptIn(ExperimentalPagerApi::class)

package com.compose.codearticle.presentaion.screens.profileScreen

import android.annotation.SuppressLint
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.TabRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.compose.codearticle.R
import com.compose.codearticle.presentaion.navigation.Screen
import com.compose.codearticle.presentaion.screens.myArticles.MyArticles
import com.compose.codearticle.presentaion.theme.Orange
import com.compose.codearticle.presentaion.theme.Ubuntu
import com.compose.codearticle.presentaion.utilities.NoRippleInteractionSource
import com.exyte.animatednavbar.utils.noRippleClickable
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@Composable
fun ProfileScreen(navController: NavController) {

     ProfileContent(navController)
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProfileContent(navController: NavController) {
    Scaffold(topBar = { SearchNotificationSection(navController = navController) }) {

        BoxWithConstraints(modifier = Modifier.padding(it)) {
            val screenHeight = maxHeight
            val scrollState = rememberScrollState()
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(state = scrollState)
            ) {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.primary),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    AsyncImage(
                        model = "https://cdn.24.co.za/files/Cms/General/d/2694/af1c6139c4a54413844c145b2e67dee4.jpg",
                        contentDescription = "user profile image",
                        modifier = Modifier
                            .padding(bottom = 10.dp)
                            .size(100.dp)
                            .clip(CircleShape),
                        alignment = Alignment.Center,
                        contentScale = ContentScale.Crop

                    )
                    Text(
                        text = "Ahmed khater", fontWeight = FontWeight.Bold,
                        fontSize = 13.sp,
                        color = MaterialTheme.colorScheme.onBackground,
                        fontFamily = Ubuntu,
                    )

                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(vertical = 25.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "20\n posts", fontWeight = FontWeight.Bold,
                            fontSize = 13.sp,
                            color = MaterialTheme.colorScheme.onBackground.copy(0.5f),
                            fontFamily = Ubuntu,
                            textAlign = TextAlign.Center
                        )

                        Text(
                            text = "355\n followers", fontWeight = FontWeight.Bold,
                            fontSize = 13.sp,
                            color = MaterialTheme.colorScheme.onBackground.copy(0.5f),
                            fontFamily = Ubuntu,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = "20\n following", fontWeight = FontWeight.Bold,
                            fontSize = 13.sp,
                            color = MaterialTheme.colorScheme.onBackground.copy(0.5f),
                            fontFamily = Ubuntu,
                            textAlign = TextAlign.Center

                        )


                    }
                }

                Column(modifier = Modifier.height(screenHeight)) {
                    val tabList = listOf("Articles", "Favorite")
                    val pagerState =
                        rememberPagerState(initialPage = 0)
                    val coroutineScope = rememberCoroutineScope()
                    val infiniteTransition = rememberInfiniteTransition(label = "change color")

                    val color by infiniteTransition.animateColor(
                        initialValue = Color.Cyan,
                        targetValue = Orange ,
                        animationSpec = infiniteRepeatable(
                            animation = tween(3000, easing = LinearEasing),
                            repeatMode = RepeatMode.Reverse
                        ),label = "indicator color"
                    )
                    TabRow(
                        modifier = Modifier.fillMaxWidth(),
                        backgroundColor = MaterialTheme.colorScheme.background,
                        contentColor = Color.Black,
                        selectedTabIndex = pagerState.currentPage,
                         indicator = { tabPositions ->
                            TabRowDefaults.Indicator(
                                Modifier
                                    .pagerTabIndicatorOffset(pagerState, tabPositions)
                                    .padding(horizontal = 40.dp).clip(RoundedCornerShape(10.dp)),
                                color = color
                            )
                        }
                    ) {

                        tabList.forEachIndexed { index, title ->
                            Tab(
                                text = {
                                    Text(
                                        title,
                                        color = if (pagerState.currentPage == index) MaterialTheme.colorScheme.onBackground else MaterialTheme.colorScheme.onBackground.copy(
                                            0.5f
                                        )
                                    )
                                },
                                selected = pagerState.currentPage == index,
                                onClick = {
                                    coroutineScope.launch {
                                        pagerState.animateScrollToPage(index)
                                    }
                                },
                                interactionSource = NoRippleInteractionSource()
                            )
                        }
                    }

                    HorizontalPager(
                        state = pagerState,
                        count = tabList.size,
                        modifier = Modifier
                            .padding(bottom = 55.dp)
                            .fillMaxHeight()
                            .nestedScroll(remember {
                                object : NestedScrollConnection {
                                    override fun onPreScroll(
                                        available: Offset,
                                        source: NestedScrollSource
                                    ): Offset {
                                        return if (available.y > 0) Offset.Zero else Offset(
                                            x = 0f,
                                            y = -scrollState.dispatchRawDelta(-available.y)
                                        )
                                    }
                                }
                            })
                    ) { page: Int ->
                        when (page) {
                            0 -> MyArticles(navController)
                            1 -> MyArticles(navController)
                        }
                    }
                }
            }
        }
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchNotificationSection(navController: NavController) {

    TopAppBar(modifier = Modifier.padding(horizontal = 20.dp), title = {
        Text(
            text = "Profile", fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = MaterialTheme.colorScheme.onBackground,
            fontFamily = Ubuntu,
            textAlign = TextAlign.Center
        )
    }, navigationIcon = {
        Icon(imageVector = Icons.Default.ArrowBackIos,
            contentDescription = "Back to Main Screen",
            modifier = Modifier
                .size(20.dp)
                .noRippleClickable {
                    navController.popBackStack()
                })
    }, actions = {
        Icon(
            painter = painterResource(id = R.drawable.profile_menu),
            contentDescription = "Go to settings",
            modifier = Modifier
                .size(24.dp)
                .noRippleClickable {
                    navController.navigate(Screen.SettingScreen.route)
                },
            tint = MaterialTheme.colorScheme.onBackground
        )
    })
//    Row(
//        modifier = Modifier
//            .background(MaterialTheme.colorScheme.background)
//            .fillMaxWidth()
//            .padding(start = 20.dp, end = 20.dp, top = 50.dp, bottom = 20.dp),
//        verticalAlignment = Alignment.CenterVertically,
//        horizontalArrangement = Arrangement.SpaceBetween
//    ) {
//        Text(
//            text = "Code Article", fontWeight = FontWeight.Bold,
//            fontSize = 15.sp,
//            color = Orange,
//            fontFamily = Ubuntu,
//            modifier = Modifier.weight(1f)
//        )
//
//        Icon(
//            imageVector = Icons.Outlined.Menu,
//            contentDescription = "Go to settings",
//            modifier = Modifier
//                .size(24.dp)
//                .clickable {
//                    navController.navigate(Screen.SettingScreen.route)
//                },
//            tint = MaterialTheme.colorScheme.onBackground
//        )
//
//    }

}

