package com.compose.codearticle.presentaion.screens.publishScreen

import android.net.Uri
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.TopEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight.Companion.Light
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.compose.codearticle.R
import com.compose.codearticle.presentaion.screens.publishScreen.composables.PublishScreenDialog
import com.compose.codearticle.presentaion.screens.publishScreen.uiStates.PublishPostUiEvent
import com.compose.codearticle.presentaion.theme.Orange0_7
import com.compose.codearticle.presentaion.theme.Ubuntu
import com.compose.codearticle.presentaion.utilities.MainDialog


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

    AnimatedVisibility(
        visible = animatedUp,
        enter = slideInVertically { it / 2 },
        exit = slideOutVertically { it / 2 }
    ) {
        Box(Modifier.fillMaxSize()) {
            PostArticle(navController, postArticleViewModel = postArticleViewModel)
        }
    }
    LaunchedEffect(key1 = Unit) {
        animatedUp = !animatedUp
    }

}

@Composable
fun PostArticle(
    navController: NavController,
    postArticleViewModel: PostArticleViewModel,
) {
    Column(Modifier.fillMaxSize()) {

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
        Modifier.verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.SpaceEvenly
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
        AnimatedVisibility(
            visible = !postArticleViewModel.postScreenState.isImageVisible,
            enter = slideInVertically { it },
            exit = slideOutVertically { it },
            modifier = Modifier
                .align(CenterHorizontally),
        ) {
            Box(
                Modifier
                    .padding(bottom = 30.dp)
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.outline)
                    .clickable {
                        singlePhotoPickerLauncher.launch(
                            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                        )
                    }, contentAlignment = Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.add_image), contentDescription = "Add Image",
                    modifier = Modifier.size(24.dp)
                    )
            }
        }


    }


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
    BackHandler {
        if (postArticleViewModel.postScreenState.postDescription.text.isNotBlank() || postArticleViewModel.postScreenState.isImageVisible)
            postArticleViewModel.onEvent(PublishPostUiEvent.CancelPost) else
            navController.popBackStack()
    }
}

fun isPostEnable(postArticleViewModel: PostArticleViewModel) {
    val colorList = listOf(Orange0_7.toArgb(), DarkGray.toArgb())
    postArticleViewModel.onEvent(PublishPostUiEvent.IsPublishEnable(colorList))
}