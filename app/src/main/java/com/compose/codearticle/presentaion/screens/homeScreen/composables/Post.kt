package com.compose.codearticle.presentaion.screens.homeScreen.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.font.FontWeight.Companion.Light
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.compose.codearticle.R
import com.compose.codearticle.presentaion.screens.homeScreen.HomeViewModel
import com.compose.codearticle.presentaion.screens.homeScreen.uiStates.PostUiState
import com.compose.codearticle.presentaion.theme.BackgroundMainColor
import com.compose.codearticle.presentaion.theme.CardsColors
import com.compose.codearticle.presentaion.theme.MainColor
import com.compose.codearticle.presentaion.theme.Orange
import com.compose.codearticle.presentaion.theme.SettingBackground
import com.compose.codearticle.presentaion.theme.Ubuntu
import com.compose.codearticle.presentaion.theme.cardColor
import com.compose.codearticle.presentaion.theme.postColor
import com.compose.codearticle.presentaion.utilities.bounceClick

@Composable
fun Post(
    postCardUiState: PostUiState,
    navController: NavController,
    homeViewModel: HomeViewModel
) {


    Card(

        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 0.dp, vertical = 5.dp)
            .clip(RoundedCornerShape(16.dp)),
        colors = CardDefaults.cardColors(containerColor =  Color( BackgroundMainColor))


    ) {


        var isLiked by rememberSaveable() {
            mutableStateOf(postCardUiState.isLiked)
        }

        var isDropDownMenuActive by rememberSaveable {
            mutableStateOf(postCardUiState.isDropDownMenuActive)
        }
        var isSavedToLocal by rememberSaveable {
            mutableStateOf(postCardUiState.isSavedToLocal)
        }
        var likes by rememberSaveable {
            mutableStateOf(postCardUiState.likes)
        }

        val boxSize = with(LocalDensity.current) { (postCardUiState.media.originalHeight ).dp.toPx() }


        Row(
            Modifier .padding(start = 8.dp, top = 15.dp, end = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            AsyncImage(
                model = postCardUiState.postedBy?.avatar,
                contentDescription = postCardUiState.postedBy?.avatar,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop,
                fallback = rememberAsyncImagePainter(model = "https://i.redd.it/vr2o7iiob5k91.jpg"),
                placeholder = rememberAsyncImagePainter(model = "https://i.redd.it/vr2o7iiob5k91.jpg"),
                error = rememberAsyncImagePainter(model = "https://i.redd.it/vr2o7iiob5k91.jpg"),
                filterQuality = FilterQuality.Low
            )
            Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {

                Text(
                    text = postCardUiState.postedBy?.username!!,
                    fontWeight = Bold,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontFamily = Ubuntu

                )
                Text(
                    text = postCardUiState.createdAt,
                    fontWeight = Light,
                    fontSize = 13.sp,
                    color = MaterialTheme.colorScheme.inversePrimary.copy(0.6f),
                    fontFamily = Ubuntu
                )
            }
            Spacer(modifier = Modifier.weight(1f))


            Box(
                modifier = Modifier
                    .size(35.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.outline)
                    .clickable {
                        isDropDownMenuActive = !isDropDownMenuActive
                    }, contentAlignment = Alignment.Center
            ) {
                Icon(
                    painterResource(id = R.drawable.baseline_more_horiz_24),
                    contentDescription = "more",
                    modifier = Modifier.size(20.dp),
                    tint = MaterialTheme.colorScheme.onBackground
                )

                if (isDropDownMenuActive)
                    PostCardDropdownMenu(onExpand = true) {
                        isDropDownMenuActive = !isDropDownMenuActive
                    }

            }


        }



        Column(modifier = Modifier

            .padding(  8.dp )
        ) {
            ExpandableText(
                text = postCardUiState.postDescription
            )

            val context = LocalContext.current
            val density = context.resources.displayMetrics.density
            val finalHeight = (postCardUiState.media.originalHeight / density).toInt().dp


            AsyncImage(
                model = postCardUiState.media.postImage,
                contentDescription = postCardUiState.media.postImage,
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(
                        20.dp,
                        spotColor = postCardUiState.shadowColor
                    )
                    .height(finalHeight)
                    .bounceClick(0.98f) { }
                    .clip(RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp))
                    .border(
                        1.dp, Color.LightGray.copy(0.1f),
                        RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp)
                    ),
                contentScale = ContentScale.Crop,

                fallback = rememberAsyncImagePainter(model = "https://i.redd.it/vr2o7iiob5k91.jpg"),
                placeholder = rememberAsyncImagePainter(model = "https://i.redd.it/vr2o7iiob5k91.jpg"),
                error = rememberAsyncImagePainter(model = "https://i.redd.it/vr2o7iiob5k91.jpg"),
                alignment = Alignment.Center,
                filterQuality = FilterQuality.Low
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(15.dp),
                modifier = Modifier.padding(vertical = 10.dp)

            ) {
                Icon(
                    if (isLiked) painterResource(id = R.drawable.like_filled) else painterResource(
                        id = R.drawable.like
                    ),
                    contentDescription = "favorite",
                    modifier = Modifier
                        .size(20.dp)
                        .bounceClick {
                            isLiked = !isLiked
                            likes = if (isLiked) likes!! + 1 else likes!! - 1
                        },
                    tint = if (isLiked) Color.Red else MaterialTheme.colorScheme.onBackground
                )

                Icon(
                    painter = painterResource(id = R.drawable.comment),
                    contentDescription = null,
                    modifier = Modifier.size(20.dp),
                    tint = MaterialTheme.colorScheme.onBackground
                )
                Icon(
                    painter = painterResource(id = R.drawable.share),
                    contentDescription = null,
                    modifier = Modifier.size(20.dp),
                    tint = MaterialTheme.colorScheme.onBackground
                )
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    if (isSavedToLocal) painterResource(id = R.drawable.bookmark_filled) else painterResource(
                        id = R.drawable.ic_bookmark
                    ),
                    contentDescription = "save",
                    modifier = Modifier
                        .size(24.dp)
                        .bounceClick { isSavedToLocal = !isSavedToLocal },
                    tint = if (isSavedToLocal) Orange else MaterialTheme.colorScheme.onBackground
                )
            }

            Row(
                modifier = Modifier
                    .padding(bottom = 10.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "$likes likes",
                    fontWeight = Light,
                    fontSize = 13.sp,
                    color = MaterialTheme.colorScheme.inversePrimary.copy(0.7f),
                    fontFamily = Ubuntu,
                )
                Text(
                    text = "${postCardUiState.commentsCount} comments",
                    fontWeight = Light,
                    fontSize = 13.sp,
                    color = MaterialTheme.colorScheme.inversePrimary.copy(0.7f),
                    fontFamily = Ubuntu,
                )
            }


        }

    }


}