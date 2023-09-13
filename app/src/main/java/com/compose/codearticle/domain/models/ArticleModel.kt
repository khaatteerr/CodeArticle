package com.compose.codearticle.domain.models

import com.compose.codearticle.data.source.remote.responseModels.Attachment
import com.compose.codearticle.data.source.remote.responseModels.User
import com.compose.codearticle.presentaion.screens.homeScreen.uiStates.ImageMedia
import com.compose.codearticle.presentaion.screens.homeScreen.uiStates.PostUiState
import com.compose.codearticle.presentaion.screens.homeScreen.uiStates.PostedBy

data class ArticleModel(
    val id: Int,
    val title: String,
    val content: String,
    val liked: Boolean,
    val attachment: Attachment,
    val user: User,
    val commentsCount: Int
)