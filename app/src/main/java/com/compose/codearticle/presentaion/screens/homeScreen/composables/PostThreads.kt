package com.compose.codearticle.presentaion.screens.homeScreen.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
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
import coil.request.ImageRequest
import coil.size.Size
import com.compose.codearticle.R
import com.compose.codearticle.presentaion.screens.homeScreen.HomeViewModel
import com.compose.codearticle.presentaion.screens.homeScreen.uiStates.PostUiState
import com.compose.codearticle.presentaion.theme.Ubuntu
import com.compose.codearticle.presentaion.utilities.bounceClick

@Composable
fun PostThreads(
    postCardUiState: PostUiState, navController: NavController, homeViewModel: HomeViewModel
) {

    Column() {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Max)
                .padding(horizontal = 15.dp, vertical = 10.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxHeight()
            ) {
                Box(Modifier.size(40.dp)) {
                    AsyncImage(
                        model = postCardUiState.postedBy?.avatar,
                        contentDescription = postCardUiState.postedBy?.avatar,
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                    if (postCardUiState.isFollowing.not()) {
                        Box(
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .clip(CircleShape)
                                .background(White), contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "add",
                                tint = Color.Black,
                                modifier = Modifier.size(15.dp)
                            )
                        }
                    }
                }
                Spacer(Modifier.height(5.dp))
                Divider(
                    modifier = Modifier
                        .weight(1f)
                        .width(1.dp), color = Color.Gray
                )
                Spacer(Modifier.height(3.dp))
                Box(modifier = Modifier.size(width = 36.dp, height = 30.dp)) {
                    Image(
                        painter = rememberAsyncImagePainter(postCardUiState.postedBy?.avatar),
                        contentDescription = null,
                        modifier = Modifier
                            .size(15.dp)
                            .clip(CircleShape)
                            .align(Alignment.CenterStart)
                    )
                    Image(
                        painter = rememberAsyncImagePainter(postCardUiState.postedBy?.avatar),
                        contentDescription = null,
                        modifier = Modifier
                            .size(20.dp)
                            .clip(CircleShape)
                            .align(Alignment.TopEnd)
                    )

                    Image(
                        painter = rememberAsyncImagePainter(postCardUiState.postedBy?.avatar),
                        contentDescription = null,
                        modifier = Modifier
                            .size(10.dp)
                            .clip(CircleShape)
                            .align(Alignment.BottomCenter)
                    )
                }
            }
            Spacer(modifier = Modifier.width(10.dp))
            Column(
                horizontalAlignment = Alignment.Start, modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = postCardUiState.postedBy?.username!!,
                        fontWeight = Bold,
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontFamily = Ubuntu,
                        modifier = Modifier

                    )
                    Spacer(modifier = Modifier.width(5.dp))

                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = postCardUiState.createdAt, color = Color.Gray, fontWeight = Light
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.post_more),
                        contentDescription = null,
                        modifier = Modifier.size(15.dp),
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = postCardUiState.postDescription,
                    fontWeight = Light,
                    fontSize = 13.sp,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontFamily = Ubuntu,
                    modifier = Modifier
                        .border(
                            1.dp,
                            if (postCardUiState.media.postImage == "") Color.LightGray.copy(0.3f) else Color.Transparent,
                            RoundedCornerShape(10.dp)
                        )
                        .padding(if (postCardUiState.media.postImage == "") 10.dp else 0.dp),
                )
                Spacer(modifier = Modifier.height(10.dp))
                val model =
                    ImageRequest.Builder(LocalContext.current).data(postCardUiState.media.postImage)
                        .size(Size.ORIGINAL).build()
                val painter = rememberAsyncImagePainter(model = model)

                if (postCardUiState.media.postImage != "") {
//https://www.google.com/search?q=Jetpack+Compose+set+image+height+before+loading+remote+image&sxsrf=AB5stBiLikx8B2CmFgr4hVUdQAgJvn6aEw%3A1689622549367&ei=FZi1ZKz0FdaKkdUP4Puw6Ag&ved=0ahUKEwjs0u--vpaAAxVWRaQEHeA9DI0Q4dUDCBA&uact=5&oq=Jetpack+Compose+set+image+height+before+loading+remote+image&gs_lp=Egxnd3Mtd2l6LXNlcnAiPEpldHBhY2sgQ29tcG9zZSBzZXQgaW1hZ2UgaGVpZ2h0IGJlZm9yZSBsb2FkaW5nIHJlbW90ZSBpbWFnZUgAUABYAHAAeACQAQCYAQCgAQCqAQC4AQPIAQDiAwQYACBB&sclient=gws-wiz-serp
                    AsyncImage(
                        model = model,
                        contentDescription = postCardUiState.media.postImage,
                        modifier = Modifier

                            .bounceClick(0.9f) { }
                            .clip(RoundedCornerShape(15.dp)),
                        contentScale = ContentScale.FillWidth,
                        fallback = painterResource(R.drawable.baseline_crop_landscape_24),
                        placeholder = painterResource(R.drawable.baseline_crop_landscape_24),
                        error = painterResource(id = R.drawable.baseline_crop_landscape_24),
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(15.dp)
                ) {
                    Icon(
                        if (postCardUiState.isLiked) painterResource(id = R.drawable.like_filled) else painterResource(
                            id = R.drawable.like
                        ),
                        contentDescription = "favorite",
                        modifier = Modifier
                            .size(20.dp)
                            .bounceClick {
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
                        painter = painterResource(id = R.drawable.repost),
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
                }
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    Text(
                        text = "${postCardUiState.commentsCount} replies",
                        color = Color.Gray,
                        fontSize = 14.sp
                    )
                    Text(text = "•", color = Color.Gray, fontSize = 14.sp)
                    Text(
                        text = "${postCardUiState.likes} likes",
                        color = Color.Gray,
                        fontSize = 14.sp
                    )
                }
            }
        }
        Divider(
            Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.outline.copy(0.2f),
            thickness = 1.dp
        )
    }

