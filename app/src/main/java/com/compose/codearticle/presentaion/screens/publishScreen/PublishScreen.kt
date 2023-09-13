package com.compose.codearticle.presentaion.screens.publishScreen

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateIntOffsetAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.TopEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight.Companion.Light
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.compose.codearticle.R
import com.compose.codearticle.presentaion.screens.publishScreen.composables.PublishScreenDialog
import com.compose.codearticle.presentaion.screens.publishScreen.uiStates.PublishPostUiEvent
import com.compose.codearticle.presentaion.theme.Orange
import com.compose.codearticle.presentaion.theme.Orange0_7
import com.compose.codearticle.presentaion.theme.Ubuntu
import com.compose.codearticle.presentaion.utilities.MainDialog
import kotlin.math.roundToInt


@Composable
fun PostScreen(
    navController: NavController,
    postArticleViewModel: PostArticleViewModel = hiltViewModel()
) {

    PostContent(navController, postArticleViewModel)
}


@Composable
fun PostContent(
    navController: NavController,
    postArticleViewModel: PostArticleViewModel,

    ) {
    var animatedUp by remember {
        mutableStateOf(false)
    }


    Box(Modifier.fillMaxSize()) {
        PostArticle(navController, postArticleViewModel = postArticleViewModel)
    }
}


@Composable
fun PostArticle(
    navController: NavController,
    postArticleViewModel: PostArticleViewModel,
) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(top = 30.dp)
    ) {

        PostOrBackSection(postArticleViewModel, navController) {
            if (postArticleViewModel.postScreenState.postDescription.text.isNotBlank() || postArticleViewModel.postScreenState.isImageVisible)
                postArticleViewModel.onEvent(PublishPostUiEvent.CancelPost) else
                navController.popBackStack()
        }


        DescriptionAndImageSection(postArticleViewModel)

        IsDialogShown(postArticleViewModel, navController)


    }
}


@Composable
fun PostOrBackSection(
    postArticleViewModel: PostArticleViewModel,
    navController: NavController,
    onCancelClick: () -> Unit,
) {


    Row(
        Modifier
            .fillMaxWidth()
            .padding(16.dp), verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            painter = painterResource(id = R.drawable.outline_clear_24),
            contentDescription = "Cancel Post and Back to Home",
            modifier = Modifier
                .size(24.dp)
                .clickable {

                    onCancelClick()
                },
            tint = MaterialTheme.colorScheme.onBackground
        )

        Text(
            text = postArticleViewModel.postScreenState.dateTime, fontWeight = Light,
            fontSize = 15.sp,
            color = MaterialTheme.colorScheme.onBackground.copy(0.5f),
            fontFamily = Ubuntu
        )

        Box(
            contentAlignment = Center,
            modifier = Modifier
                .clip(RoundedCornerShape(15.dp))
                .background(Color(postArticleViewModel.postScreenState.postButtonColor))
                .clickable(enabled = postArticleViewModel.postScreenState.postButtonEnable) {
                    postArticleViewModel.onEvent(PublishPostUiEvent.Publish)
                }
                .padding(10.dp)

        ) {
            Text(
                text = "Post", fontWeight = Light,
                fontSize = 15.sp,
                color = MaterialTheme.colorScheme.onBackground,
                fontFamily = Ubuntu
            )
        }
    }


}

