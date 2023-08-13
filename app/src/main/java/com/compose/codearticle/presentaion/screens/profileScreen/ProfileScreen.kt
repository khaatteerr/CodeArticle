@file:OptIn(ExperimentalPagerApi::class)

package com.compose.codearticle.presentaion.screens.profileScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.TabRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.compose.codearticle.presentaion.navigation.Screen
import com.compose.codearticle.presentaion.screens.myArticles.MyArticles
import com.compose.codearticle.presentaion.screens.profileScreen.composables.TabItem
import com.compose.codearticle.presentaion.screens.settingScreen.changePassword.ChangePasswordScreen
import com.compose.codearticle.presentaion.theme.Orange
import com.compose.codearticle.presentaion.theme.Ubuntu
import com.compose.codearticle.presentaion.theme.White0_7
import com.compose.codearticle.presentaion.utilities.NoRippleInteractionSource
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@Composable
fun ProfileScreen(navController: NavController) {

    Final(navController)
//    ProfileContent(navController)
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Final(navController: NavController) {
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
                }

                Column(modifier = Modifier.height(screenHeight)) {
                    val tabList = listOf("Articles", "Favorite")
                    val pagerState =
                        rememberPagerState(initialPage = 0)
                    val coroutineScope = rememberCoroutineScope()

                    TabRow(
                        modifier = Modifier.fillMaxWidth(),
                        backgroundColor = MaterialTheme.colorScheme.background,
                        contentColor = Color.Black,
                        selectedTabIndex = pagerState.currentPage,
                        // Override the indicator, using the provided pagerTabIndicatorOffset modifier
                        indicator = { tabPositions ->
                            TabRowDefaults.Indicator(
                                Modifier
                                    .pagerTabIndicatorOffset(pagerState, tabPositions)
                                    .padding(horizontal = 40.dp),
                                color = MaterialTheme.colorScheme.tertiary
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





@Composable
fun SearchNotificationSection(navController: NavController) {
    Row(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, top = 50.dp, bottom = 20.dp),
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

