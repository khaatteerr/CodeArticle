package com.compose.codearticle.presentaion.screens.profileScreen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.compose.codearticle.presentaion.navigation.Screen
import com.compose.codearticle.presentaion.screens.homeScreen.HomeScreen
import com.compose.codearticle.presentaion.screens.myArticles.MyArticles
import com.compose.codearticle.presentaion.screens.profileScreen.composables.TabItem
import com.compose.codearticle.presentaion.screens.settingScreen.SettingScreen
import com.compose.codearticle.presentaion.screens.settingScreen.changePassword.ChangePasswordScreen
import com.compose.codearticle.presentaion.screens.settingScreen.updateData.UpdateDataScreen
import com.compose.codearticle.presentaion.theme.MainColor
import com.compose.codearticle.presentaion.theme.Orange
import com.compose.codearticle.presentaion.theme.Ubuntu
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.launch

@Composable
fun ProfileScreen(navController: NavController) {

    ProfileContent(navController)
}

@Composable
fun ProfileContent(navController: NavController) {
    Column(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.secondary)

    ) {
        SearchNotificationSection(navController)
        UploadedPostsSection(navController)
    }
}

@Composable
fun SearchNotificationSection(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 50.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Code Article", fontWeight = FontWeight.Bold,
            fontSize = 15.sp,
            color = Orange,
            fontFamily = Ubuntu,
            modifier = Modifier.weight(1f)
        )

        Icon(
            imageVector = Icons.Outlined.Menu,
            contentDescription = "Go to settings",
            modifier = Modifier
                .size(24.dp)
                .clickable {
                    navController.navigate(Screen.SettingScreen.route)
                },
            tint = MaterialTheme.colorScheme.onBackground
        )

    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun UploadedPostsSection(navController: NavController) {
    Column(
        Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
            .background(MaterialTheme.colorScheme.primary),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        AsyncImage(
            model = "https://cdn.24.co.za/files/Cms/General/d/2694/af1c6139c4a54413844c145b2e67dee4.jpg",
            contentDescription = "user profile image",
            modifier = Modifier
                .padding(top = 50.dp, bottom = 10.dp)
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

        val tabs = listOf(
            TabItem(
                title = "Articles",
                icon = Icons.Filled.Favorite,
                screen = {
                    MyArticles(navController = navController)
                }
            ),
            TabItem(
                title = "Favorite",
                icon = Icons.Filled.Add,
                screen = {

                    ChangePasswordScreen(navController = navController)
                }
            ))
        val pagerState = rememberPagerState(
        ) { tabs.size }
        val coroutineScope = rememberCoroutineScope()

        TabRow(
            selectedTabIndex = pagerState.currentPage,
            modifier = Modifier.background(Color.White),
            contentColor = Color.White,
            divider = {},
            indicator = { tabPositions ->
                Box(
                    modifier = Modifier
                        .tabIndicatorOffset(tabPositions[pagerState.currentPage])
                        .height(4.dp)
                        .padding(horizontal = 50.dp)
                        .background(
                            color = MaterialTheme.colorScheme.secondary,
                            RoundedCornerShape(8.dp)
                        )
                )
            }
        ) {
            tabs.forEachIndexed { index, item ->

                Tab(
                    selected = index == pagerState.currentPage,
                    text = { Text(text = item.title) },
                    onClick = { coroutineScope.launch { pagerState.animateScrollToPage(index) } },
                )
            }
        }
        HorizontalPager(
            modifier = Modifier,
            state = pagerState,
            userScrollEnabled = true,
            reverseLayout = false,
            beyondBoundsPageCount = 0,
            pageContent = {
                tabs[pagerState.currentPage].screen()
            }
        )
    }
}
