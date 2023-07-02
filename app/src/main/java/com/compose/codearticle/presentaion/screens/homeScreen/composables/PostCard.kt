package com.compose.codearticle.presentaion.screens.homeScreen.composables

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.font.FontWeight.Companion.Light
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.compose.codearticle.R
import com.compose.codearticle.presentaion.screens.homeScreen.uiStates.PostUiState
import com.compose.codearticle.presentaion.screens.homeScreen.HomeViewModel
import com.compose.codearticle.presentaion.screens.homeScreen.uiStates.HomeUiEvent
import com.compose.codearticle.presentaion.theme.Ubuntu

@Composable
fun PostCard(
    postCardUiState: PostUiState,
    navController: NavController,
    homeViewModel: HomeViewModel
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 5.dp, vertical = 5.dp)
            .height(400.dp)
            .border(1.dp, MaterialTheme.colorScheme.outline, RoundedCornerShape(16.dp))
            .clip(RoundedCornerShape(16.dp)),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surface)
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, start = 16.dp, end = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            Image(
                painter = rememberAsyncImagePainter(model = postCardUiState.postedBy?.avatar),
                contentDescription = postCardUiState.postedBy?.avatar,
                modifier = Modifier
                    .size(35.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Column(
                Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Text(
                    text = postCardUiState.postedBy?.username!!,
                    fontWeight = Bold,
                    fontSize = 15.sp,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontFamily = Ubuntu

                )
                Text(
                    text = postCardUiState.createdAt,
                    fontWeight = Light,
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.inversePrimary,
                    fontFamily = Ubuntu

                )
            }

            var isDropDownMenuActive by remember { mutableStateOf(postCardUiState.isDropDownMenuActive) }

            Box(
                modifier = Modifier
                    .size(24.dp)
                    .clickable {
                        isDropDownMenuActive = true
                    }, contentAlignment = Alignment.Center
            ) {
                Icon(
                    painterResource(id = R.drawable.baseline_more_horiz_24),
                    contentDescription = "more",
                    modifier = Modifier
                        .size(24.dp),
                    tint = MaterialTheme.colorScheme.onBackground
                )
                if (isDropDownMenuActive)
                    PostCardDropdownMenu(onExpand = true) {
                        isDropDownMenuActive = false
                    }
            }


        }


        Text(
            text = postCardUiState.postDescription, maxLines = 3,
            fontWeight = Light,
            fontSize = 13.sp,
            color = MaterialTheme.colorScheme.inversePrimary,
            fontFamily = Ubuntu,
            modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp),
        )

        Image(
            painter = rememberAsyncImagePainter(model = postCardUiState.postImage),
            contentDescription = postCardUiState.postImage,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                .clip(RoundedCornerShape(10.dp)),
            contentScale = ContentScale.Crop
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(15.dp),
            modifier = Modifier

                .padding(horizontal = 20.dp, vertical = 10.dp)
        ) {
            var isLiked by remember {
                mutableStateOf(postCardUiState.isLiked)
            }
            Box(Modifier.clickable {
                isLiked=!isLiked
            }) {
                Icon(
                   if (isLiked) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder ,
                    contentDescription = "favorite",
                    modifier = Modifier.size(24.dp),
                    tint = if (isLiked) Color.Red else MaterialTheme.colorScheme.onBackground
                )
            }
            Icon(
                painterResource(id = R.drawable.comment_dots),
                contentDescription = "more",
                modifier = Modifier.size(24.dp),
                tint = MaterialTheme.colorScheme.onBackground
            )
            Icon(
                painterResource(id = R.drawable.outline_share_24),
                contentDescription = "share",
                modifier = Modifier.size(24.dp),
                tint = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                painterResource(id = R.drawable.ic_bookmark),
                contentDescription = "save",
                modifier = Modifier.size(24.dp),
                tint = MaterialTheme.colorScheme.onBackground
            )
        }

    }

}