@Composable
fun DescriptionAndImageSection(postArticleViewModel: PostArticleViewModel) {

    isPostEnable(postArticleViewModel)

    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
     ) {
        TextField(
            value = postArticleViewModel.postScreenState.postDescription.text,
            onValueChange = {
                postArticleViewModel.onEvent(PublishPostUiEvent.DescriptionTextChanged(it))
            },
            modifier = Modifier
                .fillMaxWidth()
                .requiredHeight(300.dp),
            placeholder = {
                Text(
                    text = "What do you want to talk about?", fontWeight = Light,
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
                    fontFamily = Ubuntu
                )
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.primary,
                unfocusedContainerColor = MaterialTheme.colorScheme.primary,
                focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                unfocusedIndicatorColor = MaterialTheme.colorScheme.primary,
                cursorColor = MaterialTheme.colorScheme.inversePrimary
            ),
            textStyle = TextStyle(
                fontWeight = Light,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
                fontFamily = Ubuntu
            )
        )

        val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.PickVisualMedia(),
            onResult = { uri ->
                postArticleViewModel.onEvent(
                    PublishPostUiEvent.SelectedImage(
                        uri ?: Uri.EMPTY
                    )
                )
            }
        )
        Column(
            Modifier
                .fillMaxWidth()
                .height(300.dp)
        ) {

            AnimatedVisibility(
                visible = postArticleViewModel.postScreenState.isImageVisible,
                enter = slideInVertically { it },
                exit = slideOutHorizontally { it },
                modifier = Modifier.align(CenterHorizontally)
            ) {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .align(CenterHorizontally)
                        .border(1.dp, MaterialTheme.colorScheme.outline, RoundedCornerShape(16.dp))
                        .clip(RoundedCornerShape(16.dp))

                ) {

                    AsyncImage(
                        model = postArticleViewModel.postScreenState.imageUri,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth(),
                    )
                    Box(
                        modifier = Modifier
                            .padding(10.dp)
                            .size(30.dp)
                            .align(TopEnd)
                            .background(
                                MaterialTheme.colorScheme.outline, CircleShape
                            )
                            .clickable {
                                postArticleViewModel.onEvent(PublishPostUiEvent.DeleteImage)
                            }, contentAlignment = Center
                    ) {

                        Icon(
                            painter = painterResource(id = R.drawable.outline_clear_24),
                            contentDescription = null,
                            modifier = Modifier
                                .size(24.dp)
                                .fillMaxWidth(),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
        }

       Spacer(modifier = Modifier.weight(1f))


        var moved by remember { mutableStateOf(false) }

        val pxToMove = with(LocalDensity.current) {
            -50.dp.toPx().roundToInt()
        }

        val offsetForGallery by animateIntOffsetAsState(
            targetValue = if (moved) {
                IntOffset(pxToMove, pxToMove)
            } else {
                IntOffset.Zero
            },
            label = "offsetForGallery",
            animationSpec =
            spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessLow
            )
        )
        val offsetForCamera by animateIntOffsetAsState(
            targetValue = if (moved) {
                IntOffset(-pxToMove, pxToMove)
            } else {
                IntOffset.Zero
            },
            label = "offsetForCamera",
            animationSpec =
            spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessLow
            )

        )
        var currentRotation by remember { mutableFloatStateOf(0f) }

        val rotation = remember { androidx.compose.animation.core.Animatable(currentRotation) }

        LaunchedEffect(moved) {
            if (moved) {
                rotation.animateTo(
                    targetValue = currentRotation + 45f,
                    animationSpec = tween(200)
                ) {
                    currentRotation = value
                }
            } else {
                rotation.animateTo(
                    targetValue = currentRotation - 45f,
                    animationSpec = tween(
                        durationMillis = 200,
                    )
                ) {
                    currentRotation = value
                }

            }
        }
        LaunchedEffect(postArticleViewModel.postScreenState.isImageVisible){
            if (postArticleViewModel.postScreenState.isImageVisible){
                moved = false
            }
        }
        AnimatedVisibility(
            visible = !postArticleViewModel.postScreenState.isImageVisible,
            enter = slideInVertically { it },
            exit = slideOutVertically { it },
            modifier = Modifier
                .align(CenterHorizontally),
        ) {
        Box(modifier = Modifier
            .align(CenterHorizontally)
            .padding(bottom = 50.dp)
            .size(50.dp) ) {
            Box(
                Modifier
                    .offset {
                        offsetForGallery
                    }

                    .size(50.dp)
                    .clickable {
                        singlePhotoPickerLauncher.launch(
                            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                        )
                    }
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.outline), contentAlignment = Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.add_image),
                    contentDescription = "Add Image",
                    modifier = Modifier.size(24.dp), tint = Orange0_7
                )
            }

            Box(
                Modifier
                    .offset {
                        offsetForCamera
                    }

                    .size(50.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.outline), contentAlignment = Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.add_camera),
                    contentDescription = "Add Image",
                    modifier = Modifier.size(24.dp), tint = Orange0_7
                )
            }

            val infiniteTransition = rememberInfiniteTransition(label = "change color")

            val color by infiniteTransition.animateColor(
                initialValue = Color.Cyan,
                targetValue = Orange ,
                animationSpec = infiniteRepeatable(
                     animation = tween(3000, easing = LinearEasing),
                    repeatMode = RepeatMode.Reverse
                ),label = "select box color"
            )

            Box(
                Modifier
                    .size(50.dp)
                    .rotate(rotation.value)
                    .clip(CircleShape)
                    .background(color)
                    .clickable {
                        moved = !moved
                    }, contentAlignment = Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.outline_clear_24),
                    contentDescription = "Add Image",
                    modifier = Modifier.size(24.dp),

                )
            }
        }

    }}


}


@Composable
fun IsDialogShown(postArticleViewModel: PostArticleViewModel, navController: NavController) {
    if (postArticleViewModel.postScreenState.isDialogShown) {
        MainDialog {
            PublishScreenDialog(
                { postArticleViewModel.onEvent(PublishPostUiEvent.DismissDialog) },
                { postArticleViewModel.onEvent(PublishPostUiEvent.SaveDialog) },
                { navController.popBackStack() })
        }
    }
//    BackHandler {
//        if (postArticleViewModel.postScreenState.postDescription.text.isNotBlank() || postArticleViewModel.postScreenState.isImageVisible)
//            postArticleViewModel.onEvent(PublishPostUiEvent.CancelPost) else
//            navController.popBackStack()
//    }
}

fun isPostEnable(postArticleViewModel: PostArticleViewModel) {
    val colorList = listOf(Orange0_7.toArgb(), DarkGray.toArgb())
    postArticleViewModel.onEvent(PublishPostUiEvent.IsPublishEnable(colorList))
}