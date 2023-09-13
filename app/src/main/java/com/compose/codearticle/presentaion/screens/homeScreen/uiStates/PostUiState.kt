package com.compose.codearticle.presentaion.screens.homeScreen.uiStates

import androidx.compose.ui.graphics.Color
import com.compose.codearticle.presentaion.theme.Orange

data class PostUiState(
    val postedBy: PostedBy? = null,
    val createdAt: String = "",
    var postDescription: String = "",
    var id: String = "-1",
    var isFollowing: Boolean = false,
    var isLiked: Boolean = false,
    var likes: Int? = null,
    val commentsCount: Int? = null,
    val media: ImageMedia,
    var isSavedToLocal: Boolean = false,
    var isDropDownMenuActive: Boolean = false,
    val shadowColor: Color = listOf(
        Orange,
        Color.Gray,
        Color.White,
    ).random()
)

data class PostModel(
    val postedBy: PostedBy? = null,
    val createdAt: String = "",
    val media: ImageMedia,
    val postDescription: String = "",
    var id: String = "-1",
    var isLiked: Boolean = false,
    var likes: Int? = null,
    val comments_count: Int? = null,
    var saveToLocal: Boolean = false,
    var isDropDownMenuActive: Boolean = false,
)

data class ImageMedia(
    val postImage: String = "",
    val originalHeight: Int = 0,
    val originalWidth: Int = 1
)

data class PostedBy(
    val _id: String? = null,
    val avatar: String? = null,
    val username: String? = null
)

data class CommentedBy(
    val commentId: String? = null,
    val avatar: String? = null,
    val username: String? = null,
    val userId: String? = null,
    val content: String? = null
)
