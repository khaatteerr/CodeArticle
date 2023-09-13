package com.compose.codearticle.presentaion.screens.homeScreen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Message
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.compose.codearticle.presentaion.navigation.Screen
import com.compose.codearticle.presentaion.screens.homeScreen.composables.CustomTab
import com.compose.codearticle.presentaion.screens.homeScreen.composables.Post
import com.compose.codearticle.presentaion.screens.homeScreen.composables.StoryItem
import com.compose.codearticle.presentaion.screens.homeScreen.uiStates.PostUiState
import com.compose.codearticle.presentaion.screens.myArticles.MyArticles
import com.compose.codearticle.presentaion.theme.Ubuntu
import com.compose.codearticle.presentaion.utilities.ShimmerEffect
import com.compose.codearticle.presentaion.utilities.shimmerEffect
import com.exyte.animatednavbar.utils.noRippleClickable
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class, ExperimentalFoundationApi::class)
@Composable
fun StoriesHomeScreen(
    navController: NavController, homeViewModel: HomeViewModel = hiltViewModel()
) {
    Scaffold(topBar = { MessageNotificationSection(navController = navController) }) { it ->
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
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    val stories = homeViewModel.postsUiState.stories.collectAsLazyPagingItems()
                    Text(
                        text = "Stories",
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 20.dp, start = 20.dp),
                        fontSize = 18.sp,
                        color = Color.White,
                        fontFamily = Ubuntu,
                        fontWeight = FontWeight.Thin
                    )
                    if (stories.loadState.refresh is LoadState.Loading) {

                        Row(
                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                            modifier = Modifier.padding(20.dp)
                        ) {

                            for (i in 1..3) {

                                Box(
                                    modifier = Modifier
                                        .width(120.dp)
                                        .height(150.dp)
                                        .clip(RoundedCornerShape(16.dp))
                                        .shimmerEffect()
                                )
                            }
                        }

                    } else {
                        LazyRow(
                            contentPadding = PaddingValues(20.dp),
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            items(stories.itemCount) { storyItem ->
                                StoryItem(stories[storyItem]!!)
                            }
                        }
                    }
                    Column(
                        modifier = Modifier
                            .height(screenHeight)

                            .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
                    ) {


                        val tabList = listOf("Explore", "Discover")
                        val pagerState = rememberPagerState(initialPage = 0)
                        val coroutineScope = rememberCoroutineScope()



                        CustomTab(
                            items = tabList,
                            selectedItemIndex = pagerState.currentPage,
                            onClick = {
                                 coroutineScope.launch {
                                    pagerState.animateScrollToPage(it)
                                }
                            },
                        )
                        HorizontalPager(
                            state = pagerState,
                            count = tabList.size,
                            modifier = Modifier
                                .padding( top = 10.dp)
                                .fillMaxHeight()
                                .nestedScroll(remember {
                                    object : NestedScrollConnection {
                                        override fun onPreScroll(
                                            available: Offset, source: NestedScrollSource
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
                                0 -> Posts(navController, homeViewModel)

                                1 -> MyArticles(navController)
                            }
                        }
                    }


                }
            }
        }
    }
}

@Composable
fun Posts(
    navController: NavController,
    homeViewModel: HomeViewModel,
 ) {
    val articlesPageItems: LazyPagingItems<PostUiState> =
        homeViewModel.postsUiState.articles.collectAsLazyPagingItems()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
    ) {


        if (articlesPageItems.loadState.refresh is LoadState.Loading) {

            Column {

                for (i in 1..3) {

                    ShimmerEffect(modifier = Modifier.padding(top = if (i == 1) 70.dp else 10.dp))
                }
            }

        } else {

            LazyColumn(
                contentPadding = PaddingValues(
                    start = 10.dp, end = 10.dp, bottom = 55.dp, top = 0.dp
                ),
                verticalArrangement = Arrangement.spacedBy(5.dp),

                ) {
                items(articlesPageItems.itemCount) { post ->

                    Post(
                        postCardUiState = articlesPageItems[post]!!, navController, homeViewModel = homeViewModel
                    )

                }
                item {
                    if (articlesPageItems.loadState.append is LoadState.Loading) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .fillMaxWidth()
                                .size(35.dp)
                                .align(Alignment.Center)
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MessageNotificationSection(navController: NavController) {

    TopAppBar(modifier = Modifier.padding(end = 10.dp), title = {
        Text(
            text = "Home",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = MaterialTheme.colorScheme.onBackground,
            fontFamily = Ubuntu,
            textAlign = TextAlign.Center
        )
    }, actions = {
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {

            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .size(40.dp)
                    .background(MaterialTheme.colorScheme.outline),
                contentAlignment = Alignment.Center
            ) {

                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription = "Go to notifications",
                    modifier = Modifier
                        .size(20.dp)
                        .noRippleClickable {
                            navController.navigate(Screen.SettingScreen.route)
                        },
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .size(40.dp)
                    .background(MaterialTheme.colorScheme.outline),
                contentAlignment = Alignment.Center
            ) {

                Icon(
                    imageVector = Icons.Default.Message,
                    contentDescription = "Go to messages",
                    modifier = Modifier
                        .size(20.dp)
                        .noRippleClickable {
                            navController.navigate(Screen.SettingScreen.route)
                        },
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
        }
    })


}
