package com.compose.codearticle.presentaion.screens.homeScreen

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeightIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarData
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.Message
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.compose.codearticle.R
import com.compose.codearticle.presentaion.navigation.Screen
import com.compose.codearticle.presentaion.screens.homeScreen.composables.Post
import com.compose.codearticle.presentaion.screens.homeScreen.composables.PostCard
import com.compose.codearticle.presentaion.screens.homeScreen.uiStates.HomeUiEvent
import com.compose.codearticle.presentaion.screens.homeScreen.uiStates.PostUiState
import com.compose.codearticle.presentaion.theme.MainColor
import com.compose.codearticle.presentaion.theme.Ubuntu
import com.compose.codearticle.presentaion.theme.cardColor
import com.compose.codearticle.presentaion.utilities.ShimmerEffect
import com.exyte.animatednavbar.utils.noRippleClickable
import kotlinx.coroutines.flow.collectLatest
import kotlin.math.roundToInt

@Composable
fun HomeScreen(navController: NavController, homeViewModel: HomeViewModel = hiltViewModel()) {
    HomeContent(navController, homeViewModel)
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeContent(navController: NavController, homeViewModel: HomeViewModel) {

    PostsSections(navController = navController, homeViewModel = homeViewModel)

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchSection(homeViewModel: HomeViewModel = hiltViewModel(), modifier: Modifier = Modifier) {


    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp


    val screenAfterSearch = screenWidth - 120.dp
    val searchBarSize by animateDpAsState(
        if (homeViewModel.postsUiState.isSearchBarActive) screenWidth else screenAfterSearch,
        label = "searchBarSize"
    )
    val searchBarHeight by animateDpAsState(
        if (homeViewModel.postsUiState.isSearchBarActive) screenHeight else 40.dp,
        label = "searchBarHeight"
    )

    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxWidth()
            .animateContentSize()
            .background(MainColor),
        verticalAlignment = Alignment.CenterVertically
    ) {

        AnimatedVisibility(
            visible = homeViewModel.postsUiState.isSearchBarActive.not()
        ) {
            AsyncImage(
                model = "https://pixlr.com/images/index/remove-bg.webp",
                modifier = Modifier
                    .padding(top = 20.dp, end = 10.dp, start = 20.dp)
                    .animateContentSize()
                    .size(35.dp)
                    .clip(CircleShape),
                contentDescription = "Profile Image",
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center,
                fallback = rememberAsyncImagePainter(model = "https://i.redd.it/vr2o7iiob5k91.jpg"),
                placeholder = rememberAsyncImagePainter(model = "https://i.redd.it/vr2o7iiob5k91.jpg"),
                error = rememberAsyncImagePainter(model = "https://i.redd.it/vr2o7iiob5k91.jpg"),
                filterQuality = FilterQuality.Low

            )
        }

        SearchBar(
            modifier = Modifier
                .padding(bottom = 25.dp)
                .height(searchBarHeight)
                .width(searchBarSize),
            query = homeViewModel.postsUiState.textSearch,
            onQueryChange = { homeViewModel.onEvent(HomeUiEvent.SearchTextChanged(it)) },
            onSearch = {
                homeViewModel.onEvent(HomeUiEvent.Search)
                homeViewModel.onEvent(HomeUiEvent.AddToHistory(it))
            },
            active = homeViewModel.postsUiState.isSearchBarActive,
            onActiveChange = {
                homeViewModel.onEvent(HomeUiEvent.SearchBarActive(it))
            },
            placeholder = {
                Text(
                    text = "Search",
                    fontFamily = Ubuntu,
                    modifier = Modifier
                        .requiredHeightIn(40.dp)
                        .padding(top = 10.dp)
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Search,
                    contentDescription = "Search Icon",
                    tint = MaterialTheme.colorScheme.onBackground
                )
            },
            trailingIcon = {
                if (homeViewModel.postsUiState.isSearchBarActive) {
                    Icon(
                        modifier = Modifier
                            .clip(CircleShape)
                            .clickable {
                                homeViewModel.onEvent(HomeUiEvent.ClearOrCloseSearchBar)
                            },
                        imageVector = Icons.Outlined.Close,
                        contentDescription = "Close Icon"
                    )
                }
            },
            shape = RoundedCornerShape(10.dp),

            colors = SearchBarDefaults.colors(
                containerColor = MaterialTheme.colorScheme.secondary,
            )
        ) {
            val context = LocalContext.current
            homeViewModel.postsUiState.searchHistory.forEach {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 14.dp)
                        .clickable {
                            Toast
                                .makeText(context, it, Toast.LENGTH_LONG)
                                .show()
                        }) {
                    Icon(
                        modifier = Modifier.padding(end = 10.dp),
                        painter = painterResource(id = R.drawable.outline_history_24),
                        contentDescription = "History Icon",
                        tint = Color.White.copy(0.8f)
                    )
                    Text(text = it, color = MaterialTheme.colorScheme.onSurface)
                }
            }

        }
        AnimatedVisibility(
            visible = homeViewModel.postsUiState.isSearchBarActive.not()
        )
        {
            Icon(
                modifier = Modifier
                    .animateContentSize()
                    .padding(start = 10.dp, end = 10.dp, top = 20.dp)
                    .size(35.dp),
                painter = painterResource(id = R.drawable.chat_plus),
                contentDescription = "Chat Icon",
                tint = MaterialTheme.colorScheme.onBackground
            )
        }

    }
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PostsSections(
    navController: NavController,
    homeViewModel: HomeViewModel
) {

//    val article = homeViewModel.articlePagingFlow.collectAsLazyPagingItems()
//    val context = LocalContext.current
//    LaunchedEffect(key1 = article.loadState) {
//        if (article.loadState.refresh is LoadState.Error) {
//            Toast.makeText(
//                context,
//                "Error: " + (article.loadState.refresh as LoadState.Error).error.message,
//                Toast.LENGTH_LONG
//            ).show()
//        }
//    }
//    Box(modifier = Modifier.fillMaxSize()) {
//        if (article.loadState.refresh is LoadState.Loading) {
//            CircularProgressIndicator(
//                modifier = Modifier.align(Alignment.Center)
//            )
//        } else {
//            LazyColumn(
//                modifier = Modifier.fillMaxSize(),
//                verticalArrangement = Arrangement.spacedBy(16.dp),
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//
//                items(article) { article ->
//                    AsyncImage(
//                        model = article!!.attachment.image,
//                        contentDescription = null,
//                        modifier = Modifier.size(200.dp)
//                    )
//                }
//                item {
//                    if (article.loadState.append is LoadState.Loading) {
//                        CircularProgressIndicator()
//                    }
//                }
//            }
//        }
//    }
    val toolbarHeight = 60.dp
    val toolbarHeightPx = with(LocalDensity.current) { toolbarHeight.roundToPx().toFloat() }
    val toolbarOffsetHeightPx = remember { mutableFloatStateOf(0f) }

    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {

                val delta = available.y
                val newOffset = toolbarOffsetHeightPx.floatValue + delta
                toolbarOffsetHeightPx.floatValue = newOffset.coerceIn(-toolbarHeightPx, 0f)
                return Offset.Zero
            }
        }
    }
    val articlesPageItems :LazyPagingItems<PostUiState> = homeViewModel.postsUiState.articles.collectAsLazyPagingItems()

    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = articlesPageItems.loadState) {
        if (articlesPageItems.loadState.refresh is LoadState.Error) {

            snackBarHostState.showSnackbar( "Error: " + (articlesPageItems.loadState.refresh as LoadState.Error).error.message)
        }


    }
    Scaffold(
        Modifier
            .nestedScroll(nestedScrollConnection)
            .fillMaxSize()
            .padding(top = 25.dp, bottom = 0.dp)
            .background(MainColor),
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) },


        topBar = {
            SearchSection(modifier = Modifier

                .offset { IntOffset(x = 0, y = toolbarOffsetHeightPx.floatValue.roundToInt()) })
        }) {


        Box(modifier = Modifier
            .fillMaxSize()
            .background(MainColor)) {


            if (articlesPageItems.loadState.refresh is LoadState.Loading) {

                Column {

                    for (i in 1..3) {

                        ShimmerEffect(modifier = Modifier.padding(top = if (i == 1) 70.dp else 10.dp))
                    }
                }

            }

            else {

                LazyColumn(
                    contentPadding = PaddingValues(
                        start = 10.dp,
                        end = 10.dp,
                        bottom = 55.dp,
                        top = 70.dp
                    ),
                    verticalArrangement = Arrangement.spacedBy(5.dp),

                    ) {
                    items(articlesPageItems.itemCount ) { post ->

                        Post(postCardUiState = articlesPageItems[post]!!, navController, homeViewModel = homeViewModel)

                    }
                    item {
                        if(articlesPageItems.loadState.append is LoadState.Loading) {
                            CircularProgressIndicator(modifier = Modifier
                                .fillMaxWidth()
                                .size(35.dp)
                                .align(Alignment.Center))
                        }
                    }
                }
            }
        }
    }
}