//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(IntrinsicSize.Max)
//            .padding(horizontal = 15.dp, vertical = 10.dp)
//    ) {
//        Column(
//            horizontalAlignment = Alignment.CenterHorizontally,
//            modifier = Modifier.fillMaxHeight()
//        ) {
//            Box(Modifier.size(40.dp)) {
//                AsyncImage(
//                    model = postCardUiState.postedBy?.avatar,
//                    contentDescription = postCardUiState.postedBy?.avatar,
//                    modifier = Modifier
//                        .size(40.dp)
//                        .clip(CircleShape),
//                    contentScale = ContentScale.Crop
//                )
//                if (postCardUiState.isFollowing.not()) {
//                    Box(
//                        modifier = Modifier
//                            .align(Alignment.BottomEnd)
//                            .clip(CircleShape)
//                            .background(White), contentAlignment = Alignment.Center
//                    ) {
//                        Icon(
//                            imageVector = Icons.Default.Add,
//                            contentDescription = "add",
//                            tint = Color.Black,
//                            modifier = Modifier.size(15.dp)
//                        )
//                    }
//                }
//            }
//            Spacer(Modifier.height(5.dp))
//            Divider(
//                modifier = Modifier
//                    .weight(1f)
//                    .width(1.dp), color = Gray
//            )
//            Spacer(Modifier.height(3.dp))
//            Box(modifier = Modifier.size(width = 36.dp, height = 30.dp)) {
//                AsyncImage(
//                    model = postCardUiState.postedBy?.avatar,
//                    contentDescription = postCardUiState.postedBy?.avatar,
//                    modifier = Modifier
//                        .size(15.dp)
//                        .clip(CircleShape)
//                        .align(Alignment.CenterStart),
//                    contentScale = ContentScale.Crop
//                )
//                AsyncImage(
//                    model = postCardUiState.postedBy?.avatar,
//                    contentDescription = postCardUiState.postedBy?.avatar,
//                    modifier = Modifier
//                        .size(20.dp)
//                        .clip(CircleShape)
//                        .align(Alignment.TopEnd),
//                    contentScale = ContentScale.Crop
//                )
//                AsyncImage(
//                    model = postCardUiState.postedBy?.avatar,
//                    contentDescription = postCardUiState.postedBy?.avatar,
//                    modifier = Modifier
//                        .size(10.dp)
//                        .clip(CircleShape)
//                        .align(Alignment.BottomCenter),
//                    contentScale = ContentScale.Crop
//                )
//            }
//
//        }
//        Spacer(modifier = Modifier.width(10.dp))
//
//        Column(horizontalAlignment = Alignment.Start, modifier = Modifier.fillMaxWidth()) {
//            Row(verticalAlignment = Alignment.CenterVertically) {
//                Text(
//                    text = postCardUiState.postedBy?.username!!,
//                    fontWeight = Bold,
//                    fontSize = 14.sp,
//                    color = MaterialTheme.colorScheme.onSurface,
//                    fontFamily = Ubuntu,
//                    modifier = Modifier
//                        .fillMaxWidth()
//                )
//                Spacer(modifier = Modifier.weight(1f))
//                Text(
//                    text = postCardUiState.createdAt,
//                    fontWeight = Light,
//                    fontSize = 13.sp,
//                    color = MaterialTheme.colorScheme.inversePrimary.copy(0.6f),
//                    fontFamily = Ubuntu
//
//                )
//                Spacer(modifier = Modifier.width(10.dp))
//                Box(modifier = Modifier.size(15.dp).noRippleClickable {
//                    postCardUiState.changeDropDownMenuState.invoke()
//                }
//                ) {
//                    Icon(
//                        painterResource(id = R.drawable.baseline_more_horiz_24),
//                        contentDescription = "more",
//                        modifier = Modifier,
//                        tint = MaterialTheme.colorScheme.onBackground
//                    )
//
//                    if (postCardUiState.isDropDownMenuActive) PostCardDropdownMenu(onExpand = true) {
//                        postCardUiState.changeDropDownMenuState.invoke()
//                    }
//
//                }
//            }
//            Spacer(modifier = Modifier.height(8.dp))
//
//            //remove padding
//            Text(
//                text = postCardUiState.postDescription, maxLines = 3,
//                fontWeight = Light,
//                fontSize = 13.sp,
//                color = White.copy(0.8f),
//                fontFamily = Ubuntu,
//                modifier = Modifier
//                    .border(
//                        1.dp,
//                        if (postCardUiState.postImage == "") Color.LightGray.copy(0.3f) else Color.Transparent,
//                        RoundedCornerShape(10.dp)
//                    )
//                    .padding(if (postCardUiState.postImage == "") 10.dp else 0.dp),
//            )
//            Spacer(modifier = Modifier.height(10.dp))
//            val model =
//                ImageRequest.Builder(LocalContext.current).data(postCardUiState.postImage)
//                    .size(Size.ORIGINAL).placeholder(
//                        R.drawable.add_image
//                    )
//                    .build()
//
//            val painter = rememberAsyncImagePainter(model = model)
//            if (postCardUiState.postImage != "") {
//                Image(
//                    painter =painter,
//                    contentDescription = postCardUiState.postImage,
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .bounceClick(0.9f) { }
//                        .clip(RoundedCornerShape(15.dp)),
//                    contentScale = ContentScale.Crop
//                )
//                Spacer(modifier = Modifier.height(15.dp))
//            }
//
//
//            Row(
//                verticalAlignment = Alignment.CenterVertically,
//                horizontalArrangement = Arrangement.spacedBy(15.dp)
//            ) {
//                Icon(
//                    painter = painterResource(id = if (postCardUiState.isLiked) R.drawable.like_filled else R.drawable.like),
//                    contentDescription = null,
//                    modifier = Modifier
//                        .size(18.dp)
//                        .bounceClick(onClick = {
//                            postCardUiState.changeLikeState()
//                        }),
//                    tint = if (postCardUiState.isLiked) Color(0xFFEA333E) else MaterialTheme.colorScheme.onBackground
//                )
//                Icon(
//                    painter = painterResource(id = R.drawable.comment),
//                    contentDescription = null,
//                    modifier = Modifier.size(20.dp)
//                )
//                Icon(
//                    painter = painterResource(id = R.drawable.repost),
//                    contentDescription = null,
//                    modifier = Modifier.size(20.dp)
//                )
//                Icon(
//                    painter = painterResource(id = R.drawable.share),
//                    contentDescription = null,
//                    modifier = Modifier.size(20.dp)
//                )
//            }
//            Spacer(modifier = Modifier.height(20.dp))
//            Row(
//                verticalAlignment = Alignment.CenterVertically,
//                horizontalArrangement = Arrangement.spacedBy(5.dp)
//            ) {
//                Text(text = "${postCardUiState.comments} replies", color = Gray, fontSize = 14.sp)
//                Text(text = "•", color = Gray, fontSize = 14.sp)
//                Text(text = "${postCardUiState.likes} likes", color = Gray, fontSize = 14.sp)
//            }
//            Spacer(modifier = Modifier.height(5.dp))
//            Divider(
//                Modifier.fillMaxWidth(),
//                color = MaterialTheme.colorScheme.outline.copy(0.2f),
//                thickness = 1.dp
//            )
//        }
//
//
//
////        Column(
////            modifier = Modifier
////
////                .fillMaxWidth()
////                .background(MaterialTheme.colorScheme.surface),
////            horizontalAlignment = Alignment.Start,
////            verticalArrangement = Arrangement.SpaceBetween
////        ) {
////
////
////            Row(
////                Modifier
////                    .fillMaxWidth()
////                    .padding(horizontal = 15.dp, vertical = 10.dp),
////                horizontalArrangement = Arrangement.Center,
////                verticalAlignment = Alignment.CenterVertically
////            ) {
////                Box(Modifier.size(40.dp)) {
////                    AsyncImage(
////                        model = postCardUiState.postedBy?.avatar,
////                        contentDescription = postCardUiState.postedBy?.avatar,
////                        modifier = Modifier
////                            .size(35.dp)
////                            .clip(CircleShape),
////                        contentScale = ContentScale.Crop
////                    )
////                    Box(
////                        modifier = Modifier
////                            .align(Alignment.BottomEnd)
////                            .clip(CircleShape)
////                            .background(White), contentAlignment = Alignment.Center
////                    ) {
////                        Icon(
////                            imageVector = Icons.Default.Add,
////                            contentDescription = "add",
////                            tint = Color.Black,
////                            modifier = Modifier.size(15.dp)
////                        )
////                    }
////                }
////                Text(
////                    text = postCardUiState.postedBy?.username!!,
////                    fontWeight = Bold,
////                    fontSize = 14.sp,
////                    color = MaterialTheme.colorScheme.onSurface,
////                    fontFamily = Ubuntu,
////                    modifier = Modifier
////                        .fillMaxWidth()
////                        .padding(start = 5.dp)
////                        .weight(1f)
////                )
////
////                Text(
////                    text = postCardUiState.createdAt,
////                    fontWeight = Light,
////                    fontSize = 13.sp,
////                    color = MaterialTheme.colorScheme.inversePrimary.copy(0.6f),
////                    fontFamily = Ubuntu
////
////                )
////
////                Box(modifier = Modifier.noRippleClickable {
////                    postCardUiState.changeDropDownMenuState.invoke()
////                }
////                ) {
////                    Icon(
////                        painterResource(id = R.drawable.baseline_more_horiz_24),
////                        contentDescription = "more",
////                        modifier = Modifier,
////                        tint = MaterialTheme.colorScheme.onBackground
////                    )
////
////                    if (postCardUiState.isDropDownMenuActive) PostCardDropdownMenu(onExpand = true) {
////                        postCardUiState.changeDropDownMenuState.invoke()
////                    }
////
////                }
////
////
////            }
////            Row(
////                Modifier
////                    .fillMaxWidth()
////                    .height(IntrinsicSize.Max),
////                horizontalArrangement = Arrangement.Start,
////                verticalAlignment = Alignment.CenterVertically
////            ) {
////                Divider(
////                    Modifier
////                        .fillMaxHeight()
////                        .padding(horizontal = 30.dp)
////                        .width(1.dp),
////                    color = Color.LightGray.copy(0.5f),
////
////                    )
////                Column(
////                    Modifier
////                        .fillMaxWidth()
////                        .fillMaxHeight()
////                        .padding(end = 15.dp)
////                ) {
////                    Text(
////                        text = postCardUiState.postDescription, maxLines = 3,
////                        fontWeight = FontWeight.Light,
////                        fontSize = 13.sp,
////                        color = Color.White.copy(0.8f),
////                        fontFamily = Ubuntu,
////                        modifier = Modifier
////                            .padding(end = 10.dp)
////                            .border(
////                                1.dp,
////                                if (postCardUiState.postImage == "") Color.LightGray.copy(0.3f) else Color.Transparent,
////                                RoundedCornerShape(10.dp)
////                            )
////                            .padding(if (postCardUiState.postImage == "") 10.dp else 0.dp),
////                    )
////                    val showShimmer = remember { mutableStateOf(true) }
////
////                    val model =
////                        ImageRequest.Builder(LocalContext.current).data(postCardUiState.postImage)
////                            .size(Size.ORIGINAL).placeholder(
////                                R.drawable.add_image
////                            )
////                            .build()
////
////                    val painter = rememberAsyncImagePainter(model = model)
////                    val painterState = painter.state
////                    if (painterState is AsyncImagePainter.State.Success) {
////                        LaunchedEffect(key1 = painterState) {
////                            showShimmer.value = false
////                        }
////                    }
////
////                    if (postCardUiState.postImage != "") {
////
////                        Image(
////                            painter = painter,
////                            contentDescription = postCardUiState.postImage,
////                            modifier = Modifier
////                                .fillMaxWidth()
////                                .bounceClick(0.9f) { }
////                                .padding(top = 16.dp, end = 10.dp)
////                                .clip(RoundedCornerShape(10.dp)),
////                            contentScale = ContentScale.FillWidth)
////                    }
////
////
////                }
////            }
////            Row(
////                horizontalArrangement = Arrangement.spacedBy(15.dp),
////                modifier = Modifier.padding(top = 15.dp, start = 60.dp)
////            ) {
////
////                Box(Modifier.bounceClick(onClick = {
////                    postCardUiState.changeLikeState()
////                })) {
////                    Icon(
////                        if (postCardUiState.isLiked) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
////                        contentDescription = "favorite",
////                        modifier = Modifier.size(24.dp),
////                        tint = if (postCardUiState.isLiked) Color.Red else MaterialTheme.colorScheme.onBackground
////                    )
////                }
////                val context = LocalContext.current
////                var openBottomSheet by remember {
////                    mutableStateOf(false)
////                }
////                val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
////                val scope = rememberCoroutineScope()
////                Icon(
////                    painterResource(id = R.drawable.comment_threads),
////                    contentDescription = "more",
////                    modifier = Modifier
////                        .rotate(270f)
////                        .size(22.dp)
////                        .clickable {
////                            openBottomSheet = !openBottomSheet
////                        },
////                    tint = MaterialTheme.colorScheme.onBackground
////                )
////                Icon(
////                    painterResource(id = R.drawable.arrows_reply),
////                    contentDescription = "share",
////                    modifier = Modifier.size(24.dp),
////                    tint = MaterialTheme.colorScheme.onBackground
////                )
////
////// Sheet content
////                if (openBottomSheet) {
////                    ModalBottomSheet(
////                        onDismissRequest = { openBottomSheet = false },
////                        sheetState = bottomSheetState,
////                        modifier = Modifier.fillMaxSize()
////                    ) {
////                        Row(
////                            Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
////                        ) {
////                            Button(
////
////                                onClick = {
////                                    scope.launch { bottomSheetState.hide() }.invokeOnCompletion {
////                                        if (!bottomSheetState.isVisible) {
////                                            openBottomSheet = false
////                                        }
////                                    }
////                                }) {
////                                Text("Hide Bottom Sheet")
////                            }
////                        }
////                    }
////                }
////                Icon(
////                    painterResource(id = R.drawable.share),
////                    contentDescription = "share",
////                    modifier = Modifier.size(22.dp),
////                    tint = MaterialTheme.colorScheme.onBackground
////                )
////                //     Spacer(modifier = Modifier.weight(1f))
////
////            }
////
////            Row(
////                modifier = Modifier
////                    .fillMaxWidth()
////                    .padding(start = 15.dp, bottom = 10.dp),
////                horizontalArrangement = Arrangement.spacedBy(10.dp),
////                verticalAlignment = Alignment.CenterVertically
////            ) {
////                Box {
////                    AsyncImage(
////                        model = postCardUiState.postedBy?.avatar,
////                        contentDescription = postCardUiState.postedBy?.avatar,
////                        modifier = Modifier
////                            .padding(start = 18.dp, bottom = 5.dp)
////                            .size(20.dp)
////                            .clip(CircleShape),
////                        contentScale = ContentScale.Crop
////                    )
////                    AsyncImage(
////                        model = postCardUiState.postedBy?.avatar,
////                        contentDescription = postCardUiState.postedBy?.avatar,
////                        modifier = Modifier
////                            .padding(top = 10.dp)
////                            .size(15.dp)
////                            .clip(CircleShape),
////                        contentScale = ContentScale.Crop
////                    )
////                    AsyncImage(
////                        model = postCardUiState.postedBy?.avatar,
////                        contentDescription = postCardUiState.postedBy?.avatar,
////                        modifier = Modifier
////                            .padding(start = 15.dp, top = 25.dp)
////                            .size(12.dp)
////                            .clip(CircleShape),
////                        contentScale = ContentScale.Crop
////                    )
////                }
////                Text(
////                    text = "${postCardUiState.likes} likes",
////                    fontWeight = Light,
////                    fontSize = 13.sp,
////                    color = MaterialTheme.colorScheme.inversePrimary.copy(0.7f),
////                    fontFamily = Ubuntu,
////                )
////                Text(
////                    text = "${postCardUiState.comments?.size} replies",
////                    fontWeight = Light,
////                    fontSize = 13.sp,
////                    color = MaterialTheme.colorScheme.inversePrimary.copy(0.7f),
////                    fontFamily = Ubuntu,
////                )
////            }
////
////            Divider(
////                Modifier.fillMaxWidth(),
////                color = MaterialTheme.colorScheme.outline.copy(0.2f),
////                thickness = 1.dp
////            )
////        }
//
//    }


}