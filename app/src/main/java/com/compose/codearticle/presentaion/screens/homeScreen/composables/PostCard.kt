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
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
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
import com.compose.codearticle.presentaion.theme.Orange
import com.compose.codearticle.presentaion.theme.Ubuntu
import com.compose.codearticle.presentaion.utilities.bounceClick

@Composable
fun PostCard(
    postCardUiState: PostUiState,
    navController: NavController,
    homeViewModel: HomeViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface),
    ) {
        Row(
            Modifier,
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
            Text(
                text = postCardUiState.postedBy?.username!!,
                fontWeight = Bold,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurface,
                fontFamily = Ubuntu

            )
            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = postCardUiState.createdAt,
                fontWeight = Light,
                fontSize = 13.sp,
                color = MaterialTheme.colorScheme.inversePrimary.copy(0.6f),
                fontFamily = Ubuntu
            )

            Box(
                modifier = Modifier
                    .size(20.dp)
                    .clip(CircleShape)
                    .clickable {
                        postCardUiState.changeDropDownMenuState.invoke()
                    }, contentAlignment = Alignment.Center
            ) {
                Icon(
                    painterResource(id = R.drawable.baseline_more_horiz_24),
                    contentDescription = "more",
                    modifier = Modifier.size(20.dp),
                    tint = MaterialTheme.colorScheme.onBackground
                )

                if (postCardUiState.isDropDownMenuActive)
                    PostCardDropdownMenu(onExpand = true) {
                        postCardUiState.changeDropDownMenuState.invoke()
                    }

            }


        }

        Column() {
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
                    .height(finalHeight)
                    .bounceClick(0.98f) { }
                    .clip(RoundedCornerShape(10.dp))
                    .border(
                        1.dp, Color.LightGray.copy(0.1f),
                        RoundedCornerShape(10.dp)
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
                    if (postCardUiState.isLiked) painterResource(id = R.drawable.like_filled) else painterResource(
                        id = R.drawable.like
                    ),
                    contentDescription = "favorite",
                    modifier = Modifier
                        .size(20.dp)
                        .bounceClick {
                            postCardUiState.changeLikeState()
                        },
                    tint = if (postCardUiState.isLiked) Color.Red else MaterialTheme.colorScheme.onBackground
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
                    if (postCardUiState.isSavedToLocal) painterResource(id = R.drawable.bookmark_filled) else painterResource(
                        id = R.drawable.ic_bookmark
                    ),
                    contentDescription = "save",
                    modifier = Modifier
                        .size(24.dp)
                        .bounceClick { postCardUiState.SaveToLocal.invoke() },
                    tint = if (postCardUiState.isSavedToLocal) Orange else MaterialTheme.colorScheme.onBackground
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
                    text = "${postCardUiState.likes} likes",
                    fontWeight = Light,
                    fontSize = 13.sp,
                    color = MaterialTheme.colorScheme.inversePrimary.copy(0.7f),
                    fontFamily = Ubuntu,
                )
                Text(
                    text = "${postCardUiState.comments_count} comments",
                    fontWeight = Light,
                    fontSize = 13.sp,
                    color = MaterialTheme.colorScheme.inversePrimary.copy(0.7f),
                    fontFamily = Ubuntu,
                )
            }
            Divider(
                Modifier
                    .fillMaxWidth()
                    .height(1.dp),
                color = MaterialTheme.colorScheme.outline.copy(0.2f),
                thickness = 1.dp
            )

        }

    }


}