@file:OptIn(ExperimentalMaterial3Api::class)

package com.compose.codearticle.presentaion.screens.homeScreen

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.compose.codearticle.R
import com.compose.codearticle.presentaion.screens.homeScreen.composables.PostCard
import com.compose.codearticle.presentaion.screens.homeScreen.uiStates.HomeUiEvent
import com.compose.codearticle.presentaion.screens.homeScreen.uiStates.PostUiState
import com.compose.codearticle.presentaion.screens.homeScreen.uiStates.PostedBy
import com.compose.codearticle.presentaion.theme.Ubuntu

@Composable
fun HomeScreen(navController: NavController, homeViewModel: HomeViewModel = hiltViewModel()) {
    HomeContent(navController, homeViewModel)
}

@Composable
fun HomeContent(navController: NavController, homeViewModel: HomeViewModel) {


    Column(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        SearchSection(homeViewModel)

        PostsSections(
            listOf(
                PostUiState(

                    PostedBy(
                        "1",
                        avatar = "https://nationaltoday.com/wp-content/uploads/2022/04/Cristiano-Ronaldo-Birthday.jpg",
                        "Ahmed Khater"
                    ),

                    "2 hours ago",
                    "https://i.redd.it/vr2o7iiob5k91.jpg", "Sky:\n" +
                            "The sky, a vast expanse stretching endlessly above, is a mesmerizing tapestry of celestial wonders. It encompasses the boundless realm that connects us to the universe, revealing its awe-inspiring secrets. During the day, the sky unfolds in a symphony of vibrant hues, from the crisp azure of a cloudless sky to the fiery embrace of the sun's golden rays. Wispy clouds dance across the canvas, casting playful shadows upon the earth below. As "
                    ,"1"
                ),
                PostUiState(
                    PostedBy(
                        "2",
                        avatar = "https://cloudfront-us-east-2.images.arcpublishing.com/reuters/XEEPZPVPD5O4RO76334OJYCQ4M.jpg",
                        "Khaled Abady"
                    ),

                    "5 hours ago",
                    "https://images.unsplash.com/photo-1580615633247-58fedbee9197?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwyNTUzMjl8MHwxfHNlYXJjaHw0NzQ4fHx2ZXJ0aWNhbHxlbnwwfDF8fHwxNjYxNjI5MDIx&ixlib=rb-1.2.1&q=80&w=1080",
                    "Sky:\n" +
                            "The sky, a vast expanse stretching endlessly above, is a mesmerizing tapestry of celestial wonders. It encompasses the boundless realm that connects us to the universe, revealing its awe-inspiring secrets. During the day, the sky unfolds in a symphony of vibrant hues, from the crisp azure of a cloudless sky to the fiery embrace of the sun's golden rays. Wispy clouds dance across the canvas, casting playful shadows upon the earth below. As "
                    ,"2"
                ),
                PostUiState(
                    PostedBy(
                        "3",
                        avatar = "https://upload.wikimedia.org/wikipedia/commons/thumb/3/33/Sergio_Ramos_Interview_2021.jpg/280px-Sergio_Ramos_Interview_2021.jpg",
                        "Salem Gamal"
                    ),

                    "yesterday",
                    "https://images.unsplash.com/photo-1661355103273-e3fc8ad60c68?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwyNTUzMjl8MHwxfHNlYXJjaHw0NzQ0fHx2ZXJ0aWNhbHxlbnwwfDF8fHwxNjYxNjI5MDIx&ixlib=rb-1.2.1&q=80&w=1080",
                    "Sky:\n" +
                            "The sky, a vast expanse stretching endlessly above, is a mesmerizing tapestry of celestial wonders. It encompasses the boundless realm that connects us to the universe, revealing its awe-inspiring secrets. During the day, the sky unfolds in a symphony of vibrant hues, from the crisp azure of a cloudless sky to the fiery embrace of the sun's golden rays. Wispy clouds dance across the canvas, casting playful shadows upon the earth below. As "
                 ,   "3"
                ),
                PostUiState(
                    PostedBy(
                        "4",
                        avatar = "https://cdn.24.co.za/files/Cms/General/d/2694/af1c6139c4a54413844c145b2e67dee4.jpg",
                        "Mohamed Reda"
                    ),

                    "2 hours ago",
                    "https://images.unsplash.com/photo-1610736702440-9dfab24cd7da?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwyNTUzMjl8MHwxfHNlYXJjaHw3Njd8fE1vYmlsZSUyMFdhbGxwYXBlcnN8ZW58MHwxfHx8MTY2MTYzMTcyMw&ixlib=rb-1.2.1&q=80&w=1080",
                    "Sky:\n" +
                            "The sky, a vast expanse stretching endlessly above, is a mesmerizing tapestry of celestial wonders. It encompasses the boundless realm that connects us to the universe, revealing its awe-inspiring secrets. During the day, the sky unfolds in a symphony of vibrant hues, from the crisp azure of a cloudless sky to the fiery embrace of the sun's golden rays. Wispy clouds dance across the canvas, casting playful shadows upon the earth below. As "
                  ,  "4"
                ),
            ), navController, homeViewModel
        )
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchSection(homeViewModel: HomeViewModel) {


    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp


    val screenAfterSearch = screenWidth - 120.dp
    val searchBarSize by animateDpAsState(if (homeViewModel.postsUiState.isSearchBarActive) screenWidth else screenAfterSearch)
    val searchBarHeight by animateDpAsState(if (homeViewModel.postsUiState.isSearchBarActive) screenHeight else 50.dp)

    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp),
        verticalAlignment = Alignment.Bottom
    ) {
        AnimatedVisibility(
            visible = homeViewModel.postsUiState.isSearchBarActive.not()
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = "https://pixlr.com/images/index/remove-bg.webp"),
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .animateContentSize()
                    .size(35.dp)
                    .clip(CircleShape),
                contentDescription = "Profile Image",
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center
            )
        }
        SearchBar(
            modifier = Modifier
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
                        modifier = Modifier.clickable {
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
                    .padding(horizontal = 10.dp)
                    .size(35.dp),
                painter = painterResource(id = R.drawable.chat_plus),
                contentDescription = "Chat Icon",
                tint = MaterialTheme.colorScheme.onBackground
            )
        }

    }
}


@Composable
fun PostsSections(
    posts: List<PostUiState>,
    navController: NavController,
    homeViewModel: HomeViewModel
) {
    val state = rememberLazyListState()

    Column {
        LazyColumn(
            state = state, modifier = Modifier

                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            items(posts.size) {

                PostCard(postCardUiState = posts[it], navController, homeViewModel = homeViewModel)
            }
        }
    }

}
